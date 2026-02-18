# Spring Boot – miten sovellus toimii?

Tämä dokumentti selittää TicketGuru-projektin arkkitehtuurin ja HTTP-pyynnön kulun clientista tietokantaan ja takaisin. Sopii taustaltaan Javaa ja Spring Bootia opiskeleville.

---

## 1. Kokonaiskuva – mitä tapahtuu kun selain kutsuu API:a?

```
[Selain/Postman]  →  HTTP-pyyntö  →  [Spring Boot]  →  [Controller]  →  [Repository]  →  [H2-tietokanta]
                                                                                              ↓
[Selain/Postman]  ←  JSON-vastaus  ←  [Spring Boot]  ←  [Controller]   ←  [Repository]  ←  [H2-tietokanta]
```

---

## 2. Projektin rakenne – mitä mikäkin luokka tekee?

```
src/main/java/com/example/ticketguru/
├── TicketGuruApplication.java    # Sovelluksen käynnistyspiste
├── controller/
│   ├── EventController.java      # Vastaanottaa HTTP-pyynnöt, palauttaa vastaukset
│   └── GlobalExceptionHandler.java  # Käsittelee virheitä
├── dto/
│   └── EventDto.java             # Tietoja pyyntöjen ja vastausten välillä
├── domain/
│   └── Event.java                # Tietokantataulun malli (entity)
└── repository/
    └── EventRepository.java      # Tietokantahaut ja tallennukset
```

---

## 3. H2-tietokanta – miten se syntyy?

### 3.1 Konfiguraatio

**`application.properties`:**
```properties
spring.datasource.url=jdbc:h2:mem:ticketguru
spring.jpa.hibernate.ddl-auto=create-drop
```

- **`jdbc:h2:mem:ticketguru`** – H2 käynnistyy keskusmuistiin (ei tiedostoa)
- **`create-drop`** – Hibernate luo taulut käynnistyksessä ja poistaa ne sammutuksessa
- Tietokanta ei ole pysyvä: kun sovellus sammuu, data katoaa

### 3.2 Miten taulut syntyvät?

1. Sovellus käynnistyy → `TicketGuruApplication` käynnistyy
2. Spring lataa `Event`-entiteetin (`@Entity`)
3. Hibernate lukee `Event`-luokan ja luo taulun `tapahtuma`:
   - `id` → BIGINT, autogeneroitu
   - `kuvaus` → VARCHAR(255)
   - `aika` → TIMESTAMP
   - jne.

**Entity määrittää taulun rakenteen:**
```java
@Entity
@Table(name = "tapahtuma")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String kuvaus;
    // ...
}
```

---

## 4. Komponenttien roolit

### 4.1 Entity (Event.java)

- **Rooli:** Kuvastaa tietokantataulua
- **Käyttö:** Repository palauttaa ja tallentaa `Event`-olioita
- **Huom:** Entityä ei suoraan palauteta HTTP-vastauksessa (voisi paljastaa turhaa tai aiheuttaa ongelmia)

### 4.2 DTO (EventDto.java)

- **Rooli:** Siirrettävän datan malli (Data Transfer Object)
- **Käyttö:** Controller käyttää DTO:ta pyynnön bodyssä ja vastauksessa
- **Miksi?** Rajataan mitä kenttiä lähetetään/palautetaan, validointi tapahtuu tässä tasossa

```java
// Esim. vastauksessa palautetaan vain DTO:n kentät
{"id": 1, "kuvaus": "Super-Gaala", "aika": "2025-03-05T20:00:00", ...}
```

### 4.3 Repository (EventRepository.java)

- **Rooli:** Tietokantaoperaatiot (haku, tallennus, poisto)
- **Rakenne:** Rajapinta, joka perii `JpaRepository<Event, Long>`
- Spring luo toteutuksen automaattisesti – ei tarvitse kirjoittaa SQL:ää

```java
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByOrderByAikaAsc();  // Spring päättelee SQL:n metodin nimestä
}
```

### 4.4 Controller (EventController.java)

- **Rooli:** Vastaanottaa HTTP-pyynnöt ja palauttaa vastaukset
- **Käyttö:** Lukee polun ja metodin, kutsuu repositorya, muuntaa tulokset DTO:iksi

```java
@RestController
@RequestMapping("/api/events")
public class EventController {
    @GetMapping
    public List<EventDto> getAll() {
        return eventRepository.findAllByOrderByAikaAsc()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
```

---

## 5. HTTP-pyynnön kulku – vaihe vaiheelta

### Esimerkki: `GET http://localhost:8080/api/events`

| Vaihe | Komponentti | Tapahtuu |
|-------|-------------|----------|
| 1 | **Selain / Postman** | Lähettää GET-pyynnön osoitteeseen `/api/events` |
| 2 | **Tomcat (sisäänrakennettu palvelin)** | Ottaa pyynnön vastaan portissa 8080 |
| 3 | **Spring DispatcherServlet** | Ohjaa pyynnön oikealle controllerille polun perusteella |
| 4 | **EventController.getAll()** | `@GetMapping` vastaa polkuun `/api/events` ilman lisäpolkua |
| 5 | **EventRepository.findAllByOrderByAikaAsc()** | Spring Data suorittaa SQL:n: `SELECT * FROM tapahtuma ORDER BY aika` |
| 6 | **H2-tietokanta** | Palauttaa rivit tietokannasta |
| 7 | **Repository** | Palauttaa `List<Event>` |
| 8 | **Controller** | `toDto()` muuntaa jokaisen `Event` → `EventDto` |
| 9 | **Jackson** | Serialisoi `List<EventDto>` JSON-muotoon |
| 10 | **Tomcat** | Lähettää vastauksen HTTP-responseina |
| 11 | **Selain / Postman** | Näyttää vastaanotetun JSON:n |

---

### Esimerkki: `POST http://localhost:8080/api/events` + JSON-body

| Vaihe | Komponentti | Tapahtuu |
|-------|-------------|----------|
| 1 | **Client** | Lähettää POST + JSON `{"kuvaus":"Testi","aika":"...","kapasiteetti":500}` |
| 2 | **Tomcat** | Ottaa pyynnön vastaan |
| 3 | **DispatcherServlet** | Ohjaa POST `/api/events` → EventController |
| 4 | **EventController.create()** | `@PostMapping` vastaa, `@RequestBody` → Jackson muuntaa JSON:n `EventDto`:ksi |
| 5 | **@Valid** | Tarkistaa EventDto:n (esim. kuvaus ei tyhjä, kapasiteetti ≥ 1) |
| 6 | **Controller** | `toEntity(dto)` muuntaa EventDto → Event |
| 7 | **eventRepository.save(event)** | Spring Data suorittaa `INSERT INTO tapahtuma ...` |
| 8 | **H2** | Tallentaa rivin, palauttaa luodun Eventin (id generoitu) |
| 9 | **Controller** | `toDto(event)` → palauttaa luodun tapahtuman JSONina |
| 10 | **Client** | Vastaus 201 Created + JSON |

---

## 6. Dependency Injection (DI) – miten komponentit yhdistetään?

Spring hallitsee olioiden luomista ja injektoi riippuvuudet automaattisesti:

```java
@RestController
public class EventController {
    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;  // Spring injektoi tämän
    }
}
```

- Spring skannaa luokat (`@RestController`, `@Repository`, `@Component`)
- Luo yhden instanssin jokaisesta (singleton)
- Kun EventController luodaan, Spring antaa sille valmiin EventRepository-instanssin

---

## 7. Lyhyt yhteenveto

| Käsite | Tehtävä |
|--------|---------|
| **Entity** | Kuvaa tietokantataulua, ORM-mapataan |
| **Repository** | Tietokantaoperaatiot, Spring Data luo SQL:n |
| **DTO** | Siirrettävä data, validointi, ei suoraa linkkiä tietokantaan |
| **Controller** | HTTP-endpointit, kutsuu repositorya, muuntaa DTOiksi |
| **H2** | Kehitystietokanta, taulut syntyvät entiteeteistä |
| **Jackson** | Muuntaa Java-oliot ↔ JSON automaattisesti |

**Pyynnön kulku:** Client → Tomcat → Controller → Repository → H2 → takaisin DTOina → JSON → Client.

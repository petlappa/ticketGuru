## TicketGuru – lipunmyyntijärjestelmä

Spring Boot -pohjainen backend TicketGuru-lipunmyyntijärjestelmälle. Projektissa on:

- **REST API tapahtumille (events)** – CRUD-rajapinta client-projektille
- **H2-kehitystietokanta** – taulut generoidaan automaattisesti JPA-entiteeteistä
- **Dokumentaatio** – sprinttisuunnitelmat, tietokantakaaviot, API- ja arkkitehtuurikuvaus

---

## Teknologiat

- **Java 17**
- **Spring Boot 4.0.2**
  - `spring-boot-starter-web`
  - `spring-boot-starter-data-jpa`
  - `spring-boot-starter-validation`
- **H2** (in-memory dev-tietokanta)
- **Maven**

---

## Kehitysympäristön käynnistys

1. Varmista, että Java 17 ja Maven ovat asennettu.
2. Kloonaa repo:

```bash
git clone https://github.com/<user>/ticketGuru.git
cd ticketGuru
```

3. Käynnistä sovellus:

```bash
mvn spring-boot:run
```

Sovellus käynnistyy oletuksena osoitteeseen: `http://localhost:8080`.

---

## Events REST API – pikakatsaus

Base-URL:

```text
http://localhost:8080/api
```

Tapahtumiin liittyvät endpointit:

| Metodi | Polku             | Kuvaus                |
|--------|-------------------|-----------------------|
| GET    | `/events`         | Hae kaikki tapahtumat |
| GET    | `/events/{id}`    | Hae yksi tapahtuma    |
| POST   | `/events`         | Luo uusi tapahtuma    |
| PUT    | `/events/{id}`    | Päivitä tapahtuma     |
| DELETE | `/events/{id}`    | Poista tapahtuma      |

Lisätiedot pyynnöistä ja vastauksista:  
**`docs/api/API-dokumentaatio.md`**

Postman-kokoelma:  
**`docs/api/TicketGuru-Events.postman_collection.json`**

---

## Testidatan luonti curlilla

Projektin juuressa on skripti, joka luo 10 erilaista tapahtumaa:

```bash
./test-api-events.sh
```

Skripti olettaa, että sovellus on käynnissä portissa 8080.

---

## Dokumentaatio

- **Sprinttisuunnitelmat**:  
  - `docs/Sprint1-suunnitelma.md`  
  - `docs/Sprint2-suunnitelma.md`  
  - `docs/Sprint3-suunnitelma.md`

- **Tietokanta**:  
  - `docs/Tietokanta.md` (ER-kaaviot: Mermaid, 1:* -notaatio)  
  - HTML-kaaviot: `docs/tietokantakaavio.html`, `docs/tietokantakaavio-1-N.html`

- **Käyttäjäroolit ja -tarinat**:  
  - `docs/Käyttäjäroolit-ja-käyttäjätarinat.md`

- **Spring Boot -arkkitehtuuri** (aloittelijaystävällinen selitys):  
  - `docs/Spring-Boot-ja-arkkitehtuuri.md`

---

## Koodi – tärkeimmät paketit

- `src/main/java/com/example/ticketguru/`
  - `TicketGuruApplication.java` – sovelluksen käynnistyspiste
  - `controller/` – REST-controllerit (mm. `EventController`)
  - `dto/` – DTO-luokat (`EventDto`)
  - `domain/` – JPA-entiteetit (`Event`, `Ticket`, `TicketType`, `SalesTransaction`)
  - `repository/` – Spring Data JPA -repositoryt

---

## Testaus

Perustestit:

```bash
mvn test
```

Manuaalinen API-testaus:

- Selain / Postman → `http://localhost:8080/api/events`
- Postman-kokoelma: `docs/api/TicketGuru-Events.postman_collection.json`


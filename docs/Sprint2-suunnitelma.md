# Sprint 2 – Suunnitelma

**Kesto:** 1–2 viikkoa  
**Tavoite:** Tietokannan suunnittelu ja toteutus, entiteettien ja repositoryjen valmis

---

## Sprintin tuotteet

| # | Tuote | Tilanne |
|---|-------|---------|
| 1 | Tietokantakaavio | Done |
| 2 | Dokumentaatio: Tietokanta | Done |
| 3 | Entity-luokat ja niiden suhteet | Done |
| 4 | Repository-luokat | Done |

---

## Tavoitteet ja tarkoitus

- **Viimeistellä näkemys järjestelmän tietosisällöstä** – tietomalli dokumentoitu
- **Suunnitella tietokanta** – kaavio, taulut, suhteet
- **Toteuttaa tietokannan ensimmäinen versio** – JPA-entiteetit, H2/dev-tietokanta
- **Tutustua sprintin suunnitteluun ja Scrum-tiimin päivittäiseen toimintaan** – vrt. Scrum-aiheet alla

---

## 1. Tietokantakaavio

- ER-kaavio (Mermaid) dokumentissa `docs/Tietokanta.md`
- Taulut: Tapahtuma, Lipputyyppi, Myyntitapahtuma, Lippu
- Suhteet ja avaimet kuvattu

---

## 2. Dokumentaatio: Tietokanta

**Tiedosto:** `docs/Tietokanta.md`

- Tietokantakaavio
- Taulukuvaukset (sarakkeet, tyypit)
- Suhteiden kuvaukset
- Indeksit ja uniikkiusrajoitteet

---

## 3. Entity-luokat (JPA)

- `Event` (Tapahtuma)
- `TicketType` (Lipputyyppi)
- `SalesTransaction` (Myyntitapahtuma)
- `Ticket` (Lippu)

Suhteet: OneToMany/ManyToOne dokumentaation mukaisesti.

---

## 4. Repository-luokat (Spring Data JPA)

- `EventRepository`
- `TicketTypeRepository`
- `SalesTransactionRepository`
- `TicketRepository`

Perus-CRUD riittää; mahdollisia lisäkyselyjä tarpeen mukaan.

---

## Scrum – sprintin suunnittelu ja päivittäinen toiminta

### Sprint planning

- **Ennen sprinttiä:** backlog-kohteiden priorisointi (tärkeimmät ensin)
- **Sprint planning -tapaaminen:** valitaan sprint goal ja tehtävät
- **Sprint backlog:** mitä tällä sprintillä tehdään (DoD kriteerit)

### Daily Scrum (päivittäinen stand-up)

- Mitä tein eilen?
- Mitä teen tänään?
- Mitä esteitä tai haasteita on?

### Sprint Review

- Esitellään valmiit tuotteet
- Backlog-päivitys

### Sprint Retrospective

- Mitä meni hyvin?
- Mitä voisi parantaa?
- Mitä kokeillaan seuraavalla sprintillä?

### Definition of Done (Sprint 2)

- [ ] Tietokantakaavio on dokumentissa
- [ ] Tietokanta-luku on kirjoitettu
- [ ] Kaikki neljä entity-luokkaa on toteutettu oikein
- [ ] Kaikki neljä repository on toteutettu
- [ ] Sovellus käynnistyy, tietokanta luodaan (H2)
- [ ] Testit menevät läpi

---

## Riippuvuudet ja tekniset muutokset

- `spring-boot-starter-data-jpa`
- `com.h2database:h2`
- `application.properties` – H2-konfiguraatio

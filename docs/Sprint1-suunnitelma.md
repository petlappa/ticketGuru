# Sprint 1 – Suunnitelma

**Kesto:** 1–2 viikkoa (määrittele tarkemmin tiimin toiminnan mukaan)  
**Tavoite:** Dokumentaation perusta ja käyttöliittymän alustava luonnos

---

## Sprintin tuotteet

| # | Tuote | Tilanne |
|---|-------|---------|
| 1 | Dokumentaatio: Johdanto | Todo |
| 2 | Dokumentaatio: Järjestelmän määrittely | Todo |
| 3 | Käyttöliittymä alustavasti | Todo |

---

## 1. Dokumentaatio: Johdanto

**Tiedosto:** `docs/Johdanto.md` (tai `docs/01-johdanto.md`)

### Sisältö
- Projektin tarkoitus ja tausta (lipunmyyntijärjestelmä TicketGuru)
- Projektin laajuus ja rajat
- Käytetyt teknologiat (Spring Boot, Java 17)
- Dokumentaation rakenne ja lukemisen ohje
- Mahdollinen viittaus UI-määrittelyyn (PDF)

### Tehtävät
- [ ] Luo `docs/`-kansio jos ei ole
- [ ] Kirjoita Johdanto-luku
- [ ] Päivitä README viittaamaan dokumentaatioon (tarvittaessa)

---

## 2. Dokumentaatio: Järjestelmän määrittely

**Tiedosto:** `docs/Järjestelmän-määrittely.md` (tai `docs/02-järjestelmän-määrittely.md`)

### Sisältö
- Toiminnalliset vaatimukset (käyttötapaukset)
  - Lippujen myynti
  - Lipputyyppien hallinta
  - Tapahtumien hallinta
  - Myyntiraportit
- Ei-toiminnalliset vaatimukset
  - Suorituskyky, skaalautuvuus
  - Käytettävyys, saavutettavuus
- Järjestelmän arkkitehtuuri (yleiskuva)
  - Tietokanta (esim. H2/PostgreSQL)
  - REST API / palvelukerrokset
- Tietomalli (lyhyt kuvaus)
  - Tapahtuma, Lipputyyppi, Myyntitapahtuma, Lippu jne.

### Tehtävät
- [ ] Kirjoita Järjestelmän määrittely -luku
- [ ] Kuvaile tietomalli (taulukot/yksinkertaiset luokkakaaviot)
- [ ] Tallenna käyttötapaukset (käyttäjä, toiminto, tulos)

---

## 3. Käyttöliittymä alustavasti

**Tarkoitus:** Toimiva alustava UI ilman täydellistä toiminnallisuutta

### Vaihtoehdot toteutukselle

| Vaihtoehto | Kuvaus | Suositeltu |
|------------|--------|------------|
| A | Thymeleaf + Spring MVC | ✅ Jos backend- ja frontend-tiimi sama |
| B | React/Vue + REST API | Jos frontend erillinen |
| C | Staattiset HTML-sivut | Yksinkertaisin alustava demo |

### Toteutettavat näkymät (alustavasti)

1. **Etusivu / navigaatio**
   - Linkit: Lippujen myynti, Lipputyypit, Tapahtumat, Raportit

2. **Lippujen myynti** (`/myy`)
   - Valinta: tapahtuma, lipputyyppi, määrä
   - Näkymä: summa, Myy-nappi
   - *Alustavasti: ei vielä oikeaa tietokantaa, mock-data ok*

3. **Lipputyypit** (`/lipputyypit`)
   - Listaus (kuvaus, hinta)
   - Alustava muokkauslomake (Uusi / Muokkaa)

4. **Tapahtumat** (`/tapahtumat`)
   - Listaus (aika, kaupunki, kuvaus)
   - Alustava Uusi tapahtuma -lomake

5. **Raportti** (`/raportti`)
   - Yhteenveto lipputyypeittäin
   - Lista myyntitapahtumista

### Tekninen toteutus (esim. Thymeleaf)

- Spring Web + Thymeleaf (lisää `spring-boot-starter-web`, `spring-boot-starter-thymeleaf`)
- Controllerit: `SalesController`, `TicketTypeController`, `EventController`, `ReportController`
- HTML-mallit `src/main/resources/templates/` – pohja (layout), sivut
- Mock-data controller-tasolla tai yksinkertaiset `@RestController` + JSON

### Tehtävät
- [ ] Lisää tarvittavat riippuvuudet (`pom.xml`)
- [ ] Luo yhteinen layout (header, navigaatio, footer)
- [ ] Luo sivut: etusivu, myynti, lipputyypit, tapahtumat, raportti
- [ ] Mahdollinen mock-data, jotta sivut näyttävät toimivilta

---

## Sprint backlog (tehtävät prioriteettijärjestyksessä)

| Prioriteetti | Tehtävä | Arvio |
|--------------|---------|-------|
| 1 | Luo docs-kansio ja Johdanto.md | 2–4 h |
| 2 | Kirjoita Järjestelmän määrittely | 4–6 h |
| 3 | Lisää Web + Thymeleaf -riippuvuudet | 0.5 h |
| 4 | Luo peruslayout ja navigaatio | 2–3 h |
| 5 | Luo etusivu | 1 h |
| 6 | Luo Lipputyypit-sivu (listaus + lomake) | 2–3 h |
| 7 | Luo Tapahtumat-sivu (listaus + lomake) | 2–3 h |
| 8 | Luo Lippujen myynti -sivu | 2–3 h |
| 9 | Luo Raportti-sivu | 2–3 h |
| 10 | Lisää mock-data ja testaa virta | 1–2 h |

**Yhteensä arvio:** noin 19–28 tuntia (2–3,5 työpäivää yhdelle kehittäjälle)

---

## Definition of Done (sprintille)

- [ ] Johdanto ja Järjestelmän määrittely ovat luettavissa `docs/`-kansiossa
- [ ] Kaikki neljä pääsivua (myynti, lipputyypit, tapahtumat, raportti) avautuvat
- [ ] Navigaatio toimii sivujen välillä
- [ ] Ulkoasu vastaa suuntaa-antavasti UI-määrittelyä (PDF)
- [ ] Sovellus käynnistyy ilman virheitä (`mvn spring-boot:run`)

---

## Mahdolliset riskit ja riippuvuudet

- **Dokumentaatio:** Vaatii sopimuksen projektin laajuudesta
- **UI:** Thymeleaf-valinnalla ei tarvita erillistä frontend-kehitysympäristöä
- **Mock-data:** Mahdollistaa UI-kehitystä ilman tietokantaa; tietomalli voidaan vahvistaa sprintissä 2

---

## Seuraava sprint (ehdotus)

- Tietomallin toteutus (entiteetit, JPA)
- Tietokanta ja migraatiot
- Controllerien yhdistäminen oikeaan tietoon
- Lomakkeiden tallennus

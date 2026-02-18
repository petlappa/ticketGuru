# Sprint 3 – Suunnitelma

**Kesto:** 1–2 viikkoa  
**Tavoite:** E2E-toiminto, arkkitehtuurin validointi, näytettävä tulos asiakkaalle

---

## Sprintin tuotteet

| # | Tuote | Tilanne |
|---|-------|---------|
| 1 | API-dokumentaatio (events) | Done |
| 2 | Events REST API (CRUD) | Done |
| 3 | Postman-demo valmis | Manual |

---

## Tavoitteet

- **E2E-validaatio** – Pyyntö → Controller → Service/Repository → Tietokanta → Vastaus
- **Asiakkaalle näytettävää** – Events API + Postman-demo
- **Client-tiimin tuki** – Dokumentoitu rajapinta tapahtumien CRUD:lle

---

## Tuotteet

### 1. API-dokumentaatio

- Base-URL ja endpointit
- Pyyntö: metodi, polku, parametrit, body
- Vastaus: paluukoodi, sisältö
- Tiedosto: `docs/api/API-dokumentaatio.md`

### 2. Events REST API

- `GET /api/events` – kaikki tapahtumat
- `GET /api/events/{id}` – yksi tapahtuma
- `POST /api/events` – uusi tapahtuma
- `PUT /api/events/{id}` – päivitä tapahtuma
- `DELETE /api/events/{id}` – poista tapahtuma

### 3. Katselmus

- Postman-demo rajapinnan käytöstä

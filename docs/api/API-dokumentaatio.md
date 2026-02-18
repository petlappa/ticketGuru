# TicketGuru REST API – Tapahtumat (Events)

Tämä dokumentti kuvaa TicketGuru-palvelun **tapahtumat** (events) -endpointin. Client-tiimi käyttää rajapintaa tapahtumien luontiin, muokkaukseen, hakuun ja poistamiseen.

---

## Base-URL

Kehitysympäristössä:

```
http://localhost:8080
```

API-polun etuliite: `/api`

---

## Tapahtuma (Event) – resurssin rakenne

| Kenttä | Tyyppi | Pakollinen | Kuvaus |
|--------|--------|------------|--------|
| id | Long | (vain vastaus) | Autogeneroitu tunniste |
| kuvaus | String | Kyllä | Tapahtuman nimi/kuvaus |
| aika | DateTime (ISO 8601) | Kyllä | Tapahtuman ajankohta |
| kaupunki | String | Ei | Kaupunki |
| paikka | String | Ei | Tapahtumapaikka |
| kapasiteetti | Integer | Kyllä | Maksimimäärä lippuja (≥ 1) |

---

## Endpointit

### 1. Hae kaikki tapahtumat

**Polku** : `/api/events`

**Metodi** : `GET`

**Path parametrit** : –

**Query parametrit** : –

**Request body** : –

#### Onnistunut vastaus

**Koodi** : `200 OK`

**Content-Type** : `application/json`

**Esimerkki** :

```json
[
  {
    "id": 1,
    "kuvaus": "Super-Gaala",
    "aika": "2025-03-05T20:00:00",
    "kaupunki": "Helsinki",
    "paikka": "Finlandia-talo",
    "kapasiteetti": 1700
  }
]
```

---

### 2. Hae yksi tapahtuma

**Polku** : `/api/events/{id}`

**Metodi** : `GET`

**Path parametrit** :

| Parametri | Tyyppi | Kuvaus |
|-----------|--------|--------|
| id | Long | Tapahtuman tunniste |

**Query parametrit** : –

**Request body** : –

#### Onnistunut vastaus

**Koodi** : `200 OK`

**Esimerkki** :

```json
{
  "id": 1,
  "kuvaus": "Super-Gaala",
  "aika": "2025-03-05T20:00:00",
  "kaupunki": "Helsinki",
  "paikka": "Finlandia-talo",
  "kapasiteetti": 1700
}
```

#### Virhe: ei löydy

**Koodi** : `404 Not Found`

**Esimerkki** :

```json
{
  "message": "Tapahtumaa ei löydy id:llä 999"
}
```

---

### 3. Luo uusi tapahtuma

**Polku** : `/api/events`

**Metodi** : `POST`

**Path parametrit** : –

**Query parametrit** : –

**Request body** : `application/json`

**Data constraints** :

```json
{
  "kuvaus": "[pakollinen, max 255 merkkiä]",
  "aika": "[pakollinen, ISO 8601]",
  "kaupunki": "[valinnainen, max 100 merkkiä]",
  "paikka": "[valinnainen, max 255 merkkiä]",
  "kapasiteetti": "[pakollinen, kokonaisluku ≥ 1]"
}
```

**Esimerkki** :

```json
{
  "kuvaus": "Super-Gaala",
  "aika": "2025-03-05T20:00:00",
  "kaupunki": "Helsinki",
  "paikka": "Finlandia-talo",
  "kapasiteetti": 1700
}
```

#### Onnistunut vastaus

**Koodi** : `201 Created`

**Headers** : `Location: /api/events/{id}`

**Esimerkki** :

```json
{
  "id": 1,
  "kuvaus": "Super-Gaala",
  "aika": "2025-03-05T20:00:00",
  "kaupunki": "Helsinki",
  "paikka": "Finlandia-talo",
  "kapasiteetti": 1700
}
```

#### Virhe: validointi epäonnistuu

**Koodi** : `400 Bad Request`

**Esimerkki** :

```json
{
  "kuvaus": "Kenttä on pakollinen",
  "kapasiteetti": "Täytyy olla vähintään 1"
}
```

---

### 4. Päivitä tapahtuma

**Polku** : `/api/events/{id}`

**Metodi** : `PUT`

**Path parametrit** :

| Parametri | Tyyppi | Kuvaus |
|-----------|--------|--------|
| id | Long | Tapahtuman tunniste |

**Query parametrit** : –

**Request body** : `application/json` (sama rakenne kuin POST)

**Esimerkki** :

```json
{
  "kuvaus": "Super-Gaala (päivitetty)",
  "aika": "2025-03-05T21:00:00",
  "kaupunki": "Helsinki",
  "paikka": "Finlandia-talo",
  "kapasiteetti": 1800
}
```

#### Onnistunut vastaus

**Koodi** : `200 OK`

**Esimerkki** :

```json
{
  "id": 1,
  "kuvaus": "Super-Gaala (päivitetty)",
  "aika": "2025-03-05T21:00:00",
  "kaupunki": "Helsinki",
  "paikka": "Finlandia-talo",
  "kapasiteetti": 1800
}
```

#### Virhe: ei löydy

**Koodi** : `404 Not Found`

---

### 5. Poista tapahtuma

**Polku** : `/api/events/{id}`

**Metodi** : `DELETE`

**Path parametrit** :

| Parametri | Tyyppi | Kuvaus |
|-----------|--------|--------|
| id | Long | Tapahtuman tunniste |

**Query parametrit** : –

**Request body** : –

#### Onnistunut vastaus

**Koodi** : `204 No Content`

**Body** : tyhjä

#### Virhe: ei löydy

**Koodi** : `404 Not Found`

---

## Postman-kokoelma

Kokoelma on tiedostossa `docs/api/TicketGuru-Events.postman_collection.json`.

1. Käynnistä sovellus: `mvn spring-boot:run`
2. Postman: File → Import → valitse kokoelma-tiedosto
3. baseUrl-muuttuja on oletuksena `http://localhost:8080`

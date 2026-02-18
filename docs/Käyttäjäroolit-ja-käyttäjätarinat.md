# Käyttäjäroolit ja käyttäjätarinat

## Käyttäjäroolit

| Rooli | Kuvaus |
|-------|--------|
| **Kassamyyjä** | Myy lippuja tapahtumissa. Valitsee tapahtuman, lipputyypit ja määrät, suorittaa myynnin ja tulostaa liput. |
| **Tapahtuman järjestäjä** | Luo ja muokkaa tapahtumia sekä lipputyyppejä. Hallinnoi tapahtumakohtaista tarjontaa. |
| **Esimies / Raportoija** | Tarkastelee myyntiraportteja ja -tilastoja. Seuraa myyntitilannetta tapahtumittain. |

---

## Käyttäjätarinat

### Lippujen myynti (Kassamyyjä)

| ID | Tarina | Prioriteetti |
|----|--------|--------------|
| US-1 | **Kassamyyjänä** haluan valita myyntitapahtumalle tapahtuman, jotta voin myydä oikean tapahtuman lippuja | Must have |
| US-2 | **Kassamyyjänä** haluan valita lipputyypin (esim. aikuinen, lapsi) ja määrän, jotta voin myydä oikeanlaisia lippuja | Must have |
| US-3 | **Kassamyyjänä** haluan nähdä ostosten summan ennen vahvistusta, jotta varmistan oikean hinnan | Must have |
| US-4 | **Kassamyyjänä** haluan vahvistaa myynnin (Myy), jotta liput rekisteröidään ja myyntitapahtuma luodaan | Must have |
| US-5 | **Kassamyyjänä** haluan saada jokaiselle lipulle uniikki koodi, jotta liput voidaan tunnistaa ovelta | Must have |
| US-6 | **Kassamyyjänä** haluan tulostaa ostetut liput, jotta asiakas saa fyysiset liput | Should have |

### Lipputyyppien hallinta (Tapahtuman järjestäjä)

| ID | Tarina | Prioriteetti |
|----|--------|--------------|
| US-7 | **Tapahtuman järjestäjänä** haluan listata kaikki lipputyypit, jotta näen mitä tyyppejä on olemassa | Must have |
| US-8 | **Tapahtuman järjestäjänä** haluan luoda uuden lipputyypin (kuvaus, hinta), jotta voin tarjota eri hintaeroja (aikuinen, lapsi, eläkeläinen jne.) | Must have |
| US-9 | **Tapahtuman järjestäjänä** haluan muokata olemassa olevaa lipputyyppiä, jotta voin päivittää hintoja tai kuvauksia | Must have |

### Tapahtumien hallinta (Tapahtuman järjestäjä)

| ID | Tarina | Prioriteetti |
|----|--------|--------------|
| US-10 | **Tapahtuman järjestäjänä** haluan listata kaikki tapahtumat, jotta näen mitä tapahtumia on tarjolla | Must have |
| US-11 | **Tapahtuman järjestäjänä** haluan luoda uuden tapahtuman (kuvaus, aika, paikka, kaupunki, liput kpl), jotta myyjät voivat myydä sen lippuja | Must have |
| US-12 | **Tapahtuman järjestäjänä** haluan muokata tapahtumaa, jotta voin korjata virheitä tai päivittää tietoja | Must have |

### Myyntiraportit (Esimies / Raportoija)

| ID | Tarina | Prioriteetti |
|----|--------|--------------|
| US-13 | **Esimiehenä** haluan nähdä myyntiraportin lipputyypeittäin (kpl, yhteensä), jotta tiedän mitkä lipputyypit myyvät parhaiten | Must have |
| US-14 | **Esimiehenä** haluan nähdä tapahtuman myyntitapahtumat (aika, tunniste, summa), jotta voin seurata myyntiä | Must have |
| US-15 | **Esimiehenä** haluan avata yksittäisen myyntitapahtuman ja nähdä sen liput, jotta voin tarkastella yksityiskohtia | Should have |

---

## Roolien oikeudet (matriisi)

| Ominaisuus | Kassamyyjä | Tapahtuman järjestäjä | Esimies |
|------------|:----------:|:---------------------:|:-------:|
| Myydä lippuja | ✓ | ✓ | ✓ |
| Tulostaa liput | ✓ | ✓ | ✓ |
| Luoda/muokata lipputyyppejä | | ✓ | |
| Luoda/muokata tapahtumia | | ✓ | |
| Tarkastella myyntiraportteja | | ✓ | ✓ |
| Tarkastella myyntitapahtuman yksityiskohtia | | | ✓ |

*Huom: Matriisi voidaan tarkentaa tarpeen mukaan. Alustavassa sprintissä rooleja ei ehkä vielä erotella.*

---

## Hyväksymiskriteerit (esimerkki US-4)

**US-4: Kassamyyjänä haluan vahvistaa myynnin (Myy), jotta liput rekisteröidään**

- Kun myyjä painaa "Myy", luodaan myyntitapahtuma
- Jokainen valittu lippu tallennetaan tietokantaan uniikilla koodilla
- Näytetään vahvistus: myyntitapahtuman tunniste, liput ja summa
- Maksettu-päivämäärä tallennetaan

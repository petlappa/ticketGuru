#!/bin/bash
# Luo 10 testitapahtumaa API:in kautta
# Käynnistä ensin: mvn spring-boot:run

BASE_URL="http://localhost:8080/api/events"

curl -X POST "$BASE_URL" -H "Content-Type: application/json" \
  -d '{"kuvaus":"Super-Gaala","aika":"2025-03-05T20:00:00","kaupunki":"Helsinki","paikka":"Finlandia-talo","kapasiteetti":1700}'
echo ""

curl -X POST "$BASE_URL" -H "Content-Type: application/json" \
  -d '{"kuvaus":"Jazz-ilta","aika":"2025-04-12T19:00:00","kaupunki":"Tampere","paikka":"Tullikamarin Areena","kapasiteetti":800}'
echo ""

curl -X POST "$BASE_URL" -H "Content-Type: application/json" \
  -d '{"kuvaus":"Rockfestivaali","aika":"2025-06-20T18:00:00","kaupunki":"Turku","paikka":"Ruissalo","kapasiteetti":15000}'
echo ""

curl -X POST "$BASE_URL" -H "Content-Type: application/json" \
  -d '{"kuvaus":"Teatteri: Hamlet","aika":"2025-02-15T19:30:00","kaupunki":"Helsinki","paikka":"Kansallisteatteri","kapasiteetti":650}'
echo ""

curl -X POST "$BASE_URL" -H "Content-Type: application/json" \
  -d '{"kuvaus":"Lasten huvipuistopäivä","aika":"2025-05-01T10:00:00","kaupunki":"Espoo","paikka":"Linnanmäki","kapasiteetti":5000}'
echo ""

curl -X POST "$BASE_URL" -H "Content-Type: application/json" \
  -d '{"kuvaus":"Stand up -koomikot","aika":"2025-03-28T20:00:00","kaupunki":"Oulu","paikka":"Ouluhalli","kapasiteetti":1200}'
echo ""

curl -X POST "$BASE_URL" -H "Content-Type: application/json" \
  -d '{"kuvaus":"Klassinen konsertti","aika":"2025-04-08T19:00:00","kaupunki":"Helsinki","paikka":"Musiikkitalo","kapasiteetti":1700}'
echo ""

curl -X POST "$BASE_URL" -H "Content-Type: application/json" \
  -d '{"kuvaus":"Elokuvafestivaali","aika":"2025-09-19T17:00:00","kaupunki":"Helsinki","paikka":"Bio Rex","kapasiteetti":450}'
echo ""

curl -X POST "$BASE_URL" -H "Content-Type: application/json" \
  -d '{"kuvaus":"Urheilutilaisuus: Jalkapallo","aika":"2025-07-05T16:00:00","kaupunki":"Helsinki","paikka":"Olympiastadion","kapasiteetti":40000}'
echo ""

curl -X POST "$BASE_URL" -H "Content-Type: application/json" \
  -d '{"kuvaus":"Pienoiskonsertti","aika":"2025-03-10T20:00:00","kaupunki":"Jyväskylä","paikka":"Lutakko","kapasiteetti":350}'
echo ""

echo "Valmis. 10 tapahtumaa luotu."

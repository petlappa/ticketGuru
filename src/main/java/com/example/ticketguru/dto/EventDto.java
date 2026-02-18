package com.example.ticketguru.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class EventDto {

    private Long id;

    @NotBlank(message = "Kuvaus on pakollinen")
    private String kuvaus;

    @NotNull(message = "Aika on pakollinen")
    private LocalDateTime aika;

    private String kaupunki;

    private String paikka;

    @NotNull(message = "Kapasiteetti on pakollinen")
    @Min(value = 1, message = "Kapasiteetin täytyy olla vähintään 1")
    private Integer kapasiteetti;

    public EventDto() {
    }

    public EventDto(Long id, String kuvaus, LocalDateTime aika, String kaupunki, String paikka, Integer kapasiteetti) {
        this.id = id;
        this.kuvaus = kuvaus;
        this.aika = aika;
        this.kaupunki = kaupunki;
        this.paikka = paikka;
        this.kapasiteetti = kapasiteetti;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }

    public LocalDateTime getAika() {
        return aika;
    }

    public void setAika(LocalDateTime aika) {
        this.aika = aika;
    }

    public String getKaupunki() {
        return kaupunki;
    }

    public void setKaupunki(String kaupunki) {
        this.kaupunki = kaupunki;
    }

    public String getPaikka() {
        return paikka;
    }

    public void setPaikka(String paikka) {
        this.paikka = paikka;
    }

    public Integer getKapasiteetti() {
        return kapasiteetti;
    }

    public void setKapasiteetti(Integer kapasiteetti) {
        this.kapasiteetti = kapasiteetti;
    }
}

package com.example.ticketguru.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tapahtuma")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 255)
    private String kuvaus;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime aika;

    @Column(length = 100)
    private String kaupunki;

    @Column(length = 255)
    private String paikka;

    @Min(1)
    @Column(nullable = false)
    private Integer kapasiteetti;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> liput = new ArrayList<>();

    public Event() {
    }

    public Event(String kuvaus, LocalDateTime aika, String kaupunki, String paikka, Integer kapasiteetti) {
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

    public List<Ticket> getLiput() {
        return liput;
    }

    public void setLiput(List<Ticket> liput) {
        this.liput = liput;
    }
}

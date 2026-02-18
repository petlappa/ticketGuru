package com.example.ticketguru.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lipputyyppi")
public class TicketType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 255)
    private String kuvaus;

    @DecimalMin("0.00")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal hinta;

    @OneToMany(mappedBy = "ticketType", cascade = CascadeType.ALL)
    private List<Ticket> liput = new ArrayList<>();

    public TicketType() {
    }

    public TicketType(String kuvaus, BigDecimal hinta) {
        this.kuvaus = kuvaus;
        this.hinta = hinta;
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

    public BigDecimal getHinta() {
        return hinta;
    }

    public void setHinta(BigDecimal hinta) {
        this.hinta = hinta;
    }

    public List<Ticket> getLiput() {
        return liput;
    }

    public void setLiput(List<Ticket> liput) {
        this.liput = liput;
    }
}

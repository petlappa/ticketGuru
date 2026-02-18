package com.example.ticketguru.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "myyntitapahtuma", uniqueConstraints = {
        @UniqueConstraint(columnNames = "tunniste")
})
public class SalesTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true, length = 50)
    private String tunniste;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime aika;

    @DecimalMin("0.00")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal summa;

    @OneToMany(mappedBy = "salesTransaction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticket> liput = new ArrayList<>();

    public SalesTransaction() {
    }

    public SalesTransaction(String tunniste, LocalDateTime aika, BigDecimal summa) {
        this.tunniste = tunniste;
        this.aika = aika;
        this.summa = summa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTunniste() {
        return tunniste;
    }

    public void setTunniste(String tunniste) {
        this.tunniste = tunniste;
    }

    public LocalDateTime getAika() {
        return aika;
    }

    public void setAika(LocalDateTime aika) {
        this.aika = aika;
    }

    public BigDecimal getSumma() {
        return summa;
    }

    public void setSumma(BigDecimal summa) {
        this.summa = summa;
    }

    public List<Ticket> getLiput() {
        return liput;
    }

    public void setLiput(List<Ticket> liput) {
        this.liput = liput;
    }
}

package com.example.ticketguru.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "lippu", uniqueConstraints = {
        @UniqueConstraint(columnNames = "koodi")
})
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true, length = 50)
    private String koodi;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tapahtuma_id", nullable = false)
    private Event event;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lipputyyppi_id", nullable = false)
    private TicketType ticketType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "myyntitapahtuma_id", nullable = false)
    private SalesTransaction salesTransaction;

    public Ticket() {
    }

    public Ticket(String koodi, Event event, TicketType ticketType, SalesTransaction salesTransaction) {
        this.koodi = koodi;
        this.event = event;
        this.ticketType = ticketType;
        this.salesTransaction = salesTransaction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKoodi() {
        return koodi;
    }

    public void setKoodi(String koodi) {
        this.koodi = koodi;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public SalesTransaction getSalesTransaction() {
        return salesTransaction;
    }

    public void setSalesTransaction(SalesTransaction salesTransaction) {
        this.salesTransaction = salesTransaction;
    }
}

package com.example.ticketguru.repository;

import com.example.ticketguru.domain.Event;
import com.example.ticketguru.domain.SalesTransaction;
import com.example.ticketguru.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Optional<Ticket> findByKoodi(String koodi);

    List<Ticket> findBySalesTransaction(SalesTransaction salesTransaction);

    List<Ticket> findByEvent(Event event);
}

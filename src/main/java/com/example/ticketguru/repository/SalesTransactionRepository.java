package com.example.ticketguru.repository;

import com.example.ticketguru.domain.Event;
import com.example.ticketguru.domain.SalesTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SalesTransactionRepository extends JpaRepository<SalesTransaction, Long> {

    Optional<SalesTransaction> findByTunniste(String tunniste);

    List<SalesTransaction> findByLiput_EventOrderByAikaDesc(Event event);
}

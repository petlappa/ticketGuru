package com.example.ticketguru.repository;

import com.example.ticketguru.domain.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketTypeRepository extends JpaRepository<TicketType, Long> {
}

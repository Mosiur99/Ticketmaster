package com.practice.ticket_master.repository;

import com.practice.ticket_master.domain.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}

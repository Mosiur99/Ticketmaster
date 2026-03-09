package com.practice.ticket_master.domain;

import com.practice.ticket_master.enumeration.TicketStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @Column(name = "seat", nullable = false)
    private String seat;

    @Column(name = "status", nullable = false)
    private TicketStatus status;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}

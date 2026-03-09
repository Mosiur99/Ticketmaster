package com.practice.ticket_master.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "venue")
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;
}

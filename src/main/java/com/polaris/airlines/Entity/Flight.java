package com.polaris.airlines.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "airline_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_flight_airline")
    )
    private Airline airline;

    @Column(nullable = false, unique = true)
    private String flightNumber;

    @Column(nullable = false)
    private String sourceAirport;

    @Column(nullable = false)
    private String destinationAirport;
}

package com.polaris.airlines.Entity;

import com.polaris.airlines.Enum.FareType;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class FareBucket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "flight_instance_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_farebucket_flightinstance")
    )
    private FlightInstance flightInstance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FareType fareType;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private LocalDateTime activeFrom;

    @Column(nullable = false)
    private LocalDateTime activeUntil;
}
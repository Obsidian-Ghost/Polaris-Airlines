package com.polaris.airlines.Entity;

import com.polaris.airlines.Enum.EligibleFareType;
import com.polaris.airlines.Enum.SeatClass;
import com.polaris.airlines.Enum.SeatStatus;
import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Entity
@Data
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "flight_instance_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_seat_flightinstance")
    )
    private FlightInstance flightInstance;

    @Column(nullable = false)
    private String seatCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatClass seatClass;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EligibleFareType eligibleFareType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatStatus status;
}

package com.polaris.airlines.Repository;

import com.polaris.airlines.Entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FlightInstanceSeatRepository extends JpaRepository<Seat, UUID> {
    List<Seat> findByFlightInstanceId(UUID flightInstanceId);
}


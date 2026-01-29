package com.polaris.airlines.Repository;

import com.polaris.airlines.Entity.FlightInstance;
import com.polaris.airlines.Enum.FlightStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface FlightSearchRepository extends JpaRepository<FlightInstance, UUID> {


    @Query("""
        SELECT DISTINCT fi FROM FlightInstance fi
        JOIN FETCH fi.flight f
        WHERE f.sourceAirport = :source
        AND f.destinationAirport = :destination
        AND fi.departureDate = :departureDate
        AND fi.status = :status
        ORDER BY fi.departureTime
        """)
    List<FlightInstance> searchFlights(
            @Param("source") String sourceAirport,
            @Param("destination") String destinationAirport,
            @Param("departureDate") LocalDate departureDate,
            @Param("status") FlightStatus status
    );
}
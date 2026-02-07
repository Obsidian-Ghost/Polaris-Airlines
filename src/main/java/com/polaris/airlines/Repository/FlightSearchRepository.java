package com.polaris.airlines.Repository;

import com.polaris.airlines.DTO.FlightInstanceSeatResponse;
import com.polaris.airlines.Entity.FlightInstance;
import com.polaris.airlines.Enum.FlightStatus;
import com.polaris.airlines.Service.FlightInstanceSeatService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    boolean existsById(UUID flightInstanceId);
}
package com.polaris.airlines.Repository;


import com.polaris.airlines.Entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportSearchRepository extends JpaRepository<Airport, Integer> {

    // ============================================================
    // Simple search
    // Uses: idx_airports_iata, idx_airports_name, idx_airports_city
    // ============================================================
    @Query("""
        SELECT a FROM Airport a
        WHERE a.scheduledService = true
        AND (
            LOWER(a.iataCode) LIKE LOWER(CONCAT(:query, '%')) OR
            LOWER(a.municipality) LIKE LOWER(CONCAT('%', :query, '%')) OR
            LOWER(a.name) LIKE LOWER(CONCAT('%', :query, '%'))
        )
        ORDER BY
            CASE
                WHEN LOWER(a.iataCode) LIKE LOWER(CONCAT(:query, '%')) THEN 1
                WHEN LOWER(a.municipality) LIKE LOWER(CONCAT(:query, '%')) THEN 2
                WHEN LOWER(a.name) LIKE LOWER(CONCAT(:query, '%')) THEN 3
                ELSE 4
            END,
            a.municipality
    """)
    List<Airport> searchAirports(@Param("query") String query);
}
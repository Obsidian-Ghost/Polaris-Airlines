package com.polaris.airlines.DTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record FlightSearchResponseDTO(
        UUID flight_instance_id,
        String flightNumber,
        String airlineName,
        String sourceAirport,
        String destinationAirport,
        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        String duration,
        List<FareOptionDTO> fareOptions
) {}

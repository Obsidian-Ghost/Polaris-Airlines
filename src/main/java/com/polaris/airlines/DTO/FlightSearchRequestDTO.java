package com.polaris.airlines.DTO;

import java.time.LocalDate;

public record FlightSearchRequestDTO(
        String source,
        String destination,
        LocalDate departureDate
){}

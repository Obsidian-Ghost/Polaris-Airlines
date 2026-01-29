package com.polaris.airlines.DTO;

public record AirportSearchResponse(
        String iataCode,
        String name,
        String municipality
){}
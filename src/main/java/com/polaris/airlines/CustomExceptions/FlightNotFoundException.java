package com.polaris.airlines.CustomExceptions;

import java.util.UUID;

public class FlightNotFoundException extends RuntimeException {

    public FlightNotFoundException(UUID flightInstanceId) {
        super("Flight not found with id: " + flightInstanceId);
    }
}

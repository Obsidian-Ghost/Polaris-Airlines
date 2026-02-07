package com.polaris.airlines.DTO;

import java.util.List;
import java.util.UUID;

public record FlightInstanceSeatResponse(
        UUID flightInstanceId,
        List<SeatDTO> seats
) {}

package com.polaris.airlines.DTO;

import com.polaris.airlines.Enum.EligibleFareType;
import com.polaris.airlines.Enum.SeatClass;
import com.polaris.airlines.Enum.SeatStatus;

import java.util.UUID;

public record SeatDTO(
        UUID id,
        String seatCode,
        SeatClass seatClass,
        EligibleFareType eligibleFareType,
        SeatStatus status
) {}

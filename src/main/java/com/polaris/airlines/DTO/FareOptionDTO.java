package com.polaris.airlines.DTO;

import com.polaris.airlines.Enum.FareType;

import java.time.LocalDateTime;

public record FareOptionDTO(
        FareType fareType,      // NORMAL or OFFER
        Double price,
        LocalDateTime activeFrom,
        LocalDateTime activeUntil
) {}
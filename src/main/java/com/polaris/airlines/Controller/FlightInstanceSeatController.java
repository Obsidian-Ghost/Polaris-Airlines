package com.polaris.airlines.Controller;

import com.polaris.airlines.DTO.FlightInstanceSeatResponse;
import com.polaris.airlines.Service.FlightInstanceSeatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/flights")
public class FlightInstanceSeatController {
    private final FlightInstanceSeatService flightInstanceSeatService;

    @GetMapping("/{flightInstanceId}/seats")
    public ResponseEntity<FlightInstanceSeatResponse> getSeats(
            @PathVariable UUID flightInstanceId
    ) {
        FlightInstanceSeatResponse response =
                flightInstanceSeatService.getSeats(flightInstanceId);

        return ResponseEntity.ok(response);
    }
}
package com.polaris.airlines.Controller;

import com.polaris.airlines.DTO.FlightSearchRequestDTO;
import com.polaris.airlines.DTO.FlightSearchResponseDTO;
import com.polaris.airlines.Service.FlightSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightSearchController {

    private final FlightSearchService flightSearchService;

    @GetMapping("/search")
    public ResponseEntity<List<FlightSearchResponseDTO>> searchFlights(
            @RequestParam String source,
            @RequestParam String destination,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        FlightSearchRequestDTO request = new FlightSearchRequestDTO(source, destination, date);
        List<FlightSearchResponseDTO> flights = flightSearchService.searchFlights(request);

        return ResponseEntity.ok(flights);
    }

}
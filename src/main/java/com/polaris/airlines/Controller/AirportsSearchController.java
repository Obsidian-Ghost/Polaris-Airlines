package com.polaris.airlines.Controller;

import com.polaris.airlines.DTO.AirportSearchResponse;
import com.polaris.airlines.Service.AirportSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/airports")
@RequiredArgsConstructor
public class AirportsSearchController {

    private final AirportSearchService airportService;

    @GetMapping("/search")
    @Validated
    public ResponseEntity<List<AirportSearchResponse>> searchAirports(@RequestParam String query) {

        // Validation
        if (query == null || query.trim().length() < 2) {
            return ResponseEntity.badRequest().build();  // 400 Bad Request
        }

        // Search
        List<AirportSearchResponse> results = airportService.searchAirports(query);

        // Success
        return ResponseEntity.ok(results);  // 200 OK
    }

}

package com.polaris.airlines.Service;

import com.polaris.airlines.DTO.AirportSearchResponse;
import com.polaris.airlines.Entity.Airport;
import com.polaris.airlines.Repository.AirportSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AirportSearchService {

    private final AirportSearchRepository airportRepository;

    public List<AirportSearchResponse> searchAirports(String query) {

        // Use full-text search for better performance
        List<Airport> airports = airportRepository
                .searchAirports(query.trim());

        // Convert to DTO
        return airports.stream()
                .filter(a -> a.getIataCode() != null && !a.getIataCode().isBlank())
                .limit(10)  // Limit to 10 results
                .map(this::toDTO)
                .collect(Collectors.toList());
    }


    // Private helper: Entity to DTO conversion
    private AirportSearchResponse toDTO(Airport airport) {
        return new AirportSearchResponse(
                airport.getIataCode(),
                airport.getName(),
                airport.getMunicipality()
        );
    }
}

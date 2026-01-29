package com.polaris.airlines.Service;

import com.polaris.airlines.DTO.FareOptionDTO;
import com.polaris.airlines.DTO.FlightSearchRequestDTO;
import com.polaris.airlines.DTO.FlightSearchResponseDTO;
import com.polaris.airlines.Entity.FareBucket;
import com.polaris.airlines.Entity.FlightInstance;
import com.polaris.airlines.Enum.FlightStatus;
import com.polaris.airlines.Repository.FareBucketRepository;
import com.polaris.airlines.Repository.FlightSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FlightSearchService {

    private final FlightSearchRepository flightSearchRepository;

    private final FareBucketRepository fareBucketRepository;

    public List<FlightSearchResponseDTO> searchFlights(FlightSearchRequestDTO request) {

        // Fetch all matching flight instances
        List<FlightInstance> availableFlightInstances = flightSearchRepository.searchFlights(
                request.source(),
                request.destination(),
                request.departureDate(),
                FlightStatus.SCHEDULED
        );

        // Collect all FlightInstance IDs
        List<UUID> flightInstanceIds = new ArrayList<>();
        for (FlightInstance fi : availableFlightInstances) {
            flightInstanceIds.add(fi.getId());
        }

        // Fetch ALL fare buckets
        List<FareBucket> allFareBuckets = new ArrayList<>();
        if (!flightInstanceIds.isEmpty()) {
            allFareBuckets = fareBucketRepository.findByFlightInstanceIdIn(flightInstanceIds);
        }

        // Group fare buckets by FlightInstance ID
        Map<UUID, List<FareBucket>> fareInstanceMapping = new HashMap<>();

        for (FareBucket fb : allFareBuckets) {
            UUID flightInstanceId = fb.getFlightInstance().getId();

            if (!fareInstanceMapping.containsKey(flightInstanceId)) {
                fareInstanceMapping.put(flightInstanceId, new ArrayList<>());
            }

            fareInstanceMapping.get(flightInstanceId).add(fb);
        }

        // Ensure every flight has an entry (avoid null later)
        for (FlightInstance fi : availableFlightInstances) {
            fareInstanceMapping.putIfAbsent(fi.getId(), new ArrayList<>());
        }

        // Convert to DTOs
        return toDTO(availableFlightInstances, fareInstanceMapping);
    }


    private String calculateDuration(LocalDateTime departure, LocalDateTime arrival) {
        Duration duration = Duration.between(departure, arrival);
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        return String.format("%dh %dm", hours, minutes);
    }


    private List<FlightSearchResponseDTO> toDTO(List<FlightInstance> availableFlightInstances, Map<UUID,List<FareBucket>> fareInstanceMapping){
        List<FlightSearchResponseDTO> finalList = new ArrayList<>();
        for (FlightInstance fi : availableFlightInstances){
            finalList.add(
                    new FlightSearchResponseDTO(
                            fi.getId(),
                            fi.getFlight().getFlightNumber(),
                            fi.getFlight().getAirline().getAirlineName(),
                            fi.getFlight().getSourceAirport(),
                            fi.getFlight().getDestinationAirport(),
                            fi.getDepartureTime(),
                            fi.getArrivalTime(),
                            calculateDuration(fi.getDepartureTime(),fi.getArrivalTime()),
                            toFareDTO(fareInstanceMapping.get(fi.getId()))

                    )
            );
        }
        return finalList;
    }


    public List<FareOptionDTO> toFareDTO(List<FareBucket> fareBuckets){
        List<FareOptionDTO> fodto = new ArrayList<>();
        for( FareBucket fb : fareBuckets){
            fodto.add(
                    new FareOptionDTO(
                            fb.getFareType(),
                            fb.getPrice(),
                            fb.getActiveFrom(),
                            fb.getActiveUntil()
                    )
            );
        }
        return fodto;
    }


}
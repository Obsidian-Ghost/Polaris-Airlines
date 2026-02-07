package com.polaris.airlines.Service;

import com.polaris.airlines.CustomExceptions.FlightNotFoundException;
import com.polaris.airlines.DTO.FlightInstanceSeatResponse;
import com.polaris.airlines.DTO.SeatDTO;
import com.polaris.airlines.Entity.Seat;
import com.polaris.airlines.Repository.FlightInstanceSeatRepository;
import com.polaris.airlines.Repository.FlightSearchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class FlightInstanceSeatService {
    private final FlightInstanceSeatRepository flightInstanceSeatRepository;
    private final FlightSearchRepository flightSearchRepository;

    public FlightInstanceSeatResponse getSeats(UUID flightInstanceId){
        if (!flightSearchRepository.existsById(flightInstanceId)){
            throw new FlightNotFoundException(flightInstanceId);
        }

        List<Seat> availableSeats = flightInstanceSeatRepository.findByFlightInstanceId(flightInstanceId);
        List<SeatDTO> seatDTOS = new ArrayList<>();
        for (Seat seat : availableSeats){
            seatDTOS.add(
                    new SeatDTO(
                            seat.getId(),
                            seat.getSeatCode(),
                            seat.getSeatClass(),
                            seat.getEligibleFareType(),
                            seat.getStatus()
                    )
            );
        }
        return new FlightInstanceSeatResponse(flightInstanceId,seatDTOS);
    }
}

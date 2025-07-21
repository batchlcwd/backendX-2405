package com.substring.irctc.dto;

import com.substring.irctc.entity.CoachType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AvailableTrainResponse {

    private Long trainId;
    private  String trainNumber;
    private String trainName;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Map<CoachType, Integer> seatsAvailable;
    private Map<CoachType, Double> priceByCoach;


}

package com.substring.irctc.dto;

import com.substring.irctc.entity.CoachType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainSeatDto {


    private  Long id;
    private  Long trainScheduleId;
    private  CoachType coachType;
    private  Integer totalSeats;
    private  Integer availableSeats;
    private  Double price;
    private Integer seatNumberToAssign;

    private Integer trainSeatOrder;


}

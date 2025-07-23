package com.substring.irctc.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainScheduleDto {

    private Long Id;

    private Long trainId;

    private LocalDate runDate;

    private  Integer availableSeats;


}

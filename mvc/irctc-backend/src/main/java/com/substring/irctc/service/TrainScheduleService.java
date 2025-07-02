package com.substring.irctc.service;

import com.substring.irctc.dto.TrainScheduleDto;
import com.substring.irctc.entity.Train;
import com.substring.irctc.entity.TrainSchedule;

import java.util.List;

public interface TrainScheduleService {

    TrainScheduleDto createSchedule(TrainScheduleDto trainScheduleDto);

    List<TrainScheduleDto> getTrainSchedulesByTrainId(Long trainId);

    void deleteTrainSchedule(Long trainScheduleId);


    TrainScheduleDto updateTrainSchedule(Long trainScheduleId, TrainScheduleDto trainScheduleDto);

}

package com.substring.irctc.service;


import com.substring.irctc.dto.TrainSeatDto;

import java.util.List;

public interface TrainSeatService {


    TrainSeatDto createSeatInfor(TrainSeatDto trainSeatDto);
    List<TrainSeatDto> getSeatInfoByTrainScheduleId(Long scheduleId);
    void deleteSeatInfo(Long seatId);
    TrainSeatDto updateSeatInfo(Long seatId, TrainSeatDto trainSeatDto);
    public List<Integer> bookSeat(int seatToBook, Long seatId);
}

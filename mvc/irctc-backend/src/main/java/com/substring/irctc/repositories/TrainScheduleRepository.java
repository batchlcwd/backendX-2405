package com.substring.irctc.repositories;

import com.substring.irctc.entity.TrainSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainScheduleRepository extends JpaRepository<TrainSchedule,Long>
{


    @Query("SELECT ts FROM TrainSchedule ts WHERE ts.train.id = ?1")
    List<TrainSchedule> findByTrainId(Long trainId);
}

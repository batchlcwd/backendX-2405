package com.substring.irctc.repositories;

import com.substring.irctc.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainRepository extends JpaRepository<Train,Long>
{

    @Query("""
            
            SELECT tr.train from TrainRoute tr
            WHERE tr.station.id= :sourceStationId OR tr.station.id= :destinationStationId
            GROUP by tr.train.id
            HAVING SUM(CASE WHEN tr.station.id= :sourceStationId THEN 1 ELSE 0 END)>0 AND SUM(CASE WHEN tr.station.id= :destinationStationId THEN 1 ELSE 0 END)>0 AND (   MIN(CASE WHEN tr.station.id= :sourceStationId THEN tr.stationOrder ELSE 999999 END)     < MIN(CASE WHEN tr.station.id= :destinationStationId THEN tr.stationOrder ELSE 999999 END)         )
            
            """)
    List<Train> findTrainBySourceAndDestinationInOrder(@Param("sourceStationId") Long sourceStationId,  @Param("destinationStationId") Long destinationStationId);


}

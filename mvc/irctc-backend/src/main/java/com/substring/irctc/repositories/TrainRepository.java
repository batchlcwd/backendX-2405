package com.substring.irctc.repositories;

import com.substring.irctc.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train,Long>
{

}

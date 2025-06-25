package com.substring.irctc.repositories;

import com.substring.irctc.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepo extends JpaRepository<Station, Long> {
}

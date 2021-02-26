package com.dev.fitbooking.dao;

import com.dev.fitbooking.model.FitnessRoom;
import java.util.List;
import java.util.Optional;

public interface FitnessRoomDao {
    FitnessRoom add(FitnessRoom fitnessRoom);

    Optional<FitnessRoom> get(Long id);

    List<FitnessRoom> getAll();
}

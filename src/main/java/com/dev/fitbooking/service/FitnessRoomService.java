package com.dev.fitbooking.service;

import com.dev.fitbooking.model.FitnessRoom;
import java.util.List;

public interface FitnessRoomService {
    void add(FitnessRoom fitnessRoom);

    FitnessRoom get(Long id);

    List<FitnessRoom> getAll();
}

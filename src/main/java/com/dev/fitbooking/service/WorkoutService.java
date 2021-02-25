package com.dev.fitbooking.service;

import com.dev.fitbooking.model.Workout;
import java.util.List;

public interface WorkoutService {
    void add(Workout workout);

    Workout get(Long id);

    List<Workout> getAll();
}

package com.dev.fitbooking.dao;

import com.dev.fitbooking.model.Workout;
import java.util.List;
import java.util.Optional;

public interface WorkoutDao {
    Workout add(Workout workout);

    Optional<Workout> get(Long id);

    List<Workout> getAll();
}

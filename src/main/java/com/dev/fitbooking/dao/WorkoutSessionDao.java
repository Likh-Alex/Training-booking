package com.dev.fitbooking.dao;

import com.dev.fitbooking.model.WorkoutSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WorkoutSessionDao {
    WorkoutSession add(WorkoutSession workoutSession);

    List<WorkoutSession> getAll();

    List<WorkoutSession> findAvailableSessions(Long movieId, LocalDate date);

    WorkoutSession update(WorkoutSession workoutSession);

    boolean delete(Long id);

    Optional<WorkoutSession> get(Long id);
}

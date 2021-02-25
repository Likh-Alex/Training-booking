package com.dev.fitbooking.service;

import com.dev.fitbooking.model.WorkoutSession;
import java.time.LocalDate;
import java.util.List;

public interface WorkoutSessionService {
    void add(WorkoutSession workoutSession);

    List<WorkoutSession> getAll();

    List<WorkoutSession> findAvailableSessions(Long movieId, LocalDate date);

    void update(WorkoutSession workoutSession);

    void delete(Long id);

    WorkoutSession get(Long id);
}

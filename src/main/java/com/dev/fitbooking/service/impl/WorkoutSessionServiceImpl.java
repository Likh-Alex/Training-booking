package com.dev.fitbooking.service.impl;

import com.dev.fitbooking.dao.WorkoutSessionDao;
import com.dev.fitbooking.model.WorkoutSession;
import com.dev.fitbooking.service.WorkoutSessionService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkoutSessionServiceImpl implements WorkoutSessionService {
    private final WorkoutSessionDao workoutSessionDao;

    @Autowired
    public WorkoutSessionServiceImpl(WorkoutSessionDao workoutSessionDao) {
        this.workoutSessionDao = workoutSessionDao;
    }

    @Override
    public void add(WorkoutSession workoutSession) {
        workoutSessionDao.add(workoutSession);
    }

    @Override
    public List<WorkoutSession> getAll() {
        return workoutSessionDao.getAll();
    }

    @Override
    public List<WorkoutSession> findAvailableSessions(Long movieId, LocalDate date) {
        return workoutSessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public void update(WorkoutSession workoutSession) {
        workoutSessionDao.update(workoutSession);
    }

    @Override
    public void delete(Long id) {
        workoutSessionDao.delete(id);
    }

    @Override
    public WorkoutSession get(Long id) {
        return workoutSessionDao.get(id).orElseThrow(()
                -> new RuntimeException("Can not get user with id: " + id));
    }
}

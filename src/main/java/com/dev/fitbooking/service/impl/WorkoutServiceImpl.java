package com.dev.fitbooking.service.impl;

import com.dev.fitbooking.dao.WorkoutDao;
import com.dev.fitbooking.model.Workout;
import com.dev.fitbooking.service.WorkoutService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkoutServiceImpl implements WorkoutService {
    private final WorkoutDao workoutDao;

    @Autowired
    public WorkoutServiceImpl(WorkoutDao workoutDao) {
        this.workoutDao = workoutDao;
    }

    @Override
    public void add(Workout workout) {
        workoutDao.add(workout);
    }

    @Override
    public Workout get(Long id) {
        return workoutDao.get(id).orElseThrow(()
                -> new RuntimeException("Can not get workout with id: " + id));
    }

    @Override
    public List<Workout> getAll() {
        return workoutDao.getAll();
    }
}

package com.dev.fitbooking.controller;

import com.dev.fitbooking.dto.request.WorkoutRequestDto;
import com.dev.fitbooking.dto.response.WorkoutResponseDto;
import com.dev.fitbooking.model.Workout;
import com.dev.fitbooking.service.WorkoutService;
import com.dev.fitbooking.service.mapper.WorkoutMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workouts")
public class WorkoutController {
    private final WorkoutService workoutService;
    private final WorkoutMapper workoutMapper;

    @Autowired
    public WorkoutController(WorkoutService workoutService, WorkoutMapper workoutMapper) {
        this.workoutService = workoutService;
        this.workoutMapper = workoutMapper;
    }

    @PostMapping
    public void add(@RequestBody @Valid WorkoutRequestDto requestDto) {
        Workout workout = workoutMapper.toEntity(requestDto);
        workoutService.add(workout);
    }

    @GetMapping
    public List<WorkoutResponseDto> getAll() {
        return workoutService.getAll()
                .stream()
                .map(workoutMapper::toDto)
                .collect(Collectors.toList());
    }
}

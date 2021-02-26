package com.dev.fitbooking.controller;

import com.dev.fitbooking.dto.request.WorkoutSessionRequestDto;
import com.dev.fitbooking.dto.response.WorkoutSessionResponseDto;
import com.dev.fitbooking.model.WorkoutSession;
import com.dev.fitbooking.service.WorkoutSessionService;
import com.dev.fitbooking.service.mapper.WorkoutSessionMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workout-sessions")
public class WorkoutSessionController {
    private final WorkoutSessionMapper workoutSessionMapper;
    private final WorkoutSessionService workoutSessionService;

    public WorkoutSessionController(WorkoutSessionMapper workoutSessionMapper,
                                    WorkoutSessionService service) {
        this.workoutSessionMapper = workoutSessionMapper;
        workoutSessionService = service;
    }

    @PostMapping
    public void add(@RequestBody @Valid WorkoutSessionRequestDto requestDto) {
        WorkoutSession workoutSession = workoutSessionMapper.toEntity(requestDto);
        workoutSessionService.add(workoutSession);
    }

    @GetMapping("/available")
    public List<WorkoutSessionResponseDto> getAllByDate(
            @RequestParam Long id,
            @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date) {
        List<WorkoutSession> availableSessions = workoutSessionService
                .findAvailableSessions(id, date);
        return availableSessions.stream()
                .map(workoutSessionMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody @Valid WorkoutSessionRequestDto requestDto) {
        WorkoutSession workoutSession = workoutSessionMapper.toEntity(requestDto);
        workoutSession.setId(id);
        workoutSessionService.update(workoutSession);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        workoutSessionService.delete(id);
    }
}

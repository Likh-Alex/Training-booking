package com.dev.fitbooking.service.mapper;

import com.dev.fitbooking.dto.request.WorkoutRequestDto;
import com.dev.fitbooking.dto.response.WorkoutResponseDto;
import com.dev.fitbooking.model.Workout;
import org.springframework.stereotype.Component;

@Component
public class WorkoutMapper {
    public WorkoutResponseDto toDto(Workout workout) {
        WorkoutResponseDto dto = new WorkoutResponseDto();
        dto.setId(workout.getId());
        dto.setDescription(workout.getDescription());
        dto.setType(workout.getType());
        return dto;
    }

    public Workout toEntity(WorkoutRequestDto dto) {
        Workout workout = new Workout();
        workout.setType(dto.getType());
        workout.setDescription(dto.getDescription());
        return workout;
    }
}

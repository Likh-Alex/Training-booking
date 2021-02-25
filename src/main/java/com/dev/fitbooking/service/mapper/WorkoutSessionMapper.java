package com.dev.fitbooking.service.mapper;

import com.dev.fitbooking.dto.request.WorkoutSessionRequestDto;
import com.dev.fitbooking.dto.response.WorkoutSessionResponseDto;
import com.dev.fitbooking.model.WorkoutSession;
import com.dev.fitbooking.service.FitnessRoomService;
import com.dev.fitbooking.service.WorkoutService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkoutSessionMapper {
    private final FitnessRoomService fitnessRoomService;
    private final WorkoutService workoutService;

    @Autowired
    public WorkoutSessionMapper(FitnessRoomService fitnessRoomService,
                                WorkoutService workoutService) {
        this.fitnessRoomService = fitnessRoomService;
        this.workoutService = workoutService;
    }

    public WorkoutSession toEntity(WorkoutSessionRequestDto dto) {
        WorkoutSession workoutSession = new WorkoutSession();
        workoutSession.setWorkout(workoutService.get(dto.getWorkoutId()));
        workoutSession.setFitnessRoom(fitnessRoomService.get(dto.getFitnessRoomId()));
        LocalDateTime parsedDateTime = LocalDateTime.parse(dto.getWorkoutTime(),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        workoutSession.setWorkoutTime(parsedDateTime);
        return workoutSession;
    }

    public WorkoutSessionResponseDto toDto(WorkoutSession workoutSession) {
        WorkoutSessionResponseDto dto = new WorkoutSessionResponseDto();
        dto.setId(workoutSession.getId());
        dto.setWorkoutTime(workoutSession.getWorkoutTime().toString());

        dto.setWorkoutId(workoutSession.getWorkout().getId());
        dto.setWorkoutType(workoutSession.getWorkout().getType());

        dto.setFitnessRoomId(workoutSession.getFitnessRoom().getId());
        dto.setFitnessRoomCapacity(workoutSession.getFitnessRoom().getCapacity());
        dto.setFitnessRoomDescription(workoutSession.getFitnessRoom().getDescription());

        return dto;
    }
}

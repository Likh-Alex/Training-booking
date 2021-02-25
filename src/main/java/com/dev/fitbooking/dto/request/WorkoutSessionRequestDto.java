package com.dev.fitbooking.dto.request;

import javax.validation.constraints.NotNull;

public class WorkoutSessionRequestDto {
    @NotNull
    private Long workoutId;
    @NotNull
    private Long fitnessRoomId;
    @NotNull
    private String workoutTime;

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    public Long getFitnessRoomId() {
        return fitnessRoomId;
    }

    public void setFitnessRoomId(Long fitnessRoomId) {
        this.fitnessRoomId = fitnessRoomId;
    }

    public String getWorkoutTime() {
        return workoutTime;
    }

    public void setWorkoutTime(String workoutTime) {
        this.workoutTime = workoutTime;
    }
}

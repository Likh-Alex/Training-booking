package com.dev.fitbooking.dto.response;

public class WorkoutSessionResponseDto {
    private Long id;
    private Long workoutId;
    private String workoutType;
    private Long fitnessRoomId;
    private int fitnessRoomCapacity;
    private String workoutTime;
    private String fitnessRoomDescription;

    public String getFitnessRoomDescription() {
        return fitnessRoomDescription;
    }

    public void setFitnessRoomDescription(String fitnessRoomDescription) {
        this.fitnessRoomDescription = fitnessRoomDescription;
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }

    public int getFitnessRoomCapacity() {
        return fitnessRoomCapacity;
    }

    public void setFitnessRoomCapacity(int fitnessRoomCapacity) {
        this.fitnessRoomCapacity = fitnessRoomCapacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

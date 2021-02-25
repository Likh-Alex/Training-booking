package com.dev.fitbooking.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "workout_session")
public class WorkoutSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Workout workout;
    @ManyToOne
    private FitnessRoom fitnessRoom;
    private LocalDateTime workoutTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public FitnessRoom getFitnessRoom() {
        return fitnessRoom;
    }

    public void setFitnessRoom(FitnessRoom fitnessRoom) {
        this.fitnessRoom = fitnessRoom;
    }

    public LocalDateTime getWorkoutTime() {
        return workoutTime;
    }

    public void setWorkoutTime(LocalDateTime showTime) {
        this.workoutTime = showTime;
    }

    @Override
    public String toString() {
        return "WorkoutSession{"
                + "id=" + id
                + ", workout=" + workout
                + ", fitnessRoom=" + fitnessRoom
                + ", showTime=" + workoutTime
                + '}';
    }
}

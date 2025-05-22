package model;

import java.util.ArrayList;
import java.util.List;

public class WorkoutSession {
    private String date;  // e.g. "2025-05-15"
    private List<Exercise> exercises;
    private int durationMinutes; // total duration in minutes

    public WorkoutSession(String date, int durationMinutes) {
        this.date = date;
        this.durationMinutes = durationMinutes;
        this.exercises = new ArrayList<>();
    }

    // Getters and setters
    public String getDate() {
        return date;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    // Add exercise
    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    // Remove exercise by name
    public void removeExercise(String exerciseName) {
        exercises.removeIf(e -> e.getName().equalsIgnoreCase(exerciseName));
    }

    // Display session details
    public void displaySession() {
        System.out.println("Date: " + date);
        System.out.println("Duration: " + durationMinutes + " minutes");
        System.out.println("Exercises:");
        for (Exercise e : exercises) {
            e.displayExercise();
        }
    }
}

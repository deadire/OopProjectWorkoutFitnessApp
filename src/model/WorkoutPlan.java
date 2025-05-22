package model;

public class WorkoutPlan {
    private String planName;
    private Exercise[] exercises;

    public WorkoutPlan(String planName, Exercise[] exercises) {
        this.planName = planName;
        this.exercises = exercises;
    }

    public void displayPlan() {
        System.out.println("Workout Plan: " + planName);
        System.out.println("Exercises:");
        for (Exercise ex : exercises) {
            ex.displayExercise();
        }
    }
}

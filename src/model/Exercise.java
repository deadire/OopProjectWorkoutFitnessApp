package model;

public class Exercise {
    private String name;
    private String muscleGroup;
    private int sets;
    private int reps;
    private double weight;  // in kilograms, can be 0 if bodyweight

    public Exercise(String name, String muscleGroup, int sets, int reps, double weight) {
        this.name = name;
        this.muscleGroup = muscleGroup;
        this.sets = sets;
        this.reps = reps;
        this.weight = weight;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getMuscleGroup() {
        return muscleGroup;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public double getWeight() {
        return weight;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    // Display exercise details
    public void displayExercise() {
        System.out.println("model.Exercise: " + name);
        System.out.println("Muscle Group: " + muscleGroup);
        System.out.println("Sets: " + sets);
        System.out.println("Reps: " + reps);
        System.out.println("Weight: " + weight + " kg");
        System.out.println();
    }
}

package model;

public class ProgressLog {
    private String date;
    private double weight;
    private int caloriesBurned;

    public ProgressLog(String date, double weight, int caloriesBurned) {
        this.date = date;
        this.weight = weight;
        this.caloriesBurned = caloriesBurned;
    }

    public void displayLog() {
        System.out.println("Date: " + date);
        System.out.println("Weight: " + weight + " kg");
        System.out.println("Calories Burned: " + caloriesBurned);
    }
}

    


package model;

public class FitnessGoal {
    private String goalId;
    private String memberId;
    private String description;
    private String targetDate; // Format: "YYYY-MM-DD"
    private double progress; // 0.0 to 100.0 (%)

    public FitnessGoal(String goalId, String memberId, String description, String targetDate) {
        this.goalId = goalId;
        this.memberId = memberId;
        this.description = description;
        this.targetDate = targetDate;
        this.progress = 0.0;
    }

    // Getters and setters
    public String getGoalId() {
        return goalId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getDescription() {
        return description;
    }

    public String getTargetDate() {
        return targetDate;
    }

    public double getProgress() {
        return progress;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }

    public void updateProgress(double newProgress) {
        if (newProgress >= 0.0 && newProgress <= 100.0) {
            this.progress = newProgress;
        } else {
            System.out.println("Progress must be between 0 and 100");
        }
    }

    // Display goal info
    public void displayGoal() {
        System.out.println("Goal ID: " + goalId);
        System.out.println("Description: " + description);
        System.out.println("Target Date: " + targetDate);
        System.out.println("Progress: " + progress + "%");
    }
}

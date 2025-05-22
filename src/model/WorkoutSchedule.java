package model;

import java.util.ArrayList;
import java.util.List;

public class WorkoutSchedule {
    private String scheduleId;
    private String memberId;   // Which member owns this schedule
    private String trainerId;  // Optional: assigned trainer
    private List<WorkoutSession> sessions;

    public WorkoutSchedule(String scheduleId, String memberId, String trainerId) {
        this.scheduleId = scheduleId;
        this.memberId = memberId;
        this.trainerId = trainerId;
        this.sessions = new ArrayList<>();
    }

    // Getters and setters
    public String getScheduleId() {
        return scheduleId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(String trainerId) {
        this.trainerId = trainerId;
    }

    public List<WorkoutSession> getSessions() {
        return sessions;
    }

    // Add a workout session
    public void addSession(WorkoutSession session) {
        sessions.add(session);
    }

    // Remove a workout session by date (or customize)
    public void removeSession(String date) {
        sessions.removeIf(s -> s.getDate().equals(date));
    }

    // Display all sessions
    public void displaySchedule() {
        System.out.println("Workout Schedule for model.Member ID: " + memberId);
        for (WorkoutSession session : sessions) {
            session.displaySession();
            System.out.println("----------------------");
        }
    }
}

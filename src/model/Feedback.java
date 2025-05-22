package model;

import java.time.LocalDate;

public class Feedback {
    private String feedbackId;
    private String memberId;
    private String content;
    private LocalDate date;

    public Feedback(String feedbackId, String memberId, String content, LocalDate date) {
        this.feedbackId = feedbackId;
        this.memberId = memberId;
        this.content = content;
        this.date = date;
    }

    // Getters and setters
    public String getFeedbackId() {
        return feedbackId;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Display feedback info
    public void displayFeedback() {
        System.out.println("model.Feedback ID: " + feedbackId);
        System.out.println("model.Member ID: " + memberId);
        System.out.println("Date: " + date);
        System.out.println("Content: " + content);
    }
}

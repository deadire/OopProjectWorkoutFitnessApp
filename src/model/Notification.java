package model;

import java.time.LocalDate;

public class Notification {
    private String notificationId;
    private String recipientId;  // Could be memberId or trainerId
    private String message;
    private LocalDate date;

    public Notification(String notificationId, String recipientId, String message, LocalDate date) {
        this.notificationId = notificationId;
        this.recipientId = recipientId;
        this.message = message;
        this.date = date;
    }

    // Getters and setters
    public String getNotificationId() {
        return notificationId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public String getMessage() {
        return message;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Display notification details
    public void displayNotification() {
        System.out.println("model.Notification ID: " + notificationId);
        System.out.println("Recipient ID: " + recipientId);
        System.out.println("Date: " + date);
        System.out.println("Message: " + message);
    }
}

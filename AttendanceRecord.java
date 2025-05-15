import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;

public class AttendanceRecord {
    private String recordId;
    private String memberId;
    private LocalDate date;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;

    public AttendanceRecord(String recordId, String memberId, LocalDate date, LocalTime checkInTime, LocalTime checkOutTime) {
        this.recordId = recordId;
        this.memberId = memberId;
        this.date = date;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }

    // Getters and setters
    public String getRecordId() {
        return recordId;
    }

    public String getMemberId() {
        return memberId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getCheckInTime() {
        return checkInTime;
    }

    public LocalTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    // Calculate duration of attendance in minutes
    public long getDurationMinutes() {
        if (checkInTime != null && checkOutTime != null) {
            return Duration.between(checkInTime, checkOutTime).toMinutes();
        }
        return 0;
    }

    // Display attendance details
    public void displayAttendance() {
        System.out.println("Record ID: " + recordId);
        System.out.println("Member ID: " + memberId);
        System.out.println("Date: " + date);
        System.out.println("Check-in Time: " + checkInTime);
        System.out.println("Check-out Time: " + checkOutTime);
        System.out.println("Duration: " + getDurationMinutes() + " minutes");
    }
}

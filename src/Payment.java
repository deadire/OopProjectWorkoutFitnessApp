import java.time.LocalDate;

public class Payment {
    private String paymentId;
    private String memberId;
    private double amount;
    private LocalDate paymentDate;
    private String paymentMethod;  // e.g. "Credit Card", "Cash"

    public Payment(String paymentId, String memberId, double amount, LocalDate paymentDate, String paymentMethod) {
        this.paymentId = paymentId;
        this.memberId = memberId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
    }

    // Getters and setters
    public String getPaymentId() {
        return paymentId;
    }

    public String getMemberId() {
        return memberId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    // Display payment info
    public void displayPayment() {
        System.out.println("Payment ID: " + paymentId);
        System.out.println("Member ID: " + memberId);
        System.out.println("Amount: $" + amount);
        System.out.println("Date: " + paymentDate);
        System.out.println("Method: " + paymentMethod);
    }
}

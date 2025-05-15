import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MembershipPlan {
    private String planId;
    private String planName;
    private double monthlyFee;
    private int durationMonths;
    private String description;
    private boolean includesTrainer;

    public MembershipPlan(String planId, String planName, double monthlyFee, 
                         int durationMonths, String description, boolean includesTrainer) {
        this.planId = planId;
        this.planName = planName;
        this.monthlyFee = monthlyFee;
        this.durationMonths = durationMonths;
        this.description = description;
        this.includesTrainer = includesTrainer;
    }

    public void displayPlan() {
        System.out.println("Plan ID: " + planId);
        System.out.println("Plan Name: " + planName);
        System.out.println("Monthly Fee: $" + monthlyFee);
        System.out.println("Duration: " + durationMonths + " months");
        System.out.println("Description: " + description);
        System.out.println("Includes Trainer: " + (includesTrainer ? "Yes" : "No"));
    }

    // Escape commas to avoid split errors when saving/loading
    private String escapeCommas(String text) {
        return text.replace(",", "<comma>");
    }

    private String unescapeCommas(String text) {
        return text.replace("<comma>", ",");
    }

    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(planId + "," + planName + "," + monthlyFee + "," + 
                         durationMonths + "," + escapeCommas(description) + "," + includesTrainer);
            writer.newLine();
            System.out.println("Membership plan saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving membership plan: " + e.getMessage());
        }
    }

    public static List<MembershipPlan> loadFromFile(String filename) {
        List<MembershipPlan> plans = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    MembershipPlan plan = new MembershipPlan(
                        parts[0],                      // planId
                        parts[1],                      // planName
                        Double.parseDouble(parts[2]),  // monthlyFee
                        Integer.parseInt(parts[3]),    // durationMonths
                        parts[4].replace("<comma>", ","), // description
                        Boolean.parseBoolean(parts[5]) // includesTrainer
                    );
                    plans.add(plan);
                }
            }
            System.out.println("Membership plans loaded from file.");
        } catch (IOException e) {
            System.out.println("Error loading membership plans: " + e.getMessage());
        }
        return plans;
    }

    // Getters and setters if you want (optional)
    public String getPlanId() {
        return planId;
    }
    public String getPlanName() {
        return planName;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setDurationMonths(int durationMonths) {
        this.durationMonths = durationMonths;
    }
    public void setIncludesTrainer(boolean includesTrainer) {
        this.includesTrainer = includesTrainer;
    }
    public void setMonthlyFee(double monthlyFee) {
        this.monthlyFee = monthlyFee;
    }
    public void setPlanId(String planId) {
        this.planId = planId;
    }
    public void setPlanName(String planName) {
        this.planName = planName;
    }
    public String getDescription() {
        return description;
    }
    public int getDurationMonths() {
        return durationMonths;
    }
    public double getMonthlyFee() {
        return monthlyFee;
    }
    
}

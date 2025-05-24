package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Member extends User {
    private int age;
    private double weight;
    private double height;
    private String goal;
    private MembershipPlan membershipPlan;

    // Old constructor - without membershipPlan (for backward compatibility)
    public Member(String id, String name, int age, double weight, double height, String goal) {
        this(id, name, age, weight, height, goal, null);
    }

    // New constructor with membershipPlan
    public Member(String id, String name, int age, double weight, double height, String goal, MembershipPlan membershipPlan) {
        super(id, name);
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.goal = goal;
        this.membershipPlan = membershipPlan;
    }
    public String toFileString() {
        return id + "," + name + "," + age + "," + weight + "," + height + "," + goal + "," +
                (membershipPlan != null ? membershipPlan.getPlanId() : "none");
    }
    // Getters and Setters
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public MembershipPlan getMembershipPlan() {
        return membershipPlan;
    }

    public void setMembershipPlan(MembershipPlan membershipPlan) {
        this.membershipPlan = membershipPlan;
    }

    @Override
    public void displayInfo() {
        System.out.println("model.Member Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Age: " + age);
        System.out.println("Weight: " + weight + " kg");
        System.out.println("Height: " + height + " cm");
        System.out.println("Goal: " + goal);
        if (membershipPlan != null) {
            System.out.println("Membership Plan:");
            membershipPlan.displayPlan();
        } else {
            System.out.println("Membership Plan: None");
        }
    }

    // Save member to file (append mode)
    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            // Format: id,name,age,weight,height,goal,planId (or "null")
            writer.write(id + "," + name + "," + age + "," + weight + "," + height + "," + goal + "," +
                (membershipPlan != null ? membershipPlan.getPlanId() : "null"));
            writer.newLine();
            System.out.println("model.Member saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving member: " + e.getMessage());
        }
    }

    // Load members from file and link membership plans from a list
    public static List<Member> loadFromFile(String filename, List<MembershipPlan> plans) {
        List<Member> members = new ArrayList<>();
        File file = new File(filename);

        // ✅ Auto-create file if it doesn't exist
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Created missing file: " + file.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Unable to create file: " + filename + " - " + e.getMessage());
                return members;
            }
            return members; // File just created, so return empty list
        }

        // ✅ Now read the file safely
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    MembershipPlan plan = null;
                    String planId = parts[6];
                    if (!planId.equals("null")) {
                        for (MembershipPlan p : plans) {
                            if (p.getPlanId().equals(planId)) {
                                plan = p;
                                break;
                            }
                        }
                    }

                    Member member = new Member(
                            parts[0],
                            parts[1],
                            Integer.parseInt(parts[2]),
                            Double.parseDouble(parts[3]),
                            Double.parseDouble(parts[4]),
                            parts[5],
                            plan
                    );
                    members.add(member);
                }
            }
            System.out.println("Members loaded from: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error loading members: " + e.getMessage());
        }

        return members;
    }

}

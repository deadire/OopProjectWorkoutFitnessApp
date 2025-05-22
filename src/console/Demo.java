package console;

import model.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Demo {
    private static Admin admin;
    private static List<MembershipPlan> membershipPlans = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeSystem();
        showMainMenu();
    }

    private static void initializeSystem() {
        // Create sample membership plans
        membershipPlans.add(new MembershipPlan("P001", "Basic", 29.99, 1, "Access to gym equipment", false));
        membershipPlans.add(new MembershipPlan("P002", "Premium", 59.99, 1, "Access to equipment + 2 trainer sessions", true));
        membershipPlans.add(new MembershipPlan("P003", "Annual", 499.99, 12, "Year-long access with all benefits", true));

        // Create admin account
        admin = new Admin("A001", "model.Admin model.User", "admin", "admin123");
        
        // Load initial data from files if they exist
        loadInitialData();
    }

    private static void loadInitialData() {
        // Load members with their membership plans
        admin.loadMembersFromFile("members.txt");
        
        // Load trainers
        admin.loadTrainersFromFile("trainers.txt");
    }

    private static void showMainMenu() {
        while (true) {
            System.out.println("\n=== Gym Management System ===");
            System.out.println("1. model.Admin Login");
            System.out.println("2. Exit");
            System.out.print("Select option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    System.out.println("Exiting system...");
                    saveAllData();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void adminLogin() {
        System.out.println("\n=== model.Admin Login ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        if (admin.getUsername().equals(username) && admin.checkPassword(password)) {
            System.out.println("Login successful!");
            showAdminMenu();
        } else {
            System.out.println("Invalid credentials. Try again.");
        }
    }

    private static void showAdminMenu() {
        while (true) {
            System.out.println("\n=== model.Admin Dashboard ===");
            System.out.println("1. Manage Members");
            System.out.println("2. Manage Trainers");
            System.out.println("3. View Membership Plans");
            System.out.println("4. Generate Reports");
            System.out.println("5. Save All Data");
            System.out.println("6. Logout");
            System.out.print("Select option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    manageMembers();
                    break;
                case 2:
                    manageTrainers();
                    break;
                case 3:
                    viewMembershipPlans();
                    break;
                case 4:
                    generateReports();
                    break;
                case 5:
                    saveAllData();
                    System.out.println("All data saved successfully.");
                    break;
                case 6:
                    return; // Return to main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void manageMembers() {
        while (true) {
            System.out.println("\n=== model.Member Management ===");
            System.out.println("1. Add New model.Member");
            System.out.println("2. View All Members");
            System.out.println("3. Find model.Member by ID");
            System.out.println("4. Remove model.Member");
            System.out.println("5. Back to model.Admin Menu");
            System.out.print("Select option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    addNewMember();
                    break;
                case 2:
                    admin.listMembers();
                    break;
                case 3:
                    findMemberById();
                    break;
                case 4:
                    removeMember();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addNewMember() {
        System.out.println("\n=== Add New model.Member ===");
        System.out.print("model.Member ID: ");
        String id = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        System.out.print("Weight (kg): ");
        double weight = scanner.nextDouble();
        System.out.print("Height (cm): ");
        double height = scanner.nextDouble();
        scanner.nextLine(); // consume newline
        System.out.print("Goal: ");
        String goal = scanner.nextLine();
        
        // Show available membership plans
        System.out.println("\nAvailable Membership Plans:");
        for (int i = 0; i < membershipPlans.size(); i++) {
            System.out.println((i + 1) + ". " + membershipPlans.get(i).getPlanName());
        }
        System.out.print("Select plan (0 for none): ");
        int planChoice = scanner.nextInt();
        scanner.nextLine();
        
        MembershipPlan selectedPlan = (planChoice > 0 && planChoice <= membershipPlans.size()) 
            ? membershipPlans.get(planChoice - 1) 
            : null;
        
        Member newMember = new Member(id, name, age, weight, height, goal, selectedPlan);
        admin.addMember(newMember);
        System.out.println("model.Member added successfully!");
    }

    private static void findMemberById() {
        System.out.print("\nEnter model.Member ID: ");
        String id = scanner.nextLine();
        Member member = admin.getMemberById(id);
        
        if (member != null) {
            member.displayInfo();
            showMemberOptions(member);
        } else {
            System.out.println("model.Member not found.");
        }
    }

    private static void showMemberOptions(Member member) {
        System.out.println("\nmodel.Member Options:");
        System.out.println("1. Record Attendance");
        System.out.println("2. Create Workout Plan");
        System.out.println("3. View Progress");
        System.out.println("4. Back to model.Member Management");
        System.out.print("Select option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1:
                recordAttendance(member);
                break;
            case 2:
                createWorkoutPlan(member);
                break;
            case 3:
                viewMemberProgress(member);
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void recordAttendance(Member member) {
        System.out.println("\n=== Record Attendance ===");
        System.out.println("Recording attendance for: " + member.getName());
        
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        
        AttendanceRecord record = new AttendanceRecord(
            "AR" + System.currentTimeMillis(),
            member.getId(),
            today,
            now,
            null
        );
        
        System.out.println("Check-in recorded at: " + now);
        System.out.print("Record check-out now? (y/n): ");
        String choice = scanner.nextLine();
        
        if (choice.equalsIgnoreCase("y")) {
            LocalTime checkoutTime = LocalTime.now();
            record.setCheckOutTime(checkoutTime);
            System.out.println("Check-out recorded at: " + checkoutTime);
            System.out.println("Duration: " + record.getDurationMinutes() + " minutes");
        }
        
        System.out.println("Attendance recorded successfully!");
    }

    private static void createWorkoutPlan(Member member) {
        System.out.println("\n=== Create Workout Plan ===");
        System.out.print("Plan Name: ");
        String planName = scanner.nextLine();
        
        List<Exercise> exercises = new ArrayList<>();
        boolean addingExercises = true;
        
        while (addingExercises) {
            System.out.println("\nAdd model.Exercise:");
            System.out.print("model.Exercise Name: ");
            String name = scanner.nextLine();
            System.out.print("Muscle Group: ");
            String muscleGroup = scanner.nextLine();
            System.out.print("Sets: ");
            int sets = scanner.nextInt();
            System.out.print("Reps: ");
            int reps = scanner.nextInt();
            System.out.print("Weight (kg): ");
            double weight = scanner.nextDouble();
            scanner.nextLine();
            
            exercises.add(new Exercise(name, muscleGroup, sets, reps, weight));
            
            System.out.print("Add another exercise? (y/n): ");
            addingExercises = scanner.nextLine().equalsIgnoreCase("y");
        }
        
        WorkoutPlan plan = new WorkoutPlan(planName, exercises.toArray(new Exercise[0]));
        System.out.println("\nWorkout Plan Created:");
        plan.displayPlan();
    }

    private static void viewMemberProgress(Member member) {
        System.out.println("\n=== model.Member Progress ===");
        System.out.println("model.Member: " + member.getName());
        System.out.println("Current Weight: " + member.getWeight() + " kg");
        System.out.println("Height: " + member.getHeight() + " cm");
        
        // In a real system, you would load actual progress logs here
        System.out.println("\nSample Progress Logs:");
        ProgressLog log1 = new ProgressLog("2025-01-01", 85.5, 350);
        ProgressLog log2 = new ProgressLog("2025-02-01", 83.2, 400);
        ProgressLog log3 = new ProgressLog("2025-03-01", 81.8, 450);
        
        log1.displayLog();
        System.out.println("-----");
        log2.displayLog();
        System.out.println("-----");
        log3.displayLog();
    }

    private static void removeMember() {
        System.out.print("\nEnter model.Member ID to remove: ");
        String id = scanner.nextLine();
        admin.removeMember(id);
    }

    private static void manageTrainers() {
        while (true) {
            System.out.println("\n=== model.Trainer Management ===");
            System.out.println("1. Add New model.Trainer");
            System.out.println("2. View All Trainers");
            System.out.println("3. Find model.Trainer by ID");
            System.out.println("4. Remove model.Trainer");
            System.out.println("5. Back to model.Admin Menu");
            System.out.print("Select option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    addNewTrainer();
                    break;
                case 2:
                    admin.listTrainers();
                    break;
                case 3:
                    findTrainerById();
                    break;
                case 4:
                    removeTrainer();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addNewTrainer() {
        System.out.println("\n=== Add New model.Trainer ===");
        System.out.print("model.Trainer ID: ");
        String id = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Specialty: ");
        String specialty = scanner.nextLine();
        
        Trainer newTrainer = new Trainer(id, name, specialty);
        admin.addTrainer(newTrainer);
        System.out.println("model.Trainer added successfully!");
    }

    private static void findTrainerById() {
        System.out.print("\nEnter model.Trainer ID: ");
        String id = scanner.nextLine();
        Trainer trainer = admin.getTrainerById(id);
        
        if (trainer != null) {
            trainer.displayInfo();
        } else {
            System.out.println("model.Trainer not found.");
        }
    }

    private static void removeTrainer() {
        System.out.print("\nEnter model.Trainer ID to remove: ");
        String id = scanner.nextLine();
        admin.removeTrainer(id);
    }

    private static void viewMembershipPlans() {
        System.out.println("\n=== Membership Plans ===");
        for (MembershipPlan plan : membershipPlans) {
            plan.displayPlan();
            System.out.println("----------------------");
        }
    }

    private static void generateReports() {
        System.out.println("\n=== Reports ===");
        System.out.println("1. model.Member List");
        System.out.println("2. model.Trainer List");
        System.out.println("3. Membership Statistics");
        System.out.println("4. Back to model.Admin Menu");
        System.out.print("Select report: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1:
                System.out.println("\n=== model.Member List Report ===");
                admin.listMembers();
                break;
            case 2:
                System.out.println("\n=== model.Trainer List Report ===");
                admin.listTrainers();
                break;
            case 3:
                showMembershipStatistics();
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static void showMembershipStatistics() {
        System.out.println("\n=== Membership Statistics ===");
        int totalMembers = admin.getMembers().size();
        int withPlan = 0;
        
        for (Member member : admin.getMembers()) {
            if (member.getMembershipPlan() != null) {
                withPlan++;
            }
        }
        
        System.out.println("Total Members: " + totalMembers);
        System.out.println("Members with Plan: " + withPlan);
        System.out.println("Members without Plan: " + (totalMembers - withPlan));
        System.out.printf("Percentage with Plan: %.1f%%\n", (withPlan * 100.0 / totalMembers));
    }

    private static void saveAllData() {
        admin.saveToFile("admin.txt");
        admin.saveMembersToFile("members.txt");
        admin.saveTrainersToFile("trainers.txt");
        
        // Save membership plans
        for (MembershipPlan plan : membershipPlans) {
            plan.saveToFile("plans.txt");
        }
    }
}
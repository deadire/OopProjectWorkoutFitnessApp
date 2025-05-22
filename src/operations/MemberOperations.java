package operations;

import model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberOperations {
    private Admin admin;
    private List<MembershipPlan> membershipPlans;
    private Scanner scanner;

    public MemberOperations(Admin admin, List<MembershipPlan> membershipPlans, Scanner scanner) {
        this.admin = admin;
        this.membershipPlans = membershipPlans;
        this.scanner = scanner;
    }

    public void manageMembers() {
        while (true) {
            System.out.println("\n=== Member Management ===");
            System.out.println("1. Add New Member");
            System.out.println("2. View All Members");
            System.out.println("3. Find Member by ID");
            System.out.println("4. Remove Member");
            System.out.println("5. Back to Admin Menu");
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

    private void addNewMember() {
        System.out.println("\n=== Add New Member ===");
        System.out.print("Member ID: ");
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
        System.out.println("Member added successfully!");
    }

    private void findMemberById() {
        System.out.print("\nEnter Member ID: ");
        String id = scanner.nextLine();
        Member member = admin.getMemberById(id);

        if (member != null) {
            member.displayInfo();
            showMemberOptions(member);
        } else {
            System.out.println("Member not found.");
        }
    }

    private void showMemberOptions(Member member) {
        System.out.println("\nMember Options:");
        System.out.println("1. Record Attendance");
        System.out.println("2. Create Workout Plan");
        System.out.println("3. View Progress");
        System.out.println("4. Back to Member Management");
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

    private void recordAttendance(Member member) {
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

    private void createWorkoutPlan(Member member) {
        System.out.println("\n=== Create Workout Plan ===");
        System.out.print("Plan Name: ");
        String planName = scanner.nextLine();

        List<Exercise> exercises = new ArrayList<>();
        boolean addingExercises = true;

        while (addingExercises) {
            System.out.println("\nAdd Exercise:");
            System.out.print("Exercise Name: ");
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

    private void viewMemberProgress(Member member) {
        System.out.println("\n=== Member Progress ===");
        System.out.println("Member: " + member.getName());
        System.out.println("Current Weight: " + member.getWeight() + " kg");
        System.out.println("Height: " + member.getHeight() + " cm");

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

    private void removeMember() {
        System.out.print("\nEnter Member ID to remove: ");
        String id = scanner.nextLine();
        admin.removeMember(id);
    }
}
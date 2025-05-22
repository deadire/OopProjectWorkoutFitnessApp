package console;

import model.Admin;
import model.MembershipPlan;
import operations.MemberOperations;
import operations.TrainerOperations;
import operations.ReportGenerator;
import java.util.List;
import java.util.Scanner;

public class MenuHandler {
    private Admin admin;
    private List<MembershipPlan> membershipPlans;
    private Scanner scanner;
    private MemberOperations memberOps;
    private TrainerOperations trainerOps;
    private ReportGenerator reportGenerator;

    public MenuHandler(Admin admin, List<MembershipPlan> membershipPlans, Scanner scanner) {
        this.admin = admin;
        this.membershipPlans = membershipPlans;
        this.scanner = scanner;
        this.memberOps = new MemberOperations(admin, membershipPlans, scanner);
        this.trainerOps = new TrainerOperations(admin, scanner);
        this.reportGenerator = new ReportGenerator(admin,scanner);
    }

    public void showMainMenu() {
        while (true) {
            System.out.println("\n=== Gym Management System ===");
            System.out.println("1. Admin Login");
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

    private void adminLogin() {
        System.out.println("\n=== Admin Login ===");
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

    private void showAdminMenu() {
        while (true) {
            System.out.println("\n=== Admin Dashboard ===");
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
                    memberOps.manageMembers();
                    break;
                case 2:
                    trainerOps.manageTrainers();
                    break;
                case 3:
                    viewMembershipPlans();
                    break;
                case 4:
                    reportGenerator.generateReports();
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

    private void viewMembershipPlans() {
        System.out.println("\n=== Membership Plans ===");
        for (MembershipPlan plan : membershipPlans) {
            plan.displayPlan();
            System.out.println("----------------------");
        }
    }

    private void saveAllData() {
        admin.saveToFile("admin.txt");
        admin.saveMembersToFile("members.txt");
        admin.saveTrainersToFile("trainers.txt");

        for (MembershipPlan plan : membershipPlans) {
            plan.saveToFile("plans.txt");
        }
    }
}
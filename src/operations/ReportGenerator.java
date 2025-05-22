package operations;
import model.Admin;
import model.Member;

import java.util.Scanner;

public class ReportGenerator {
    private Admin admin;
    private Scanner scanner;

    public ReportGenerator(Admin admin, Scanner scanner) {
        this.admin = admin;
        this.scanner = scanner;
    }

    public void generateReports() {
        while (true) {
            System.out.println("\n=== Reports ===");
            System.out.println("1. Member List");
            System.out.println("2. Trainer List");
            System.out.println("3. Membership Statistics");
            System.out.println("4. Back to Admin Menu");
            System.out.print("Select report: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n=== Member List Report ===");
                    admin.listMembers();
                    break;
                case 2:
                    System.out.println("\n=== Trainer List Report ===");
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
    }

    private void showMembershipStatistics() {
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
}
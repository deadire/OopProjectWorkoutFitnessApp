package console;

import model.Admin;
import model.MembershipPlan;
import java.util.*; // For List, Scanner, etc.

public class Demo {
    private static Admin admin;
    private static List<MembershipPlan> membershipPlans = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static MenuHandler menuHandler;

    public static void main(String[] args) {
        initializeSystem();
        menuHandler = new MenuHandler(admin, membershipPlans, scanner);
        menuHandler.showMainMenu();
    }

    private static void initializeSystem() {
        // Create sample membership plans
        membershipPlans.add(new MembershipPlan("P001", "Basic", 29.99, 1, "Access to gym equipment", false));
        membershipPlans.add(new MembershipPlan("P002", "Premium", 59.99, 1, "Access to equipment + 2 trainer sessions", true));
        membershipPlans.add(new MembershipPlan("P003", "Annual", 499.99, 12, "Year-long access with all benefits", true));

        // Create admin account
        admin = new Admin("A001", "Admin User", "admin", "admin123");

        // Load initial data from files if they exist
        loadInitialData();
    }

    private static void loadInitialData() {
        admin.loadMembersFromFile("members.txt");
        admin.loadTrainersFromFile("trainers.txt");
    }
}

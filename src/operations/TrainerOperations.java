package operations;
import model.Admin;
import model.Trainer;

import java.util.Scanner;

public class TrainerOperations {
    private Admin admin;
    private Scanner scanner;

    public TrainerOperations(Admin admin, Scanner scanner) {
        this.admin = admin;
        this.scanner = scanner;
    }

    public void manageTrainers() {
        while (true) {
            System.out.println("\n=== Trainer Management ===");
            System.out.println("1. Add New Trainer");
            System.out.println("2. View All Trainers");
            System.out.println("3. Find Trainer by ID");
            System.out.println("4. Remove Trainer");
            System.out.println("5. Back to Admin Menu");
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

    private void addNewTrainer() {
        System.out.println("\n=== Add New Trainer ===");
        System.out.print("Trainer ID: ");
        String id = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Specialty: ");
        String specialty = scanner.nextLine();

        Trainer newTrainer = new Trainer(id, name, specialty);
        admin.addTrainer(newTrainer);
        System.out.println("Trainer added successfully!");
    }

    private void findTrainerById() {
        System.out.print("\nEnter Trainer ID: ");
        String id = scanner.nextLine();
        Trainer trainer = admin.getTrainerById(id);

        if (trainer != null) {
            trainer.displayInfo();
        } else {
            System.out.println("Trainer not found.");
        }
    }

    private void removeTrainer() {
        System.out.print("\nEnter Trainer ID to remove: ");
        String id = scanner.nextLine();
        admin.removeTrainer(id);
    }
}
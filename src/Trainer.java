import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Trainer extends User {
    private String specialty;

    public Trainer(String id, String name, String specialty) {
        super(id, name);
        this.specialty = specialty;
    }

    // Getter and Setter
    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public void displayInfo() {
        System.out.println("Trainer Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Specialty: " + specialty);
    }

    // Save a single trainer (append mode)
    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            // Format: id,name,specialty
            writer.write(id + "," + name + "," + specialty);
            writer.newLine();
            System.out.println("Trainer saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving trainer: " + e.getMessage());
        }
    }

    // Load all trainers from file
    public static List<Trainer> loadFromFile(String filename) {
        List<Trainer> trainers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    Trainer trainer = new Trainer(parts[0], parts[1], parts[2]);
                    trainers.add(trainer);
                }
            }
            System.out.println("Trainers loaded from file.");
        } catch (IOException e) {
            System.out.println("Error loading trainers: " + e.getMessage());
        }
        return trainers;
    }
}

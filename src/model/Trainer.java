package model;

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
        System.out.println("model.Trainer Name: " + name);
        System.out.println("ID: " + id);
        System.out.println("Specialty: " + specialty);
    }

    // Save a single trainer (append mode)
    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            // Format: id,name,specialty
            writer.write(id + "," + name + "," + specialty);
            writer.newLine();
            System.out.println("model.Trainer saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving trainer: " + e.getMessage());
        }
    }

    // Load all trainers from file
    public static List<Trainer> loadFromFile(String filename) {
        List<Trainer> trainers = new ArrayList<>();
        File file = new File(filename);

        // ✅ Create file if it doesn't exist
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Created missing file: " + file.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Unable to create " + filename + ": " + e.getMessage());
            }
            return trainers;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Trainer trainer = Trainer.fromFileString(line); // assumes this method exists
                if (trainer != null) {
                    trainers.add(trainer);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading trainers: " + e.getMessage());
        }

        return trainers;
    }
    public static Trainer fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length >= 3) {
            String id = parts[0];
            String name = parts[1];
            String specialty = parts[2];
            return new Trainer(id, name, specialty);
        }
        return null;
    }

}

package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Admin extends User {
    private String username;
    private String password;

    private List<Member> members;
    private List<Trainer> trainers;

    public Admin(String id, String name, String username, String password) {
        super(id, name);
        this.username = username;
        this.password = password;
        this.members = new ArrayList<>();
        this.trainers = new ArrayList<>();
    }

    // Authentication
    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    // Getters
    public String getUsername() {
        return username;
    }
    public List<Member> getMembers() {
        return members;
    }
    

    // ==== model.Member Management ====
    public void addMember(Member member) {
        members.add(member);
        System.out.println("model.Member added: " + member.getName());
    }

    public void removeMember(String memberId) {
        members.removeIf(m -> m.getId().equals(memberId));
        System.out.println("model.Member with ID " + memberId + " removed.");
    }

    public Member getMemberById(String memberId) {
        for (Member m : members) {
            if (m.getId().equals(memberId)) {
                return m;
            }
        }
        return null;
    }

    public void listMembers() {
        System.out.println("=== Members ===");
        for (Member m : members) {
            System.out.println("- " + m.getName() + " (ID: " + m.getId() + ")");
        }
    }

    // ==== model.Trainer Management ====
    public void addTrainer(Trainer trainer) {
        trainers.add(trainer);
        System.out.println("model.Trainer added: " + trainer.getName());
    }

    public void removeTrainer(String trainerId) {
        trainers.removeIf(t -> t.getId().equals(trainerId));
        System.out.println("model.Trainer with ID " + trainerId + " removed.");
    }

    public Trainer getTrainerById(String trainerId) {
        for (Trainer t : trainers) {
            if (t.getId().equals(trainerId)) {
                return t;
            }
        }
        return null;
    }

    public void listTrainers() {
        System.out.println("=== Trainers ===");
        for (Trainer t : trainers) {
            System.out.println("- " + t.getName() + " (ID: " + t.getId() + ")");
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("model.Admin Name: " + name);
        System.out.println("Username: " + username);
    }

    // ==== FILE HANDLING ====

    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(id + "," + name + "," + username + "," + password);
            writer.newLine();
            System.out.println("model.Admin saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving admin: " + e.getMessage());
        }
    }

    public static List<Admin> loadFromFile(String filename) {
        List<Admin> admins = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    Admin admin = new Admin(parts[0], parts[1], parts[2], parts[3]) {
                        @Override
                        public void displayInfo() {
                            super.displayInfo();
                        }
                    };
                    admins.add(admin);
                }
            }
            System.out.println("Admins loaded from file.");
        } catch (IOException e) {
            System.out.println("Error loading admins: " + e.getMessage());
        }
        return admins;
    }

    public void saveMembersToFile(String filename) {
        for (Member m : members) {
            m.saveToFile(filename);
        }
    }

    public void loadMembersFromFile(String filename) {
        List<MembershipPlan> plans = MembershipPlan.loadFromFile("plans.txt");
        this.members = Member.loadFromFile("members.txt", plans);


    }

    public void saveTrainersToFile(String filename) {
        for (Trainer t : trainers) {
            t.saveToFile(filename);
        }
    }

    public void loadTrainersFromFile(String filename) {
        this.trainers = Trainer.loadFromFile(filename);
    }
}

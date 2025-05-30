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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/" + filename, true))) {
            writer.write(id + "," + name + "," + username + "," + password);
            writer.newLine();
            System.out.println("model.Admin saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving admin: " + e.getMessage());
        }
    }

    public static List<MembershipPlan> loadFromFile(String filename) {
        List<MembershipPlan> plans = new ArrayList<>();
        File file = new File("data/" + filename);

        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Created missing file: " + file.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Unable to create " + filename);
            }
            return plans; // return empty list
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                plans.add(MembershipPlan.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading membership plans: " + e.getMessage());
        }
        return plans;
    }

    // Save all members (overwrite mode)
    public void saveMembersToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/" + filename))) {
            for (Member m : members) {
                writer.write(m.toFileString());
                writer.newLine();
            }
            System.out.println("Members saved to: " + new File("data/" + filename).getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error saving members: " + e.getMessage());
        }
    }

    // Append only a single new member
    public void saveNewMemberToFile(String filename, Member member) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/" + filename, true))) {
            writer.write(member.toFileString());
            writer.newLine();
            System.out.println("New member appended to: " + new File("data/" + filename).getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error saving new member: " + e.getMessage());
        }
    }

    public void loadMembersFromFile(String filename) {
        List<MembershipPlan> plans = MembershipPlan.loadFromFile("plans.txt");
        this.members = Member.loadFromFile("data/" + filename, plans);
        System.out.println("Members loaded from: " + new File("data/" + filename).getAbsolutePath());
    }

    public void saveTrainersToFile(String filename) {
        for (Trainer t : trainers) {
            t.saveToFile("data/" + filename);
        }
    }

    public void loadTrainersFromFile(String filename) {
        this.trainers = Trainer.loadFromFile("data/" + filename);
    }

    public static Admin fromFileString(String line) {
        String[] parts = line.split(",");
        if (parts.length >= 4) {
            return new Admin(parts[0], parts[1], parts[2], parts[3]);
        }
        return null;
    }
}

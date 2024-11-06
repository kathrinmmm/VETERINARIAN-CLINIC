package service;

import Repository.IRepository;
import model.User;
import model.Pet;
import model.Veterinarian;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserService {
    private Map<String, User> users;
    private Scanner scanner;

    public UserService(IRepository<User> userRepository) {
        users = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public UserService() {
        users = new HashMap<>();
        scanner = new Scanner(System.in);  // Inițializare scanner
    }

    public void registerUser() {
        System.out.println("--Registration Data--");

        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Last name: ");
        String lastName = scanner.nextLine();

        System.out.print("First name: ");
        String firstName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        int role = 0;
        while (role != 1 && role != 2) {
            System.out.print("Role (1 for Pet, 2 for Veterinarian): ");
            if (scanner.hasNextInt()) {
                role = scanner.nextInt();
                scanner.nextLine();
                if (role != 1 && role != 2) {
                    System.out.println("Please choose between 1 (for Pet) and 2 (for Veterinarian)");
                }
            } else {
                System.out.println("Invalid input. Please enter 1 or 2.");
                scanner.nextLine();
            }
        }

        User user;
        if (role == 1) {
            PetService petService = new PetService();
            user = new Pet(id, firstName, lastName, email, username, password, "PetName", "BirthDate", "M", "Species", "Breed");
            petService.registerPet(user); // Transmitem user-ul ca parametru
        } else {
            System.out.print("Specialization: ");
            String specialization = scanner.nextLine();
            user = new Veterinarian(id, firstName, lastName, email, username, password, specialization);
        }

        users.put(username, user);
        System.out.println("User has been successfully registered.");
    }

    public User loginUser() {
        System.out.println("--Log In Data--");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Log In successful");
            return user;
        } else {
            System.out.println("Wrong username or password.");
            return null;
        }
    }

    public void resetPassword() {
        System.out.println("-- Resetting Password --");

        User user = null;
        while (user == null) {
            System.out.print("Username: ");
            String username = scanner.nextLine();

            user = users.get(username);
            if (user == null) {
                System.out.println("Wrong username. Please try again.");
            }
        }

        System.out.print("New password: ");
    }
}
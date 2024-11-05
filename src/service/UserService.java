package service;

import model.User;
import model.Pet;
import model.Veterinarian;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserService {
    private Map<String, User> users;
    private Scanner scanner;

    public UserService() {
        users = new HashMap<>();
        scanner = new Scanner(System.in);
    }


    public void registerUser() {
        System.out.println("Introdu datele pentru înregistrare:");

        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumăm newline

        System.out.print("Nume: ");
        String firstName = scanner.nextLine();

        System.out.print("Prenume: ");
        String lastName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Parola: ");
        String password = scanner.nextLine();

        System.out.print("Rol (Pet/Veterinarian): ");
        String role = scanner.nextLine();

        User user;
        if (role.equalsIgnoreCase("Pet")) {
            PetService petService = new PetService();
            petService.registerPet();
            user = new Pet(id, firstName, lastName, email, username, password, "PetName", "BirthDate", "M", "Species", "Breed");
        } else {
            System.out.print("Specializare: ");
            String specialization = scanner.nextLine();
            user = new Veterinarian(id, firstName, lastName, email, username, password, specialization);
        }

        users.put(username, user);
        System.out.println("Utilizatorul a fost înregistrat cu succes.");
    }

    public User loginUser() {
        System.out.println("Introdu datele pentru logare:");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Parola: ");
        String password = scanner.nextLine();

        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Logare reușită!");
            return user;
        } else {
            System.out.println("Username sau parolă incorectă.");
            return null;
        }
    }

    public void resetPassword() {
        System.out.println("Introdu datele pentru resetarea parolei:");

        System.out.print("Username: ");
        String username = scanner.nextLine();

        User user = users.get(username);
        if (user != null) {
            System.out.print("Introdu noua parolă: ");
            String newPassword = scanner.nextLine();

            user.setPassword(newPassword);
            System.out.println("Parola a fost resetată cu succes.");
        } else {
            System.out.println("Username inexistent. Vă rugăm să verificați și să încercați din nou.");
        }
    }
}

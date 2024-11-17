package UI;

import model.*;
import service.Service;
import Controller.appController;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UI {
    private int vetIdCounter = 1;
    private int petOwnerIdCounter = 1;
    private final appController appController;  // Using appController instead of Service
    private final Scanner scanner;

    public UI(appController appController, Scanner scanner) {
        this.appController = appController;
        this.scanner = scanner;
    }

    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Login");
            System.out.println("2. Sign-in");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    loginMenu();
                    break;
                case "2":
                    signUpMenu();
                    break;
                case "3":
                    System.out.println("Exiting... Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid selection. Please choose 1, 2, or 3.");
                    break;
            }
        }
    }

    private void loginMenu() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (appController.login(username, password)) {
            System.out.println("Login successful!");
            selectUserType();
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private void signUpMenu() {
        System.out.println("==== Sign In ====" );
        System.out.print("What are you?: ");
        System.out.println("1. Veterinarian");
        System.out.println("2. Pet Owner");

        int userType = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (userType == 1) {
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();
            System.out.print("Enter Phone: ");
            String phone = scanner.nextLine();
            System.out.print("Enter Address: ");
            String address = scanner.nextLine();
            int vetId = vetIdCounter++;
            System.out.println("Your assigned Veterinarian ID is: " + vetId);

            Veterinarian veterinarian = new Veterinarian(vetId, name, username, password, email, phone, address);
            appController.signInVet(veterinarian);
            veterinarianMenu(vetId);

        } else if (userType == 2) {
            System.out.print("Enter Pet Name: ");
            String petName = scanner.nextLine();

            int petOwnerId = petOwnerIdCounter++;
            System.out.println("Your assigned Pet Owner ID is: " + petOwnerId);

            Pet pet = new Pet(petOwnerId, petName, username, password);
            appController.signInPet(pet);
            petOwnerMenu(petOwnerId);

        } else {
            System.out.println("Invalid user type.");
            return;
        }

        System.out.println("Sign-up successful!");
    }

    private void selectUserType() {
        boolean validSelection = false;
        while (!validSelection) {
            System.out.println("\nSelect your role:");
            System.out.println("1. Veterinarian");
            System.out.println("2. Pet Owner");
            System.out.print("Choose an option: ");

            String roleChoice = scanner.nextLine();
            switch (roleChoice) {
                case "1":
                    int vetId = vetIdCounter++;
                    System.out.println("You selected: Veterinarian");
                    System.out.println("Your assigned Veterinarian ID is: " + vetId);
                    veterinarianMenu(vetId);
                    validSelection = true;
                    break;
                case "2":
                    int petOwnerId = petOwnerIdCounter++;
                    System.out.println("You selected: Pet Owner");
                    System.out.println("Your assigned Pet Owner ID is: " + petOwnerId);
                    petOwnerMenu(petOwnerId);
                    validSelection = true;
                    break;
                default:
                    System.out.println("Invalid selection. Please choose 1 or 2.");
                    break;
            }
        }
    }

    private void veterinarianMenu(int vetId) {
        boolean running = true;
        while (running) {
            System.out.println("\n==== Veterinarian Menu ====");
            System.out.println("1. View Appointments");
            System.out.println("2. Add Disease to Pet");
            System.out.println("3. Notify and Cancel Appointments for Vacation");
            System.out.println("4. Delete Account");
            System.out.println("0. Logout");
            System.out.print("Your choice: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    viewAppointments(vetId);
                    break;
                case "2":
                    addDiseaseToPet(vetId);
                    break;
                case "3":
                    notifyAndCancelAppointments(vetId);
                    break;
                case "4":
                    deleteAccount(vetId, true);  // Veterinarian account deletion
                    break;
                case "0":
                    System.out.println("Logging out...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void petOwnerMenu(int petOwnerId) {
        boolean running = true;
        while (running) {
            System.out.println("\n==== Pet Owner Menu ====");
            System.out.println("1. Add Appointment");
            System.out.println("2. View Appointments");
            System.out.println("3. Cancel Appointment");
            System.out.println("4. View Health Record");
            System.out.println("5. Delete Account");
            System.out.println("0. Logout");
            System.out.print("Your choice: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addAppointment(petOwnerId);
                    break;
                case "2":
                    viewAppointments(petOwnerId);
                    break;
                case "3":
                    cancelAppointment(petOwnerId);
                    break;
                case "4":
                    viewHealthRecord(petOwnerId);
                    break;
                case "5":
                    deleteAccount(petOwnerId, false);
                    break;
                case "0":
                    System.out.println("Logging out...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void viewAppointments(int userId) {
        List<Appointment> appointments = appController.seeAppointments(userId);
        appointments.forEach(appointment -> System.out.println(appointment.toString()));
    }

    private void addDiseaseToPet(int vetId) {

    }

    private void notifyAndCancelAppointments(int vetId) {
        System.out.print("Enter the start date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        System.out.print("Enter the end date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();

        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        appController.notifyAndCancelAppointmentsForVacation(vetId, start, end);
    }

    private void deleteAccount(int id, boolean isPet) {
        appController.deleteAccount(id, isPet);
        System.out.println("Account deleted successfully.\n");
    }

    private void addAppointment(int petOwnerId) {
        System.out.println("Enter appointment details:");
        System.out.print("Pet Name: ");
        String petName = scanner.nextLine();
        System.out.print("Owner Name: ");
        String ownerName = scanner.nextLine();
        System.out.print("Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();


    }

    private void cancelAppointment(int petOwnerId) {
        System.out.print("Enter appointment ID to cancel: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine();
        appController.cancelAppointment(appointmentId);
        System.out.println("Appointment canceled successfully.\n");
    }

    private void viewHealthRecord(int petOwnerId) {
        System.out.println("Displaying health record for Pet Owner ID: " + petOwnerId + ".\n");
    }
}

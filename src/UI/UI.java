package UI;

import model.*;
import service.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UI {
    private final Service service;
    private final Scanner scanner;

    public UI(Service service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void mainMenu() {
        while (true) {
            System.out.println("==== Welcome to the Veterinary clinic ====");
            System.out.println("1. Log In");
            System.out.println("2. Sign Up");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    logInMenu();
                    break;
                case 2:
                    signUpMenu();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void logInMenu() {
        System.out.println("==== Log In ====");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        boolean isLoggedIn = service.login(username, password);
        if (isLoggedIn) {
            System.out.println("Login successful!");
            userMenu(username);
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private void signUpMenu() {
        System.out.println("==== Sign Up ====" );
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

            Veterinarian veterinarian = new Veterinarian(0, name, username, password, email, phone, address);
            service.createVeterinarian(veterinarian);
        } else if (userType == 2) {
            System.out.print("Enter Pet Name: ");
            String petName = scanner.nextLine();

            Pet pet = new Pet(0, petName, username, password);
            service.createPet(pet);
        } else {
            System.out.println("Invalid user type.");
            return;
        }

        System.out.println("Sign-up successful! Redirecting to log in...");
        logInMenu(); // Apelarea directă a metodei de log in
    }

    private void userMenu(String username) {
        boolean isVet = service.getAllVeterinarians().stream().anyMatch(vet -> vet.getUsername().equals(username));

        if (isVet) {
            veterinarianMenu(username);
        } else {
            petOwnerMenu(username);
        }
    }

    private void veterinarianMenu(String username) {
        while (true) {
            System.out.println("==== Veterinarian Menu ====");
            System.out.println("1. View Appointments");
            System.out.println("2. Add Disease to Pet");
            System.out.println("3. Notify and Cancel Appointments for Vacation");
            System.out.println("4. Delete Account");
            System.out.println("0. Logout");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewAppointments(username);
                    break;
                case 2:
                    addDiseaseToPet();
                    break;
                case 3:
                    notifyAndCancelAppointments(username);
                    break;
                case 4:
                    deleteAccount(username, true);
                    return;
                case 0:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void petOwnerMenu(String username) {
        while (true) {
            System.out.println("==== Pet Owner Menu ====");
            System.out.println("1. Add Appointment");
            System.out.println("2. View Appointments");
            System.out.println("3. Cancel Appointment");
            System.out.println("4. View Health Record");
            System.out.println("5. Delete Account");
            System.out.println("0. Logout");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addAppointment(username);
                    break;
                case 2:
                    viewAppointments(username);
                    break;
                case 3:
                    cancelAppointment(username);
                    break;
                case 4:
                    viewHealthRecord(username);
                    break;
                case 5:
                    deleteAccount(username, false);
                    return;
                case 0:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void viewAppointments(String username) {
        List<Appointment> appointments = service.seeAppointments(getUserId(username));
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            appointments.forEach(System.out::println);
        }
    }

    private void addAppointment(String username) {
        System.out.print("Enter Pet ID: ");
        int petId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Veterinarian ID: ");
        int vetId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        Appointment appointment = new Appointment(petId, vetId, date);
        service.addAppointment(appointment);
        System.out.println("Appointment added successfully!");
    }

    private void cancelAppointment(String username) {
        System.out.print("Enter Appointment ID to cancel: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine();

        service.cancelAppointment(appointmentId);
        System.out.println("Appointment canceled successfully.");
    }

    private void viewHealthRecord(String username) {
        System.out.print("Enter Pet ID: ");
        int petId = scanner.nextInt();
        scanner.nextLine();

        HealthRecord healthRecord = service.seeHealthRecord(petId);
        if (healthRecord != null) {
            System.out.println(healthRecord);
        } else {
            System.out.println("No health record found for this pet.");
        }
    }


    private void addDiseaseToPet() {
        System.out.print("Enter Pet ID: ");
        int petId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Disease Name: ");
        String diseaseName = scanner.nextLine();
        System.out.print("Enter Disease Description: ");
        String description = scanner.nextLine();

        Disease disease = new Disease(0, diseaseName, description);
        service.addDiseaseForPet(petId, disease);
        System.out.println("Disease added to pet successfully.");
    }

    private void notifyAndCancelAppointments(String username) {
        System.out.print("Enter Start Date (YYYY-MM-DD): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter End Date (YYYY-MM-DD): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        int vetId = getUserId(username);
        service.notifyAndCancelAppointmentsForVacation(vetId, startDate, endDate);
        System.out.println("Notifications sent and appointments canceled.");
    }

    private void deleteAccount(String username, boolean isVet) {
        int userId = getUserId(username);
        service.deleteAccount(userId, isVet);
        System.out.println("Account deleted successfully.");
    }

    private int getUserId(String username) {
        if (service.getAllVeterinarians().stream().anyMatch(vet -> vet.getUsername().equals(username))) {
            return service.getAllVeterinarians().stream().filter(vet -> vet.getUsername().equals(username)).findFirst().get().getUser_id();
        } else {
            return service.getAllPets().stream().filter(pet -> pet.getUsername().equals(username)).findFirst().get().getUser_id();
        }
    }

//    public static void main(String[] args) {
//        Service service = new Service(IRepository<Pet> petRepo, IRepository<Veterinarian> vetRepo, IRepository<Appointment> appRepo, IRepository<HealthRecord> hrRepo, IRepository<Disease> diseaseRepository, IRepository<Vaccine> vaccineRepository, IRepository<Test> testRepository);
//        UI ui = new UI(service);
//        ui.mainMenu();
//    }
}
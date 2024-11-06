package UI;
import Controller.appController;
import Repository.IRepository;
import model.Disease;
import model.User;
import model.Vaccine;
import model.Veterinarian;
import service.*;

import java.util.Scanner;

public class UI {
    private appController controller;
    private Scanner scanner;

    public UI(appController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void showMainMenu() {
        while (true) {
            System.out.println("==== Main Menu ====");
            System.out.println("1. Sign In (Veterinarian or Pet)");
            System.out.println("2. Log In (Veterinarian or Pet)");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    signInMenu();
                    break;
                case 2:
                    logInMenu();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void signInMenu() {
        System.out.println("Select User Type:");
        System.out.println("1. Veterinarian");
        System.out.println("2. Pet Owner");
        int userType = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (userType == 1) {
            controller.registerUser();
        } else if (userType == 2) {
            controller.registerUser();
        } else {
            System.out.println("Invalid user type.");
        }
    }

    private void logInMenu() {
        System.out.println("Select User Type:");
        System.out.println("1. Veterinarian");
        System.out.println("2. Pet Owner");
        int userType = scanner.nextInt();
        scanner.nextLine();  // consume newline

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (userType == 1) {
            User veterinarian = controller.loginUser();
            if (veterinarian != null) {
                veterinarianMenu();
            } else {
                System.out.println("Invalid login. Try again.");
            }
        } else if (userType == 2) {
            User petOwner = controller.loginUser();
            if (petOwner != null) {
                petOwnerMenu();
            } else {
                System.out.println("Invalid login. Try again.");
            }
        } else {
            System.out.println("Invalid user type.");
        }
    }

    // Veterinarian menu after successful login
    private void veterinarianMenu() {
        while (true) {
            System.out.println("==== Veterinarian Menu ====");
            System.out.println("1. Add Pet");
            System.out.println("2. Add Disease");
            System.out.println("3. Add Vaccine");
            System.out.println("4. View All Pets");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    addPetMenu();
                    break;
                case 2:
                    addDiseaseMenu();
                    break;
                case 3:
                    addVaccineMenu();
                    break;
                case 4:
                    controller.DisplayAllPets();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void addPetMenu() {
        System.out.print("Enter Pet Name: ");
        String petName = scanner.nextLine();
        System.out.print("Enter Pet Age: ");
        int petAge = scanner.nextInt();
        controller.registerPet();
    }

    private void addDiseaseMenu() {
        System.out.print("Enter Disease ID: ");
        int diseaseId = scanner.nextInt();
        scanner.nextLine();  // consume newline
        System.out.print("Enter Disease Name: ");
        String diseaseName = scanner.nextLine();
        System.out.print("Enter Disease Type: ");
        String diseaseType = scanner.nextLine();
        controller.addDisease(diseaseId, diseaseName, diseaseType);
    }

    private void addVaccineMenu() {
        System.out.print("Enter Vaccine ID: ");
        int vaccineId = scanner.nextInt();
        scanner.nextLine();  // consume newline
        System.out.print("Enter Vaccine Name: ");
        String vaccineName = scanner.nextLine();
        controller.addVaccine();
    }

    private void petOwnerMenu() {
        while (true) {
            System.out.println("==== Pet Owner Menu ====");
            System.out.println("1. Add Appointment");
            System.out.println("2. View Health Record");
            System.out.println("3. View Available Tests");
            System.out.println("4. View Available Vaccines");
            System.out.println("5. View Notifications");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    addAppointmentMenu();
                    break;
                case 2:
                    viewHealthRecordMenu();
                    break;
                case 3:
                    viewAvailableTests();
                    break;
                case 4:
                    viewAvailableVaccines();
                    break;
                case 5:
                    viewNotifications();
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void addAppointmentMenu() {
        System.out.print("Enter Pet ID: ");
        int petId = scanner.nextInt();
    }

    private void viewHealthRecordMenu() {
        System.out.print("Enter Pet ID: ");
        int petId = scanner.nextInt();
    }

    private void viewAvailableTests() {
        System.out.print("Enter Disease ID: ");
        int diseaseId = scanner.nextInt();
    }

    private void viewAvailableVaccines() {
        System.out.print("Enter Disease ID: ");
        int diseaseId = scanner.nextInt();
    }

    private void viewNotifications() {
        controller.listNotifications();
    }

    public static void main(String[] args) {
        IRepository<Veterinarian> veterinarianRepository = null;
        IRepository<Disease> diseaseRepository = null;
        IRepository<Vaccine> vaccineRepository;
        appController controller = new appController(
                new AppointmentService(new NotificationService(),new VeterinarianService(veterinarianRepository)), new DiseaseService(), new HealthRecordService(),
                new VaccineService(), new PetService(), new VeterinarianService(veterinarianRepository),
                new TestService(), new PetDiseaseService(new DiseaseService(diseaseRepository),new PetService()), new UserService(), new NotificationService());
        UI ui = new UI(controller);
        ui.showMainMenu();
    }
}


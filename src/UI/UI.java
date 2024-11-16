//package UI;
//import Controller.appController;
//import Repository.IRepository;
//import model.*;
//import service.*;
//
//import java.util.Scanner;
//
//public class UI {
//    private appController controller;
//    private Scanner scanner;
//
//    public UI(appController controller) {
//        this.controller = controller;
//        this.scanner = new Scanner(System.in);
//    }
//
//    public void showMainMenu() {
//        while (true) {
//            System.out.println("==== Main Menu ====");
//            System.out.println("1. Sign In");
//            System.out.println("2. Log In");
//            System.out.println("3. Administration");
//            System.out.println("4. Exit");
//            System.out.print("Choose an option: ");
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (choice) {
//                case 1:
//                    controller.registerUser();
//                    break;
//                case 2:
//                    User user = controller.loginUser();
//                    if (user != null) {
//                        if (user instanceof Pet) {
//                            petMenu(user);
//                        } else if (user instanceof Veterinarian) {
//                            veterinarianMenu(user);
//                        }
//                    }
//                    break;
//                case 3:
//                    System.out.println("Goodbye!");
//                    return;
//                default:
//                    System.out.println("Invalid choice, please try again.");
//            }
//        }
//    }
//    private void petMenu(User user) {
//        while (true) {
//            System.out.println("==== Pet Menu ====");
//            System.out.println("1. Add Pet");
//            System.out.println("2. Find Pet");
//            System.out.println("3. Add Appointment ");
//            System.out.println("4. Logout");
//            System.out.print("Choose an option: ");
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (choice) {
//                case 1:
//                    addPetMenu();
//                    break;
//                case 2:
//                    findPetByID();
//                case 3:
//                    addAppointmentMenu();
//                    break;
//                case 4:
//                    System.out.println("Logging out...");
//                    return;
//                default:
//                    System.out.println("Invalid choice, please try again.");
//
//
//
//            }
//        }
//    }
//    private void veterinarianMenu(User user) {
//        while (true) {
//            System.out.println("==== Veterinarian Menu ====");
//            System.out.println("1. Add New Disease");
//            System.out.println("2. Add New Vaccine");
//            System.out.println("3. View All Pets");
//            System.out.println("4. Logout");
//            System.out.print("Choose an option: ");
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (choice) {
//                case 1:
//                    addDiseaseMenu();
//                    break;
//                case 2:
//                    addVaccineMenu();
//                    break;
//                case 3:
//                    controller.DisplayAllPets();
//                    break;
//                case 4:
//                    System.out.println("Logging out...");
//                    return;
//                default:
//                    System.out.println("Invalid choice, please try again.");
//            }
//        }
//    }
//
//    private void addPetMenu() {
//        System.out.print("Enter Pet Name: ");
//        String petName = scanner.nextLine();
//        System.out.print("Enter Pet Age: ");
//        int petAge = scanner.nextInt();
//        controller.registerPet();
//    }
//
//    private void addDiseaseMenu() {
//        System.out.print("Enter Disease ID: ");
//        int diseaseId = scanner.nextInt();
//        scanner.nextLine();
//        System.out.print("Enter Disease Name: ");
//        String diseaseName = scanner.nextLine();
//        System.out.print("Enter Disease Type: ");
//        String diseaseType = scanner.nextLine();
//        controller.addDisease(diseaseId, diseaseName, diseaseType);
//    }
//
//    private void addVaccineMenu() {
//        System.out.print("Enter Vaccine ID: ");
//        int vaccineId = scanner.nextInt();
//        scanner.nextLine();
//        System.out.print("Enter Vaccine Name: ");
//        String vaccineName = scanner.nextLine();
//        controller.addVaccine();
//    }
//
////    private void petOwnerMenu() {
////        while (true) {
////            System.out.println("==== Pet Owner Menu ====");
////            System.out.println("1. Add Appointment");
////            System.out.println("2. View Health Record");
////            System.out.println("3. View Available Tests");
////            System.out.println("4. View Available Vaccines");
////            System.out.println("5. View Notifications");
////            System.out.println("6. Logout");
////            System.out.print("Choose an option: ");
////            int choice = scanner.nextInt();
////            scanner.nextLine();
////
////            switch (choice) {
////                case 1:
////                    addAppointmentMenu();
////                    break;
////                case 2:
////                    viewHealthRecordMenu();
////                    break;
////                case 3:
////                    viewAvailableTests();
////                    break;
////                case 4:
////                    viewAvailableVaccines();
////                    break;
////                case 5:
////                    viewNotifications();
////                    break;
////                case 6:
////                    System.out.println("Logging out...");
////                    return;
////                default:
////                    System.out.println("Invalid choice, please try again.");
////            }
////        }
////    }
//
//    private void addAppointmentMenu() {
//
//    }
//
//    private void viewHealthRecordMenu() {
//        System.out.print("Enter Pet ID: ");
//        int petId = scanner.nextInt();
//    }
//
//    private void viewAvailableTests() {
//        System.out.print("Enter Disease ID: ");
//        int diseaseId = scanner.nextInt();
//    }
//
//    private void viewAvailableVaccines() {
//        System.out.print("Enter Disease ID: ");
//        int diseaseId = scanner.nextInt();
//    }
//
//    private void viewNotifications() {
//        controller.listNotifications();
//    }
//
//    private void findPetByID() {
//        System.out.print("Enter Pet ID: ");
//        int petId=scanner.nextInt();
//        controller.findPetById(petId);
//    }
//
//    public static void main(String[] args) {
//        IRepository<Veterinarian> veterinarianRepository = null;
//        IRepository<Disease> diseaseRepository = null;
//        IRepository<Vaccine> vaccineRepository;
//        appController controller = new appController(
//                new AppointmentService(new NotificationService(),new VeterinarianService(veterinarianRepository)), new DiseaseService(), new HealthRecordService(),
//                new VaccineService(), new PetService(), new VeterinarianService(veterinarianRepository),
//                new TestService(), new PetDiseaseService(new DiseaseService(diseaseRepository),new PetService()), new UserService(), new NotificationService());
//        UI ui = new UI(controller);
//        ui.showMainMenu();
//    }
//}
//

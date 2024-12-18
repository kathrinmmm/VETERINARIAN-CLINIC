package UI;
import Model.*;
import Controller.Controller;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Utils.*;
/**
 * The {@code UI} class represents the user interface for interacting with the system.
 * It handles login, sign-in, and service operations for pets and veterinarians, as well as
 * providing menus for various functionalities such as viewing tests, vaccines, and diseases.
 */
public class UI {

    private final Controller Controller;
    private final Scanner scanner;
    private Integer currentPetId = null;
    private Integer currentVetId = null;
    /**
     * Constructs a {@code UI} instance.
     *
     * @param Controller the controller that handles business logic
     * @param scanner the scanner object to read user input
     */
    public UI(Controller Controller, Scanner scanner) {
        this.Controller = Controller;
        this.scanner = scanner;
    }
    /**
     * Starts the main menu loop and provides options for the user to interact with the system.
     * Options include logging in, signing up, accessing services, or exiting the application.
     */
    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("\n====Main Menu====\n");
            System.out.println("1. Log In");
            System.out.println("2. Sign In");
            System.out.println("3. Service");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    LogInMenu();
                    break;
                case "2":
                    SignInMenu();
                    break;
                case "3":
                    ServiceMenu();
                    break;
                case "0":
                    System.out.println("Exiting... Goodbye!");
                    running = false;
                    break;
            }
        }
    }
    /**
     * Provides the login menu where users can enter their username and password to log in
     * as a Pet or Veterinarian. If login is successful, the appropriate menu for the user type is displayed.
     */
    private void LogInMenu() {
        System.out.println("Login Menu:");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        try {
            Object loggedInUser = Controller.login(username, password);
            if (loggedInUser instanceof Pet) {
                Pet pet = (Pet) loggedInUser;
                System.out.println("Login successful! You are logged in as a Pet.");
                currentPetId = pet.getId();
                System.out.println("petid is: "+pet.getId());
                showPetMenu(pet);
            } else if (loggedInUser instanceof Veterinarian) {
                Veterinarian vet = (Veterinarian) loggedInUser;
                System.out.println("Login successful! You are logged in as a Veterinarian.");
                currentVetId = vet.getId();
                System.out.println("vet id is: "+vet.getId());
                showVetMenu(vet);
            } else {
                System.out.println("Invalid credentials.");
            }
        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }

    /**
     * Provides the sign-in menu where users can sign up as either a Pet or Veterinarian.
     * This menu collects necessary information such as name, username, password, and type (for both Pets.txt and Veterinarians).
     * After successful registration, the user is logged in and their respective menu is shown.
     */

    private void SignInMenu() {
        System.out.println("Sign In Menu:");

        System.out.println("Are you signing up as a Pet or Veterinarian?");
        System.out.println("Enter 'Pet' or 'Vet'");
        String userType = scanner.nextLine().trim().toLowerCase();

        if (userType.equals("pet")) {
            System.out.println("Enter pet name: ");
            String name = scanner.nextLine();
            System.out.println("Enter Username: ");
            String username = scanner.nextLine();
            System.out.println("Enter Password: ");
            String password = scanner.nextLine();
            System.out.println("Enter pet Type (DOG, CAT, BIRD, REPTILE): ");
            String petType = scanner.nextLine().toUpperCase();

            try {
                AnimalType animalType = AnimalType.valueOf(petType);
                Pet pet = new Pet(name, username, password, animalType);
                Controller.createPet(pet);
                currentPetId = pet.getId();
                System.out.println("petid is: "+pet.getId());
                System.out.println("Pet registered successfully!");
                showPetMenu(pet);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid animal type.");
            }
        } else if (userType.equals("vet")) {
            System.out.println("Enter Veterinarian name: ");
            String vetName = scanner.nextLine();
            System.out.println("Enter Username: ");
            String vetUsername = scanner.nextLine();
            System.out.println("Enter Password: ");
            String vetPassword = scanner.nextLine();
            System.out.println("Enter Veterinarian Specialization (DERMATOLOGY, SURGERY, DENTISTRY, INTERNAL, ONCOLOGY, GENERAL): ");
            String specializationInput = scanner.nextLine().toUpperCase();

            try {
                Specialization specialization = Specialization.valueOf(specializationInput);
                Veterinarian vet = new Veterinarian(vetName, vetUsername, vetPassword, specialization);
                Controller.createVet(vet);
                System.out.println("Veterinarian registered successfully!");
                currentVetId=vet.getId();
                System.out.println("vet id is: "+vet.getId());
                showVetMenu(vet);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid specialization type.");
            }
        } else {
            System.out.println("Invalid option. Please enter 'Pet' or 'Vet'.");
        }
    }
    /**
     * Provides the service menu where users can add new tests, vaccines, or diseases,
     * or view the list of available tests, vaccines, diseases, or appointments.
     * This menu allows the user to interact with various service-related functionalities.
     */
    private void ServiceMenu() {
        boolean running = true;
        while (running) {
            System.out.println("===Service Menu===");
            System.out.println("1. Add New Test");
            System.out.println("2. Add New Vaccine");
            System.out.println("3. Add New Disease");
            System.out.println("4. See all Tests");
            System.out.println("5. See all Vaccines");
            System.out.println("6. See all Diseases");
            System.out.println("7. See all Appointments");
            System.out.println("8. Get Appointments from a specific Date");
            System.out.println("9. See all Vets.txt by Specialization");
            System.out.println("10. Sort appointments by date");
            System.out.println("11. See appointments in date range");
            System.out.println("0. Exit");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    System.out.println("Enter Test name: ");
                    String tname = scanner.nextLine();
                    System.out.println("Enter Test Type ( BLOOD,\n" +
                            "    X_RAY,\n" +
                            "    ULTRASOUND,\n" +
                            "    ALLERGY)");
                    String tinput = scanner.nextLine().toUpperCase();

                    try {
                        TestType testType = TestType.valueOf(tinput);
                        HealthTest healthTest = new HealthTest(tname, testType);
                        Controller.createTest(healthTest);
                        System.out.println("Test added successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("The specified type is invalid.");
                    }
                    break;
                case "2":
                    System.out.println("Enter Vaccine name: ");
                    String vaccname = scanner.nextLine();
                    System.out.println("Enter Vaccine Type ( RABIES,\n" +
                            "    DISTEMPER,\n" +
                            "    FELINE_LEUKEMIA,\n" +
                            "    PARROT_FEVER)");
                    String vaccinput = scanner.nextLine().toUpperCase();

                    try {
                        VaccineType vaccineType = VaccineType.valueOf(vaccinput);
                        Vaccine vaccine = new Vaccine(vaccname, vaccineType);
                        Controller.createVaccine(vaccine);
                        System.out.println("Vaccine added successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("The specified type is invalid.");
                    }
                    break;
                case "3":
                    System.out.println("Enter Disease name: ");
                    String disname = scanner.nextLine();
                    System.out.println("Enter Disease Type ( \nCANINE_PARAVOVIRUS,\n" +
                            "    RABIES,\n" +
                            "    FELINE_LEUKEMIA,\n" +
                            "    PSITTACOSIS,\n" +
                            "    CANINE_DISTEMPER,\n" +
                            "    HEARTWORM,\n" +
                            "    LYME_DISEASE,\n" +
                            "    KENNEL_COUGH,\n" +
                            "    LEPTOSPIROSIS,\n" +
                            "    CANINE_HEPATITIS,\n" +
                            "    TICK_FEVER,\n" +
                            "    BABESIOSIS,\n" +
                            "    ANTHRAX,\n" +
                            "    BRUCELLOSIS,\n" +
                            "    RINGWORM): ");
                    String disinput = scanner.nextLine().toUpperCase();

                    try {
                        DiseaseType diseaseType = DiseaseType.valueOf(disinput);
                        Disease disease = new Disease(disname, diseaseType);
                        Controller.createDisease(disease);
                        System.out.println("Disease added successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("The specified type is invalid.");
                    }
                    break;
                case "4":
                    List<HealthTest> healthTests = Controller.getAllTests();
                    if (healthTests.isEmpty()) {
                        System.out.println("No tests found.");
                    } else {
                        System.out.println("\nList of all tests:");
                        for (HealthTest healthTest : healthTests) {
                            System.out.println(healthTest);
                        }
                    }
                    break;
                case "5":
                    List<Vaccine> vaccines = Controller.getAllVaccines();
                    if (vaccines.isEmpty()) {
                        System.out.println("No vaccine found.");
                    } else {
                        System.out.println("\nList of all vaccines:");
                        for (Vaccine v : vaccines) {
                            System.out.println(v);
                        }
                    }
                    break;
                case "6":
                    List<Disease> diseases = Controller.getAllDiseases();
                    if (diseases.isEmpty()) {
                        System.out.println("No diseases found.");
                    } else {
                        System.out.println("\nList of all Diseases.txt:");
                        for (Disease dis : diseases) {
                            System.out.println(dis);
                        }
                    }
                    break;
                case "7":
                    List<Appointment> appoint = Controller.getAllAppointments();
                    if (appoint.isEmpty()) {
                        System.out.println("No appointments found.");
                    } else {
                        System.out.println("\nList of all appointments:");
                        for (Appointment app : appoint) {
                            System.out.println(app);
                        }
                    }
                    break;
                case "8":
                    System.out.println("Enter the date (dd-MM-yyyy):");
                    String date1 = scanner.nextLine();
                    List<Appointment> appointmentsByDate = Controller.getAppointmentsByDate(date1);
                    if (appointmentsByDate.isEmpty()) {
                        System.out.println("No appointments found for the given date.");
                    } else {
                        System.out.println("\nAppointments on " + date1 + ":");
                        for (Appointment app : appointmentsByDate) {
                            System.out.println(app);
                        }
                    }
                    break;
                case"9":
                    List<Veterinarian> sortedVets = Controller.sortVeterinariansBySpecialization();
                    sortedVets.forEach(vet -> System.out.println("-Id:" + vet.getId()+" Name: " + vet.getName() + ", Specialization: " + vet.getSpecialization()));
                    break;
                case "10":
                    List<Appointment> sortedAppointments = Controller.sortAppointmentsByDate();
                    sortedAppointments.forEach(app -> System.out.println(
                            "- ID: " + app.getAppId() + ", Data: " + app.getDate() + ", Pet ID: " + app.getPetId()));
                    break;
                case "11":
                    System.out.println("Enter start date (format: dd-MM-yyyy):");
                    String startDate = scanner.next();
                    System.out.println("Enter end date (format: dd-MM-yyyy):");
                    String endDate = scanner.next();

                    try {
                        List<Appointment> appointmentsInRange = Controller.getAppointmentsInDateRange(startDate, endDate);

                        if (appointmentsInRange.isEmpty()) {
                            System.out.println("No appointments in the specified interval.");
                        } else {
                            System.out.println("Appointments in date range: " + startDate + " - " + endDate + ":");
                            appointmentsInRange.forEach(app -> System.out.println(
                                    "- ID: " + app.getAppId() + ", Date: " + app.getDate() + ", Pet ID: " + app.getPetId()));
                        }
                    } catch (Exception e) {
                        System.out.println("Eroare: " + e.getMessage());
                    }
                    break;
                case "0":
                    System.out.println("Exiting... Goodbye!");
                    running = false;
                    break;
            }
        }
    }

    /**
     * Displays the pet menu, allowing the pet user to view and manage appointments, notifications, and health records.
     *
     * @param pet the pet object representing the logged-in pet
     */
    private void showPetMenu(Pet pet) {
        boolean runningPetMenu = true;
        while (runningPetMenu) {
            System.out.println("\n===Pet Menu===");
            System.out.println("1. Book Appointment");
            System.out.println("2. See Appointments");
            System.out.println("3. See Notifications.txt");
            System.out.println("4. See Health Record");
            System.out.println("5. Check Reminders");
            System.out.println("6. Delete Account");
            System.out.println("7. Cancel Appointment");
            System.out.println("0. Log Out");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.println("Available Vaccines.txt:");
                    List<Vaccine> vacc = Controller.getAllVaccines();
                    for (Vaccine vaccine : vacc) {
                        System.out.println(vaccine.getId() + ": " + vaccine.getName());
                    }
                    System.out.println("Enter vaccine IDs separated by commas (or press Enter to skip):");
                    String vaccineInput = scanner.nextLine();
                    ArrayList<Vaccine> selectedVaccines = new ArrayList<>();
                    if (!vaccineInput.isBlank()) {
                        for (String id : vaccineInput.split(",")) {
                            Vaccine vaccine = vacc.stream()
                                    .filter(v -> v.getId() == Integer.parseInt(id.trim()))
                                    .findFirst()
                                    .orElse(null);
                            if (vaccine != null) {
                                selectedVaccines.add(vaccine);
                            }
                        }
                    }

                    System.out.println("Available Tests.txt:");
                    List<HealthTest> tst = Controller.getAllTests();
                    for (HealthTest healthTest : tst) {
                        System.out.println(healthTest.getId() + ": " + healthTest.getName());
                    }
                    System.out.println("Enter test IDs separated by commas (or press Enter to skip):");
                    String testInput = scanner.nextLine();
                    ArrayList<HealthTest> selectedHealthTests = new ArrayList<>();
                    if (!testInput.isBlank()) {
                        for (String id : testInput.split(",")) {
                            HealthTest healthTest = tst.stream()
                                    .filter(t -> t.getId() == Integer.parseInt(id.trim()))
                                    .findFirst()
                                    .orElse(null);
                            if (healthTest != null) {
                                selectedHealthTests.add(healthTest);
                            }
                        }
                    }

                    List<Veterinarian> vets = Controller.getAllVeterinarians();
                    if (vets.isEmpty()) {
                        System.out.println("No vets found.");
                    } else {
                        System.out.println("\nList of all Vets.txt:");
                        for (Veterinarian vet : vets) {
                            System.out.println(vet);
                        }
                    }
                    System.out.println("Enter Veterinarian ID:");
                    int vetId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter Appointment Date (dd-MM-yyyy):");
                    String date = scanner.nextLine();
                    System.out.println("Enter Appointment Time (HH:mm):");
                    String time = scanner.nextLine();
                    System.out.println("Enter Appointment Type (ROUTINE,BEHAVIOR,DENTAL,EMERGENCY,SURGICAL):");
                    String type = scanner.nextLine().toUpperCase();
                    try {
                        AppointmentType appType= AppointmentType.valueOf(type);
                        Appointment appointment = new Appointment( currentPetId, vetId, date, time, appType, selectedHealthTests, selectedVaccines);
                        Controller.createAppointment(appointment);
                        Controller.sendConfirmationNotification(currentPetId);
                    } catch (IllegalArgumentException e) {
                        System.out.println("The specified type is invalid.");
                    }
                    break;
                case "2":
                    List<Appointment> appointmentsByPet = Controller.getAppointmentsByPet(currentPetId);
                    if (appointmentsByPet.isEmpty()) {
                        System.out.println("No appointments found for the given pet.");
                    } else {
                        System.out.println("\nAppointments for Pet ID " + currentPetId + ":");
                        for (Appointment app : appointmentsByPet) {
                            System.out.println(app);
                        }
                    }
                    break;
                case "3":
                    List<Notification> notifications = Controller.getNotificationsByUserId(currentPetId);

                    if (notifications.isEmpty()) {
                        System.out.println("You have no notifications.");
                    } else {
                        System.out.println("Your Notifications.txt:");
                        for (Notification notification : notifications) {
                            System.out.println(
                                    "Title: " + notification.getTitle() +
                                            " Date: " + notification.getDate());
                        }
                    }
                    break;
                case "4":
                    HealthRecord hrpet = Controller.getHealthRecordByPetId(currentPetId);
                    if (hrpet == null) {
                        System.out.println("No health record found for the given Pet ID.");
                    } else {
                        System.out.println("\nHealth Record for Pet " + currentPetId + ":");
                        System.out.println(hrpet);
                    }
                    break;
                case "5":
                    Controller.showUpcomingAppointments(currentPetId);
                    break;
                case "6":
                    Controller.deletePet(currentPetId);
                    System.out.println("Deleting Account...");
                    runningPetMenu=false;
                    break;
                case "7":
                    List<Appointment> appointmentsByPet1 = Controller.getAppointmentsByPet(currentPetId);
                    if (appointmentsByPet1.isEmpty()) {
                        System.out.println("No appointments found for the given pet.");
                    } else {
                        System.out.println("\nAppointments for Pet ID " + currentPetId + ":");
                        for (Appointment app : appointmentsByPet1) {
                            System.out.println(app);
                        }
                        System.out.println("Enter Appointment ID:");
                        int appointmentId = scanner.nextInt();
                        scanner.nextLine();
                        Controller.deleteApp(appointmentId);
                        Controller.sendCancellationNotification(currentPetId);
                    }
                    break;
                case "0":
                    System.out.println("Logging Out...");
                    runningPetMenu=false;
                    break;
            }
        }
    }
    /**
     * Displays the veterinarian menu, allowing the vet user to manage appointments, health records, and notifications for pets.
     *
     * @param vet the veterinarian object representing the logged-in vet
     */
    private void showVetMenu(Veterinarian vet) {
        boolean runningVetMenu = true;
        while (runningVetMenu) {
            System.out.println("\n===Vet Menu===");
            System.out.println("1. See all Appointments");
            System.out.println("2. Add Appointment for Pet");
            System.out.println("3. See Health Record for Pet");
            System.out.println("4. Add disease for Pet");
            System.out.println("5. Compose a notification for Pet");
            System.out.println("6. Cancel Appointments for Vacation and Notify Pets.txt");
            System.out.println("7. Cancel Appointment");
            System.out.println("8. See Notifications.txt");
            System.out.println("9. Delete Account");
            System.out.println("0. Log Out");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    List<Appointment> appointmentsByVet = Controller.getAppointmentsByVet(currentVetId);
                    if (appointmentsByVet.isEmpty()) {
                        System.out.println("No appointments found for the given veterinarian.");
                    } else {
                        System.out.println("\nAppointments for Veterinarian ID " + currentVetId + ":");
                        for (Appointment app : appointmentsByVet) {
                            System.out.println(app);
                        }
                    }
                    break;
                case "2":
                    System.out.println("Available Vaccines.txt:");
                    List<Vaccine> vacc = Controller.getAllVaccines();
                    for (Vaccine vaccine : vacc) {
                        System.out.println(vaccine.getId() + ": " + vaccine.getName());
                    }
                    System.out.println("Enter vaccine IDs separated by commas (or press Enter to skip):");
                    String vaccineInput = scanner.nextLine();
                    ArrayList<Vaccine> selectedVaccines = new ArrayList<>();
                    if (!vaccineInput.isBlank()) {
                        for (String id : vaccineInput.split(",")) {
                            Vaccine vaccine = vacc.stream()
                                    .filter(v -> v.getId() == Integer.parseInt(id.trim()))
                                    .findFirst()
                                    .orElse(null);
                            if (vaccine != null) {
                                selectedVaccines.add(vaccine);
                            }
                        }
                    }

                    System.out.println("Available Tests.txt:");
                    List<HealthTest> tst = Controller.getAllTests();
                    for (HealthTest healthTest : tst) {
                        System.out.println(healthTest.getId() + ": " + healthTest.getName());
                    }
                    System.out.println("Enter test IDs separated by commas (or press Enter to skip):");
                    String testInput = scanner.nextLine();
                    ArrayList<HealthTest> selectedHealthTests = new ArrayList<>();
                    if (!testInput.isBlank()) {
                        for (String id : testInput.split(",")) {
                            HealthTest healthTest = tst.stream()
                                    .filter(t -> t.getId() == Integer.parseInt(id.trim()))
                                    .findFirst()
                                    .orElse(null);
                            if (healthTest != null) {
                                selectedHealthTests.add(healthTest);
                            }
                        }
                    }
                    List<Pet> pets = Controller.getAllPets();
                    if (pets.isEmpty()) {
                        System.out.println("No pets found.");
                    } else {
                        System.out.println("\nList of all pets:");
                        for (Pet pet : pets) {
                            System.out.println(pet);
                        }
                    }
                    System.out.println("Enter Pet ID:");
                    int petId = scanner.nextInt();
                    System.out.println("Enter Appointment Date (dd-MM-yyyy):");
                    String date = scanner.nextLine();
                    System.out.println("Enter Appointment Time (HH:mm):");
                    String time = scanner.nextLine();
                    System.out.println("Enter Appointment Type (ROUTINE,BEHAVIOR,DENTAL,EMERGENCY,SURGICAL):");
                    String type = scanner.nextLine().toUpperCase();
                    try {
                        AppointmentType appType= AppointmentType.valueOf(type);
                        Appointment appointment = new Appointment( petId, currentVetId, date, time, appType, selectedHealthTests, selectedVaccines);
                        Controller.createAppointment(appointment);
                        System.out.println("Appointment added successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("The specified type is invalid.");
                    }
                    break;
                case "3":
                    List<Pet> pets1 = Controller.getAllPets();
                    if (pets1.isEmpty()) {
                        System.out.println("No pets found.");
                    } else {
                        System.out.println("\nList of all pets:");
                        for (Pet pet : pets1) {
                            System.out.println(pet);
                        }
                    }
                    System.out.println("Enter Pet ID:");
                    int pid = scanner.nextInt();
                    scanner.nextLine();
                    HealthRecord hrpet = Controller.getHealthRecordByPetId(pid);
                    if (hrpet == null) {
                        System.out.println("No health record found for the given Pet ID.");
                    } else {
                        System.out.println("\nHealth Record for Pet " + pid + ":");
                        System.out.println(hrpet);
                    }
                    break;
                case "4":
                    List<Pet> pets2 = Controller.getAllPets();
                    if (pets2.isEmpty()) {
                        System.out.println("No pets found.");
                    } else {
                        System.out.println("\nList of all pets:");
                        for (Pet pet : pets2) {
                            System.out.println(pet);
                        }
                    }
                    System.out.println("Enter Pet ID:");
                    int petid1 = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Diseases.txt: ");
                    List<Disease> diseases1 = Controller.getAllDiseases();
                    for (Disease disease : diseases1) {
                        System.out.println(disease.getId() + ": " + disease.getName());
                    }
                    System.out.println("Enter disease ID: ");
                    String diseaseInput = scanner.nextLine().trim();

                    if (!diseaseInput.isBlank()) {
                        try {
                            int diseaseId = Integer.parseInt(diseaseInput);

                            Disease selectedDisease = diseases1.stream()
                                    .filter(d -> d.getId() == diseaseId)
                                    .findFirst()
                                    .orElse(null);

                            if (selectedDisease != null) {
                                System.out.println("Disease selected: " + selectedDisease.getName());
                                Controller.addDiseaseToPet(petid1, selectedDisease);
                            } else {
                                System.out.println("Disease with ID " + diseaseId + " not found.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid ID input. Please enter a valid integer.");
                        }
                    } else {
                        System.out.println("No disease ID entered.");
                    }

                    break;
                case "5":
                    System.out.println("Enter Pet Id:");
                    int userId = Integer.parseInt(scanner.nextLine().trim());

                    System.out.println("Enter Notification Title:");
                    String title = scanner.nextLine().trim();

                    LocalDate today = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    String date2 = today.format(formatter);

                    System.out.println("Enter Notification Type (CANCELLATION, REMINDER, CONFIRMATION, VACATION, OTHER):");
                    String ntypeInput = scanner.nextLine().trim().toUpperCase();

                    try {
                        NotificationType type1 = NotificationType.valueOf(ntypeInput);
                        Notification notif=new Notification(userId, title, date2, type1);
                        Controller.sendNotification(notif);
                        System.out.println("Notification sent to Pet Id " + userId);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid Notification Type.");
                    }
                    break;
                case "6":
                    System.out.print("Enter start date (dd-MM-yyyy): ");
                    String startDateStr = scanner.nextLine();

                    System.out.print("Enter end date (dd-MM-yyyy): ");
                    String endDateStr = scanner.nextLine();
                    try {
                        Controller.cancelAppointmentsForVetInPeriod(currentVetId, startDateStr, endDateStr);
                        System.out.println("Appointments canceled successfully, notifications sent to the pets.");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "7":
                    List<Appointment> appointmentsByVet1 = Controller.getAppointmentsByVet(currentVetId);
                    if (appointmentsByVet1.isEmpty()) {
                        System.out.println("No appointments found for the given veterinarian.");
                    } else {
                        System.out.println("\nAppointments for Veterinarian ID " + currentVetId + ":");
                        for (Appointment app : appointmentsByVet1) {
                            System.out.println(app);
                        }
                        System.out.println("Enter Appointment ID:");
                        int appointmentId = scanner.nextInt();
                        scanner.nextLine();
                        Controller.deleteApp(appointmentId);
                        Controller.sendCancellationNotification(currentVetId);
                    }
                    break;
                case "8":
                    List<Notification> notifications = Controller.getNotificationsByUserId(currentVetId);

                    if (notifications.isEmpty()) {
                        System.out.println("You have no notifications.");
                    } else {
                        System.out.println("Your Notifications.txt:");
                        for (Notification notification : notifications) {
                            System.out.println(
                                    "Title: " + notification.getTitle() +
                                            " Date: " + notification.getDate());
                        }
                    }
                    break;
                case "9":
                    Controller.deleteVet(currentVetId);
                    System.out.println("Deleting Account...");
                    runningVetMenu=false;
                    break;
                case "0":
                    System.out.println("Logging Out...");
                    runningVetMenu=false;
                    break;
            }
        }
    }

}


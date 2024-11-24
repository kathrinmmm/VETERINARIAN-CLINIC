package Service;
import Utils.*;
import Model.*;
import Repository.IRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Service class that acts as an intermediary between the repositories and the user interface.
 * It provides methods for managing pets, veterinarians, appointments, health records, diseases, vaccines, tests, notifications, and user authentication.
 */
public class Service {
    private final IRepository<Pet> petRepository;
    private final IRepository<Veterinarian> vetRepository;
    private final IRepository<Appointment> appointmentRepository;
    private final IRepository<HealthRecord> healthRecordRepository;
    private final IRepository<Disease> diseaseRepository;
    private final IRepository<Vaccine> vaccineRepository;
    private final IRepository<Test> testRepository;
    private final IRepository<Notification> notificationRepository;
    private final IRepository<User> userRepository;
    private Integer loggedInUserId = null;
    /**
     * Constructs a Service object with the specified repositories for various entities.
     *
     * @param petRepo            The repository for managing pets.
     * @param vetRepo            The repository for managing veterinarians.
     * @param appRepo            The repository for managing appointments.
     * @param hrRepo             The repository for managing health records.
     * @param diseaseRepository  The repository for managing diseases.
     * @param vaccineRepository  The repository for managing vaccines.
     * @param testRepository     The repository for managing tests.
     * @param notificationRepository The repository for managing notifications.
     * @param userRepository     The repository for managing users.
     */
    public Service(IRepository<Pet> petRepo, IRepository<Veterinarian> vetRepo, IRepository<Appointment> appRepo, IRepository<HealthRecord> hrRepo, IRepository<Disease> diseaseRepository, IRepository<Vaccine> vaccineRepository, IRepository<Test> testRepository, IRepository<Notification> notificationRepository, IRepository<User> userRepository) {
        this.petRepository = petRepo;
        this.vetRepository = vetRepo;
        this.appointmentRepository = appRepo;
        this.healthRecordRepository = hrRepo;
        this.diseaseRepository =diseaseRepository;
        this.vaccineRepository = vaccineRepository;
        this.testRepository = testRepository;
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
        initializeRepositories();
        this.loggedInUserId = null;
    }
    /**
     * Initializes sample data in the repositories for pets, veterinarians, health records, diseases, vaccines, tests, and appointments.
     */
    public void initializeRepositories() {
        Pet pet1=new Pet(1,"Miti","Mitisor22","1234",AnimalType.CAT);
        Pet pet2=new Pet(2,"Bella","andreea25","123",AnimalType.DOG);
        petRepository.create(pet1);
        petRepository.create(pet2);

        Veterinarian vet1=new Veterinarian(1,"Aurel","aurell","123", Specialization.DENTISTRY);
        Veterinarian vet2=new Veterinarian(2,"Ana","anaaremere","123", Specialization.DENTISTRY);
        Veterinarian vet3=new Veterinarian(3,"Octavian","ovi12","123", Specialization.SURGERY);
        vetRepository.create(vet1);
        vetRepository.create(vet2);
        vetRepository.create(vet3);


        HealthRecord hr1=new HealthRecord(1,1);
        HealthRecord hr2=new HealthRecord(2,2);
        healthRecordRepository.create(hr1);
        healthRecordRepository.create(hr2);


        Vaccine vac1=new Vaccine(1,"Nobivac DAPPv",VaccineType.DISTEMPER);
        Vaccine vac2=new Vaccine(2,"Vanguard Plus 5 L4",VaccineType.DISTEMPER);
        Vaccine vac3=new Vaccine(3,"Canine Spectra 10",VaccineType.DISTEMPER);
        Vaccine vac4=new Vaccine(4,"PureVax FeLV",VaccineType.FELINE_LEUKEMIA);
        Vaccine vac5=new Vaccine(5,"Nobivac Feline 2-FeLV",VaccineType.FELINE_LEUKEMIA);
        Vaccine vac6=new Vaccine(6,"Fel-O-Vax Lv-K",VaccineType.FELINE_LEUKEMIA);
        Vaccine vac7=new Vaccine(7,"Nobivac Rabies",VaccineType.RABIES);
        Vaccine vac8=new Vaccine(8,"Imrab 3",VaccineType.RABIES);
        Vaccine vac9=new Vaccine(9,"Rabvac 3",VaccineType.RABIES);
        Vaccine vac10=new Vaccine(10,"C.Psittaci Inactivated Vaccine",VaccineType.PARROT_FEVER);
        Vaccine vac11=new Vaccine(11,"Chlamydia Vaccine",VaccineType.PARROT_FEVER);
        vaccineRepository.create(vac1);
        vaccineRepository.create(vac2);
        vaccineRepository.create(vac3);
        vaccineRepository.create(vac4);
        vaccineRepository.create(vac5);
        vaccineRepository.create(vac6);
        vaccineRepository.create(vac7);
        vaccineRepository.create(vac8);
        vaccineRepository.create(vac9);
        vaccineRepository.create(vac10);
        vaccineRepository.create(vac11);

        Disease dis1=new Disease(1,"Canine Parvo",DiseaseType.CANINE_PARAVOVIRUS);
        Disease dis2=new Disease(2,"Chlamydiosis",DiseaseType.PSITTACOSIS);
        Disease dis3=new Disease(3,"Hydrophobia",DiseaseType.RABIES);
        Disease dis4=new Disease(4,"FeLV",DiseaseType.FELINE_LEUKEMIA);
        Disease dis5=new Disease(5,"Lepto",DiseaseType.LEPTOSPIROSIS);
        Disease dis6=new Disease(6,"Borrelia Infection",DiseaseType.LYME_DISEASE);
        Disease dis7=new Disease(7,"Bacillus Anthracis Infection",DiseaseType.ANTHRAX);
        Disease dis8=new Disease(8,"Piroplasmosis",DiseaseType.BABESIOSIS);
        Disease dis9=new Disease(9,"Malta Fever",DiseaseType.BRUCELLOSIS);
        Disease dis10=new Disease(10,"Hardpad Disease",DiseaseType.CANINE_DISTEMPER);
        Disease dis11=new Disease(11,"Adenovirus Type 1",DiseaseType.CANINE_HEPATITIS);
        Disease dis12=new Disease(12,"Dirofilariasis",DiseaseType.HEARTWORM);
        Disease dis13=new Disease(13,"Bordetella Bronchiseptica",DiseaseType.KENNEL_COUGH);
        Disease dis14=new Disease(14,"Dermatophytosis",DiseaseType.RINGWORM);
        Disease dis15=new Disease(15,"Ehrlichiosis",DiseaseType.TICK_FEVER);
        diseaseRepository.create(dis1);
        diseaseRepository.create(dis2);
        diseaseRepository.create(dis3);
        diseaseRepository.create(dis4);
        diseaseRepository.create(dis5);
        diseaseRepository.create(dis6);
        diseaseRepository.create(dis7);
        diseaseRepository.create(dis8);
        diseaseRepository.create(dis9);
        diseaseRepository.create(dis10);
        diseaseRepository.create(dis11);
        diseaseRepository.create(dis12);
        diseaseRepository.create(dis13);
        diseaseRepository.create(dis14);
        diseaseRepository.create(dis15);


        Test test1=new Test(1,"IgE",TestType.ALLERGY);
        Test test2=new Test(2,"Intradermal Allergy Testing",TestType.ALLERGY);
        Test test3=new Test(3,"Patch Testing",TestType.ALLERGY);
        Test test4=new Test(4,"Complete Blood Count",TestType.BLOOD);
        Test test5=new Test(5,"Biochemistry Panel ",TestType.BLOOD);
        Test test6=new Test(6,"Coagulation Profile",TestType.BLOOD);
        Test test7=new Test(7,"Abdominal Radiographs ",TestType.X_RAY);
        Test test8=new Test(8,"Thoracic Radiographs ",TestType.X_RAY);
        Test test9=new Test(9,"Dental Radiographs ",TestType.X_RAY);
        Test test10=new Test(10,"Abdominal Ultrasound",TestType.ULTRASOUND);
        Test test11=new Test(11,"Cardiac Ultrasound",TestType.ULTRASOUND);
        Test test12=new Test(12,"Pregnancy Ultrasound",TestType.ULTRASOUND);
        testRepository.create(test1);
        testRepository.create(test2);
        testRepository.create(test3);
        testRepository.create(test4);
        testRepository.create(test5);
        testRepository.create(test6);
        testRepository.create(test7);
        testRepository.create(test8);
        testRepository.create(test9);
        testRepository.create(test10);
        testRepository.create(test11);
        testRepository.create(test12);


        Appointment app1 = new Appointment(1, 1, 3, "25-11-2024","18:30", AppointmentType.ROUTINE,List.of(test1), List.of(vac1));
        Appointment app2=new Appointment(2,2,2,"25-11-2024","12:00",AppointmentType.ROUTINE,List.of(), List.of());
        appointmentRepository.create(app1);
        appointmentRepository.create(app2);
        hr1.addAppointment(app1);
        hr2.addAppointment(app2);
    }
    /**
     * Deletes a pet by its ID from the pet repository.
     *
     * @param id The ID of the pet to be deleted.
     */

    public void deletePet(Integer id){
        petRepository.delete(id);
    }
    /**
     * Deletes a veterinarian by its ID from the veterinarian repository.
     *
     * @param id The ID of the veterinarian to be deleted.
     */
    public void deleteVet(Integer id){
        vetRepository.delete(id);
    }
    /**
     * Deletes an appointment by its ID from the appointment repository.
     *
     * @param id The ID of the appointment to be deleted.
     */
    public void deleteApp(Integer id){
        appointmentRepository.delete(id);
    }
    /**
     * Adds a new pet to the pet repository.
     *
     * @param pet The pet to be added.
     */
    public void addPet(Pet pet) {
        pet.setId(IdGenerator.generatePetId());
        petRepository.create(pet);
        HealthRecord healthRecord = new HealthRecord(IdGenerator.getHRId(), pet.getId() );
        healthRecordRepository.create(healthRecord);

    }
    /**
     * Adds a new veterinarian to the veterinarian repository.
     *
     * @param vet The veterinarian to be added.
     */

    public void addVeterinarian(Veterinarian vet) {
        vet.setId(IdGenerator.generateVetId());
        vetRepository.create(vet);
    }
    /**
     * Adds a new appointment to the appointment repository and associates it with the appropriate health record.
     *
     * @param app The appointment to be added.
     */
    public void addAppointment(Appointment app) {
        app.setAppId(IdGenerator.getAppId());
        appointmentRepository.create(app);
        HealthRecord healthRecord = healthRecordRepository.getAll().stream()
                .filter(hr -> hr.getPetId()==app.getPetId())
                .findFirst()
                .orElse(null);

        if (healthRecord != null) {
            healthRecord.addAppointment(app);
        }
    }
    /**
     * Adds a new disease to the disease repository.
     *
     * @param disease The disease to be added.
     */

    public void addDisease(Disease disease){
        disease.setId(IdGenerator.getDiseaseId());
        diseaseRepository.create(disease);
    }
    /**
     * Adds a disease to a specific pet's health record.
     *
     * @param petId   The ID of the pet.
     * @param disease The disease to be added.
     */
    public void addDiseaseToPet(int petId, Disease disease) {
        HealthRecord healthRecord = healthRecordRepository.getAll().stream()
                .filter(hr -> hr.getPetId()==(petId))
                .findFirst()
                .orElse(null);

        if (healthRecord != null) {
            healthRecord.addDisease(petId,disease);
        }
    }

    /**
     * Retrieves a pet's health record by its ID.
     *
     * @param petId The ID of the pet whose health record is to be retrieved.
     * @return The health record of the pet.
     */
    public HealthRecord getHealthRecordByPetId(int petId) {
        return healthRecordRepository.getAll().stream()
                .filter(hr -> hr.getPetId()==(petId))
                .findFirst()
                .orElse(null);
    }
    /**
     * Adds a new vaccine to the vaccine repository.
     *
     * @param vaccine The vaccine to be added.
     */

    public void addVaccine(Vaccine vaccine){
        vaccine.setId(IdGenerator.getVaccineId());
        vaccineRepository.create(vaccine);
    }
    /**
     * Adds a new test to the test repository.
     *
     * @param test The test to be added.
     */
    public void addTest(Test test){
        test.setId(IdGenerator.getTestId());
        testRepository.create(test);
    }
    /**
     * Adds a new notification to the notification repository.
     *
     * @param n The notification to be added.
     */
    public void addNotification(Notification n) {
        notificationRepository.create(n);
    }
    /**
     * Retrieves a list of all pets in the repository.
     *
     * @return A list of all pets.
     */

    public List<Pet> getAllPets() {
        return petRepository.getAll();
    }
    /**
     * Retrieves a list of all veterinarians in the repository.
     *
     * @return A list of all veterinarians.
     */

    public List<Veterinarian> getAllVeterinarians() {
        return vetRepository.getAll();
    }
    /**
     * Retrieves a list of all diseases in the repository.
     *
     * @return A list of all diseases.
     */

    public List<Disease> getAllDiseases(){
        return diseaseRepository.getAll();
    }
    /**
     * Retrieves a list of all tests in the repository.
     *
     * @return A list of all tests.
     */

    public List<Test> getAllTests(){
        return testRepository.getAll();
    }
    /**
     * Retrieves a list of all appointments in the repository.
     *
     * @return A list of all appointments.
     */
    public List<Appointment> getAllAppointments(){
        return appointmentRepository.getAll();
    }
    /**
     * Retrieves a list of all vaccines in the repository.
     *
     * @return A list of all vaccines.
     */
    public List<Vaccine> getAllVaccines(){
        return vaccineRepository.getAll();
    }
    /**
     * Retrieves a list of appointments for a specific date.
     *
     * @param date The date for which appointments are to be retrieved.
     * @return A list of appointments on the specified date.
     */
    public List<Appointment> getAppointmentsByDate(String date) {
        return appointmentRepository.getAll().stream()
                .filter(app -> app.getDate().equals(date))
                .collect(Collectors.toList());
    }
    /**
     * Retrieves a list of appointments for a specific pet.
     *
     * @param petId The ID of the pet for which appointments are to be retrieved.
     * @return A list of appointments for the specified pet.
     */
    public List<Appointment> getAppointmentsByPet(int petId) {
        return appointmentRepository.getAll().stream()
                .filter(app -> app.getPetId() == petId)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of appointments for a specific veterinarian.
     *
     * @param vetId The ID of the veterinarian for which appointments are to be retrieved.
     * @return A list of appointments for the specified veterinarian.
     */
    public List<Appointment> getAppointmentsByVet(int vetId) {
        return appointmentRepository.getAll().stream()
                .filter(app -> app.getVetId() == vetId)
                .collect(Collectors.toList());
    }
    /**
     * Retrieves a list of notifications for a specific user.
     *
     * @param userId The ID of the user for which notifications are to be retrieved.
     * @return A list of notifications for the specified user.
     */
    public List<Notification> getNotificationsByUserId(int userId) {
        return notificationRepository.getAll().stream()
                .filter(notification -> notification.getUserId() == userId)
                .collect(Collectors.toList());
    }
    /**
     * Sends a notification to a user.
     * This method creates a new notification with the provided message and notification type,
     * then stores it in the repository for the given user.
     *
     * @param userId The ID of the user to whom the notification will be sent.
     * @param message The content of the notification.
     * @param notificationType The type of notification (e.g., reminder, vacation, etc.).
     */
    public void sendNotificationToUser(Integer userId, String message, NotificationType notificationType) {
        Notification notification = new Notification(
                IdGenerator.getNotifId(),
                userId,
                message,
                LocalDate.now().toString(),
                notificationType
        );
        addNotification(notification);
    }

    /**
     * Clears all notifications for a specific user.
     * This method removes all notifications associated with the given user ID from the repository.
     *
     * @param userId The ID of the user whose notifications should be cleared.
     */
    public void clearNotificationsByUserId(int userId) {
        List<Notification> notificationsToRemove = notificationRepository.getAll().stream()
                .filter(notification -> notification.getUserId() == userId)
                .collect(Collectors.toList());

        for (Notification notification : notificationsToRemove) {
            notificationRepository.delete(notification.getId());
        }
    }
    /**
     * Retrieves all notifications of a specific type.
     * This method filters the notifications repository to find notifications that match the given type.
     *
     * @param type The type of notifications to retrieve (e.g., reminders, alerts, etc.).
     * @return A list of notifications matching the specified type.
     */
    public List<Notification> getNotificationsByType(NotificationType type) {
        return notificationRepository.getAll().stream()
                .filter(notification -> notification.getNotificationType() == type)
                .collect(Collectors.toList());
    }
    /**
     * Sends reminders for upcoming appointments for a specific pet.
     * This method checks all appointments for the specified pet and returns those that are within the next 3 days.
     *
     * @param petId The ID of the pet for which appointment reminders should be sent.
     * @return A list of appointments that are scheduled within the next 3 days.
     */
    public List<Appointment> sendAppointmentReminders(Integer petId) {
        List<Appointment> appointments = getAllAppointments();
        LocalDate today = LocalDate.now();

        List<Appointment> upcomingAppointments = appointments.stream()
                .filter(app -> app.getPetId() == petId)
                .filter(app -> {
                    LocalDate appDate = LocalDate.parse(app.getDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    return !appDate.isBefore(today) && appDate.isBefore(today.plusDays(3));
                })
                .collect(Collectors.toList());

        return upcomingAppointments;
    }
    /**
     * Sends a vacation notification to a user.
     * This method creates a notification informing the user that their pet's next scheduled appointment
     * is during the vacation period and sends it to the user.
     *
     * @param userId The ID of the user to be notified.
     */
    private void sendVacationNotification(Integer userId) {
        String message = "Vacation: Your pet's next scheduled appointment is during our vacation period.";
        sendNotificationToUser(userId, message, NotificationType.VACATION);
    }

    /**
     * Cancels all appointments for a veterinarian within a specified date range.
     * This method identifies and deletes appointments for a given veterinarian that fall within the specified start and end dates.
     * If appointments are canceled, a vacation notification is sent to affected users.
     *
     * @param vetId The ID of the veterinarian whose appointments should be canceled.
     * @param startDateStr The start date of the period in "dd-MM-yyyy" format.
     * @param endDateStr The end date of the period in "dd-MM-yyyy" format.
     */
    public void cancelAppointmentsForVetInPeriod(Integer vetId, String startDateStr, String endDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);

        List<Appointment> appointmentsToCancel = appointmentRepository.getAll().stream()
                .filter(app -> app.getVetId() == vetId)
                .filter(app -> {
                    LocalDate appointmentDate = LocalDate.parse(app.getDate(), formatter);
                    return !appointmentDate.isBefore(startDate) && !appointmentDate.isAfter(endDate);
                })
                .collect(Collectors.toList());


        for (Appointment app : appointmentsToCancel) {
            appointmentRepository.delete(app.getAppId());
            Integer petId=app.getPetId();
            sendVacationNotification(petId);
        }
    }

    /**
     * Allows a user to log in with a username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The logged-in user if authentication is successful, otherwise null.
     */
    public Object login(String username, String password) throws Exception {
        Pet pet = petRepository.getAll().stream()
                .filter(p -> p.getUsername().equals(username) && p.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (pet != null) {
            loggedInUserId = pet.getId();
            return pet;
        }

        Veterinarian vet = vetRepository.getAll().stream()
                .filter(v -> v.getUsername().equals(username) && v.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (vet != null) {
            loggedInUserId = vet.getId();
            return vet;
        }

        throw new Exception("Invalid username or password.");
    }

    /**
     * Retrieves the logged-in user's ID.
     *
     * @return The ID of the logged-in user.
     */
    public Integer getLoggedInUserId() {
        return loggedInUserId;
    }

    public void logout() {
        loggedInUserId = null;
    }



}

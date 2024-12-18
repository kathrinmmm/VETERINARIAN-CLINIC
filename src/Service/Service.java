package Service;
import Utils.*;
import Model.*;
import Repository.IRepository;
import Exception.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.LocalDate;
import java.util.stream.Collectors;

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
    private final IRepository<HealthTest> testRepository;
    private final IRepository<Notification> notificationRepository;
    //private final IRepository<User> userRepository;
    private final IRepository<Pet_Disease> petDiseaseRepository;
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
    //* @param userRepository     The repository for managing users.
     */
    public Service(IRepository<Pet> petRepo, IRepository<Veterinarian> vetRepo, IRepository<Appointment> appRepo, IRepository<HealthRecord> hrRepo, IRepository<Disease> diseaseRepository, IRepository<Vaccine> vaccineRepository, IRepository<HealthTest> testRepository, IRepository<Notification> notificationRepository, IRepository<Pet_Disease> petDiseaseRepository ) {
        this.petRepository = petRepo;
        this.vetRepository = vetRepo;
        this.appointmentRepository = appRepo;
        this.healthRecordRepository = hrRepo;
        this.diseaseRepository =diseaseRepository;
        this.vaccineRepository = vaccineRepository;
        this.testRepository = testRepository;
        this.notificationRepository = notificationRepository;
        this.petDiseaseRepository= petDiseaseRepository;
        //this.userRepository = userRepository;
        //initializeRepositories();
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


        HealthTest healthTest1 =new HealthTest(1,"IgE",TestType.ALLERGY);
        HealthTest healthTest2 =new HealthTest(2,"Intradermal Allergy Testing",TestType.ALLERGY);
        HealthTest healthTest3 =new HealthTest(3,"Patch Testing",TestType.ALLERGY);
        HealthTest healthTest4 =new HealthTest(4,"Complete Blood Count",TestType.BLOOD);
        HealthTest healthTest5 =new HealthTest(5,"Biochemistry Panel ",TestType.BLOOD);
        HealthTest healthTest6 =new HealthTest(6,"Coagulation Profile",TestType.BLOOD);
        HealthTest healthTest7 =new HealthTest(7,"Abdominal Radiographs ",TestType.X_RAY);
        HealthTest healthTest8 =new HealthTest(8,"Thoracic Radiographs ",TestType.X_RAY);
        HealthTest healthTest9 =new HealthTest(9,"Dental Radiographs ",TestType.X_RAY);
        HealthTest healthTest10 =new HealthTest(10,"Abdominal Ultrasound",TestType.ULTRASOUND);
        HealthTest healthTest11 =new HealthTest(11,"Cardiac Ultrasound",TestType.ULTRASOUND);
        HealthTest healthTest12 =new HealthTest(12,"Pregnancy Ultrasound",TestType.ULTRASOUND);
        testRepository.create(healthTest1);
        testRepository.create(healthTest2);
        testRepository.create(healthTest3);
        testRepository.create(healthTest4);
        testRepository.create(healthTest5);
        testRepository.create(healthTest6);
        testRepository.create(healthTest7);
        testRepository.create(healthTest8);
        testRepository.create(healthTest9);
        testRepository.create(healthTest10);
        testRepository.create(healthTest11);
        testRepository.create(healthTest12);


        Appointment app1 = new Appointment(1, 1, 3, "2024-12-10","18:30", AppointmentType.ROUTINE,List.of(healthTest1), List.of(vac1));
        Appointment app2=new Appointment(2,2,2,"2024-12-17","12:00",AppointmentType.ROUTINE,List.of(), List.of());
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

    public void deletePet(Integer id) throws EntityNotFoundException {

        Pet pet = petRepository.getAll().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Pet with id " + id + " not found"));
        petRepository.delete(id);
    }
    /**
     * Deletes a veterinarian by its ID from the veterinarian repository.
     *
     * @param id The ID of the veterinarian to be deleted.
     */
    public void deleteVet(Integer id) throws EntityNotFoundException, ValidationException {
        if (id == null || id <= 0) {
            throw new ValidationException("Veterinarian ID must be valid.");
        }

        Veterinarian vet = vetRepository.getAll().stream()
                .filter(v -> v.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Veterinarian with ID " + id + " not found"));

        vetRepository.delete(id);
    }
    /**
     * Deletes an appointment by its ID from the appointment repository.
     *
     * @param id The ID of the appointment to be deleted.
     */
    public void deleteApp(Integer id) throws EntityNotFoundException, ValidationException {
        if (id == null || id <= 0) {
            throw new ValidationException("Appointment ID must be valid.");
        }

        Appointment appointment = appointmentRepository.getAll().stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Appointment with ID " + id + " not found"));

        appointmentRepository.delete(id);
    }
    /**
     * Adds a new pet to the pet repository.
     *
     * @param pet The pet to be added.
     */
    public void addPet(Pet pet) throws ValidationException,LogicException {
        if (pet == null) {
            throw new ValidationException("Pet object cannot be null.");
        }
        if (pet.getUsername() == null || pet.getUsername().trim().isEmpty()) {
            throw new ValidationException("Username cannot be null or empty.");
        }
        if (pet.getName() == null || pet.getName().trim().isEmpty()) {
            throw new ValidationException("Pet name cannot be null or empty.");
        }
        if (pet.getAnimalType() == null) {
            throw new ValidationException("Pet animal type cannot be null.");
        }

        Optional<Pet> existingPet = petRepository.getAll().stream()
                .filter(existing -> existing.getUsername().equalsIgnoreCase(pet.getUsername()))
                .findFirst();

        if (existingPet.isPresent()) {
            throw new LogicException("A pet with the same username already exists: " + pet.getUsername());
        }

        pet.setId(IdGenerator.generatePetId());
        petRepository.create(pet);

        HealthRecord healthRecord = new HealthRecord(IdGenerator.getHRId(), pet.getId());
        healthRecordRepository.create(healthRecord);

    }
    /**
     * Adds a new veterinarian to the veterinarian repository.
     *
     * @param vet The veterinarian to be added.
     */

    public void addVeterinarian(Veterinarian vet) throws ValidationException, LogicException{
        if (vet.getName() == null || vet.getName().isEmpty()) {
            throw new ValidationException("Veterinarian name cannot be null or empty");
        }
        if (vet.getSpecialization() == null) {
            throw new ValidationException("Veterinarian specialization cannot be null");
        }
        Optional<Veterinarian> existingVet = vetRepository.getAll().stream()
                .filter(existing -> existing.getUsername().equalsIgnoreCase(vet.getUsername()))
                .findFirst();

        if (existingVet.isPresent()) {
            throw new LogicException("A vet with the same username already exists: " + vet.getUsername());
        }


        vet.setId(IdGenerator.generateVetId());
        vetRepository.create(vet);
    }
    /**
     * Adds a new appointment to the appointment repository and associates it with the appropriate health record.
     *
     * @param app The appointment to be added.
     */
    public void addAppointment(Appointment app) throws LogicException, ValidationException, EntityNotFoundException {
        if (app == null) {
            throw new ValidationException("Appointment object cannot be null.");
        }
        if (app.getPetId() <= 0) {
            throw new ValidationException("PetId must be a valid positive integer.");
        }
        if (app.getVetId() <= 0) {
            throw new ValidationException("VetId must be a valid positive integer.");
        }
        if (app.getDate() == null) {
            throw new ValidationException("Appointment date cannot be null.");
        }
        if (app.getTime() == null) {
            throw new ValidationException("Appointment time cannot be null.");
        }

        try {
            LocalDate.parse(app.getDate(), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new ValidationException("Invalid date format. Expected yyyy-MM-dd.");
        }

        try {
            LocalTime.parse(app.getTime(), DateTimeFormatter.ofPattern("HH:mm"));
        } catch (DateTimeParseException e) {
            throw new ValidationException("Invalid time format. Expected HH:mm.");
        }

        boolean petExists = petRepository.getAll().stream()
                .anyMatch(pet -> pet.getId().equals(app.getPetId()));
        if (!petExists) {
            throw new EntityNotFoundException("Pet with ID " + app.getPetId() + " does not exist.");
        }

        boolean vetExists = vetRepository.getAll().stream()
                .anyMatch(vet -> vet.getId().equals(app.getVetId()));
        if (!vetExists) {
            throw new EntityNotFoundException("Veterinarian with ID " + app.getVetId() + " does not exist.");
        }

        boolean isDuplicate = appointmentRepository.getAll().stream()
                .anyMatch(existingApp -> existingApp.getDate().equals(app.getDate())
                        && existingApp.getTime().equals(app.getTime())
                        && existingApp.getVetId() == app.getVetId());

        if (isDuplicate) {
            throw new LogicException("An appointment already exists for the same vet at the given date and time.");
        }

        app.setAppId(IdGenerator.getAppId());

        appointmentRepository.create(app);

        HealthRecord healthRecord = healthRecordRepository.getAll().stream()
                .filter(hr -> hr.getPetId() == app.getPetId())
                .findFirst()
                .orElse(null);

        if (healthRecord != null) {
            healthRecord.addAppointment(app);

            healthRecordRepository.update(healthRecord.getId(), healthRecord);
        } else {
            throw new LogicException("HealthRecord not found for PetId: " + app.getPetId());
        }
    }
    /**
     * Adds a new disease to the disease repository.
     *
     * @param disease The disease to be added.
     */

    public void addDisease(Disease disease) throws ValidationException, LogicException{
        if (disease == null) {
            throw new ValidationException("Disease object cannot be null.");
        }
        if (disease.getName() == null || disease.getName().trim().isEmpty()) {
            throw new ValidationException("Disease name cannot be null or empty.");
        }
        if (disease.getDiseaseType() == null) {
            throw new ValidationException("Disease type cannot be null.");
        }

        boolean exists = diseaseRepository.getAll().stream()
                .anyMatch(existingDisease -> existingDisease.getName().equalsIgnoreCase(disease.getName()));
        if (exists) {
            throw new LogicException("Disease with name '" + disease.getName() + "' already exists.");
        }

        disease.setId(IdGenerator.getDiseaseId());
        diseaseRepository.create(disease);
    }
    /**
     * Adds a disease to a specific pet's health record.
     *
     * @param petId   The ID of the pet.
     * @param disease The disease to be added.
     */
    public void addDiseaseToPet(int petId, Disease disease) throws EntityNotFoundException, ValidationException, LogicException{
        if (petId <= 0) {
            throw new ValidationException("Invalid pet ID.");
        }

        if (disease == null || disease.getId() == null) {
            throw new ValidationException("Disease object or ID cannot be null.");
        }

        HealthRecord healthRecord = healthRecordRepository.getAll().stream()
                .filter(hr -> hr.getPetId()==petId)
                .findFirst()
                .orElse(null);

        if (healthRecord == null) {
            throw new EntityNotFoundException("HealthRecord not found for petId: " + petId);
        }

        boolean diseaseAlreadyAdded = healthRecord.getPetDiseases().stream()
                .anyMatch(existingDisease -> existingDisease.getId().equals(disease.getId()));

        if (diseaseAlreadyAdded) {
            throw new LogicException("The disease is already associated with the pet.");
        }

        healthRecord.addDisease(petId, disease);
        healthRecordRepository.update(healthRecord.getId(), healthRecord);

        Pet_Disease petDisease = new Pet_Disease();
        petDisease.setId(IdGenerator.getNextPetDiseaseId());
        petDisease.setPetid(petId);
        petDisease.setDisease(disease);

        petDiseaseRepository.create(petDisease);
    }

    /**
     * Retrieves a pet's health record by its ID.
     *
     * @param petId The ID of the pet whose health record is to be retrieved.
     * @return The health record of the pet.
     */
    public HealthRecord getHealthRecordByPetId(int petId) throws EntityNotFoundException {
        return healthRecordRepository.getAll().stream()
                .filter(hr -> hr.getPetId() == petId)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("HealthRecord not found for petId: " + petId));
    }

    /**
     * Adds a new vaccine to the vaccine repository.
     *
     * @param vaccine The vaccine to be added.
     */

    public void addVaccine(Vaccine vaccine)throws LogicException, ValidationException{
        if (vaccine == null) {
            throw new ValidationException("Vaccine object cannot be null.");
        }
        if (vaccine.getName() == null || vaccine.getName().trim().isEmpty()) {
            throw new ValidationException("Vaccine name cannot be null or empty.");
        }
        if (vaccine.getVaccineType() == null) {
            throw new ValidationException("Vaccie type cannot be null.");
        }

        boolean exists = vaccineRepository.getAll().stream()
                .anyMatch(existingVacc -> existingVacc.getName().equalsIgnoreCase(vaccine.getName()));
        if (exists) {
            throw new LogicException("Vaccine with name '" + vaccine.getName() + "' already exists.");
        }
        vaccine.setId(IdGenerator.getVaccineId());
        vaccineRepository.create(vaccine);
    }
    /**
     * Adds a new test to the test repository.
     *
     * @param healthTest The test to be added.
     */
    public void addTest(HealthTest healthTest) throws LogicException, ValidationException{
        if (healthTest == null) {
            throw new ValidationException("Test object cannot be null.");
        }
        if (healthTest.getName() == null || healthTest.getName().trim().isEmpty()) {
            throw new ValidationException("Test name cannot be null or empty.");
        }
        if (healthTest.getTestType() == null) {
            throw new ValidationException("Test type cannot be null.");
        }

        boolean exists = testRepository.getAll().stream()
                .anyMatch(existingVacc -> existingVacc.getName().equalsIgnoreCase(healthTest.getName()));
        if (exists) {
            throw new LogicException("Test with name '" + healthTest.getName() + "' already exists.");
        }
        healthTest.setId(IdGenerator.getTestId());
        testRepository.create(healthTest);
    }

    private boolean isValidDate(String date) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(date, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    /**
     * Adds a new notification to the notification repository.
     *
     * @param n The notification to be added.
     */
    public void addNotification(Notification n)throws ValidationException, LogicException {
        if (n.getUserId() <= 0) {
            throw new ValidationException("Invalid User ID.");
        }
        if (n.getTitle() == null || n.getTitle().trim().isEmpty()) {
            throw new ValidationException("Notification title cannot be null or empty.");
        }
        if (n.getDate() == null || !isValidDate(n.getDate())) {
            throw new ValidationException("Notification date must be a valid date in the format yyyy-MM-dd.");
        }
        if (n.getNotificationType() == null) {
            throw new ValidationException("Notification type cannot be null.");
        }
        notificationRepository.create(n);
    }
    /**
     * Retrieves a list of all pets in the repository.
     *
     * @return A list of all pets.
     */

    public List<Pet> getAllPets()throws EntityNotFoundException {
        List<Pet> pets = petRepository.getAll();
        if (pets == null || pets.isEmpty()) {
            throw new EntityNotFoundException("No pets found in the system.");
        }
        return pets;
    }
    /**
     * Retrieves a list of all veterinarians in the repository.
     *
     * @return A list of all veterinarians.
     */

    public List<Veterinarian> getAllVeterinarians() throws EntityNotFoundException{
        List<Veterinarian> veterinarians = vetRepository.getAll();
        if (veterinarians == null || veterinarians.isEmpty()) {
            throw new EntityNotFoundException("No veterinarians found in the system.");
        }
        return veterinarians;
    }
    /**
     * Retrieves a list of all diseases in the repository.
     *
     * @return A list of all diseases.
     */

    public List<Disease> getAllDiseases()throws EntityNotFoundException{
        List<Disease> diseases = diseaseRepository.getAll();
        if (diseases == null || diseases.isEmpty()) {
            throw new EntityNotFoundException("No diseases found in the system.");
        }
        return diseases;
    }
    /**
     * Retrieves a list of all tests in the repository.
     *
     * @return A list of all tests.
     */

    public List<HealthTest> getAllTests()throws EntityNotFoundException{
        List<HealthTest> healthTests = testRepository.getAll();
        if (healthTests == null || healthTests.isEmpty()) {
            throw new EntityNotFoundException("No tests found in the system.");
        }
        return healthTests;
    }
    /**
     * Retrieves a list of all appointments in the repository.
     *
     * @return A list of all appointments.
     */
    public List<Appointment> getAllAppointments()throws EntityNotFoundException{
        List<Appointment> appointments = appointmentRepository.getAll();
        if (appointments == null || appointments.isEmpty()) {
            throw new EntityNotFoundException("No appointments found in the system.");
        }
        return appointments;
    }
    /**
     * Retrieves a list of all vaccines in the repository.
     *
     * @return A list of all vaccines.
     */
    public List<Vaccine> getAllVaccines()throws EntityNotFoundException{
        List<Vaccine> vaccines = vaccineRepository.getAll();
        if (vaccines == null || vaccines.isEmpty()) {
            throw new EntityNotFoundException("No vaccines found in the system.");
        }
        return vaccines;
    }
    /**
     * Retrieves a list of appointments for a specific date.
     *
     * @param date The date for which appointments are to be retrieved.
     * @return A list of appointments on the specified date.
     */
    public List<Appointment> getAppointmentsByDate(String date) throws EntityNotFoundException{
        List<Appointment> appointments = appointmentRepository.getAll().stream()
                .filter(app -> app.getDate().equals(date))
                .collect(Collectors.toList());

        if (appointments.isEmpty()) {
            throw new EntityNotFoundException("No appointments found for the specified date: " + date);
        }

        return appointments;
    }
    /**
     * Retrieves a list of appointments for a specific pet.
     *
     * @param petId The ID of the pet for which appointments are to be retrieved.
     * @return A list of appointments for the specified pet.
     */
    public List<Appointment> getAppointmentsByPet(int petId) throws EntityNotFoundException{
        List<Appointment> appointments = appointmentRepository.getAll().stream()
                .filter(app -> app.getPetId() == petId)
                .collect(Collectors.toList());

        if (appointments.isEmpty()) {
            throw new EntityNotFoundException("No appointments found for petId: " + petId);
        }

        return appointments;
    }

    /**
     * Retrieves a list of appointments for a specific veterinarian.
     *
     * @param vetId The ID of the veterinarian for which appointments are to be retrieved.
     * @return A list of appointments for the specified veterinarian.
     */
    public List<Appointment> getAppointmentsByVet(int vetId) throws EntityNotFoundException{
        List<Appointment> appointments = appointmentRepository.getAll().stream()
                .filter(app -> app.getVetId() == vetId)
                .collect(Collectors.toList());

        if (appointments.isEmpty()) {
            throw new EntityNotFoundException("No appointments found for vetId: " + vetId);
        }

        return appointments;
    }
    /**
     * Retrieves a list of notifications for a specific user.
     *
     * @param userId The ID of the user for which notifications are to be retrieved.
     * @return A list of notifications for the specified user.
     */
    public List<Notification> getNotificationsByUserId(int userId) throws EntityNotFoundException{
        List<Notification> notifications = notificationRepository.getAll().stream()
                .filter(notification -> notification.getUserId() == userId)
                .collect(Collectors.toList());

        if (notifications.isEmpty()) {
            throw new EntityNotFoundException("No notifications found for userId: " + userId);
        }

        return notifications;
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
    public void sendNotificationToUser(Integer userId, String message, NotificationType notificationType){
        try {
            Notification notification = new Notification(
                    IdGenerator.getNotifId(),
                    userId,
                    message,
                    LocalDate.now().toString(),
                    notificationType
            );
            addNotification(notification);
        }catch (ValidationException e){System.out.println("Validation Exception: " + e.getMessage());}
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
    public List<Notification> getNotificationsByType(NotificationType type) throws EntityNotFoundException{
        List<Notification> notifications = notificationRepository.getAll().stream()
                .filter(notification -> notification.getNotificationType() == type)
                .collect(Collectors.toList());

        if (notifications.isEmpty()) {
            throw new EntityNotFoundException("No notifications found for the specified type: " + type);
        }

        return notifications;
    }
    /**
     * Sends reminders for upcoming appointments for a specific pet.
     * This method checks all appointments for the specified pet and returns those that are within the next 3 days.
     *
     * @param petId The ID of the pet for which appointment reminders should be sent.
     * @return A list of appointments that are scheduled within the next 3 days.
     */
    public List<Appointment> sendAppointmentReminders(Integer petId) throws EntityNotFoundException{
        List<Appointment> appointments = getAllAppointments();

        if (appointments == null || appointments.isEmpty()) {
            throw new EntityNotFoundException("No appointments found for petId: " + petId);
        }

        LocalDate today = LocalDate.now();
        List<Appointment> upcomingAppointments = appointments.stream()
                .filter(app -> app.getPetId() == petId)
                .filter(app -> {
                    LocalDate appDate = LocalDate.parse(app.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    return !appDate.isBefore(today) && appDate.isBefore(today.plusDays(3));
                })
                .collect(Collectors.toList());

        if (upcomingAppointments.isEmpty()) {
            throw new EntityNotFoundException("No upcoming appointments found for petId: " + petId);
        }

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
     * @param startDateStr The start date of the period in "yyyy-MM-dd" format.
     * @param endDateStr The end date of the period in "yyyy-MM-dd" format.
     */
    public void cancelAppointmentsForVetInPeriod(Integer vetId, String startDateStr, String endDateStr) throws EntityNotFoundException,ValidationException{
        if (vetId == null || vetId <= 0) {
            throw new ValidationException("Invalid veterinarian ID.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate;
        LocalDate endDate;

        try {
            startDate = LocalDate.parse(startDateStr, formatter);
            endDate = LocalDate.parse(endDateStr, formatter);
        } catch (DateTimeParseException e) {
            throw new ValidationException("Invalid date format, expected yyyy-MM-dd.");
        }

        List<Appointment> appointmentsToCancel = appointmentRepository.getAll().stream()
                .filter(app -> app.getVetId() == vetId)
                .filter(app -> {
                    LocalDate appointmentDate = LocalDate.parse(app.getDate(), formatter);
                    return !appointmentDate.isBefore(startDate) && !appointmentDate.isAfter(endDate);
                })
                .collect(Collectors.toList());

        if (appointmentsToCancel.isEmpty()) {
            throw new EntityNotFoundException("No appointments found for the veterinarian in the specified date range.");
        }

        for (Appointment app : appointmentsToCancel) {
            appointmentRepository.delete(app.getAppId());
            Integer petId = app.getPetId();
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
     * Sorts veterinarians by their specialization in alphabetical order.
     *
     * @return A list of veterinarians sorted by specialization.
     */
    public List<Veterinarian> sortVeterinariansBySpecialization() throws EntityNotFoundException {
        List<Veterinarian> veterinarians = vetRepository.getAll();
        if (veterinarians.isEmpty()) {
            throw new EntityNotFoundException("No veterinarians found.");
        }

        return veterinarians.stream()
                .sorted(Comparator.comparing(Veterinarian::getSpecialization))
                .collect(Collectors.toList());
    }

    /**
     * Sorts appointments by date in chronological order.
     *
     * @return A list of appointments sorted by date.
     */
    public List<Appointment> sortAppointmentsByDate() throws EntityNotFoundException {
        List<Appointment> appointments = appointmentRepository.getAll();
        if (appointments.isEmpty()) {
            throw new EntityNotFoundException("No appointments found.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return appointments.stream()
                .sorted((a1, a2) -> {
                    LocalDate date1 = LocalDate.parse(a1.getDate(), formatter);
                    LocalDate date2 = LocalDate.parse(a2.getDate(), formatter);
                    return date1.compareTo(date2);
                })
                .collect(Collectors.toList());
    }

    /**
     * Retrieves appointments within a specified date range.
     *
     * @param startDateStr The start date in "yyyy-MM-dd" format.
     * @param endDateStr The end date in "yyyy-MM-dd" format.
     * @return A list of appointments within the specified date range.
     * @throws Exception if the date format is invalid.
     */
    public List<Appointment> getAppointmentsInDateRange(String startDateStr, String endDateStr) throws ValidationException, EntityNotFoundException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate;
        LocalDate endDate;

        try {
            startDate = LocalDate.parse(startDateStr, formatter);
            endDate = LocalDate.parse(endDateStr, formatter);
        } catch (DateTimeParseException e) {
            throw new ValidationException("Invalid date format, expected yyyy-MM-dd.");
        }

        List<Appointment> appointments = appointmentRepository.getAll().stream()
                .filter(appointment -> {
                    LocalDate appointmentDate = LocalDate.parse(appointment.getDate(), formatter);
                    return !appointmentDate.isBefore(startDate) && !appointmentDate.isAfter(endDate);
                })
                .collect(Collectors.toList());

        if (appointments.isEmpty()) {
            throw new EntityNotFoundException("No appointments found in the specified date range.");
        }

        return appointments;
    }

}

package Controller;

import Model.*;
import Service.Service;
import Utils.NotificationType;

import java.util.List;

/**
 * The Controller class acts as an intermediary between the user interface and the service layer.
 * It processes user inputs and communicates with the service layer to perform actions related to pets,
 * diseases, veterinarians, appointments, tests, vaccines, and notifications.
 *
 * @author Cristiana Bleoca
 * @version 1.0
 * @since 2024-11-24
 */
public class Controller {

    private final Service service;

    /**
     * Constructs a Controller with the given service.
     *
     * @param service the service layer that handles the core business logic
     */
    public Controller(Service service) {
        this.service = service;
    }

    /**
     * Creates a new pet and adds it to the repository.
     *
     * @param pet the pet to be added
     */
    public void createPet(Pet pet) {
        service.addPet(pet);
    }

    /**
     * Creates a new disease and adds it to the repository.
     *
     * @param disease the disease to be added
     */
    public void createDisease(Disease disease) {
        service.addDisease(disease);
    }

    /**
     * Creates a new veterinarian and adds it to the repository.
     *
     * @param veterinarian the veterinarian to be added
     */
    public void createVet(Veterinarian veterinarian) {
        service.addVeterinarian(veterinarian);
    }

    /**
     * Creates a new appointment and adds it to the repository.
     *
     * @param appointment the appointment to be added
     */
    public void createAppointment(Appointment appointment) {
        service.addAppointment(appointment);
    }

    /**
     * Sends a notification and adds it to the repository.
     *
     * @param notification the notification to be sent
     */
    public void sendNotification(Notification notification) {
        service.addNotification(notification);
    }

    /**
     * Creates a new test and adds it to the repository.
     *
     * @param healthTest the test to be added
     */
    public void createTest(HealthTest healthTest) {
        service.addTest(healthTest);
    }

    /**
     * Creates a new vaccine and adds it to the repository.
     *
     * @param vaccine the vaccine to be added
     */
    public void createVaccine(Vaccine vaccine) {
        service.addVaccine(vaccine);
    }

    /**
     * Retrieves all veterinarians from the service.
     *
     * @return a list of all veterinarians
     */
    public List<Veterinarian> getAllVeterinarians() {
        return service.getAllVeterinarians();
    }

    /**
     * Retrieves all pets from the service.
     *
     * @return a list of all pets
     */
    public List<Pet> getAllPets() {
        return service.getAllPets();
    }

    /**
     * Retrieves all diseases from the service.
     *
     * @return a list of all diseases
     */
    public List<Disease> getAllDiseases() {
        return service.getAllDiseases();
    }

    /**
     * Retrieves all tests from the service.
     *
     * @return a list of all tests
     */
    public List<HealthTest> getAllTests() {
        return service.getAllTests();
    }

    /**
     * Retrieves all appointments from the service.
     *
     * @return a list of all appointments
     */
    public List<Appointment> getAllAppointments() {
        return service.getAllAppointments();
    }

    /**
     * Retrieves all vaccines from the service.
     *
     * @return a list of all vaccines
     */
    public List<Vaccine> getAllVaccines() {
        return service.getAllVaccines();
    }

    /**
     * Retrieves all appointments for a specific date.
     *
     * @param date the date for which to retrieve appointments
     * @return a list of appointments on the specified date
     */
    public List<Appointment> getAppointmentsByDate(String date) {
        return service.getAppointmentsByDate(date);
    }

    /**
     * Retrieves all appointments for a specific pet.
     *
     * @param petId the ID of the pet whose appointments are to be retrieved
     * @return a list of appointments for the specified pet
     */
    public List<Appointment> getAppointmentsByPet(int petId) {
        return service.getAppointmentsByPet(petId);
    }

    /**
     * Retrieves all appointments for a specific veterinarian.
     *
     * @param vetId the ID of the veterinarian whose appointments are to be retrieved
     * @return a list of appointments for the specified veterinarian
     */
    public List<Appointment> getAppointmentsByVet(int vetId) {
        return service.getAppointmentsByVet(vetId);
    }

    /**
     * Retrieves the health record of a pet by its ID.
     *
     * @param petId the ID of the pet whose health record is to be retrieved
     * @return the health record of the specified pet
     */
    public HealthRecord getHealthRecordByPetId(int petId) {
        return service.getHealthRecordByPetId(petId);
    }

    /**
     * Adds a disease to a pet's health record.
     *
     * @param petId the ID of the pet
     * @param disease the disease to be added to the pet
     */
    public void addDiseaseToPet(int petId, Disease disease) {
        service.addDiseaseToPet(petId, disease);
    }

    /**
     * Retrieves notifications for a specific user by their ID.
     *
     * @param userId the ID of the user whose notifications are to be retrieved
     * @return a list of notifications for the specified user
     */
    public List<Notification> getNotificationsByUserId(int userId) {
        return service.getNotificationsByUserId(userId);
    }

    /**
     * Sends a notification to a user with a specified message and notification type.
     *
     * @param userId the ID of the user
     * @param message the message to be sent
     * @param notificationType the type of notification
     */
    public void sendNotificationToUser(Integer userId, String message, NotificationType notificationType) {
        service.sendNotificationToUser(userId, message, notificationType);
    }

    /**
     * Displays upcoming appointments for a pet within the next 3 days.
     *
     * @param petId the ID of the pet whose upcoming appointments are to be displayed
     */
    public void showUpcomingAppointments(Integer petId) {
        List<Appointment> upcomingAppointments = service.sendAppointmentReminders(petId); // Calls service method to get upcoming appointments

        if (!upcomingAppointments.isEmpty()) {
            System.out.println("Upcoming appointments for pet ID " + petId + ":");
            upcomingAppointments.forEach(app -> {
                System.out.println("Appointment on " + app.getDate() + " at " + app.getTime());
            });
        } else {
            System.out.println("No upcoming appointments for pet ID " + petId + " in the next 3 days.");
        }
    }

    /**
     * Sends a cancellation notification to a user.
     *
     * @param userId the ID of the user to be notified
     */
    public void sendCancellationNotification(Integer userId) {
        String message = "Cancellation: Your appointment for your pet has been cancelled.";
        service.sendNotificationToUser(userId, message, NotificationType.CANCELLATION);
    }

    /**
     * Sends a confirmation notification to a user.
     *
     * @param userId the ID of the user to be notified
     */
    public void sendConfirmationNotification(Integer userId) {
        String message = "Confirmation: Your appointment for your pet has been confirmed.";
        service.sendNotificationToUser(userId, message, NotificationType.CONFIRMATION);
    }

    /**
     * Cancels all appointments for a veterinarian within a specified date range.
     *
     * @param vetId the ID of the veterinarian
     * @param startDateStr the start date of the period
     * @param endDateStr the end date of the period
     */
    public void cancelAppointmentsForVetInPeriod(Integer vetId, String startDateStr, String endDateStr) {
        service.cancelAppointmentsForVetInPeriod(vetId, startDateStr, endDateStr);
    }

    /**
     * Retrieves notifications by their type.
     *
     * @param type the type of notifications to retrieve
     * @return a list of notifications of the specified type
     */
    public List<Notification> getNotificationsByType(NotificationType type) {
        return service.getNotificationsByType(type);
    }


    /**
     * Logs in a user with the provided username and password.
     *
     * @param username the username of the user
     * @param password the password of the user
     * @return an object representing the logged-in user
     * @throws Exception if login fails
     */
    public Object login(String username, String password) throws Exception {
        return service.login(username, password);
    }

    /**
     * Deletes a pet from the repository by its ID.
     *
     * @param id the ID of the pet to be deleted
     */
    public void deletePet(Integer id) {
        service.deletePet(id);
    }

    /**
     * Deletes a veterinarian from the repository by its ID.
     *
     * @param id the ID of the veterinarian to be deleted
     */
    public void deleteVet(Integer id) {
        service.deleteVet(id);
    }

    /**
     * Deletes an appointment from the repository by its ID.
     *
     * @param id the ID of the appointment to be deleted
     */
    public void deleteApp(Integer id) {
        service.deleteApp(id);
    }

    public List<Veterinarian> sortVeterinariansBySpecialization(){return service.sortVeterinariansBySpecialization();}

    public List<Appointment> sortAppointmentsByDate(){return service.sortAppointmentsByDate();}

    public List<Appointment> getAppointmentsInDateRange(String startDateStr, String endDateStr) throws Exception{return service.getAppointmentsInDateRange(startDateStr,endDateStr);}
}

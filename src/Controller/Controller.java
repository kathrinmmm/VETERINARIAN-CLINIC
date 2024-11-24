package Controller;
import Model.*;
import Service.Service;
import Utils.NotificationType;

import java.time.LocalDate;
import java.util.List;


public class Controller {
    private final Service service;

    public Controller(Service service) {
        this.service = service;
    }

    public void createPet(Pet pet) {
        service.addPet(pet);
    }
    public void createDisease(Disease disease){service.addDisease(disease);}

    public void createVet(Veterinarian veterinarian) {
        service.addVeterinarian(veterinarian);
    }

    public void createAppointment(Appointment appointment) {
        service.addAppointment(appointment);
    }

    public void sendNotification(Notification notification) {
        service.addNotification(notification);
    }

    public void createTest(Test test) {service.addTest(test);}

    public void createVaccine(Vaccine vaccine) {service.addVaccine(vaccine);}

    public List<Veterinarian> getAllVeterinarians() {
        return service.getAllVeterinarians();
    }

    public List<Pet> getAllPets() {
        return service.getAllPets();
    }

    public List<Disease> getAllDiseases(){
        return service.getAllDiseases();
    }

    public List<Test> getAllTests(){return service.getAllTests();}

    public List<Appointment> getAllAppointments(){return service.getAllAppointments();}

    public List<Vaccine> getAllVaccines(){return service.getAllVaccines();}

    public List<Appointment> getAppointmentsByDate(String date){return service.getAppointmentsByDate(date);}

    public List<Appointment> getAppointmentsByPet(int petId){return service.getAppointmentsByPet(petId);}

    public List<Appointment> getAppointmentsByVet(int vetId){return service.getAppointmentsByVet(vetId);}

    public HealthRecord getHealthRecordByPetId(int petId){return service.getHealthRecordByPetId(petId);}

    public void addDiseaseToPet(int petId, Disease disease) {service.addDiseaseToPet(petId,disease);}

    public List<Notification> getNotificationsByUserId(int userId){return service.getNotificationsByUserId(userId);}

    public void sendNotificationToUser(Integer userId, String message, NotificationType notificationType){ service.sendNotificationToUser(userId,message,notificationType);}

    public void showUpcomingAppointments(Integer petId) {
        List<Appointment> upcomingAppointments = service.sendAppointmentReminders(petId); // Apelăm metoda din Service pentru a obține programările viitoare

        if (!upcomingAppointments.isEmpty()) {
            System.out.println("Upcoming appointments for pet ID " + petId + ":");
            upcomingAppointments.forEach(app -> {
                System.out.println("Appointment on " + app.getDate() + " at " + app.getTime());
            });
        } else {
            System.out.println("No upcoming appointments for pet ID " + petId + " in the next 3 days.");
        }
    }

    public void sendCancellationNotification(Integer userId) {
        String message = "Cancellation: Your appointment for your pet has been cancelled.";
        service.sendNotificationToUser(userId, message, NotificationType.CANCELLATION);
    }

    public void sendConfirmationNotification(Integer userId) {
        String message = "Confirmation: Your appointment for your pet has been confirmed.";
        service.sendNotificationToUser(userId, message, NotificationType.CONFIRMATION);
    }

    public void cancelAppointmentsForVetInPeriod(Integer vetId, String startDateStr, String endDateStr){service.cancelAppointmentsForVetInPeriod(vetId,startDateStr,endDateStr);}

    public List<Notification> getNotificationsByType(NotificationType type){return service.getNotificationsByType(type);}

    public Integer getLoggedInUserId() {return service.getLoggedInUserId();}

    public Object login(String username, String password) throws Exception {
        return service.login(username, password);
    }
    public void logout(){service.logout();}

}

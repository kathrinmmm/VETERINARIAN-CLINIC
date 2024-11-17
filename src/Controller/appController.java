package Controller;

import model.*;
import service.Service;
import java.time.LocalDate;
import java.util.List;


public class appController {

    private final Service service;

    public appController(Service service) {
        this.service = service;
    }

    public boolean login(String username, String password) {
        return service.login(username, password);
    }

    public void signInPet(Pet pet) {
        service.signInPet(pet);
    }
    public void signInVet(Veterinarian veterinarian) {
        service.signInVet(veterinarian);
    }

    public boolean resetPassword(String username, String newPassword) {
        return service.resetPassword(username, newPassword);
    }

    public void addAppointment(Appointment appointment, List<Integer> vaccineIds, List<Integer> testIds) {
        if (appointment != null && !vaccineIds.isEmpty() && !testIds.isEmpty()) {
            service.addAppointment(appointment, vaccineIds, testIds);

            System.out.println("Appointment processed successfully for pet ID: " + appointment.getPet_id());
        } else {
            System.out.println("Invalid appointment data or empty vaccine/test lists.");
        }
    }


    public void cancelAppointment(int appointmentId) {
        Appointment appointment = getAppointmentById(appointmentId);
        if (appointment != null) {
            service.cancelAppointment(appointmentId);
            Pet pet = getPetById(appointment.getPet_id());
            Veterinarian vet = getVeterinarianForAppointment(appointment);
            sendNotificationToPet(pet, "Your appointment has been cancelled.");
            sendNotificationToVet(vet, "An appointment has been cancelled.");
        }
    }

    public List<Appointment> seeAppointments(int userId) {
        return service.seeAppointments(userId);
    }

    public void sendNotificationToVet(Veterinarian vet, String message) {
        if (vet != null) {
            vet.addNotification(message);
        }
    }

    public void sendNotificationToPet(Pet pet, String message) {
        if (pet != null) {
            pet.addNotification(message);
        }
    }

    public List<String> seeFutureTestsAndVaccines(int petId, LocalDate date) {
        return service.seeFutureTestsAndVaccines(petId, date);
    }

    public void deleteAccount(int userId, boolean isPet) {
        service.deleteAccount(userId, isPet);
    }

    public void addDiseaseForPet(int petId, Disease disease) {
        service.addDiseaseForPet(petId, disease);
    }

    public void modifyAppointmentForPet(int appointmentId, Appointment updatedAppointment) {
        service.modifyAppointmentForPet(appointmentId, updatedAppointment);
    }

    // Methods for retrieving entities by ID (helper methods)
    private Pet getPetById(int petId) {
        return service.getPetRepository().read(petId);
    }

    private Veterinarian getVeterinarianForAppointment(Appointment appointment) {
        return service.getVetRepository().read(appointment.getVet_id());
    }

    private Appointment getAppointmentById(int appointmentId) {
        return service.getAppointmentRepository().read(appointmentId);
    }

    public void createDisease(Disease disease) {
        service.createDisease(disease);
    }

    public Disease getDisease(int diseaseId) {
        return service.getDisease(diseaseId);
    }

    public void updateDisease(int diseaseId, Disease disease) {
        service.updateDisease(diseaseId, disease);
    }

    public void deleteDisease(int diseaseId) {
        service.deleteDisease(diseaseId);
    }

    public List<Disease> getAllDiseases() {
        return service.getAllDiseases();
    }

    public void createVaccine(Vaccine vaccine) {
        service.createVaccine(vaccine);
    }

    public Vaccine getVaccine(int vaccineId) {
        return service.getVaccine(vaccineId);
    }

    public void updateVaccine(int vaccineId, Vaccine vaccine) {
        service.updateVaccine(vaccineId, vaccine);
    }

    public void deleteVaccine(int vaccineId) {
        service.deleteVaccine(vaccineId);
    }

    public List<Vaccine> getAllVaccines() {
        return service.getAllVaccines();
    }

    public void createTest(Test test) {
        service.createTest(test);
    }

    public Test getTest(int testId) {
        return service.getTest(testId);
    }

    public void updateTest(int testId, Test test) {
        service.updateTest(testId, test);
    }

    public void deleteTest(int testId) {
        service.deleteTest(testId);
    }

    public List<Test> getAllTests() {
        return service.getAllTests();
    }

    public void notifyAndCancelAppointmentsForVacation(int vetId, LocalDate startDate, LocalDate endDate) {
        service.notifyAndCancelAppointmentsForVacation(vetId, startDate, endDate);
    }
}

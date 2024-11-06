package Controller;

import model.*;
import service.*;
import java.util.List;
public class appController {
    private AppointmentService appointmentService;
    private DiseaseService diseaseService;
    private HealthRecordService healthRecordService;
    private VaccineService vaccineService;
    private PetService petService;
    private VeterinarianService veterinarianService;
    private TestService testService;
    private PetDiseaseService petDiseaseService;
    private UserService userService;
    private NotificationService notificationService;

    public appController(AppointmentService appointmentService, DiseaseService diseaseService,
                          HealthRecordService healthRecordService, VaccineService vaccineService, PetService petService,VeterinarianService veterinarianService, TestService testService, PetDiseaseService petDiseaseService, UserService userService, NotificationService notificationService) {
        this.appointmentService = appointmentService;
        this.diseaseService = diseaseService;
        this.healthRecordService = healthRecordService;
        this.vaccineService = vaccineService;
        this.petService = petService;
        this.veterinarianService = veterinarianService;
        this.testService = testService;
        this.petDiseaseService=petDiseaseService;
        this.userService=userService;
        this.notificationService=notificationService;
    }

    public void addAppointment() {
        appointmentService.addAppointment();
    }


    public void listAppointments() {
        appointmentService.listAppointments();
    }


    public void addDisease(int id, String name, String type) {
        diseaseService.addDisease(id, name, type);
    }
    public void addVaccine(int id, String name, String type) {
        vaccineService.addVaccine();
    }


    public void modifyTestFrequency(int diseaseId, int frequency) {
        diseaseService.modifyTestFrequency(diseaseId, frequency);
    }

    public void modifyVaccineFrequency(int diseaseId, int frequency) {
        diseaseService.modifyVaccineFrequency(diseaseId, frequency);
    }

    public void viewHealthRecord(Pet pet) {
        healthRecordService.displayHealthRecord(pet);
    }

    public void registerUser() {
        userService.registerUser();
    }

    public User loginUser() {
        return userService.loginUser();
    }

    public void resetPassword() {
        userService.resetPassword();
    }

    public void registerPet() {
        userService.registerUser();

        // După înregistrare, putem obține utilizatorul înregistrat (presupunând că login-ul este deja realizat sau că avem un obiect valid)
        User user = userService.loginUser();  // Sau un alt mod de obținere a utilizatorului valid

        // Verificăm dacă utilizatorul este valid
        if (user != null) {
            // Transmitem obiectul user către metoda registerPet din PetService
            petService.registerPet(user);
        } else {
            System.out.println("Nu s-a putut înregistra animalul de companie, deoarece utilizatorul nu a fost valid.");
        }
    }

    public void addDiseaseToPet(int petId, int diseaseId) {
        petDiseaseService.addDiseaseToPet(petId, diseaseId);
    }

    public List<Disease> getDiseasesForPet(int petId) {
        return petDiseaseService.getDiseasesForPet(petId);
    }


    public void sendVaccinationReminder(User user, Appointment appointment) {
        notificationService.sendVaccinationReminder(user, appointment);
    }

    public void sendTestReminder(User user, Appointment appointment) {
        notificationService.sendTestReminder(user, appointment);
    }

    public void sendCancellationNotification(User user, Appointment appointment) {
        notificationService.sendCancellationNotification(user, appointment);
    }

    public void remindUpcomingAppointment(User user, Appointment appointment) {
        notificationService.remindUpcomingAppointment(user, appointment);
    }

    public void addVaccine() {
        vaccineService.addVaccine();
    }

    public void addVaccineToAppointment(Appointment appointment, Vaccine vaccine) {
        vaccineService.addVaccineToAppointment(appointment, vaccine);
    }

    public void listVaccinesForAppointment(Appointment appointment) {
        vaccineService.listVaccinesForAppointment(appointment);
    }

    public void addTestToAppointment(Appointment appointment, Test test) {
        testService.addTestToAppointment(appointment, test);
    }

    public void listTestsForAppointment(Appointment appointment) {
        testService.listTestsForAppointment(appointment);
    }

    public void addVeterinarian(Veterinarian veterinarian) {
        veterinarianService.addVeterinarian(veterinarian);
    }

    public void displayAvailableDatesAndTimes(int vetId) {
        veterinarianService.displayAvailableDatesAndTimes(vetId);
    }


    public void getAllDiseases(){
        diseaseService.getAllDiseases();
    }

    public void getRecommendedTestFrequency(int diseaseId){
        diseaseService.getRecommendedTestFrequency(diseaseId);
    }

    public void getRecommendedVaccineFrequency(int diseaseId){
        diseaseService.getRecommendedVaccineFrequency(diseaseId);
    }
    public void addHealthRecord(Pet pet) {
    healthRecordService.addHealthRecord(pet);
    }

    public void getHealthRecordByPet(Pet pet) {
        healthRecordService.getHealthRecordByPet(pet);
    }

    public void addAppointmentToHealthRecord(Pet pet, Appointment appointment){
        healthRecordService.addAppointmentToHealthRecord(pet, appointment);
    }
    public void displayHealthRecord(Pet pet){
        healthRecordService.displayHealthRecord(pet);
    }
    public void listNotifications(){
        notificationService.listNotifications();
    }
    public void findPetById(int id){
        petService.findPetById(id);
    }
    public void DisplayAllPets(){
        petService.DisplayAllPets();
    }
    public void getVeterinarians(){
        veterinarianService.getVeterinarians();
    }
    public void getVeterinarianById(int id){
        veterinarianService.getVeterinarianById(id);
    }
}
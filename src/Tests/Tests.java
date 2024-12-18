package Tests;
import Controller.Controller;
import Service.Service;
import Model.*;
import Exception.*;
import Repository.*;
import Utils.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tests {
    private Controller controller;
    private Service service;
    private IRepository<Pet> inMemoryPetRepository;
    private IRepository<Veterinarian> inMemoryVetRepository;
    private IRepository<Appointment> inMemoryAppointmentRepository;
    private IRepository<HealthRecord> inMemoryHealthRecordRepository;
    private IRepository<Disease> inMemoryDiseaseRepository;
    private IRepository<Vaccine> inMemoryVaccineRepository;
    private IRepository<HealthTest> inMemoryTestRepository;
    private IRepository<Notification> inMemoryNotificationRepository;
    private IRepository<Pet_Disease> inMemoryPetDiseaseRepository;

    private IRepository<Pet> filePetRepository;
    private IRepository<Veterinarian> fileVetRepository;
    private IRepository<Appointment> fileAppointmentRepository;
    private IRepository<HealthRecord> fileHealthRecordRepository;
    private IRepository<Disease> fileDiseaseRepository;
    private IRepository<Vaccine> fileVaccineRepository;
    private IRepository<HealthTest> fileTestRepository;
    private IRepository<Notification> fileNotificationRepository;
    private IRepository<Pet_Disease> filePetDiseaseRepository;

    private final String petFilePath = "src/Files/Pets.txt";
    private final String vetFilePath = "src/Files/Vets.txt";
    private final String appFilePath = "src/Files/Appointment.txt";
    private final String healthRecordFilePath = "src/Files/HealthRecord.txt";
    private final String diseaseFilePath = "src/Files/Diseases.txt";
    private final String vaccineFilePath = "src/Files/Vaccines.txt";
    private final String testFilePath = "src/Files/Tests.txt";
    private final String notificationFilePath = "src/Files/Notifications.txt";

    @BeforeEach
    public void setUp() {
        inMemoryPetRepository = new InMemoryRepository<>();
        inMemoryVetRepository = new InMemoryRepository<>();
        inMemoryAppointmentRepository = new InMemoryRepository<>();
        inMemoryHealthRecordRepository = new InMemoryRepository<>();
        inMemoryDiseaseRepository = new InMemoryRepository<>();
        inMemoryVaccineRepository = new InMemoryRepository<>();
        inMemoryTestRepository = new InMemoryRepository<>();
        inMemoryNotificationRepository = new InMemoryRepository<>();
        inMemoryPetDiseaseRepository = new InMemoryRepository<>();

        filePetRepository = new FileRepository<>(petFilePath);
        fileVetRepository = new FileRepository<>(vetFilePath);
        fileAppointmentRepository = new FileRepository<>(appFilePath);
        fileHealthRecordRepository = new FileRepository<>(healthRecordFilePath);
        fileDiseaseRepository = new FileRepository<>(diseaseFilePath);
        fileVaccineRepository = new FileRepository<>(vaccineFilePath);
        fileTestRepository = new FileRepository<>(testFilePath);
        fileNotificationRepository = new FileRepository<>(notificationFilePath);

        service = new Service(
                inMemoryPetRepository,
                inMemoryVetRepository,
                inMemoryAppointmentRepository,
                inMemoryHealthRecordRepository,
                inMemoryDiseaseRepository,
                inMemoryVaccineRepository,
                inMemoryTestRepository,
                inMemoryNotificationRepository,
                inMemoryPetDiseaseRepository
        );
        controller = new Controller(service);

        deleteTestFiles();
        createSampleData();
    }

    private void deleteTestFiles() {
        new File(petFilePath).delete();
        new File(vetFilePath).delete();
        new File(appFilePath).delete();
        new File(healthRecordFilePath).delete();
        new File(diseaseFilePath).delete();
        new File(vaccineFilePath).delete();
        new File(testFilePath).delete();
        new File(notificationFilePath).delete();
    }


    private void createSampleData() {
        Pet pet1 = new Pet(1, "Bella", "bella_cat123", "password123", AnimalType.CAT);
        Pet pet2 = new Pet(2, "Max", "max_dog456", "password456", AnimalType.DOG);
        Pet pet3 = new Pet(3, "Chirpy", "chirpy_bird789", "birdpass", AnimalType.BIRD);

        inMemoryPetRepository.create(pet1);
        inMemoryPetRepository.create(pet2);
        inMemoryPetRepository.create(pet3);


        Veterinarian vet1 = new Veterinarian(8, "Dr. John", "johnben", "john_vet", Specialization.SURGERY);
        Veterinarian vet2 = new Veterinarian(9, "Dr. Emily", "emily1206", "emily_vet", Specialization.DENTISTRY);
        inMemoryVetRepository.create(vet1);
        inMemoryVetRepository.create(vet2);

        Vaccine vac1=new Vaccine(1,"Nobivac Rabies",VaccineType.RABIES);
        inMemoryVaccineRepository.create(vac1);

        HealthTest healthTest1 = new HealthTest(1, "Heart Disease Test", TestType.ULTRASOUND);
        HealthTest healthTest2 = new HealthTest(2, "Liver Function Test", TestType.X_RAY);

        inMemoryTestRepository.create(healthTest1);
        inMemoryTestRepository.create(healthTest2);

        Notification notification1 = new Notification(1, 1, "Appointment Cancelled", "2023-12-10", NotificationType.CANCELLATION);
        Notification notification2 = new Notification(2, 2, "Don't Forget Your Appointment Tomorrow", "2023-12-11", NotificationType.REMINDER);
        inMemoryNotificationRepository.create(notification1);
        inMemoryNotificationRepository.create(notification2);

        Disease disease1 = new Disease(1, "Canine Parvovirus", DiseaseType.CANINE_PARAVOVIRUS);
        Disease disease2 = new Disease(2, "Rabies", DiseaseType.RABIES);
        inMemoryDiseaseRepository.create(disease1);
        inMemoryDiseaseRepository.create(disease2);

//        Appointment appointment1 = new Appointment(1, 1, 1, "2024-12-10", "10:00",
//                AppointmentType.ROUTINE, new ArrayList<>(), new ArrayList<>());
//        Appointment appointment2 = new Appointment(2, 2, 2, "2024-12-11", "11:30",
//                AppointmentType.DENTAL, new ArrayList<>(), new ArrayList<>());
//        inMemoryAppointmentRepository.create(appointment1);
//        inMemoryAppointmentRepository.create(appointment2);
//
//        Pet_Disease petDisease1 = new Pet_Disease(1, 1, disease1);
//        List<Appointment> appointments = List.of(appointment1);
//        List<Pet_Disease> diseases = List.of(petDisease1);
//
//        HealthRecord healthRecord1 = new HealthRecord(1, 1, appointments, diseases);
//        inMemoryHealthRecordRepository.create(healthRecord1);

    }

    @Test
    public void testPetCRUDInMemory() {
        Pet retrievedPet = inMemoryPetRepository.read(3);
        assertNotNull(retrievedPet);
        assertEquals("Chirpy", retrievedPet.getName());
        assertEquals(AnimalType.BIRD, retrievedPet.getAnimalType());

        retrievedPet.setName("Updated Chirpy");
        retrievedPet.setAnimalType(AnimalType.REPTILE);
        inMemoryPetRepository.update(retrievedPet.getId(), retrievedPet);

        Pet updatedPet = inMemoryPetRepository.read(3);
        assertEquals("Updated Chirpy", updatedPet.getName());
        assertEquals(AnimalType.REPTILE, updatedPet.getAnimalType());

        inMemoryPetRepository.delete(3);
        Pet deletedPet = inMemoryPetRepository.read(3);
        assertNull(deletedPet);

        List<Pet> pets = inMemoryPetRepository.getAll();
        assertNotNull(pets);
        assertEquals(2, pets.size());
    }

    @Test
    public void testVeterinarianCRUDInMemory() {
        Veterinarian retrievedVet = inMemoryVetRepository.read(8);
        assertNotNull(retrievedVet);
        assertEquals("Dr. John", retrievedVet.getName());
        assertEquals(Specialization.SURGERY, retrievedVet.getSpecialization());

        Veterinarian vet3 = new Veterinarian(10, "Dr. Alex", "alex92", "123", Specialization.SURGERY);
        inMemoryVetRepository.create(vet3);

        Veterinarian addedVet = inMemoryVetRepository.read(10);
        assertNotNull(addedVet);
        assertEquals("Dr. Alex", addedVet.getName());
        assertEquals(Specialization.SURGERY, addedVet.getSpecialization());

        addedVet.setName("Dr. Alexander");
        addedVet.setSpecialization(Specialization.INTERNAL);
        inMemoryVetRepository.update(addedVet.getId(), addedVet);

        Veterinarian updatedVet = inMemoryVetRepository.read(10);
        assertEquals("Dr. Alexander", updatedVet.getName());
        assertEquals(Specialization.INTERNAL, updatedVet.getSpecialization());

        inMemoryVetRepository.delete(10);
        Veterinarian deletedVet = inMemoryVetRepository.read(10);
        assertNull(deletedVet, "Veterinarian with ID 3 should have been deleted.");

        List<Veterinarian> vets = inMemoryVetRepository.getAll();
        assertNotNull(vets);
        assertEquals(2, vets.size());

    }

    @Test
    public void testVaccineCRUDInMemory() {
        Vaccine retrievedVaccine = inMemoryVaccineRepository.read(1);
        assertNotNull(retrievedVaccine);
        assertEquals("Nobivac Rabies", retrievedVaccine.getName());
        assertEquals(VaccineType.RABIES, retrievedVaccine.getVaccineType());

        Vaccine vaccine3 = new Vaccine(3, "Canine Spectra 10", VaccineType.DISTEMPER);
        inMemoryVaccineRepository.create(vaccine3);

        Vaccine addedVaccine = inMemoryVaccineRepository.read(3);
        assertNotNull(addedVaccine);
        assertEquals("Canine Spectra 10", addedVaccine.getName());
        assertEquals(VaccineType.DISTEMPER, addedVaccine.getVaccineType());

        addedVaccine.setName("updated");
        addedVaccine.setVaccineType(VaccineType.FELINE_LEUKEMIA);
        inMemoryVaccineRepository.update(addedVaccine.getId(), addedVaccine);

        Vaccine updatedVaccine = inMemoryVaccineRepository.read(3);
        assertEquals("updated", updatedVaccine.getName());
        assertEquals(VaccineType.FELINE_LEUKEMIA, updatedVaccine.getVaccineType());

        inMemoryVaccineRepository.delete(3);
        Vaccine deletedVaccine = inMemoryVaccineRepository.read(3);
        assertNull(deletedVaccine, "Vaccine with ID 3 should have been deleted.");

        List<Vaccine> vaccines = inMemoryVaccineRepository.getAll();
        assertNotNull(vaccines);
        assertEquals(1, vaccines.size());
    }

    @Test
    public void testHealthTestCRUDInMemory() {
        HealthTest retrievedHealthTest = inMemoryTestRepository.read(1);
        assertNotNull(retrievedHealthTest);
        assertEquals("Heart Disease Test", retrievedHealthTest.getName());
        assertEquals(TestType.ULTRASOUND, retrievedHealthTest.getTestType());

        HealthTest healthTest3 = new HealthTest(3, "Blood Pressure Test", TestType.BLOOD);
        inMemoryTestRepository.create(healthTest3);

        HealthTest addedHealthTest = inMemoryTestRepository.read(3);
        assertNotNull(addedHealthTest);
        assertEquals("Blood Pressure Test", addedHealthTest.getName());
        assertEquals(TestType.BLOOD, addedHealthTest.getTestType());

        addedHealthTest.setName("Updated Blood Pressure Test");
        addedHealthTest.setTestType(TestType.ULTRASOUND);
        inMemoryTestRepository.update(addedHealthTest.getId(), addedHealthTest);

        HealthTest updatedHealthTest = inMemoryTestRepository.read(3);
        assertEquals("Updated Blood Pressure Test", updatedHealthTest.getName());
        assertEquals(TestType.ULTRASOUND, updatedHealthTest.getTestType());

        inMemoryTestRepository.delete(3);
        HealthTest deletedHealthTest = inMemoryTestRepository.read(3);
        assertNull(deletedHealthTest, "HealthTest with ID 3 should have been deleted.");

        List<HealthTest> healthTests = inMemoryTestRepository.getAll();
        assertNotNull(healthTests);
        assertEquals(2, healthTests.size(), "There should be 2 health tests after deletion.");
    }

    @Test
    public void testDiseaseCRUDInMemory() {
        Disease retrievedDisease = inMemoryDiseaseRepository.read(1);
        assertNotNull(retrievedDisease);
        assertEquals("Canine Parvovirus", retrievedDisease.getName());
        assertEquals(DiseaseType.CANINE_PARAVOVIRUS, retrievedDisease.getDiseaseType());

        Disease disease3 = new Disease(3, "Feline Leukemia", DiseaseType.FELINE_LEUKEMIA);
        inMemoryDiseaseRepository.create(disease3);

        Disease addedDisease = inMemoryDiseaseRepository.read(3);
        assertNotNull(addedDisease);
        assertEquals("Feline Leukemia", addedDisease.getName());
        assertEquals(DiseaseType.FELINE_LEUKEMIA, addedDisease.getDiseaseType());

        addedDisease.setName("Updated Feline Leukemia");
        addedDisease.setDiseaseType(DiseaseType.RINGWORM);
        inMemoryDiseaseRepository.update(addedDisease.getId(), addedDisease);

        Disease updatedDisease = inMemoryDiseaseRepository.read(3);
        assertEquals("Updated Feline Leukemia", updatedDisease.getName());
        assertEquals(DiseaseType.RINGWORM, updatedDisease.getDiseaseType());

        inMemoryDiseaseRepository.delete(3);
        Disease deletedDisease = inMemoryDiseaseRepository.read(3);
        assertNull(deletedDisease, "Disease with ID 3 should have been deleted.");

        List<Disease> diseases = inMemoryDiseaseRepository.getAll();
        assertNotNull(diseases);
        assertEquals(2, diseases.size(), "There should be 2 diseases after deletion.");
    }

    @Test
    public void testNotificationCRUD() {

        Notification retrievedNotification = inMemoryNotificationRepository.read(1);
        assertNotNull(retrievedNotification);
        assertEquals("Appointment Cancelled", retrievedNotification.getTitle());
        assertEquals(NotificationType.CANCELLATION, retrievedNotification.getNotificationType());

        Notification notification3 = new Notification(3, 102, "Your Appointment is Confirmed", "2023-12-12", NotificationType.CONFIRMATION);
        inMemoryNotificationRepository.create(notification3);

        Notification addedNotification = inMemoryNotificationRepository.read(3);
        assertNotNull(addedNotification);
        assertEquals("Your Appointment is Confirmed", addedNotification.getTitle());
        assertEquals(NotificationType.CONFIRMATION, addedNotification.getNotificationType());

        addedNotification.setTitle("Updated Appointment Confirmation");
        addedNotification.setNotificationType(NotificationType.VACATION);
        inMemoryNotificationRepository.update(3, addedNotification);

        Notification updatedNotification = inMemoryNotificationRepository.read(3);
        assertEquals("Updated Appointment Confirmation", updatedNotification.getTitle());
        assertEquals(NotificationType.VACATION, updatedNotification.getNotificationType());

        inMemoryNotificationRepository.delete(3);
        Notification deletedNotification = inMemoryNotificationRepository.read(3);
        assertNull(deletedNotification, "Notification with ID 3 should have been deleted.");

        List<Notification> notifications = inMemoryNotificationRepository.getAll();
        assertNotNull(notifications);
        assertEquals(2, notifications.size(), "There should be 2 notifications after deletion.");
    }

    @Test
    public void testAppointmentCRUD() {

        Appointment retrievedAppointment = inMemoryAppointmentRepository.read(1);
        assertNotNull(retrievedAppointment);
        assertEquals(AppointmentType.ROUTINE, retrievedAppointment.getAppointmentType());
        assertEquals("10:00", retrievedAppointment.getTime());

        List<HealthTest> healthTests = new ArrayList<>();
        healthTests.add(new HealthTest(1, "Blood Test", TestType.BLOOD));

        List<Vaccine> vaccines = new ArrayList<>();
        vaccines.add(new Vaccine(1, "Rabies", VaccineType.RABIES));

        Appointment appointment3 = new Appointment(3, 103, 203, "2024-12-12", "15:00",
                AppointmentType.SURGICAL, healthTests, vaccines);

        inMemoryAppointmentRepository.create(appointment3);

        Appointment addedAppointment = inMemoryAppointmentRepository.read(3);
        assertNotNull(addedAppointment);
        assertEquals(AppointmentType.SURGICAL, addedAppointment.getAppointmentType());
        assertEquals(1, addedAppointment.getTests().size());
        assertEquals("Rabies", addedAppointment.getVaccines().get(0).getName());

        addedAppointment.setTime("16:00");
        addedAppointment.setAppointmentType(AppointmentType.BEHAVIOR);
        inMemoryAppointmentRepository.update(3, addedAppointment);

        Appointment updatedAppointment = inMemoryAppointmentRepository.read(3);
        assertEquals("16:00", updatedAppointment.getTime());
        assertEquals(AppointmentType.BEHAVIOR, updatedAppointment.getAppointmentType());

        inMemoryAppointmentRepository.delete(3);
        Appointment deletedAppointment = inMemoryAppointmentRepository.read(3);
        assertNull(deletedAppointment, "Appointment with ID 3 should have been deleted.");

        List<Appointment> appointments = inMemoryAppointmentRepository.getAll();
        assertNotNull(appointments);
        assertEquals(2, appointments.size(), "There should be 2 appointments after deletion.");
    }

    @Test
    public void testHealthRecordCRUD() {
        Appointment appointment8 = new Appointment(1, 101, 201, "2024-12-10", "10:00", AppointmentType.ROUTINE, null, null);
        Appointment appointment9 = new Appointment(2, 101, 202, "2024-12-11", "11:00", AppointmentType.EMERGENCY, null, null);

        Disease disease3 = new Disease(8, "Canine Par", DiseaseType.CANINE_PARAVOVIRUS);

        Pet_Disease petdisease1 = new Pet_Disease(1, 1, disease3);
        Pet_Disease petdisease2 = new Pet_Disease(2, 2, disease3);

        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment8);

        List<Pet_Disease> diseases = new ArrayList<>();
        diseases.add(petdisease1);

        HealthRecord healthRecord1 = new HealthRecord(1, 1, appointments, diseases);

        healthRecord1.addAppointment(appointment9);
        healthRecord1.addDisease(2, disease3);

        inMemoryHealthRecordRepository.create(healthRecord1);
        inMemoryHealthRecordRepository.update(1, healthRecord1);

        HealthRecord retrievedHealthRecord = inMemoryHealthRecordRepository.read(1);

        assertNotNull(retrievedHealthRecord);
        assertEquals(1, retrievedHealthRecord.getPetId());
        assertEquals(1, retrievedHealthRecord.getAppointments().size());
        assertEquals(1, retrievedHealthRecord.getPetDiseases().size());

        inMemoryHealthRecordRepository.delete(1);
        HealthRecord deletedHealthRecord = inMemoryHealthRecordRepository.read(1);
        assertNull(deletedHealthRecord, "HealthRecord with ID 1 should have been deleted.");

        List<HealthRecord> allRecords = inMemoryHealthRecordRepository.getAll();
        assertTrue(allRecords.isEmpty(), "All health records should have been deleted.");

    }

    @Test
    public void testAddAppointment() {
        List<HealthTest> healthTests = new ArrayList<>();
        healthTests.add(new HealthTest(1, "Blood Test", TestType.BLOOD));

        List<Vaccine> vaccines = new ArrayList<>();
        vaccines.add(new Vaccine(1, "Rabies", VaccineType.RABIES));

        Appointment newAppointment = new Appointment(3, 1, 8, "2024-12-12", "15:00",
                AppointmentType.SURGICAL, healthTests, vaccines);

        inMemoryAppointmentRepository.create(newAppointment);

        Appointment retrievedAppointment = inMemoryAppointmentRepository.read(3);
        assertEquals("2024-12-12", retrievedAppointment.getDate(), "The date should match");
        assertEquals("15:00", retrievedAppointment.getTime(), "The time should match");
        assertEquals(AppointmentType.SURGICAL, retrievedAppointment.getAppointmentType(), "The appointment type should be surgical");
        assertEquals(1, retrievedAppointment.getTests().size(), "There should be 1 health test");
        assertEquals("Rabies", retrievedAppointment.getVaccines().get(0).getName(), "The vaccine name should be Rabies");
    }

    @Test
    public void testCancelAppointmentsForVetInPeriod() throws Exception {
        String startDateStr = "2024-12-09";
        String endDateStr = "2024-12-11";
        int vetId = 8;

        Appointment appointment1 = new Appointment(10, 1, 8, "2024-12-10", "10:00", AppointmentType.ROUTINE, new ArrayList<>(), new ArrayList<>());
        Appointment appointment2 = new Appointment(11, 2, 8, "2024-12-11", "11:30", AppointmentType.DENTAL, new ArrayList<>(), new ArrayList<>());
        Appointment appointment3 = new Appointment(12, 2, 8, "2024-12-15", "13:00", AppointmentType.ROUTINE, new ArrayList<>(), new ArrayList<>());

        inMemoryAppointmentRepository.create(appointment1);
        inMemoryAppointmentRepository.create(appointment2);
        inMemoryAppointmentRepository.create(appointment3);

        service.cancelAppointmentsForVetInPeriod(vetId, startDateStr, endDateStr);


        assertEquals(1, inMemoryAppointmentRepository.getAll().size(), "There should be one appointment left (the one outside the specified period).");

        assertNull(inMemoryAppointmentRepository.read(1), "The appointment for 2024-12-10 should have been canceled.");
        assertNull(inMemoryAppointmentRepository.read(2), "The appointment for 2024-12-11 should have been canceled.");
    }

    @Test
    public void testSendAppointmentReminders() throws EntityNotFoundException {
        String today = LocalDate.now().toString();
        String futureDate = LocalDate.now().plusDays(1).toString();
        String farFutureDate = LocalDate.now().plusDays(5).toString();

        Appointment appointment1 = new Appointment(1, 1, 8, futureDate, "10:00", AppointmentType.ROUTINE, new ArrayList<>(), new ArrayList<>());
        Appointment appointment2 = new Appointment(2, 1, 8, farFutureDate, "11:00", AppointmentType.DENTAL, new ArrayList<>(), new ArrayList<>());

        inMemoryAppointmentRepository.create(appointment1);
        inMemoryAppointmentRepository.create(appointment2);

        List<Appointment> upcomingAppointments = service.sendAppointmentReminders(1);

        assertEquals(1, upcomingAppointments.size(), "There should be exactly one upcoming appointment within the next 3 days.");
        assertEquals("2024-12-19", upcomingAppointments.get(0).getDate(), "The upcoming appointment should be on 2024-12-11.");

        assertNotEquals("2024-12-15", upcomingAppointments.get(0).getDate(), "The appointment on 2024-12-15 should be ignored as it is outside the 3-day range.");
    }

    @Test
    public void testSendAppointmentRemindersNoUpcomingAppointments() {
        String farFutureDate = LocalDate.now().plusDays(5).toString();
        Appointment appointment1 = new Appointment(1, 1, 8, farFutureDate, "11:00", AppointmentType.DENTAL, new ArrayList<>(), new ArrayList<>());
        inMemoryAppointmentRepository.create(appointment1);

        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> {
            service.sendAppointmentReminders(1);
        });

        assertEquals("No upcoming appointments found for petId: 1", thrown.getMessage(), "Exception message should indicate no upcoming appointments.");
    }

    @Test
    public void testSendAppointmentRemindersNoAppointmentsForPetId() {
        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> {
            service.sendAppointmentReminders(99);
        });

        assertEquals("No appointments found in the system.", thrown.getMessage(), "Exception message should indicate no appointments found.");
    }




}



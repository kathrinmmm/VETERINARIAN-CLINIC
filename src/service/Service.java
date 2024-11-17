package service;

import model.Pet;
import model.Veterinarian;
import model.Appointment;
import model.HealthRecord;
import model.Test;
import model.Vaccine;
import model.Disease;
import Repository.IRepository;

import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Service {
    private final IRepository<Pet> petRepository;
    private final IRepository<Veterinarian> vetRepository;
    private final IRepository<Appointment> appointmentRepository;
    private final IRepository<HealthRecord> healthRecordRepository;
    private final IRepository<Disease> diseaseRepository;
    private final IRepository<Vaccine> vaccineRepository;
    private final IRepository<Test> testRepository;
    private final Map<String, List<String>> speciesToBreeds;

    public Service(IRepository<Pet> petRepo, IRepository<Veterinarian> vetRepo, IRepository<Appointment> appRepo, IRepository<HealthRecord> hrRepo, IRepository<Disease> diseaseRepository, IRepository<Vaccine> vaccineRepository, IRepository<Test> testRepository) {
        this.petRepository = petRepo;
        this.vetRepository = vetRepo;
        this.appointmentRepository = appRepo;
        this.healthRecordRepository = hrRepo;
        this.diseaseRepository =diseaseRepository;
        this.vaccineRepository = vaccineRepository;
        this.testRepository = testRepository;
        speciesToBreeds = Map.of(
                "Caine", List.of("Labrador", "Beagle", "Bulldog", "Golden Retriever", "Cocker Spaniol"),
                "Pisica", List.of("Siamese", "Persană", "Maine Coon", "Bengaleză", "Sfinx"),
                "Papagal", List.of("Papagal ondulat", "Cacadu", "Papagal gri african", "Perus"),
                "Iepure", List.of("Iepurele Urias Belgian", "Chinchilla", "Albastru Vienez")
        );

        List<Disease> diseases = List.of(
                new Disease(1, "Canine Parvovirus", "A highly contagious viral disease in dogs."),
                new Disease(2, "Rabies", "A viral disease affecting the nervous system."),
                new Disease(3, "Feline Leukemia", "A common cause of cancer in cats."),
                new Disease(4, "Psittacosis", "A bacterial infection in birds.")
        );

        List<Vaccine> vaccines = List.of(
                new Vaccine(1, "Rabies Vaccine", "Rabies",9),
                new Vaccine(2, "Distemper Vaccine", "Canine Distemper",6),
                new Vaccine(3, "Feline Leukemia Vaccine", "Feline Leukemia",14),
                new Vaccine(4, "Parrot Fever Vaccine", "Psittacosis",10),
                new Vaccine(5, "NULL", "NULL",0)
        );

        List<Test> tests = List.of(
                new Test(1, "Blood Test"),
                new Test(2, "X-Ray"),
                new Test(3, "Ultrasound"),
                new Test(4, "Allergy Test"),
                new Test(5, "NULL")
        );

    }
    public Map<String, List<String>> getSpeciesToBreeds() {
        return speciesToBreeds;
    }

    public void createPet(Pet pet) {
        petRepository.create(pet);
    }

    public Pet getPet(int petId) {
        return petRepository.read(petId);
    }

    public void updatePet(int petId, Pet pet) {
        petRepository.update(petId, pet);
    }

    public void deletePet(int petId) {
        petRepository.delete(petId);
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }

    public void createVeterinarian(Veterinarian vet) {
        vetRepository.create(vet);
    }

    public Veterinarian getVeterinarian(int vetId) {
        return vetRepository.read(vetId);
    }

    public void updateVeterinarian(int vetId, Veterinarian vet) {
        vetRepository.update(vetId, vet);
    }

    public void deleteVeterinarian(int vetId) {
        vetRepository.delete(vetId);
    }

    public List<Veterinarian> getAllVeterinarians() {
        return vetRepository.findAll();
    }

    public void createDisease(Disease disease) {
        diseaseRepository.create(disease);
    }

    public Disease getDisease(int diseaseId) {
        return diseaseRepository.read(diseaseId);
    }

    public void updateDisease(int diseaseId, Disease disease) {
        diseaseRepository.update(diseaseId, disease);
    }

    public void deleteDisease(int diseaseId) {
        diseaseRepository.delete(diseaseId);
    }

    public List<Disease> getAllDiseases() {
        return diseaseRepository.findAll();
    }

    public void createVaccine(Vaccine vaccine) {
        vaccineRepository.create(vaccine);
    }

    public Vaccine getVaccine(int vaccineId) {
        return vaccineRepository.read(vaccineId);
    }

    public void updateVaccine(int vaccineId, Vaccine vaccine) {
        vaccineRepository.update(vaccineId, vaccine);
    }

    public void deleteVaccine(int vaccineId) {
        vaccineRepository.delete(vaccineId);
    }

    public List<Vaccine> getAllVaccines() {
        return vaccineRepository.findAll();
    }

    public void createTest(Test test) {
        testRepository.create(test);
    }

    public Test getTest(int testId) {
        return testRepository.read(testId);
    }

    public void updateTest(int testId, Test test) {
        testRepository.update(testId, test);
    }

    public void deleteTest(int testId) {
        testRepository.delete(testId);
    }

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }
    public IRepository<Pet> getPetRepository() {
        return petRepository;
    }

    public IRepository<Veterinarian> getVetRepository() {
        return vetRepository;
    }

    public IRepository<Appointment> getAppointmentRepository() {
        return appointmentRepository;
    }

    public IRepository<HealthRecord> getHealthRecordRepository() {
        return healthRecordRepository;
    }

    public boolean login(String username, String password) {
        return petRepository.findAll().stream().anyMatch(pet -> pet.getUsername().equals(username) && pet.getPassword().equals(password)) ||
                vetRepository.findAll().stream().anyMatch(vet -> vet.getUsername().equals(username) && vet.getPassword().equals(password));
    }


    public void signInPet(Pet pet) {
        petRepository.create(pet);
    }
    public void signInVet(Veterinarian vet) {
        vetRepository.create(vet);
    }

    public boolean resetPassword(String username, String newPassword) {
        for (Pet pet : petRepository.findAll()) {
            if (pet.getUsername().equals(username)) {
                pet.setPassword(newPassword);
                return true;
            }
        }
        for (Veterinarian vet : vetRepository.findAll()) {
            if (vet.getUsername().equals(username)) {
                vet.setPassword(newPassword);
                return true;
            }
        }
        return false;
    }

    public void addAppointment(Appointment appointment, List<Integer> vaccineIds, List<Integer> testIds) {
        Pet pet = petRepository.read(appointment.getPet_id());

        if (pet != null) {
            List<Vaccine> selectedVaccines = vaccineIds.stream()
                    .map(vaccineRepository::read)
                    .filter(vaccine -> vaccine != null)
                    .collect(Collectors.toList());
            appointment.setVaccines(selectedVaccines);

            List<Test> selectedTests = testIds.stream()
                    .map(testRepository::read)
                    .filter(test -> test != null)
                    .collect(Collectors.toList());
            appointment.setTests(selectedTests);

            appointmentRepository.create(appointment);

            HealthRecord healthRecord = healthRecordRepository.findAll().stream()
                    .filter(hr -> hr.getPetId() == appointment.getPet_id())
                    .findFirst()
                    .orElseGet(() -> {
                        HealthRecord newRecord = new HealthRecord(pet.getUser_id());
                        healthRecordRepository.create(newRecord);
                        return newRecord;
                    });

            healthRecord.getVaccines().addAll(selectedVaccines);
            healthRecord.getTests().addAll(selectedTests);

            healthRecordRepository.update(healthRecord.getPetId(), healthRecord);
        }
    }

    public Appointment getAppointment(int appointmentId) {
        return appointmentRepository.read(appointmentId);
    }



    public List<Appointment> seeAppointments(int userId) {
        return appointmentRepository.findAll().stream()
                .filter(app -> app.getPet_id() == userId || app.getVet_id() == userId)
                .collect(Collectors.toList());
    }

    public HealthRecord seeHealthRecord(int petId) {
        return healthRecordRepository.findAll().stream()
                .filter(hr -> hr.getPetId() == petId)
                .findFirst()
                .orElse(null);
    }

    public void cancelAppointment(int appointmentId) {
        Appointment appointment = appointmentRepository.read(appointmentId);
        if (appointment != null) {
            appointmentRepository.delete(appointmentId);
        }
    }

    public List<String> seeFutureTestsAndVaccines(int petId, LocalDate date) {
        Pet pet = petRepository.read(petId);
        if (pet != null) {
            return pet.getAppointments().stream()
                    .filter(app -> LocalDate.parse(app.getDate()).isAfter(date))
                    .flatMap(app -> Stream.concat(
                            app.getVaccines().stream().map(vaccine -> vaccine.getName()),
                            app.getTests().stream().map(test -> test.getType())
                    ))
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    public void notifyAndCancelAppointmentsForVacation(int vetId, LocalDate startDate, LocalDate endDate) {
        Veterinarian vet = vetRepository.read(vetId);

        if (vet != null) {
            String vacationMessage = "Dr. " + vet.getName() + " will be on vacation from " + startDate.toString() + " to " + endDate.toString() + ". Please reschedule your appointments if necessary.";

            petRepository.findAll().forEach(pet -> sendNotificationToPet(pet, vacationMessage));

            appointmentRepository.findAll().forEach(appointment -> {
                LocalDate appointmentDate = LocalDate.parse(appointment.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                if (appointment.getVet_id() == vetId &&
                        (appointmentDate.isEqual(startDate) || appointmentDate.isAfter(startDate)) &&
                        (appointmentDate.isEqual(endDate) || appointmentDate.isBefore(endDate))) {

                    cancelAppointment(appointment.getId());
                }
            });
        }
    }


    private void sendNotificationToPet(Pet pet, String message) {
        if (pet != null) {
            pet.addNotification(message);
        }
    }

    private void sendNotificationToVet(Veterinarian vet, String message) {
        if (vet != null) {
            vet.addNotification(message);
        }
    }


    public void deleteAccount(int userId, boolean isPet) {
        if (isPet) {
            Pet pet = petRepository.read(userId);
            if (pet != null) {
                appointmentRepository.findAll().stream()
                        .filter(appointment -> appointment.getPet_id() == userId)
                        .forEach(appointment -> appointmentRepository.delete(appointment.getId()));
                petRepository.delete(userId);
            }
        } else {
            Veterinarian vet = vetRepository.read(userId);
            if (vet != null) {
                appointmentRepository.findAll().stream()
                        .filter(appointment -> appointment.getVet_id() == userId)
                        .forEach(appointment -> appointmentRepository.delete(appointment.getId()));
                vetRepository.delete(userId);

            }
        }
    }


    public void addDiseaseForPet(int petId, Disease disease) {
        Pet pet = petRepository.read(petId);
        if (pet != null) {
            HealthRecord healthRecord = healthRecordRepository.findAll().stream()
                    .filter(hr -> hr.getPetId() == petId)
                    .findFirst()
                    .orElse(null);

            if (healthRecord != null) {
                healthRecord.getDiseases().add(disease);

                healthRecordRepository.update(healthRecord.getPetId(), healthRecord);
            }
        }
    }

    public void modifyAppointmentForPet(int appointmentId, Appointment updatedAppointment) {
        Appointment existingAppointment = appointmentRepository.read(appointmentId);
        if (existingAppointment != null) {
            existingAppointment.setDate(updatedAppointment.getDate());
            existingAppointment.setVet_id(updatedAppointment.getVet_id());
            existingAppointment.setPet_id(updatedAppointment.getPet_id());
            existingAppointment.setVaccines(updatedAppointment.getVaccines());
            existingAppointment.setTests(updatedAppointment.getTests());

            appointmentRepository.update(appointmentId, existingAppointment);
        }
    }
    public boolean usernameExists(String username) {
        return petRepository.findAll().stream().anyMatch(pet -> pet.getUsername().equals(username)) ||
                vetRepository.findAll().stream().anyMatch(vet -> vet.getUsername().equals(username));
    }
}
import Controller.appController;
import Repository.*;
import service.*;
import model.*;
import UI.UI;

public class Main {
    public static void main(String[] args) {
        IRepository<Pet> petRepository = new InMemoryRepository<>();
        IRepository<User> userRepository = new InMemoryRepository<>();
        IRepository<Disease> diseaseRepository = new InMemoryRepository<>();
        IRepository<Veterinarian> veterinarianRepository = new InMemoryRepository<>();
        IRepository<Appointment> appointmentRepository = new InMemoryRepository<>();
        IRepository<Test> testRepository = new InMemoryRepository<>();
        IRepository<Vaccine> vaccineRepository = new InMemoryRepository<>();
        IRepository<HealthRecord> healthRecordRepository = new InMemoryRepository<>();
        IRepository<Pet_Disease> petDiseaseRepository = new InMemoryRepository<>();

        PetService petService = new PetService(petRepository);
        UserService userService = new UserService(userRepository);
        DiseaseService diseaseService = new DiseaseService(diseaseRepository);
        VeterinarianService veterinarianService = new VeterinarianService(veterinarianRepository);
        AppointmentService appointmentService = new AppointmentService(appointmentRepository);
        TestService testService = new TestService(testRepository);
        VaccineService vaccineService = new VaccineService(vaccineRepository);
        HealthRecordService healthRecordService = new HealthRecordService(healthRecordRepository);
        PetDiseaseService petDiseaseService = new PetDiseaseService(petDiseaseRepository);
        NotificationService notificationService = new NotificationService();

        appController controller = new appController(appointmentService, diseaseService, healthRecordService,
                vaccineService, petService, veterinarianService,
                testService, petDiseaseService, userService, notificationService);

        UI ui = new UI(controller);
        ui.showMainMenu();
    }
}
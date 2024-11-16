import Repository.*;
import service.Service;
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

        Service service = new Service(
                petRepository,
                veterinarianRepository,
                appointmentRepository,
                healthRecordRepository,
                diseaseRepository,
                vaccineRepository,
                testRepository
        );

        UI ui = new UI(service);

        ui.mainMenu();
    }
}
import Controller.Controller;
import Repository.*;
import Service.Service;
import Model.*;
import UI.UI;
import java.util.Scanner;

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
        IRepository<Notification> notificationRepository = new InMemoryRepository<>();

        Service service = new Service(
                petRepository,
                veterinarianRepository,
                appointmentRepository,
                healthRecordRepository,
                diseaseRepository,
                vaccineRepository,
                testRepository,
                notificationRepository,
                userRepository
        );


        Scanner scanner = new Scanner(System.in);
        UI ui = new UI(new Controller(service), scanner);


        ui.run();
        scanner.close();
    }
}

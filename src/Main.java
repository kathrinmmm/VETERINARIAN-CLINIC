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

        FileRepository<Pet> petFileRepo = new FileRepository<>("src/Files/Pets.txt");
        FileRepository<Veterinarian> vetFileRepo = new FileRepository<>("src/Files/Vets.txt");
        FileRepository<Disease> diseaseFileRepo = new FileRepository<>("src/Files/Diseases.txt");
        FileRepository<Vaccine> vaccineFileRepo = new FileRepository<>("src/Files/Vaccines.txt");
        FileRepository<Test> testFileRepo = new FileRepository<>("src/Files/Tests.txt");
        FileRepository<Notification> notificationFileRepo = new FileRepository<>("src/Files/Notifications.txt");
        FileRepository<Appointment> appFileRepo = new FileRepository<>("src/Files/Appointment.txt");
        FileRepository<HealthRecord> hrFileRepo = new FileRepository<>("src/Files/HealthRecord.txt");



//        Service service = new Service(
//                petRepository,
//                veterinarianRepository,
//                appointmentRepository,
//                healthRecordRepository,
//                diseaseRepository,
//                vaccineRepository,
//                testRepository,
//                notificationRepository,
//                userRepository
//        );

        Service service = new Service(
                petFileRepo,
                vetFileRepo,
                appFileRepo,
                hrFileRepo,
                diseaseFileRepo,
                vaccineFileRepo,
                testFileRepo,
                notificationFileRepo,
                userRepository
        );


        Scanner scanner = new Scanner(System.in);
        UI ui = new UI(new Controller(service), scanner);


        ui.run();
        scanner.close();
    }
}

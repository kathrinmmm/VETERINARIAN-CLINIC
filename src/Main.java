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
        IRepository<HealthTest> testRepository = new InMemoryRepository<>();
        IRepository<Vaccine> vaccineRepository = new InMemoryRepository<>();
        IRepository<HealthRecord> healthRecordRepository = new InMemoryRepository<>();
        IRepository<Pet_Disease> petDiseaseRepository = new InMemoryRepository<>();
        IRepository<Notification> notificationRepository = new InMemoryRepository<>();

        FileRepository<Pet> petFileRepo = new FileRepository<>("src/Files/Pets.txt");
        FileRepository<Veterinarian> vetFileRepo = new FileRepository<>("src/Files/Vets.txt");
        FileRepository<Disease> diseaseFileRepo = new FileRepository<>("src/Files/Diseases.txt");
        FileRepository<Vaccine> vaccineFileRepo = new FileRepository<>("src/Files/Vaccines.txt");
        FileRepository<HealthTest> testFileRepo = new FileRepository<>("src/Files/Tests.txt");
        FileRepository<Notification> notificationFileRepo = new FileRepository<>("src/Files/Notifications.txt");
        FileRepository<Appointment> appFileRepo = new FileRepository<>("src/Files/Appointment.txt");
        FileRepository<HealthRecord> hrFileRepo = new FileRepository<>("src/Files/HealthRecord.txt");

        //in memory repo

        Service service = new Service(
                petRepository,
                veterinarianRepository,
                appointmentRepository,
                healthRecordRepository,
                diseaseRepository,
                vaccineRepository,
                testRepository,
                notificationRepository,
                petDiseaseRepository
                //userRepository
        );
        //in file repo
//        Service service = new Service(
//                petFileRepo,
//                vetFileRepo,
//                appFileRepo,
//                hrFileRepo,
//                diseaseFileRepo,
//                vaccineFileRepo,
//                testFileRepo,
//                notificationFileRepo,petDiseaseRepository
//                //userRepository
//        );

//        String DB_URL = "jdbc:mysql://127.0.0.1:3306/veterinary";
//        String DB_USER = "root";
//        String DB_PASSWORD = "kitty18";
//
//        IRepository<Veterinarian> vetRepo = new VeterinarianDBRepository(DB_URL, DB_USER, DB_PASSWORD);
//        IRepository<Pet> petRepo = new PetDBRepository(DB_URL, DB_USER, DB_PASSWORD);
//        IRepository<Notification> notifRepo = new NotificationDBRepository(DB_URL, DB_USER, DB_PASSWORD);
//        IRepository<HealthRecord> hrRepo = new HealthRecordDBRepository(DB_URL, DB_USER, DB_PASSWORD);
//        IRepository<Appointment> appRepo = new AppointmentDBRepository(DB_URL, DB_USER, DB_PASSWORD);
//        IRepository<Disease> diseaseRepo = new DiseaseDBRepository(DB_URL, DB_USER, DB_PASSWORD);
//        IRepository<Pet_Disease> p_dRepo = new Pet_DiseaseDBRepository(DB_URL, DB_USER, DB_PASSWORD);
//        IRepository<Test> testRepo = new TestDBRepository(DB_URL, DB_USER, DB_PASSWORD);
//        IRepository<Vaccine> vaccRepo = new VaccineDBRepository(DB_URL, DB_USER, DB_PASSWORD);
//
//        //db repo
//        Service service = new Service(petRepo,vetRepo,appRepo,hrRepo,diseaseRepo,vaccRepo,testRepo,notifRepo,p_dRepo);

        Scanner scanner = new Scanner(System.in);
        UI ui = new UI(new Controller(service), scanner);


        ui.run();
        scanner.close();
    }
}

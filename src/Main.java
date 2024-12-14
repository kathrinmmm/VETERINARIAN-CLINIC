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


        petRepository = new PetDBRepository("src/Repository/PetDBRepository.java", "user", "password");
        userRepository = new UserDBRepository("src/Repository/UserDBRepository.java", "user", "password") {
            @Override
            public void update(Integer id, User entity) {

            }

            @Override
            public void update(Disease obj) {

            }

            @Override
            public void update(Appointment obj) {

            }

            @Override
            public void update(HealthRecord obj) {

            }

            @Override
            public void update(Notification obj) {

            }

            @Override
            public void update(Pet obj) {

            }

            @Override
            public void update(Pet_Disease obj) {

            }

            @Override
            public void update(Test obj) {

            }

            @Override
            public void update(Vaccine obj) {

            }
        };
        diseaseRepository = new DiseaseDBRepository("src/Repository/DiseaseDBRepository.java", "user", "password") {
            @Override
            public void update(HealthRecord obj) {

            }

            @Override
            public void update(Notification obj) {

            }

            @Override
            public void update(Pet obj) {

            }

            @Override
            public void update(Pet_Disease obj) {

            }

            @Override
            public void update(Test obj) {

            }

            @Override
            public void update(User obj) {

            }

            @Override
            public void update(Vaccine obj) {

            }
        };
        veterinarianRepository = new VeterinarianDBRepository("src/Repository/VeterinarianDBRepository.java", "user", "password");
        appointmentRepository = new AppointmentDBRepository("src/Repository/AppointmentDBRepository.java", "user", "password") {
            @Override
            public void update(Disease obj) {

            }

            @Override
            public void update(HealthRecord obj) {

            }

            @Override
            public void update(Notification obj) {

            }

            @Override
            public void update(Pet obj) {

            }

            @Override
            public void update(Pet_Disease obj) {

            }

            @Override
            public void update(Test obj) {

            }

            @Override
            public void update(User obj) {

            }

            @Override
            public void update(Vaccine obj) {

            }
        };
        testRepository = new TestDBRepository("src/Repository/TestDBRepository.java", "user", "password");
        vaccineRepository = new VaccineDBRepository("src/Repository/VaccineDBRepository.java", "user", "password");
        healthRecordRepository = new HealthRecordDBRepository("src/Repository/HealthRecordDBRepository.java", "user", "password");
        petDiseaseRepository = new Pet_DiseaseDBRepository("src/Repository/Pet_DiseaseDBRepository.java", "user", "password");
        notificationRepository = new NotificationDBRepository("src/Repository/NotificationDBRepository.java", "user", "password") {
            @Override
            public void update(Integer id, Notification entity) {

            }

            @Override
            public void update(Disease obj) {

            }

            @Override
            public void update(Appointment obj) {

            }

            @Override
            public void update(HealthRecord obj) {

            }

            @Override
            public void update(Pet obj) {

            }

            @Override
            public void update(Pet_Disease obj) {

            }

            @Override
            public void update(Test obj) {

            }

            @Override
            public void update(User obj) {

            }

            @Override
            public void update(Vaccine obj) {

            }
        };


//in memory
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

      //in file
//        Service service = new Service(
//                petFileRepo,
//                vetFileRepo,
//                appFileRepo,
//                hrFileRepo,
//                diseaseFileRepo,
//                vaccineFileRepo,
//                testFileRepo,
//                notificationFileRepo,
//                userRepository
//        );

        //database repository
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

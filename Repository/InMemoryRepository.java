package Repository;

import java.util.*;

import model.User;
import model.Pet;
import model.HealthRecord;
import model.Pet_Disease;
import model.Vaccine;
import model.Test;
import model.Veterinarian;
import model.Disease;
import model.Notification;
import model.Appointment;

public class InMemoryRepository<T> extends IRepository<T> { // Changed 'extends' to 'implements'
    private Map<Integer, T> data = new HashMap<>();
    private int currentId = 1;

    @Override
    public void create(T entity) {
        data.put(currentId++, entity);
    }

    @Override
    public T read(int id) {
        return data.get(id);
    }

    @Override
    public void update(T entity) {
        // Implementation for updating the entity (not provided in your code)
    }

    @Override
    public void delete(int id) {
        data.remove(id);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(data.values());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IRepository<User> userIRepository = new InMemoryRepository<>();
        IRepository<Pet> petIRepository = new InMemoryRepository<>();
        IRepository<Appointment> appointmentIRepository = new InMemoryRepository<>(); // Fixed typo in variable name
        IRepository<HealthRecord> healthRecordIRepository = new InMemoryRepository<>();
        IRepository<Pet_Disease> petDiseaseIRepository = new InMemoryRepository<>(); // Fixed typo in variable name
        IRepository<Vaccine> vaccineIRepository = new InMemoryRepository<>();
        IRepository<Veterinarian> veterinarianIRepository = new InMemoryRepository<>();
        IRepository<Notification> notificationIRepository = new InMemoryRepository<>();
        IRepository<Disease> diseaseIRepository = new InMemoryRepository<>();
        IRepository<Test> testIRepository = new InMemoryRepository<>();
    }
}
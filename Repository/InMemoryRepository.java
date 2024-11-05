package Repository;

import java.util.*;

public class InMemoryRepository<T> extends Repository.IRepository<T> {
    private Map<Integer, T> data = new HashMap<>();
    private int currentId = 1;

    @Override
    public void create(T entity) {
        data.put(currentId, entity);
    }

    @Override
    public T read(int id) {
        if (!data.containsKey(id)) {
            throw new IllegalArgumentException("Entity with id " + id + " does not exist.");
        }
        return data.get(id);
    }

    @Override
    public void update(T entity) {
        if (data.containsKey(currentId)) {
            data.put(currentId, entity);
        }
    }

    @Override
    public void delete(int id) {
        data.remove(id);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(data.values());
    }

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        IRepository<User> userIRepository = new InMemoryRepository<>();
//        IRepository<Pet> petIRepository = new InMemoryRepository<>();
//        IRepository<Appointment> appointmentIRepository = new InMemoryRepository<>(); // Fixed typo in variable name
//        IRepository<HealthRecord> healthRecordIRepository = new InMemoryRepository<>();
//        IRepository<Pet_Disease> petDiseaseIRepository = new InMemoryRepository<>(); // Fixed typo in variable name
//        IRepository<Vaccine> vaccineIRepository = new InMemoryRepository<>();
//        IRepository<Veterinarian> veterinarianIRepository = new InMemoryRepository<>();
//        IRepository<Notification> notificationIRepository = new InMemoryRepository<>();
//        IRepository<Disease> diseaseIRepository = new InMemoryRepository<>();
//        IRepository<Test> testIRepository = new InMemoryRepository<>();
//    }
}
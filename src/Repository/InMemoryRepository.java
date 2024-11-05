package src.Repository;

import java.util.*;

public class InMemoryRepository<T> implements IRepository<T> {
    private Map<Integer, T> data = new HashMap<>();
    private int currentId = 1;

    @Override
    public void create(T entity) {
        data.put(currentId++, entity);  // Increment currentId after putting
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
        Integer id = currentId;
        if (data.containsKey(id)) {
            data.put(id, entity);
        } else {
            throw new IllegalArgumentException("Cannot update: Entity with id " + id + " does not exist.");
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
}
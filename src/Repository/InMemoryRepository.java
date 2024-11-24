package Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Model.HasId;

/**
 * The InMemoryRepository class provides an in-memory implementation of the {@link IRepository} interface.
 * It stores objects in a {@link Map} and provides CRUD (Create, Read, Update, Delete) operations for managing
 * objects that implement the {@link HasId} interface.
 *
 * @param <T> The type of the objects being stored, which must extend {@link HasId}.
 */
public class InMemoryRepository <T extends HasId> implements IRepository<T> {
    /** A map to store objects by their ID. */
    private final Map<Integer,T> map = new HashMap<>();
    /**
     * Creates a new object in the repository if it doesn't already exist.
     * If an object with the same ID already exists, it won't be added.
     *
     * @param obj The object to be created.
     */
    @Override
    public void create(T obj) {
        map.putIfAbsent(obj.getId(), obj);
    }

    /**
     * Retrieves an object from the repository by its ID.
     *
     * @param id The ID of the object to retrieve.
     * @return The object with the specified ID, or {@code null} if it doesn't exist.
     */
    @Override
    public T read(Integer id) {
        return map.get(id);
    }

    /**
     * Updates an existing object in the repository.
     * If an object with the same ID exists, it will be replaced by the new object.
     *
     * @param id The ID of the object to update.
     * @param obj The updated object to replace the existing one.
     */
    @Override
    public void update(Integer id, T obj) {
        map.replace(obj.getId(),obj);
    }


    /**
     * Deletes an object from the repository by its ID.
     *
     * @param id The ID of the object to delete.
     */
    @Override
    public void delete(Integer id) {
        map.remove(id);
    }


    /**
     * Retrieves all objects stored in the repository.
     *
     * @return A list of all objects in the repository.
     */
    @Override
    public List<T> getAll() {
        return map.values().stream().toList();
    }
}
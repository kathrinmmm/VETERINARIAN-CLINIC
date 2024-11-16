package Repository;
import java.util.Optional;
import java.util.List;

public interface IRepository<T> {
    void create(T entity);

    T read(int id);

    void update(int id, T entity);

    void delete(int id);

    List<T> findAll();

    Optional<T> findById(int id);
}
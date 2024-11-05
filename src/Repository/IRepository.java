package Repository;

import java.util.List;

public interface IRepository<T> {
    void create(T entity);

    T read(int id);

    void update(T entity);

    void delete(int id);

    List<T> findAll();
}
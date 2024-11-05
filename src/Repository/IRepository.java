package Repository;

import java.util.List;

public abstract class IRepository<T> {
    void create(T entity) {

    }

    T read(int id) {
        return null;
    }

    void update(T entity) {

    }

    void delete(int id) {

    }

    List<T> findAll() {
        return null;
    }
}

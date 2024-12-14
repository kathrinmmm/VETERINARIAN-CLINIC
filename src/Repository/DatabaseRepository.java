package Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Model.*;

public abstract class DatabaseRepository<T extends HasId> implements IRepository<T>, AutoCloseable {

    protected final Connection connection;

    public DatabaseRepository(String dbUrl, String dbUser, String dbPassword) {
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to close database connection", e);
        }
    }

    public abstract void update(T obj);
}

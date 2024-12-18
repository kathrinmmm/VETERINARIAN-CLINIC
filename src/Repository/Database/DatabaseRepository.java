package Repository.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Model.HasId;
import Repository.IRepository;


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
    public void close() throws Exception {
        connection.close();
    }
}
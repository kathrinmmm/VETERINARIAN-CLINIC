package Repository.Database;

import Model.Veterinarian;
import Utils.Specialization;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeterinarianDBRepository extends DatabaseRepository<Veterinarian> {

    public VeterinarianDBRepository(String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
        createTableIfNotExists();
    }

    /**
     * Creates the Veterinarian table in the database if it does not exist.
     */
    private void createTableIfNotExists() {
        String sql = """
            CREATE TABLE IF NOT EXISTS Veterinarian (
                Id INT PRIMARY KEY AUTO_INCREMENT,
                Name VARCHAR(255) NOT NULL,
                Username VARCHAR(255) UNIQUE NOT NULL,
                Password VARCHAR(255) NOT NULL,
                Specialization VARCHAR(50) NOT NULL
            );
        """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating Veterinarian table", e);
        }
    }

    @Override
    public void create(Veterinarian obj) {
        String sql = "INSERT INTO Veterinarian (Name, Username, Password, Specialization) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getUsername());
            statement.setString(3, obj.getPassword());
            statement.setString(4, obj.getSpecialization().name());

            statement.execute();

            // Retrieve the generated ID and set it on the object
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    obj.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating Veterinarian record", e);
        }
    }

    @Override
    public void update(Integer id, Veterinarian obj) {
        String sql = """
            UPDATE Veterinarian 
            SET Name = ?, Username = ?, Password = ?, Specialization = ? 
            WHERE Id = ?
        """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getUsername());
            statement.setString(3, obj.getPassword());
            statement.setString(4, obj.getSpecialization().name());
            statement.setInt(5, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating Veterinarian record", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Veterinarian WHERE Id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting Veterinarian record", e);
        }
    }

    @Override
    public List<Veterinarian> getAll() {
        String sql = "SELECT * FROM Veterinarian";
        List<Veterinarian> veterinarians = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                veterinarians.add(extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all Veterinarian records", e);
        }

        return veterinarians;
    }

    @Override
    public Veterinarian read(Integer id) {
        String sql = "SELECT * FROM Veterinarian WHERE Id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading Veterinarian record", e);
        }

        return null;
    }

    private static Veterinarian extractFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("Id");
        String name = resultSet.getString("Name");
        String username = resultSet.getString("Username");
        String password = resultSet.getString("Password");
        Specialization specialization = Specialization.valueOf(resultSet.getString("Specialization"));

        return new Veterinarian(id, name, username, password, specialization);
    }
}

package Repository.Database;

import Model.Vaccine;
import Utils.VaccineType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VaccineDBRepository extends DatabaseRepository<Vaccine> {

    public VaccineDBRepository(String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
        createTableIfNotExists();
    }

    /**
     * Creates the Vaccine table in the database if it does not exist.
     */
    private void createTableIfNotExists() {
        String sql = """
            CREATE TABLE IF NOT EXISTS Vaccine (
                Id INT PRIMARY KEY AUTO_INCREMENT,
                Name VARCHAR(255) NOT NULL,
                VaccineType VARCHAR(50) NOT NULL
            );
        """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating Vaccine table", e);
        }
    }

    @Override
    public void create(Vaccine obj) {
        String sql = "INSERT INTO Vaccine (Name, VaccineType) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getVaccineType().name());

            statement.execute();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    obj.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating Vaccine record", e);
        }
    }

    @Override
    public void update(Integer id, Vaccine obj) {
        String sql = "UPDATE Vaccine SET Name = ?, VaccineType = ? WHERE Id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getVaccineType().name());
            statement.setInt(3, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating Vaccine record", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Vaccine WHERE Id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting Vaccine record", e);
        }
    }

    @Override
    public List<Vaccine> getAll() {
        String sql = "SELECT * FROM Vaccine";
        List<Vaccine> vaccines = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                vaccines.add(extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all Vaccine records", e);
        }

        return vaccines;
    }

    @Override
    public Vaccine read(Integer id) {
        String sql = "SELECT * FROM Vaccine WHERE Id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading Vaccine record", e);
        }

        return null;
    }

    private static Vaccine extractFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("Id");
        String name = resultSet.getString("Name");
        VaccineType vaccineType = VaccineType.valueOf(resultSet.getString("VaccineType"));

        return new Vaccine(id, name, vaccineType);
    }
}

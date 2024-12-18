package Repository.Database;

import Model.HealthTest;
import Utils.TestType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDBRepository extends DatabaseRepository<HealthTest> {

    public TestDBRepository(String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
        createTableIfNotExists();
    }

    /**
     * Creates the Test table in the database if it does not exist.
     */
    private void createTableIfNotExists() {
        String sql = """
            CREATE TABLE IF NOT EXISTS Test (
                Id INT PRIMARY KEY AUTO_INCREMENT,
                Name VARCHAR(255) NOT NULL,
                TestType VARCHAR(50) NOT NULL
            );
        """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating Test table", e);
        }
    }

    @Override
    public void create(HealthTest obj) {
        String sql = "INSERT INTO Test (Name, TestType) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getTestType().name());

            statement.execute();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    obj.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating Test record", e);
        }
    }

    @Override
    public void update(Integer id, HealthTest obj) {
        String sql = "UPDATE Test SET Name = ?, TestType = ? WHERE Id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getTestType().name());
            statement.setInt(3, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating Test record", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Test WHERE Id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting Test record", e);
        }
    }

    @Override
    public List<HealthTest> getAll() {
        String sql = "SELECT * FROM Test";
        List<HealthTest> healthTests = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                healthTests.add(extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all Test records", e);
        }

        return healthTests;
    }

    @Override
    public HealthTest read(Integer id) {
        String sql = "SELECT * FROM Test WHERE Id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading Test record", e);
        }

        return null;
    }

    private static HealthTest extractFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("Id");
        String name = resultSet.getString("Name");
        TestType testType = TestType.valueOf(resultSet.getString("TestType"));

        return new HealthTest(id, name, testType);
    }
}

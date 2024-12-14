package Repository;

import Model.*;
import Utils.TestType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDBRepository extends DatabaseRepository<Test> {

    public TestDBRepository(String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void update(Disease obj) {

    }

    @Override
    public void update(Appointment obj) {

    }

    @Override
    public void update(HealthRecord obj) {

    }

    @Override
    public void update(Notification obj) {

    }

    @Override
    public void update(Pet obj) {

    }

    @Override
    public void update(Pet_Disease obj) {

    }

    @Override
    public void create(Test obj) {
        String sql = "INSERT INTO Test (id, name, testType) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getId());
            statement.setString(2, obj.getName());
            statement.setString(3, obj.getTestType().toString()); // Assuming TestType is an Enum

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while creating the Test", e);
        }
    }

    @Override
    public Test read(Integer id) {
        String sql = "SELECT * FROM Test WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractFromResultSet(resultSet);
                }
                return null; // No record found
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading the Test", e);
        }
    }

    @Override
    public void update(Veterinarian obj) {
        String sql = "UPDATE Veterinarian SET name = ?, username = ?, password = ?, specialization = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getUsername());
            statement.setString(3, obj.getPassword());
            statement.setString(4, obj.getSpecialization().toString());
            statement.setInt(5, obj.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while updating the Veterinarian", e);
        }
    }

    @Override
    public void update(Integer id, Test entity) {

    }

    @Override
    public void update(Test obj) {
        String sql = "UPDATE Test SET name = ?, testType = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getTestType().toString()); // Assuming TestType is an Enum
            statement.setInt(3, obj.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while updating the Test", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Test WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting the Test", e);
        }
    }

    @Override
    public List<Test> getAll() {
        String sql = "SELECT * FROM Test";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Test> tests = new ArrayList<>();
                while (resultSet.next()) {
                    tests.add(extractFromResultSet(resultSet));
                }
                return tests;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while retrieving all Tests", e);
        }
    }

    private Test extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new Test(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                TestType.valueOf(resultSet.getString("testType")) // Assuming TestType is an Enum
        );
    }
}

package Repository;

import Model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HealthRecordDBRepository extends DatabaseRepository<HealthRecord> {

    public HealthRecordDBRepository(String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void create(HealthRecord obj) {
        String sql = "INSERT INTO HealthRecord (healthId, petId, appointments, petDiseases) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getId());
            statement.setInt(2, obj.getPetId()); // Corect: int
            statement.setString(3, obj.getAppoitment()); // Corect: String
            statement.setString(4, obj.getPetDiseases()); // Corect: String

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HealthRecord read(Integer healthId) {
        String sql = "SELECT * FROM HealthRecord WHERE healthId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, healthId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extractFromResultSet(resultSet);
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
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
    public void update(Integer id, HealthRecord entity) {

    }

    @Override
    public void update(Disease obj) {

    }

    @Override
    public void update(Appointment obj) {

    }

    @Override
    public void update(HealthRecord obj) {
        String sql = "UPDATE HealthRecord SET petId = ?, appointments = ?, petDiseases = ? WHERE healthId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getPetId());
            statement.setString(2, obj.getAppointments());
            statement.setString(3, obj.getPetDiseases());
            statement.setInt(4, obj.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
    public void update(Test obj) {

    }

    @Override
    public void update(User obj) {

    }

    @Override
    public void update(Vaccine obj) {

    }

    @Override
    public void delete(Integer healthId) {
        String sql = "DELETE FROM HealthRecord WHERE healthId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, healthId);

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<HealthRecord> getAll() {
        String sql = "SELECT * FROM HealthRecord";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            List<HealthRecord> healthRecords = new ArrayList<>();

            while (resultSet.next()) {
                healthRecords.add(extractFromResultSet(resultSet));
            }

            return healthRecords;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static HealthRecord extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new HealthRecord(
                resultSet.getInt("healthId"),
                resultSet.getInt("petId"),
                resultSet.getString("appointments"),
                resultSet.getString("petDiseases")
        );
    }
}

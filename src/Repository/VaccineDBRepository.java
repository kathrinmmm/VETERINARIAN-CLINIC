package Repository;

import Model.*;
import Utils.VaccineType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VaccineDBRepository extends DatabaseRepository<Vaccine> {

    public VaccineDBRepository(String dbUrl, String dbUser, String dbPassword) {
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
    public void update(Test obj) {

    }

    @Override
    public void update(User obj) {

    }

    @Override
    public void create(Vaccine obj) {
        String sql = "INSERT INTO Vaccine (id, name, vaccineType) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getId());
            statement.setString(2, obj.getName());
            statement.setString(3, obj.getVaccineType().toString());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while creating the Vaccine", e);
        }
    }

    @Override
    public Vaccine read(Integer id) {
        String sql = "SELECT * FROM Vaccine WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractFromResultSet(resultSet);
                }
                return null; // No record found
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading the Vaccine", e);
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
    public void update(Integer id, Vaccine entity) {

    }

    @Override
    public void update(Vaccine obj) {
        String sql = "UPDATE Vaccine SET name = ?, vaccineType = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getVaccineType().toString());
            statement.setInt(3, obj.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while updating the Vaccine", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Vaccine WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting the Vaccine", e);
        }
    }

    @Override
    public List<Vaccine> getAll() {
        String sql = "SELECT * FROM Vaccine";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Vaccine> vaccines = new ArrayList<>();
                while (resultSet.next()) {
                    vaccines.add(extractFromResultSet(resultSet));
                }
                return vaccines;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while retrieving all Vaccines", e);
        }
    }

    private Vaccine extractFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        VaccineType vaccineType = VaccineType.valueOf(resultSet.getString("vaccineType"));

        return new Vaccine(id, name, vaccineType);
    }
}

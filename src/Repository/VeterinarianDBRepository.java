package Repository;

import Model.*;
import Utils.Specialization;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeterinarianDBRepository extends DatabaseRepository<Veterinarian> {

    public VeterinarianDBRepository(String dbUrl, String dbUser, String dbPassword) {
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
    public void update(Vaccine obj) {

    }

    @Override
    public void create(Veterinarian veterinarian) {
        String sql = "INSERT INTO Veterinarian (id, name, username, password, specialization) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, veterinarian.getId());
            statement.setString(2, veterinarian.getName());
            statement.setString(3, veterinarian.getUsername());
            statement.setString(4, veterinarian.getPassword());
            statement.setString(5, veterinarian.getSpecialization().toString());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while creating the Veterinarian", e);
        }
    }

    @Override
    public Veterinarian read(Integer id) {
        String sql = "SELECT * FROM Veterinarian WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractFromResultSet(resultSet);
                }
                return null; // No record found
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading the Veterinarian", e);
        }
    }

    @Override
    public void update(Integer id, Veterinarian entity) {

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
    public void delete(Integer id) {
        String sql = "DELETE FROM Veterinarian WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting the Veterinarian", e);
        }
    }

    @Override
    public List<Veterinarian> getAll() {
        String sql = "SELECT * FROM Veterinarian";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Veterinarian> veterinarians = new ArrayList<>();
                while (resultSet.next()) {
                    veterinarians.add(extractFromResultSet(resultSet));
                }
                return veterinarians;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while retrieving all Veterinarians", e);
        }
    }

    private Veterinarian extractFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        Specialization specialization = Specialization.valueOf(resultSet.getString("specialization"));

        return new Veterinarian(id, name, username, password, specialization);
    }
}

package Repository;

import Model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetDBRepository extends DatabaseRepository<Pet> {

    public PetDBRepository(String dbUrl, String dbUser, String dbPassword) {
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
    public void create(Pet obj) {
        String sql = "INSERT INTO Pet (id, name, username, password, animalType) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getId());
            statement.setString(2, obj.getName()); // Correct: String
            statement.setString(3, obj.getUsername()); // Correct: String
            statement.setString(4, obj.getPassword()); // Correct: String
            statement.setString(5, obj.getAnimalType()); // Correct: String

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while creating the pet", e);
        }
    }

    @Override
    public Pet read(Integer id) {
        String sql = "SELECT * FROM Pet WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractFromResultSet(resultSet);
                }
                return null; // No record found
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading pet", e);
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
    public void update(Integer id, Pet entity) {

    }

    @Override
    public void update(Pet obj) {
        String sql = "UPDATE Pet SET name = ?, username = ?, password = ?, animalType = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, obj.getName()); // Correct: String
            statement.setString(2, obj.getUsername()); // Correct: String
            statement.setString(3, obj.getPassword()); // Correct: String
            statement.setString(4, obj.getAnimalType()); // Correct: String
            statement.setInt(5, obj.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while updating pet", e);
        }
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
    public void delete(Integer id) {
        String sql = "DELETE FROM Pet WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting pet", e);
        }
    }

    @Override
    public List<Pet> getAll() {
        String sql = "SELECT * FROM Pet";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Pet> pets = new ArrayList<>();
                while (resultSet.next()) {
                    pets.add(extractFromResultSet(resultSet));
                }
                return pets;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while retrieving all pets", e);
        }
    }

    private static Pet extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new Pet(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getString("animalType")
        );
    }
}

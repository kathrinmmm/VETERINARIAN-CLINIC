package Repository;

import Model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Pet_DiseaseDBRepository extends DatabaseRepository<Pet_Disease> {

    public Pet_DiseaseDBRepository(String dbUrl, String dbUser, String dbPassword) {
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
    public void create(Pet_Disease obj) {
        String sql = "INSERT INTO Pet_Disease (id, petId, diseaseId) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getId());
            statement.setInt(2, obj.petid());
            statement.setInt(3, obj.getDisease().getId()); // Assuming Disease has getId()

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while creating the Pet_Disease relationship", e);
        }
    }

    @Override
    public Pet_Disease read(Integer id) {
        String sql = "SELECT * FROM Pet_Disease WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractFromResultSet(resultSet);
                }
                return null; // No record found
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading the Pet_Disease relationship", e);
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
    public void update(Integer id, Pet_Disease entity) {

    }

    @Override
    public void update(Pet_Disease obj) {
        String sql = "UPDATE Pet_Disease SET petId = ?, diseaseId = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.petid());
            statement.setInt(2, obj.getDisease().getId()); // Assuming Disease has getId()
            statement.setInt(3, obj.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while updating the Pet_Disease relationship", e);
        }
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
        String sql = "DELETE FROM Pet_Disease WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting the Pet_Disease relationship", e);
        }
    }

    @Override
    public List<Pet_Disease> getAll() {
        String sql = "SELECT * FROM Pet_Disease";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Pet_Disease> petDiseases = new ArrayList<>();
                while (resultSet.next()) {
                    petDiseases.add(extractFromResultSet(resultSet));
                }
                return petDiseases;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while retrieving all Pet_Disease relationships", e);
        }
    }

    private Pet_Disease extractFromResultSet(ResultSet resultSet) throws SQLException {
        // Fetching the associated Disease object may require a separate query or cache
        return new Pet_Disease(
                resultSet.getInt("id"),
                resultSet.getInt("petId"),
                fetchDisease(resultSet.getInt("diseaseId")) // Implement fetchDisease to load Disease by ID
        );
    }

    private Model.Disease fetchDisease(int diseaseId) {
        throw new UnsupportedOperationException("Implement fetchDisease method");
    }
}

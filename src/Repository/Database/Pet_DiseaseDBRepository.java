package Repository.Database;

import Model.Disease;
import Model.Pet_Disease;
import Utils.DiseaseType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Pet_DiseaseDBRepository extends DatabaseRepository<Pet_Disease> {

    public Pet_DiseaseDBRepository(String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        String sql = """
            CREATE TABLE IF NOT EXISTS PetDisease (
                Id INT PRIMARY KEY AUTO_INCREMENT,
                PetId INT NOT NULL,
                DiseaseId INT NOT NULL,
                DiseaseName VARCHAR(255) NOT NULL,
                DiseaseType VARCHAR(50) NOT NULL
            );
        """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating PetDisease table", e);
        }
    }

    @Override
    public void create(Pet_Disease obj) {
        String sql = "INSERT INTO PetDisease (PetId, DiseaseId, DiseaseName, DiseaseType) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.petid());
            statement.setInt(2, obj.getDisease().getId());
            statement.setString(3, obj.getDisease().getName());
            statement.setString(4, obj.getDisease().getDiseaseType().name());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating Pet_Disease record", e);
        }
    }

    @Override
    public void update(Integer id, Pet_Disease obj) {
        String sql = """
            UPDATE PetDisease 
            SET PetId = ?, DiseaseId = ?, DiseaseName = ?, DiseaseType = ? 
            WHERE Id = ? 
        """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.petid());
            statement.setInt(2, obj.getDisease().getId());
            statement.setString(3, obj.getDisease().getName());
            statement.setString(4, obj.getDisease().getDiseaseType().name());
            statement.setInt(5, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating Pet_Disease record", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM PetDisease WHERE Id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting Pet_Disease record", e);
        }
    }

    @Override
    public List<Pet_Disease> getAll() {
        String sql = "SELECT * FROM PetDisease";
        List<Pet_Disease> petDiseases = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                petDiseases.add(extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all Pet_Disease records", e);
        }

        return petDiseases;
    }

    @Override
    public Pet_Disease read(Integer id) {
        String sql = "SELECT * FROM PetDisease WHERE Id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading Pet_Disease record", e);
        }

        return null;
    }

    private static Pet_Disease extractFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("Id");
        Integer petId = resultSet.getInt("PetId");
        Integer diseaseId = resultSet.getInt("DiseaseId");
        String diseaseName = resultSet.getString("DiseaseName");
        DiseaseType diseaseType = DiseaseType.valueOf(resultSet.getString("DiseaseType"));

        Disease disease = new Disease(diseaseId, diseaseName, diseaseType);

        return new Pet_Disease(id, petId, disease);
    }
}

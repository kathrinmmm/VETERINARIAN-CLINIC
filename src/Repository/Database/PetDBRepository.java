package Repository.Database;

import Model.*;
import Utils.AnimalType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDBRepository extends DatabaseRepository<Pet> {

    public PetDBRepository(String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        String sql = """
            CREATE TABLE IF NOT EXISTS Pet (
                Id INT PRIMARY KEY AUTO_INCREMENT,
                Name VARCHAR(255) NOT NULL,
                Username VARCHAR(255) UNIQUE NOT NULL,
                Password VARCHAR(255) NOT NULL,
                AnimalType VARCHAR(50) NOT NULL
            );
        """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating Pet table", e);
        }
    }

    @Override
    public void create(Pet pet) {
        String sqlPet = "INSERT INTO Pet (Name, Username, Password, AnimalType) VALUES (?, ?, ?, ?)";
        String sqlHealthRecord = "INSERT INTO HealthRecord (PetId, appointments, petDiseases) VALUES (?, ?, ?)";

        int petId = -1;

        // Inserare în tabela Pet
        try (PreparedStatement petStatement = connection.prepareStatement(sqlPet, Statement.RETURN_GENERATED_KEYS)) {
            petStatement.setString(1, pet.getName());
            petStatement.setString(2, pet.getUsername());
            petStatement.setString(3, pet.getPassword());
            petStatement.setString(4, pet.getAnimalType().name());
            int affectedRows = petStatement.executeUpdate();
            System.out.println("Pet insert affected rows: " + affectedRows);
            try (ResultSet generatedKeys = petStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    petId = generatedKeys.getInt(1);
                    System.out.println("Generated Pet ID: " + petId);
                } else {
                    System.out.println("No ID generated for Pet");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error inserting into Pet table: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error inserting into Pet table", e);
        }

        if (petId == -1) {
            throw new RuntimeException("No Pet ID generated, cannot proceed");
        }

        try (PreparedStatement hrStatement = connection.prepareStatement(sqlHealthRecord)) {
            hrStatement.setInt(1, petId);
            hrStatement.setString(2, "[]");
            hrStatement.setString(3, "[]");
            int rowsAffected = hrStatement.executeUpdate();
            System.out.println("HealthRecord insert affected rows: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("SQLException while inserting HealthRecord: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error inserting into HealthRecord table", e);
        }
    }





    @Override
    public void update(Integer id, Pet pet) {
        String sql = """
            UPDATE Pet 
            SET Name = ?, Username = ?, Password = ?, AnimalType = ? 
            WHERE Id = ?
        """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pet.getName());
            statement.setString(2, pet.getUsername());
            statement.setString(3, pet.getPassword());
            statement.setString(4, pet.getAnimalType().name());
            statement.setInt(5, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating Pet record", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Pet WHERE Id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting Pet record", e);
        }
    }

    @Override
    public List<Pet> getAll() {
        String sql = "SELECT * FROM Pet";
        List<Pet> pets = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                pets.add(extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all Pet records", e);
        }

        return pets;
    }

    @Override
    public Pet read(Integer id) {
        String sql = "SELECT * FROM Pet WHERE Id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading Pet record", e);
        }

        return null;
    }

    private static Pet extractFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("Id");
        String name = resultSet.getString("Name");
        String username = resultSet.getString("Username");
        String password = resultSet.getString("Password");
        AnimalType animalType = AnimalType.valueOf(resultSet.getString("AnimalType"));

        return new Pet(id, name, username, password, animalType);
    }
}

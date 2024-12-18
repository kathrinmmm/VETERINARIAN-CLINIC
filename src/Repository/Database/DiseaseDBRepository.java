package Repository.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.Disease;
import Utils.DiseaseType;


public class DiseaseDBRepository extends DatabaseRepository<Disease> {

    public DiseaseDBRepository(String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        String sql = """
            CREATE TABLE IF NOT EXISTS Disease (
                DiseaseId INT PRIMARY KEY,
                Name VARCHAR(255) NOT NULL,
                DiseaseType VARCHAR(255) NOT NULL
            );
        """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating Disease table", e);
        }
    }

    @Override
    public void create(Disease obj) {
        String sql = "INSERT INTO Disease (DiseaseId, Name, DiseaseType) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getId());
            statement.setString(2, obj.getName());
            statement.setString(3, obj.getDiseaseType().name());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating Disease", e);
        }
    }

    @Override
    public void update(Integer id, Disease obj) {
        String sql = "UPDATE Disease SET Name = ?, DiseaseType = ? WHERE DiseaseId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getDiseaseType().name());
            statement.setInt(3, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating Disease", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Disease WHERE DiseaseId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting Disease", e);
        }
    }

    @Override
    public List<Disease> getAll() {
        String sql = "SELECT * FROM Disease";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<Disease> diseases = new ArrayList<>();

            while (resultSet.next()) {
                diseases.add(extractFromResultSet(resultSet));
            }

            return diseases;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all Diseases", e);
        }
    }

    @Override
    public Disease read(Integer id) {
        String sql = "SELECT * FROM Disease WHERE DiseaseId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return extractFromResultSet(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading Disease", e);
        }
    }

    public static Disease extractFromResultSet(ResultSet resultSet) throws SQLException {
        int diseaseId = resultSet.getInt("DiseaseId");
        String name = resultSet.getString("Name");
        DiseaseType diseaseType = DiseaseType.valueOf(resultSet.getString("DiseaseType"));

        return new Disease(diseaseId, name, diseaseType);
    }
}


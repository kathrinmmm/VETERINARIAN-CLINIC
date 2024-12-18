package Repository;

import Model.Appointment;
import Model.Disease;
import Model.Veterinarian;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DiseaseDBRepository extends DatabaseRepository<Disease> {
    public DiseaseDBRepository(String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
    }

//    // Constructor corect
//    public DiseaseDBRepository(Connection connection) {
//        super(connection);
//    }

    @Override
    public void create(Disease obj) {
        String sql = "INSERT INTO Disease (id, name, diseaseType) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getId());
            statement.setString(2, obj.getName());
            statement.setString(3, obj.getDiseaseType());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Disease read(Integer id) {
        String sql = "SELECT * FROM Disease WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

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
    public void update(Integer id, Disease entity) {

    }

    @Override
    public void update(Disease obj) {
        String sql = "UPDATE Disease SET name = ?, diseaseType = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getDiseaseType());
            statement.setInt(3, obj.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Appointment obj) {

    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Disease WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
    }

    private static Disease extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new Disease(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("diseaseType")
        );
    }

}

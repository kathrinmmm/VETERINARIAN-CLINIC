package Repository;

import Model.User;
import Model.Veterinarian;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class UserDBRepository extends DatabaseRepository<User> {

    public UserDBRepository(String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void create(User obj) {
        String sql = "INSERT INTO User (id, name, username, password) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getId());
            statement.setString(2, obj.getName());
            statement.setString(3, obj.getUsername());
            statement.setString(4, obj.getPassword());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while creating the User", e);
        }
    }

    @Override
    public User read(Integer id) {
        String sql = "SELECT * FROM User WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractFromResultSet(resultSet);
                }
                return null; // No record found
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading the User", e);
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
    public void update(User obj) {
        String sql = "UPDATE User SET name = ?, username = ?, password = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, obj.getName());
            statement.setString(2, obj.getUsername());
            statement.setString(3, obj.getPassword());
            statement.setInt(4, obj.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while updating the User", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM User WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting the User", e);
        }
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM User";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                List<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    users.add(extractFromResultSet(resultSet));
                }
                return users;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while retrieving all Users", e);
        }
    }

    private User extractFromResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");

        // Since User is abstract, instantiate a subclass or throw an exception
        return new User(id, name, username, password) {
            // Provide an inline subclass implementation
        };
    }
}

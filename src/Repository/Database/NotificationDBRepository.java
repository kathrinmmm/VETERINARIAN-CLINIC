package Repository.Database;

import Model.Notification;
import Utils.NotificationType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDBRepository extends DatabaseRepository<Notification> {

    public NotificationDBRepository(String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        String sql = """
            CREATE TABLE IF NOT EXISTS Notification (
                notificationId INT PRIMARY KEY AUTO_INCREMENT,
                userId INT NOT NULL,
                title VARCHAR(255) NOT NULL,
                date DATE NOT NULL,
                notificationType VARCHAR(50) NOT NULL
            );
        """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating Notification table", e);
        }
    }

    @Override
    public void create(Notification obj) {
        String sql = "INSERT INTO Notification (userId, title, date, notificationType) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, obj.getUserId());
            statement.setString(2, obj.getTitle());
            statement.setString(3, obj.getDate());
            statement.setString(4, obj.getNotificationType().name());

            statement.execute();
            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                obj.setId(generatedKeys.getInt(1)); // Setăm ID-ul generat automat
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error creating Notification", e);
        }
    }

    @Override
    public void update(Integer id, Notification obj) {
        String sql = """
            UPDATE Notification 
            SET userId = ?, title = ?, date = ?, notificationType = ? 
            WHERE notificationId = ?
        """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getUserId());
            statement.setString(2, obj.getTitle());
            statement.setString(3, obj.getDate());
            statement.setString(4, obj.getNotificationType().name());
            statement.setInt(5, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating Notification", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Notification WHERE notificationId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting Notification", e);
        }
    }

    @Override
    public List<Notification> getAll() {
        String sql = "SELECT * FROM Notification";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<Notification> notifications = new ArrayList<>();

            while (resultSet.next()) {
                notifications.add(extractFromResultSet(resultSet));
            }

            return notifications;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all notifications", e);
        }
    }

    @Override
    public Notification read(Integer id) {
        String sql = "SELECT * FROM Notification WHERE notificationId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return extractFromResultSet(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading Notification", e);
        }
    }

    private static Notification extractFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("notificationId");
        int userId = resultSet.getInt("userId");
        String title = resultSet.getString("title");
        String date = resultSet.getString("date");
        NotificationType notificationType = NotificationType.valueOf(resultSet.getString("notificationType"));

        return new Notification(id, userId, title, date, notificationType);
    }
}

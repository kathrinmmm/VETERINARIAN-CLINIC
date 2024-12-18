package Repository;

import Model.Notification;
import Model.Veterinarian;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class NotificationDBRepository extends DatabaseRepository<Notification> {

    public NotificationDBRepository(String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void create(Notification obj) {
        String sql = "INSERT INTO Notification (id, userId, title, date, notificationType) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getId());
            statement.setInt(2, obj.getUserId()); // Correct: int
            statement.setString(3, obj.getTitle()); // Correct: String
            statement.setString(4, obj.getDate()); // Assuming it's a String; otherwise, convert to appropriate type
            statement.setString(5, obj.getNotificationType()); // Correct: String

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while creating notification", e);
        }
    }

    @Override
    public Notification read(Integer id) {
        String sql = "SELECT * FROM Notification WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractFromResultSet(resultSet);
                }
                return null; // No record found
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while reading notification", e);
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
    public void update(Notification obj) {
        String sql = "UPDATE Notification SET userId = ?, title = ?, date = ?, notificationType = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getUserId());
            statement.setString(2, obj.getTitle());
            statement.setString(3, obj.getDate());
            statement.setString(4, obj.getNotificationType());
            statement.setInt(5, obj.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while updating notification", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Notification WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting notification", e);
        }
    }

    @Override
    public List<Notification> getAll() {
        String sql = "SELECT * FROM Notification";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                List<Notification> notifications = new ArrayList<>();
                while (resultSet.next()) {
                    notifications.add(extractFromResultSet(resultSet));
                }
                return notifications;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while retrieving all notifications", e);
        }
    }

    private static Notification extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new Notification(
                resultSet.getInt("id"),
                resultSet.getInt("userId"),
                resultSet.getString("title"),
                resultSet.getString("date"),
                resultSet.getString("notificationType")
        );
    }
}

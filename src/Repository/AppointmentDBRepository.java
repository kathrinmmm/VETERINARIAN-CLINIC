package Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Model.Appointment;
import Model.Veterinarian;

public abstract class AppointmentDBRepository extends DatabaseRepository<Appointment> {
    public AppointmentDBRepository(String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
    }

    // Constructor (dacă este necesar să inițializez conexiunea)
//    public AppointmentDBRepository(Connection connection) {
//        super(connection);
//    }

    @Override
    public void create(Appointment obj) {
        String sql = "INSERT INTO Appointment(AppId, PetID, VetID, AppointmentType, Date, Time, Tests, Vaccines) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getAppId());
            statement.setInt(2, obj.getPetId());
            statement.setInt(3, obj.getVetId());
            statement.setString(4, obj.getAppointmentType());
            statement.setDate(5, java.sql.Date.valueOf(obj.getDate())); // Date
            statement.setTime(6, java.sql.Time.valueOf(obj.getTime())); // Time
            statement.setString(7, obj.getTests());
            statement.setString(8, obj.getVaccines());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Appointment read(Integer appId) {
        String sql = "SELECT * FROM Appointment WHERE AppId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, appId);

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
    public void update(Integer id, Appointment entity) {
        String sql = "UPDATE Appointment SET AppointmentType = ?, Date = ?, Time = ?, Tests = ?, Vaccines = ? WHERE AppId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getAppointmentType());
            statement.setDate(2, java.sql.Date.valueOf(entity.getDate()));
            statement.setTime(3, java.sql.Time.valueOf(entity.getTime()));
            statement.setString(4, entity.getTests());
            statement.setString(5, entity.getVaccines());
            statement.setInt(6, entity.getAppId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer AppId) {
        String sql = "DELETE FROM Appointment WHERE AppId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, AppId);

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Appointment> getAll() {
        String sql = "SELECT * FROM Appointment";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            List<Appointment> appointments = new ArrayList<>();

            while (resultSet.next()) {
                appointments.add(extractFromResultSet(resultSet));
            }

            return appointments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Appointment extractFromResultSet(ResultSet resultSet) throws SQLException {
        return new Appointment(
                resultSet.getInt("AppId"),
                resultSet.getInt("PetID"),
                resultSet.getInt("VetID"),
                resultSet.getString("AppointmentType"),
                resultSet.getDate("Date").toLocalDate(),
                resultSet.getTime("Time").toLocalTime(),
                resultSet.getString("Tests"),
                resultSet.getString("Vaccines")
        );
    }

    @Override
    public void update(Appointment obj) {

    }

    @Override
    public void update(Veterinarian obj) {

    }
}

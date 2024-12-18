package Repository.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import Model.Appointment;
import Model.HealthTest;
import Model.Vaccine;
import Utils.*;

public class AppointmentDBRepository extends DatabaseRepository<Appointment> {
    public AppointmentDBRepository(String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
        createTableIfNotExists();

    }

    private void createTableIfNotExists() {
        String sql = """
            CREATE TABLE IF NOT EXISTS Appointment (
                AppId INT PRIMARY KEY,
                PetId INT NOT NULL,
                VetId INT NOT NULL,
                AppointmentType VARCHAR(255) NOT NULL,
                date DATE NOT NULL,
                time TIME NOT NULL,
                healthTests VARCHAR(255) NOT NULL,
                vaccines VARCHAR(255) NOT NULL
            );
        """;
        String sqlTests = """
        CREATE TABLE IF NOT EXISTS AppointmentTest (
            AppointmentId INT,
            TestName VARCHAR(255),
            TestType VARCHAR(255),
            PRIMARY KEY (AppointmentId, TestName),
            FOREIGN KEY (AppointmentId) REFERENCES Appointment(AppId)
        );
    """;
        String sqlVaccines = """
        CREATE TABLE IF NOT EXISTS AppointmentVaccine (
            AppointmentId INT,
            VaccineName VARCHAR(255),
            VaccineType VARCHAR(255),
            PRIMARY KEY (AppointmentId, VaccineName),
            FOREIGN KEY (AppointmentId) REFERENCES Appointment(AppId)
        );
    """;
        try (PreparedStatement statementTests = connection.prepareStatement(sqlTests);
             PreparedStatement statementVaccines = connection.prepareStatement(sqlVaccines)) {
            statementTests.execute();
            statementVaccines.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating Test and Vaccine tables", e);
        }
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error creating Appointment table", e);
        }
    }


    @Override
    public void create(Appointment obj) {
        String sql = "INSERT INTO Appointment (AppId, PetId, VetId, AppointmentType, Date, Time, healthTests, vaccines) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getId());
            statement.setInt(2, obj.getPetId());
            statement.setInt(3, obj.getVetId());
            statement.setString(4, obj.getAppointmentType().name());
            statement.setString(5, obj.getDate());
            statement.setString(6, obj.getTime());

            statement.setString(7, obj.getTestsAsString() != null ? obj.getTestsAsString() : ""); // Default: șir vid
            statement.setString(8, obj.getVaccinesAsString() != null ? obj.getVaccinesAsString() : ""); // Default: șir vid

            statement.execute();
            System.out.println("Appointment created successfully with ID: " + obj.getId());

            saveTestsForAppointment(obj.getId(), obj.getTests());
            saveVaccinesForAppointment(obj.getId(), obj.getVaccines());
        } catch (SQLException e) {
            System.err.println("SQL Exception while creating appointment:");
            e.printStackTrace();
            throw new RuntimeException("Error creating appointment: " + e.getMessage(), e);
        } catch (Exception ex) {
            System.err.println("Unexpected error while creating appointment:");
            ex.printStackTrace();
            throw new RuntimeException("Unexpected error creating appointment: " + ex.getMessage(), ex);
        }
    }





    @Override
    public void update(Integer id, Appointment obj) {
        String sql = "UPDATE Appointment SET PetId = ?, VetId = ?, AppointmentType = ?, date = ?, time = ?, healthTests = ?, vaccines = ? WHERE AppId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, obj.getPetId());
            statement.setInt(2, obj.getVetId());
            statement.setString(3, obj.getAppointmentType().name());
            statement.setString(4, obj.getDate());
            statement.setString(5, obj.getTime());
            statement.setString(6, obj.getTestsAsString());
            statement.setString(7, obj.getVaccinesAsString());
            statement.setInt(8, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating Appointment", e);
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM Appointment WHERE AppId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting Appointment", e);
        }
    }

    @Override
    public List<Appointment> getAll() {
        String sql = "SELECT * FROM Appointment";
        List<Appointment> appointments = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Appointment app = extractFromResultSet(resultSet);

                app.setTests(getTestsForAppointment(app.getId()));
                app.setVaccines(getVaccinesForAppointment(app.getId()));

                System.out.println("Loaded appointment: " + app);
                appointments.add(app);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving appointments", e);
        }
        return appointments;
    }




    @Override
    public Appointment read(Integer id) {
        String sql = "SELECT * FROM Appointment WHERE AppId = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return extractFromResultSet(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading Appointment", e);
        }
    }


    public static Appointment extractFromResultSet(ResultSet resultSet) throws SQLException {
        int appId = resultSet.getInt("AppId");
        int petId = resultSet.getInt("PetId");
        int vetId = resultSet.getInt("VetId");
        AppointmentType appointmentType = AppointmentType.valueOf(resultSet.getString("AppointmentType"));
        String date = resultSet.getString("date");
        String time = resultSet.getString("time");

        return new Appointment(appId, petId, vetId, date, time, appointmentType, new ArrayList<>(), new ArrayList<>());
    }


    private void saveTestsForAppointment(int appointmentId, List<HealthTest> healthTests) {
        String sql = "INSERT INTO AppointmentTest (AppointmentId, TestName, TestType) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (HealthTest healthTest : healthTests) {
                statement.setInt(1, appointmentId);
                statement.setString(2, healthTest.getName());
                statement.setString(3, healthTest.getTestType().name());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving healthTests for appointment " + appointmentId, e);
        }
    }

    private void saveVaccinesForAppointment(int appointmentId, List<Vaccine> vaccines) {
        String sql = "INSERT INTO AppointmentVaccine (AppointmentId, VaccineName, VaccineType) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Vaccine vaccine : vaccines) {
                statement.setInt(1, appointmentId);
                statement.setString(2, vaccine.getName());
                statement.setString(3, vaccine.getVaccineType().name());
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving vaccines for appointment " + appointmentId, e);
        }
    }

    private List<HealthTest> getTestsForAppointment(int appointmentId) {
        List<HealthTest> healthTests = new ArrayList<>();
        String sql = "SELECT TestName, TestType FROM AppointmentTest WHERE AppointmentId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, appointmentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String testName = resultSet.getString("TestName");
                TestType testType = TestType.valueOf(resultSet.getString("TestType"));
                healthTests.add(new HealthTest(testName, testType));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving healthTests for appointment " + appointmentId, e);
        }
        return healthTests;
    }

    private List<Vaccine> getVaccinesForAppointment(int appointmentId) {
        List<Vaccine> vaccines = new ArrayList<>();
        String sql = "SELECT VaccineName, VaccineType FROM AppointmentVaccine WHERE AppointmentId = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, appointmentId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String vaccineName = resultSet.getString("VaccineName");
                VaccineType vaccineType = VaccineType.valueOf(resultSet.getString("VaccineType"));
                vaccines.add(new Vaccine(vaccineName, vaccineType));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving vaccines for appointment " + appointmentId, e);
        }
        return vaccines;
    }



}

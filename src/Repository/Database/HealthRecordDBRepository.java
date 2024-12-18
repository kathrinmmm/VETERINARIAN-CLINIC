package Repository.Database;

import java.sql.*;
import java.util.*;

import Model.*;
import Utils.*;

public class HealthRecordDBRepository extends DatabaseRepository<HealthRecord> {

    public HealthRecordDBRepository(String dbUrl, String dbUser, String dbPassword) {
        super(dbUrl, dbUser, dbPassword);
        createTables();
    }

    private void createTables() {
        String healthRecordTable = """
            CREATE TABLE IF NOT EXISTS HealthRecord (
                HealthId INT PRIMARY KEY,
                PetId INT NOT NULL
            );
        """;

        String appointmentTable = """
            CREATE TABLE IF NOT EXISTS Appointment (
                AppointmentId INT PRIMARY KEY AUTO_INCREMENT,
                HealthId INT NOT NULL,
                PetId INT NOT NULL,
                VetId INT NOT NULL,
                AppointmentType VARCHAR(50) NOT NULL,
                Date VARCHAR(20) NOT NULL,
                Time VARCHAR(20) NOT NULL,
                FOREIGN KEY (HealthId) REFERENCES HealthRecord(HealthId)
            );
        """;

        String appointmentTestTable = """
            CREATE TABLE IF NOT EXISTS AppointmentTest (
                AppointmentTestId INT PRIMARY KEY AUTO_INCREMENT,
                AppointmentId INT NOT NULL,
                TestName VARCHAR(100) NOT NULL,
                TestType VARCHAR(50) NOT NULL,
                FOREIGN KEY (AppointmentId) REFERENCES Appointment(AppointmentId)
            );
        """;

        String appointmentVaccineTable = """
            CREATE TABLE IF NOT EXISTS AppointmentVaccine (
                AppointmentVaccineId INT PRIMARY KEY AUTO_INCREMENT,
                AppointmentId INT NOT NULL,
                VaccineName VARCHAR(100) NOT NULL,
                VaccineType VARCHAR(50) NOT NULL,
                FOREIGN KEY (AppointmentId) REFERENCES Appointment(AppointmentId)
            );
        """;

        String diseaseTable = """
            CREATE TABLE IF NOT EXISTS PetDisease (
                DiseaseId INT PRIMARY KEY AUTO_INCREMENT,
                HealthId INT NOT NULL,
                DiseaseName VARCHAR(100) NOT NULL,
                DiseaseType VARCHAR(50) NOT NULL,
                FOREIGN KEY (HealthId) REFERENCES HealthRecord(HealthId)
            );
        """;

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(healthRecordTable);
            stmt.execute(appointmentTable);
            stmt.execute(appointmentTestTable);
            stmt.execute(appointmentVaccineTable);
            stmt.execute(diseaseTable);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating tables", e);
        }
    }

    // Inserăm un HealthRecord cu toate componentele
    @Override
    public void create(HealthRecord obj) {
        try {
            connection.setAutoCommit(false);

            // 1. Inserăm HealthRecord
            String healthRecordSql = "INSERT INTO HealthRecord (HealthId, PetId) VALUES (?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(healthRecordSql)) {
                stmt.setInt(1, obj.getId());
                stmt.setInt(2, obj.getPetId());
                stmt.execute();
            }

            // 2. Inserăm Appointments
            for (Appointment appointment : obj.getAppointments()) {
                int appointmentId = insertAppointment(obj.getId(), appointment);

                // 3. Inserăm Tests și Vaccines pentru fiecare Appointment
                insertAppointmentTests(appointmentId, appointment.getTests());
                insertAppointmentVaccines(appointmentId, appointment.getVaccines());
            }

            // 4. Inserăm Diseases
            insertDiseases(obj.getId(), obj.getPetDiseases());

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackEx) {
                throw new RuntimeException("Rollback failed", rollbackEx);
            }
            throw new RuntimeException("Error inserting HealthRecord", e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException autoCommitEx) {
                throw new RuntimeException("Failed to reset auto-commit", autoCommitEx);
            }
        }
    }

    private int insertAppointment(int healthId, Appointment appointment) throws SQLException {
        String sql = """
            INSERT INTO Appointment (HealthId, PetId, VetId, AppointmentType, date, time)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, healthId);
            stmt.setInt(2, appointment.getPetId());
            stmt.setInt(3, appointment.getVetId());
            stmt.setString(4, appointment.getAppointmentType().toString());
            stmt.setString(5, appointment.getDate());
            stmt.setString(6, appointment.getTime());
            stmt.execute();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);
            } else {
                throw new SQLException("Failed to retrieve Appointment ID");
            }
        }
    }

    private void insertAppointmentTests(int appointmentId, List<HealthTest> healthTests) throws SQLException {
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
            throw new RuntimeException("Error saving tests for appointment " + appointmentId, e);
        }
    }

    private void insertAppointmentVaccines(int appointmentId, List<Vaccine> vaccines) throws SQLException {
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

    private void insertDiseases(int healthId, List<Pet_Disease> diseases) throws SQLException {
        String sql = "INSERT INTO PetDisease (HealthId, DiseaseName, DiseaseType) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (Pet_Disease disease : diseases) {
                stmt.setInt(1, healthId);
                stmt.setString(2, disease.getDisease().getName());
                stmt.setString(3, disease.getDisease().getDiseaseType().toString());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    @Override
    public HealthRecord read(Integer id) {
        String sql = "SELECT * FROM HealthRecord WHERE HealthId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading HealthRecord", e);
        }
        return null;
    }


    private List<Appointment> getAppointments(int healthId) throws SQLException {
        String sql = "SELECT * FROM Appointment WHERE HealthId = ?";
        List<Appointment> appointments = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, healthId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int appointmentId = rs.getInt("AppointmentId");
                int petId = rs.getInt("PetId");
                int vetId = rs.getInt("VetId");
                AppointmentType type = AppointmentType.valueOf(rs.getString("AppointmentType"));
                String date = rs.getString("date");
                String time = rs.getString("time");

                List<HealthTest> healthTests = getAppointmentTests(appointmentId);
                List<Vaccine> vaccines = getAppointmentVaccines(appointmentId);

                appointments.add(new Appointment(appointmentId, petId, vetId, date, time, type, healthTests, vaccines));
            }
        }

        return appointments;
    }

    private List<HealthTest> getAppointmentTests(int appointmentId) {
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
            throw new RuntimeException("Error retrieving tests for appointment " + appointmentId, e);
        }
        return healthTests;
    }

    private List<Vaccine> getAppointmentVaccines(int appointmentId) {
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

    private List<Pet_Disease> getDiseases(int healthId) throws SQLException {
        String sql = "SELECT * FROM PetDisease WHERE HealthId = ?";
        List<Pet_Disease> diseases = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, healthId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int diseaseId = rs.getInt("DiseaseId");
                String name = rs.getString("DiseaseName");
                DiseaseType type = DiseaseType.valueOf(rs.getString("DiseaseType"));

                diseases.add(new Pet_Disease(diseaseId, healthId, new Disease(name, type)));
            }
        }

        return diseases;
    }

    @Override
    public List<HealthRecord> getAll() {
        String sql = "SELECT * FROM HealthRecord";
        List<HealthRecord> healthRecords = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                healthRecords.add(extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all health records", e);
        }
        return healthRecords;
    }

    @Override
    public void delete(Integer id) {
        String deleteHealthRecordSQL = "DELETE FROM HealthRecord WHERE HealthId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(deleteHealthRecordSQL)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

            String deleteAppointmentsSQL = "DELETE FROM HealthRecord_Appointment WHERE HealthId = ?";
            try (PreparedStatement appointmentStmt = connection.prepareStatement(deleteAppointmentsSQL)) {
                appointmentStmt.setInt(1, id);
                appointmentStmt.executeUpdate();
            }

            String deleteDiseasesSQL = "DELETE FROM HealthRecord_Disease WHERE HealthId = ?";
            try (PreparedStatement diseaseStmt = connection.prepareStatement(deleteDiseasesSQL)) {
                diseaseStmt.setInt(1, id);
                diseaseStmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting HealthRecord and associated entries", e);
        }
    }

    @Override
    public void update(Integer id, HealthRecord obj) {
        String updateSQL = "UPDATE HealthRecord SET PetId = ? WHERE HealthId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(updateSQL)) {
            stmt.setInt(1, obj.getPetId());
            stmt.setInt(2, id);
            stmt.executeUpdate();

            updateAppointments(id, obj.getAppointments());

            updateDiseases(id, obj.getPetDiseases());
        } catch (SQLException e) {
            throw new RuntimeException("Error updating HealthRecord", e);
        }
    }

    private void updateAppointments(Integer healthId, List<Appointment> appointments) throws SQLException {
        // Ștergem toate relațiile existente înainte de re-adăugare
        String deleteSQL = "DELETE FROM HealthRecord_Appointment WHERE HealthId = ?";
        try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSQL)) {
            deleteStmt.setInt(1, healthId);
            deleteStmt.executeUpdate();
        }
        for (Appointment appointment : appointments) {
            String insertSQL = "INSERT INTO HealthRecord_Appointment (HealthId, AppointmentId) VALUES (?, ?)";
            try (PreparedStatement insertStmt = connection.prepareStatement(insertSQL)) {
                insertStmt.setInt(1, healthId);
                insertStmt.setInt(2, appointment.getId());
                insertStmt.executeUpdate();
            }
        }
    }

    private void updateDiseases(Integer healthId, List<Pet_Disease> diseases) throws SQLException {
        String deleteSQL = "DELETE FROM HealthRecord_Disease WHERE HealthId = ?";
        try (PreparedStatement deleteStmt = connection.prepareStatement(deleteSQL)) {
            deleteStmt.setInt(1, healthId);
            deleteStmt.executeUpdate();
        }
        for (Pet_Disease disease : diseases) {
            String insertSQL = "INSERT INTO HealthRecord_Disease (HealthId, DiseaseId) VALUES (?, ?)";
            try (PreparedStatement insertStmt = connection.prepareStatement(insertSQL)) {
                insertStmt.setInt(1, healthId);
                insertStmt.setInt(2, disease.getId());
                insertStmt.executeUpdate();
            }
        }
    }

    private HealthRecord extractFromResultSet(ResultSet rs) throws SQLException {
        int healthId = rs.getInt("HealthId");
        int petId = rs.getInt("PetId");
        List<Appointment> appointments = getAppointments(healthId);
        List<Pet_Disease> diseases = getDiseases(healthId);
        return new HealthRecord(healthId, petId, appointments, diseases);
    }

}

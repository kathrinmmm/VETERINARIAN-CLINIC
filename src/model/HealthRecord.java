package model;
import java.util.ArrayList;
import java.util.List;

public class HealthRecord {
    private int petId;
    private List<Appointment> appointments;
    private List<Disease> diseases;
    private List<Vaccine> vaccines;
    private List<Test> tests;

    public HealthRecord(int petId, List<Appointment> appointments, List<Disease> diseases, List<Vaccine> vaccines, List<Test> tests) {
        this.petId = petId;
        this.appointments = appointments;
        this.diseases = diseases;
        this.vaccines = vaccines;
        this.tests = tests;
    }

    public HealthRecord(int petId) {
        this.petId = petId;
        this.appointments = new ArrayList<>();
        this.diseases = new ArrayList<>();
        this.vaccines = new ArrayList<>();
        this.tests = new ArrayList<>();
    }


    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public List<Disease> getDiseases(){ return diseases;}

    public List<Vaccine> getVaccines(){ return vaccines;}
    public List<Test> getTests(){ return tests;}

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }

    public void setVaccines(List<Vaccine> vaccines) {
        this.vaccines = vaccines;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
}

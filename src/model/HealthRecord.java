package model;

import java.util.List;

public class HealthRecord {
    private int petId;
    private List<Appointment> appointments;
    private List<Disease> diseases;

    public HealthRecord(int petId, List<Appointment> appointments) {
        this.petId = petId;
        this.appointments = appointments;
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
}

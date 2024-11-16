package model;

import java.util.List;
import java.util.ArrayList;

public class Veterinarian extends User {
    private String specialization;
    private List<Appointment> appointments;
    private List<String> notifications;

    public Veterinarian(int id, String firstName, String lastName, String email, String username, String password, String specialization) {
        super(id, firstName, lastName, email, username, password, "Veterinarian");
        this.specialization = specialization;
        this.appointments = new ArrayList<>();
        this.notifications = new ArrayList<>();
    }

    public String getName() {
        return getFirstName() + " " + getLastName();
    }
    public boolean isAvailable(String date, String time) {
        for (Appointment appointment : appointments) {
            if (appointment.getDate().equals(date) && appointment.getTime().equals(time)) {
                return false;
            }
        }
        return true;
    }

    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    @Override
    public void displayUserInfo() {
        System.out.println("Veterinarian ID: " + getUser_id() + ", Name: " + getName() + ", Specialization: " + specialization);
    }

    public void addNotification(String message) {
        notifications.add(message);  // Add the notification to the list
    }

    public List<String> getNotifications() {
        return notifications;  // Return the list of notifications
    }
}

package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Veterinarian extends User {
    private String specialization;
    private List<Appointment> appointments;
    private List<LocalDate> availableDates;
    private Map<LocalDate, List<LocalTime>> availableTimes;


    public Veterinarian(int id, String firstName, String lastName, String email, String username, String password, String specialization) {
        super(id, firstName, lastName, email, username, password, "Veterinarian");
        this.specialization = specialization;
        this.appointments = new ArrayList<>();
        this.availableDates = new ArrayList<>();
        this.availableTimes = new HashMap<>();
    }

    @Override
    public int getUser_id() {
        return super.getUser_id();
    }

    public String getName() {
        return getFirstName() + " " + getLastName();
    }

    public void addAvailableDate(LocalDate date) {
        availableDates.add(date);
        availableTimes.put(date, new ArrayList<>());
    }

    public void addAvailableTime(LocalDate date, LocalTime time) {
        if (availableTimes.containsKey(date)) {
            availableTimes.get(date).add(time);
        }
    }

    public void removeAvailableTime(LocalDate date, LocalTime time) {
        if (availableTimes.containsKey(date)) {
            availableTimes.get(date).remove(time);
        }
    }

    public List<LocalDate> getAvailableDates() {
        return availableDates;
    }

    public List<LocalTime> getAvailableTimes(LocalDate date) {
        return availableTimes.getOrDefault(date, new ArrayList<>());
    }

    public String getSpecialization() {
        return specialization;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }

    public void removeAppointment(Appointment appointment) {
        this.appointments.remove(appointment);
    }

    public boolean isAvailable(LocalDate date, LocalTime time) {
        for (Appointment appointment : appointments) {
            if (appointment.getDate().equals(date.toString()) && appointment.getTime().equals(time.toString())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Veterinarian ID: " + getUser_id() + ", Name: " + getName() + ", Specialization: " + specialization;
    }

    @Override
    public void displayUserInfo() {
        System.out.println(this.toString());
    }
}

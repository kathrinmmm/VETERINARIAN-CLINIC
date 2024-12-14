package Model;

import Utils.AppointmentType;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The Appointment class represents an appointment for a pet with a veterinarian.
 * It includes details such as the appointment ID, pet ID, veterinarian ID, date, time,
 * appointment type, and associated tests and vaccines.
 *
 * Implements the HasId interface to ensure that every Appointment has a unique ID.
 *
 * @author Cristiana Bleoca
 * @version 1.0
 * @since 2024-11-24
 */
public class Appointment implements HasId {

    private Integer AppId;
    private int PetId;
    private int VetId;
    private AppointmentType AppointmentType;
    private String date;
    private String time;
    private List<Test> tests;
    private List<Vaccine> vaccines;

    /**
     * Constructs an Appointment with all fields.
     *
     * @param appId the ID of the appointment
     * @param petId the ID of the pet for this appointment
     * @param vetId the ID of the veterinarian for this appointment
     * @param date the date of the appointment
     * @param time the time of the appointment
     * @param appointmentType the type of the appointment (e.g., check-up, emergency)
     * @param tests the list of tests associated with the appointment
     * @param vaccines the list of vaccines associated with the appointment
     */
    public Appointment(int appId, int petId, int vetId, String date, String time, AppointmentType appointmentType, List<Test> tests, List<Vaccine> vaccines) {
        this.AppId = appId;
        this.PetId = petId;
        this.VetId = vetId;
        this.date = date;
        this.time = time;
        this.AppointmentType = appointmentType;
        this.tests = tests == null ? new ArrayList<>() : tests;
        this.vaccines = vaccines == null ? new ArrayList<>() : vaccines;
    }

    /**
     * Constructs an Appointment without specifying an appointment ID.
     *
     * @param petId the ID of the pet for this appointment
     * @param vetId the ID of the veterinarian for this appointment
     * @param date the date of the appointment
     * @param time the time of the appointment
     * @param appointmentType the type of the appointment (e.g., check-up, emergency)
     * @param tests the list of tests associated with the appointment
     * @param vaccines the list of vaccines associated with the appointment
     */
    public Appointment(int petId, int vetId, String date, String time, AppointmentType appointmentType, List<Test> tests, List<Vaccine> vaccines) {
        this.PetId = petId;
        this.VetId = vetId;
        this.date = date;
        this.time = time;
        this.AppointmentType = appointmentType;
        this.tests = tests == null ? new ArrayList<>() : tests;
        this.vaccines = vaccines == null ? new ArrayList<>() : vaccines;
    }

    public Appointment(int appId, int petID, int vetID, String appointmentType, LocalDate date, LocalTime time, String tests, String vaccines) {

    }

    // Getters and Setters

    /**
     * Retrieves the list of tests associated with this appointment.
     *
     * @return a list of tests
     */
    public String getTests() {
        return tests.toString();
    }

    /**
     * Retrieves the list of vaccines associated with this appointment.
     *
     * @return a list of vaccines
     */
    public String getVaccines() {
        return vaccines.toString();
    }

    /**
     * Sets the list of tests associated with this appointment.
     *
     * @param tests the list of tests to set
     */
    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    /**
     * Sets the list of vaccines associated with this appointment.
     *
     * @param vaccines the list of vaccines to set
     */
    public void setVaccines(List<Vaccine> vaccines) {
        this.vaccines = vaccines;
    }

    /**
     * Retrieves the appointment ID.
     *
     * @return the appointment ID
     */
    public Integer getAppId() {
        return AppId;
    }

    /**
     * Sets the appointment ID.
     *
     * @param appId the appointment ID to set
     */
    public void setAppId(Integer appId) {
        this.AppId = appId;
    }

    /**
     * Retrieves the pet ID for this appointment.
     *
     * @return the pet ID
     */
    public int getPetId() {
        return PetId;
    }

    /**
     * Retrieves the veterinarian ID for this appointment.
     *
     * @return the veterinarian ID
     */
    public int getVetId() {
        return VetId;
    }

    /**
     * Retrieves the appointment type for this appointment.
     *
     * @return the appointment type
     */
    public String getAppointmentType() {
        return AppointmentType;
    }

    /**
     * Retrieves the date of the appointment.
     *
     * @return the date of the appointment
     */
    public String getDate() {
        return date;
    }

    /**
     * Retrieves the time of the appointment.
     *
     * @return the time of the appointment
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the time of the appointment.
     *
     * @param time the time of the appointment
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Sets the pet ID for this appointment.
     *
     * @param petId the pet ID to set
     */
    public void setPetId(int petId) {
        this.PetId = petId;
    }

    /**
     * Sets the veterinarian ID for this appointment.
     *
     * @param vetId the veterinarian ID to set
     */
    public void setVetId(int vetId) {
        this.VetId = vetId;
    }

    /**
     * Sets the appointment type for this appointment.
     *
     * @param appointmentType the appointment type to set
     */
    public void setAppointmentType(AppointmentType appointmentType) {
        AppointmentType = appointmentType;
    }

    /**
     * Sets the date of the appointment.
     *
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    public List<Test> getTests(String tests) {
        return new ArrayList<>(); // Example return value
    }

    /**
     * Retrieves the unique identifier for this appointment, implementing the HasId interface.
     *
     * @return the appointment ID
     */
    @Override
    public Integer getId() {
        return AppId;
    }

    /**
     * Provides a string representation of the Appointment object.
     *
     * @return a string representation of the appointment
     */
    @Override
    public String toString() {
        return "{Appointment Id: " + AppId +
                ", PetId: " + PetId +
                ", VetId: " + VetId +
                ", AppointmentType: " + AppointmentType +
                ", Date: '" + date + '\'' +
                ", Time: '" + time + '\'' +
                ", \nTests: " + tests +
                ", \nVaccines: " + vaccines + '}';
    }
}

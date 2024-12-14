package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code HealthRecord} class represents the health record of a pet. It contains
 * details such as the pet's appointments and diseases. Each health record is associated
 * with a unique ID and a specific pet ID.
 * <p>
 * This class provides methods for managing appointments and adding diseases to a pet's health record.
 * </p>
 *
 * @see Appointment
 * @see Pet_Disease
 * @see Disease
 */
public class HealthRecord implements HasId {

    private Integer HealthId;
    private int petId;
    private List<Appointment> appointments;
    private List<Pet_Disease> petDiseases;
    private String Appointment;

    /**
     * Constructs a HealthRecord object with a unique ID and a pet ID.
     *
     * @param HealthId the unique health record ID
     * @param petId the ID of the pet associated with this health record
     */
    public HealthRecord(int HealthId, int petId) {
        this.HealthId = HealthId;
        this.petId = petId;
        this.appointments = new ArrayList<>();
        this.petDiseases = new ArrayList<>();
    }

    /**
     * Constructs a HealthRecord object without specifying the health record ID.
     *
     * @param petId the ID of the pet associated with this health record
     */
    public HealthRecord(int petId) {
        this.petId = petId;
        this.appointments = new ArrayList<>();
        this.petDiseases = new ArrayList<>();
    }

    public HealthRecord(int healthId, int petId, String appointments, String petDiseases) {
        this.HealthId = healthId;
    }

    /**
     * Sets the health record ID.
     *
     * @param healthId the health record ID to set
     */
    public void setHealthId(int healthId) {
        this.HealthId = healthId;
    }

    /**
     * Retrieves the pet ID associated with this health record.
     *
     * @return the pet ID
     */
    public int getPetId() {
        return petId;
    }

    /**
     * Sets the pet ID associated with this health record.
     *
     * @param petId the pet ID to set
     */
    public void setPetId(int petId) {
        this.petId = petId;
    }

    /**
     * Retrieves the list of appointments associated with this health record.
     *
     * @return the list of appointments
     */
    public String getAppointments() {
        return appointments.toString();
    }

    /**
     * Retrieves the list of diseases associated with this pet's health record.
     *
     * @return the list of diseases
     */
    public String getPetDiseases() {
        return petDiseases.toString();
    }

    /**
     * Adds a disease to the pet's health record.
     *
     * @param petId the ID of the pet
     * @param disease the disease to add
     */
    public void addDisease(Integer petId, Disease disease) {
        int newId = petDiseases.size() + 1;
        Pet_Disease petDisease = new Pet_Disease(newId, petId, disease);
        petDiseases.add(petDisease);
    }

    /**
     * Adds an appointment to the pet's health record.
     *
     * @param appointment the appointment to add
     */
    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }

    /**
     * Retrieves the unique ID of this health record.
     *
     * @return the health record ID
     */
    @Override
    public Integer getId() {
        return HealthId;
    }

    /**
     * Provides a string representation of this health record.
     *
     * @return a string representing the health record, including appointments and diseases
     */
    @Override
    public String toString() {
        return "HealthRecord Id: " + HealthId +
                ", PetId: " + petId +
                ",\nAppointments: " + appointments +
                ",\nDiseases: " + petDiseases;
    }

    public String getAppoitment() {
        return Appointment;
    }
}

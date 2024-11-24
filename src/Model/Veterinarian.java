package Model;

import Utils.Specialization;
import java.util.ArrayList;

/**
 * The {@code Veterinarian} class represents a veterinarian, inheriting from the {@code User} class.
 * It contains details specific to veterinarians, including their specialization.
 */
public class Veterinarian extends Model.User {

    /**
     * The specialization of the veterinarian (e.g., surgery, dermatology).
     */
    private Specialization specialization;

    /**
     * Constructs a new {@code Veterinarian} with the specified ID, name, username, password, and specialization.
     *
     * @param ID the unique identifier for the veterinarian
     * @param name the name of the veterinarian
     * @param username the username of the veterinarian
     * @param password the password for the veterinarian's account
     * @param specialization the specialization of the veterinarian
     */
    public Veterinarian(Integer ID, String name, String username, String password, Specialization specialization) {
        super(ID, name, username, password);
        this.specialization = specialization;
    }

    /**
     * Constructs a new {@code Veterinarian} with the specified name, username, password, and specialization.
     * The ID is generated later.
     *
     * @param name the name of the veterinarian
     * @param username the username of the veterinarian
     * @param password the password for the veterinarian's account
     * @param specialization the specialization of the veterinarian
     */
    public Veterinarian(String name, String username, String password, Specialization specialization) {
        super(null, name, username, password);
        this.specialization = specialization;
    }

    /**
     * Retrieves the specialization of the veterinarian.
     *
     * @return the specialization of the veterinarian
     */
    public Specialization getSpecialization() {
        return specialization;
    }

    /**
     * Sets a new specialization for the veterinarian.
     *
     * @param specialization the new specialization
     */
    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    /**
     * Returns a string representation of the veterinarian, including the name, username, and specialization.
     *
     * @return a string representation of the veterinarian
     */
    @Override
    public String toString() {
        return super.toString() + ", Specialization: " + specialization;
    }
}

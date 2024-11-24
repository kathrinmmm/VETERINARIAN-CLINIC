package Model;

import Utils.VaccineType;

/**
 * The {@code Vaccine} class represents a vaccine in the system, with a unique ID, name, and vaccine type.
 * It implements the {@code HasId} interface to ensure that every vaccine has a unique identifier.
 */
public class Vaccine implements HasId {

    /**
     * The type of vaccine (e.g., prevention or treatment).
     */
    private VaccineType vaccineType;

    /**
     * The name of the vaccine.
     */
    private String name;

    /**
     * The unique identifier for the vaccine.
     */
    private Integer id;

    /**
     * Constructs a new {@code Vaccine} with the specified ID, name, and vaccine type.
     *
     * @param id the unique identifier for the vaccine
     * @param name the name of the vaccine
     * @param vaccineType the type of the vaccine (e.g., prevention or treatment)
     */
    public Vaccine(int id, String name, VaccineType vaccineType) {
        this.vaccineType = vaccineType;
        this.name = name;
        this.id = id;
    }

    /**
     * Constructs a new {@code Vaccine} with the specified name and vaccine type.
     * The ID is generated later.
     *
     * @param name the name of the vaccine
     * @param vaccineType the type of the vaccine (e.g., prevention or treatment)
     */
    public Vaccine(String name, VaccineType vaccineType) {
        this.vaccineType = vaccineType;
        this.name = name;
    }

    /**
     * Retrieves the type of the vaccine.
     *
     * @return the vaccine type
     */
    public VaccineType getVaccineType() {
        return vaccineType;
    }

    /**
     * Sets the type of the vaccine.
     *
     * @param vaccineType the new vaccine type
     */
    public void setVaccineType(VaccineType vaccineType) {
        this.vaccineType = vaccineType;
    }

    /**
     * Sets the unique ID for the vaccine.
     *
     * @param id the unique identifier for the vaccine
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the vaccine.
     *
     * @return the vaccine's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the vaccine.
     *
     * @param name the new name for the vaccine
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the unique identifier of the vaccine.
     *
     * @return the unique ID of the vaccine
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Returns a string representation of this {@code Vaccine} object, including its name and vaccine type.
     *
     * @return a string representation of the vaccine
     */
    @Override
    public String toString() {
        return "Name: '" + name + '\'' + ", Vaccine Type: " + vaccineType;
    }
}

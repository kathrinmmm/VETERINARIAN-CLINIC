package Model;

import Utils.DiseaseType;

/**
 * The Disease class represents a disease that can affect pets.
 * It includes details such as the disease ID, name, and type.
 *
 * Implements the HasId interface to ensure that every Disease has a unique ID.
 *
 * @author Cristiana Bleoca
 * @version 1.0
 * @since 2024-11-24
 */
public class Disease implements HasId {

    private Integer id;
    private String name;
    private DiseaseType diseaseType;

    /**
     * Constructs a Disease object with all fields.
     *
     * @param id the ID of the disease
     * @param name the name of the disease
     * @param diseaseType the type of the disease (e.g., bacterial, viral)
     */
    public Disease(Integer id, String name, DiseaseType diseaseType) {
        this.id = id;
        this.name = name;
        this.diseaseType = diseaseType;
    }

    /**
     * Constructs a Disease object without specifying the ID.
     *
     * @param name the name of the disease
     * @param diseaseType the type of the disease (e.g., bacterial, viral)
     */
    public Disease(String name, DiseaseType diseaseType) {
        this.name = name;
        this.diseaseType = diseaseType;
    }

    /**
     * Retrieves the unique ID of this disease.
     *
     * @return the disease ID
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Sets the ID of this disease.
     *
     * @param id the ID to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retrieves the name of this disease.
     *
     * @return the disease name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this disease.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the type of this disease.
     *
     * @param diseaseType the disease type to set
     */
    public void setDiseaseType(DiseaseType diseaseType) {
        this.diseaseType = diseaseType;
    }

    /**
     * Retrieves the type of this disease.
     *
     * @return the disease type
     */
    public DiseaseType getDiseaseType() {
        return diseaseType;
    }

    /**
     * Provides a string representation of the Disease object.
     *
     * @return a string representing the disease
     */
    @Override
    public String toString() {
        return "Name: " + name;
    }
}

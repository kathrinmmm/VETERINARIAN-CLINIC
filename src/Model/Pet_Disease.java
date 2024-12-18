package Model;

/**
 * The {@code Pet_Disease} class represents the association between a pet and a disease.
 * This class links a specific pet to a disease, including a unique identifier for this association.
 * It is used to track the diseases a pet may have and store related data.
 */
public class Pet_Disease implements HasId {

    /**
     * The unique identifier for this {@code Pet_Disease} relationship.
     */
    private Integer Id;

    /**
     * The ID of the pet associated with this disease.
     */
    private Integer petid;

    /**
     * The disease that is associated with the pet.
     */
    private Model.Disease disease;

    /**
     * Constructs a new {@code Pet_Disease} with the specified ID, pet ID, and associated disease.
     *
     * @param Id the unique identifier for this {@code Pet_Disease} relationship
     * @param petid the ID of the pet
     * @param disease the disease associated with the pet
     */
    public Pet_Disease(Integer Id, Integer petid, Model.Disease disease) {
        this.Id = Id;
        this.petid = petid;
        this.disease = disease;
    }
    public Pet_Disease() {
    }

    /**
     * Retrieves the ID of the pet associated with this disease.
     *
     * @return the pet ID
     */
    public Integer petid() {
        return petid;
    }

    /**
     * Retrieves the disease associated with the pet.
     *
     * @return the disease
     */
    public Model.Disease getDisease() {
        return disease;
    }

    /**
     * Sets the ID of the pet associated with this disease.
     *
     * @param petid the new pet ID
     */
    public void setPetid(Integer petid) {
        this.petid = petid;
    }

    /**
     * Sets the disease associated with the pet.
     *
     * @param disease the new disease
     */
    public void setDisease(Model.Disease disease) {
        this.disease = disease;
    }

    /**
     * Sets the unique identifier for this {@code Pet_Disease} relationship.
     *
     * @param id the new ID
     */
    public void setId(Integer id) {
        Id = id;
    }

    /**
     * Retrieves the unique identifier for this {@code Pet_Disease} relationship.
     *
     * @return the ID
     */
    @Override
    public Integer getId() {
        return Id;
    }

    /**
     * Returns a string representation of the {@code Pet_Disease} object, which includes the disease name.
     *
     * @return a string representation of the disease associated with the pet
     */
    @Override
    public String toString() {
        return " " + disease;
    }
}

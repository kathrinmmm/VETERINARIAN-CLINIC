package Model;

import Utils.AnimalType;

/**
 * The {@code Pet} class represents a pet in the system, inheriting from the {@code User} class.
 * This class adds additional properties specific to pets, such as their animal type.
 * A pet is associated with a unique identifier, a username, a password, and an animal type.
 * <p>
 * The class extends the {@code User} class and utilizes polymorphism to inherit common
 * properties and methods, such as ID, username, and password, while adding specifics
 * like the type of animal (e.g., dog, cat).
 * </p>
 */
public class Pet extends User {

    /**
     * The type of animal for this pet (e.g., dog, cat, etc.).
     */
    private AnimalType animalType;

    /**
     * Constructs a new {@code Pet} with the specified details, including a unique ID,
     * name, username, password, and the type of animal.
     *
     * @param Id the unique identifier for the pet
     * @param name the name of the pet
     * @param username the username of the pet
     * @param password the password of the pet (could be an owner password or similar)
     * @param animalType the type of animal (e.g., dog, cat, etc.)
     */
    public Pet(Integer Id, String name, String username, String password, AnimalType animalType) {
        super(Id, name, username, password);
        this.animalType = animalType;
    }

    /**
     * Constructs a new {@code Pet} with the specified name, username, password, and animal type,
     * but without an ID (the ID will be generated or assigned later).
     *
     * @param name the name of the pet
     * @param username the username of the pet
     * @param password the password of the pet
     * @param animalType the type of animal (e.g., dog, cat, etc.)
     */
    public Pet(String name, String username, String password, AnimalType animalType) {
        super(null, name, username, password); // ID is assigned later
        this.animalType = animalType;
    }

    /**
     * Sets the type of animal for this pet.
     *
     * @param animalType the new type of animal (e.g., dog, cat, etc.)
     */
    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    /**
     * Retrieves the type of animal for this pet.
     *
     * @return the type of animal (e.g., dog, cat, etc.)
     */
    public AnimalType getAnimalType() {
        return animalType;
    }

    /**
     * Returns a string representation of the {@code Pet} object, including its ID, name,
     * username, and type of animal.
     *
     * @return a string representation of the pet
     */
    @Override
    public String toString() {
        return super.toString() + ", Type of Animal: " + animalType;
    }
}

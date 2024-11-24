package Model;

import Utils.AnimalType;

import java.util.ArrayList;


/**
 * The {@code Pet} class represents a Pet, inheriting from the {@code User} class.
 * It contains additional details specific to pets, such as their assigned {@code animalType}.
 */
public class Pet extends User {
    /**
     * The type Of Animal
     */
    private AnimalType animalType;



    /**
     * Constructs a new {@code Admin} with the specified ID, age, name, and role.
     *
     * @param Id   the unique identifier for the pet
     * @param username the username of the pet
     * @param name the name of the pet
     * @param animalType the type of pet
     */
    public Pet(Integer Id, String name, String username,String password, AnimalType animalType) {
        super(Id, name,username, password);
        this.animalType = animalType;

    }

    public Pet(String name, String username, String password, AnimalType animalType) {
        super(null, name, username, password);
        this.animalType = animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }


    @Override
    public String toString() {
        return super.toString() + ", Type of Animal: " + animalType;
    }
}
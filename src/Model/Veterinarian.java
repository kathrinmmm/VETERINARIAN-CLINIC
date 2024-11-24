package Model;
import Utils.Specialization;

import java.util.ArrayList;

public class Veterinarian extends Model.User {
    /**
     * The specialization of the Veterinarian.
     */
    private Specialization specialization;



    /**
     * Constructs a new {@code Admin} with the specified ID, age, name, and role.
     *
     * @param ID   the unique identifier for the administrator
     * @param username  the username of the vet
     * @param password the password of the vet
     * @param specialization the specialization of the vet
     */
    public Veterinarian(Integer ID, String name,String username, String password, Specialization specialization) {
        super(ID,name, username,password);
        this.specialization = specialization;

    }
    public Veterinarian( String name,String username, String password, Specialization specialization) {
        super(null,name, username,password);
        this.specialization = specialization;

    }


    /**
     * Returns the role assigned to this administrator.
     *
     * @return the role of the administrator
     */
    public Specialization getSpecialization() {
        return specialization;
    }

    /**
     * Sets a new role for this administrator.
     *
     * @param specialization the new role
     */
    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    /**
     * Returns a string representation of the administrator, including the role information.
     *
     * @return a string representation of the administrator
     */
    @Override
    public String toString() {
        return super.toString() + ", Specialization: " + specialization;
    }
}
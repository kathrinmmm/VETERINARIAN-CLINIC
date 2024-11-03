package model;

public class Veterinarian extends User{ ;
    private String specialization;

    public Veterinarian(int id, String firstName,String lastName, String email, String password, String username,String specialization) {
        super(id,firstName,lastName, email, password, username);
        this.specialization = specialization;
    }
    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public void displayUserInfo() {
        System.out.println("Name: " + getFirstName() + " " + getLastName());
        System.out.println("Specialization: " + getSpecialization());
        System.out.println("Email: " + getEmail());
        System.out.println("username: " + getUsername());

    }
}

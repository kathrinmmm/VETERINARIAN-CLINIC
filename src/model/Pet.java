package model;

public class Pet extends User{
    private String name;
    private String birthDate;
    private String gender;
    private String breed;
    private String species;

    public Pet(int id, String firstName,String lastName, String email, String password, String username,String name, String birthDate, String gender, String breed, String species) {
        super( id, firstName,lastName, email, password, username);
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.breed = breed;
        this.species = species;
    }

    @Override
    public void displayUserInfo() {
        System.out.println("Owner Name: " + getFirstName() + " " + getLastName());
        System.out.println("Pet Name:" + getName());
        System.out.println("Gender: " + getGender());
        System.out.println("Species: " + getSpecies());
        System.out.println("Breed: " + getBreed());
        System.out.println("Birth Date: " + getBirthDate());
        System.out.println("username: " + getUsername());

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getBreed() {
        return breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }
    public String getSpecies() {
        return species;
    }
    public void setSpecies(String species) {
        this.species = species;
    }

}


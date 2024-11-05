package model;

public class Pet extends User{
    private String petName;
    private String birthDate;
    private String gender;
    private String breed;
    private String species;

    public Pet(int id, String firstName,String lastName, String email,String username, String password,String petName, String birthDate, String gender,String species, String breed) {
        super( id, firstName,lastName, email, password, username);
        this.petName = petName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.breed = breed;
        this.species = species;
    }

    @Override
    public void displayUserInfo() {
        System.out.println("Owner Name: " + getFirstName() + " " + getLastName());
        System.out.println("Pet Name:" + getPetName());
        System.out.println("Gender: " + getGender());
        System.out.println("Species: " + getSpecies());
        System.out.println("Breed: " + getBreed());
        System.out.println("Birth Date: " + getBirthDate());
        System.out.println("username: " + getUsername());

    }

    public String getPetName() {
        return petName;
    }
    public void setPetName(String petName) {
        this.petName = petName;
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


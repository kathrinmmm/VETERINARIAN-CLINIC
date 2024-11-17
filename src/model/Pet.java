package model;

import java.util.ArrayList;
import java.util.List;

public class Pet extends User {
    private String petName;
    private String birthDate;
    private String gender;
    private String breed;
    private String species;
    private List<String> notifications;
    private List<Appointment> appointments;

    public Pet(int id, String petName, String username, String password) {
        super(id, "", "", "", username, password, "Pet");
        this.petName = petName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.breed = breed;
        this.species = species;
        this.notifications = new ArrayList<String>();
        this.appointments = new ArrayList<>();
    }


    @Override
    public void displayUserInfo() {
        System.out.println("Owner Name: " + getFirstName() + " " + getLastName());
        System.out.println("Pet Name: " + getPetName());
        System.out.println("Gender: " + getGender());
        System.out.println("Species: " + getSpecies());
        System.out.println("Breed: " + getBreed());
        System.out.println("Birth Date: " + getBirthDate());
        System.out.println("Username: " + getUsername());
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
    public void addNotification(String message) {
        notifications.add(message);
    }

    public List<String> getNotifications() {
        return notifications;
    }
    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }
    public List<Appointment> getAppointments() {
        return appointments;
    }
}
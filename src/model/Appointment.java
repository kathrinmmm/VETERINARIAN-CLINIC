package model;
import java.util.List;
public class Appointment {
    private int id;
    private int pet_id;
    private int vet_id;
    private String date;
    private String time;
    private int typeOfAppointment;
    private List<Vaccine> vaccines;
    private List<Test> tests;

    public Appointment(int id, int petId, String date) {
        this.id = id;
        this.pet_id = petId;
        this.vet_id = vet_id;
        this.date = date;
        this.time = time;
        this.typeOfAppointment = typeOfAppointment;
        this.vaccines = vaccines;
        this.tests = tests;
    }
    public List<Vaccine> getVaccines() {
        return vaccines;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setVaccines(List<Vaccine> vaccines) {
        this.vaccines = vaccines;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public int getId() {
        return id;
    }

    public int getPet_id() {
        return pet_id;
    }

    public int getVet_id() {
        return vet_id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getTypeOfAppointment() {
        return typeOfAppointment;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setPet_id(int pet_id) {
        this.pet_id = pet_id;
    }

    public void setVet_id(int vet_id) {
        this.vet_id = vet_id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTypeOfAppointment(int typeOfAppointment) {
        this.typeOfAppointment = typeOfAppointment;
    }

}

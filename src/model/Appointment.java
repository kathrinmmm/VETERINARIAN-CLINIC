package model;
import java.util.List;
public class Appointment {
    private int id;
    private int pet_id;
    private int vet_id;
    private String date;
    private String time;
    private String typeOfAppointment;
    private String results;
    private List<Vaccine> vaccines;
    private List<Test> tests;

    public Appointment(int id, int petId, int vetId, String date, String time, String results,
                       String typeOfAppointment, List<Vaccine> vaccines, List<Test> tests) {
        this.id = id;
        this.pet_id = petId;
        this.vet_id = vetId;
        this.date = date;
        this.time = time;
        this.results = results;
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

    public String getTypeOfAppointment() {
        return typeOfAppointment;
    }

    public String getResults() {
        return results;
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

    public void setTypeOfAppointment(String typeOfAppointment) {
        this.typeOfAppointment = typeOfAppointment;
    }

    public void setResults(String results) {
        this.results = results;
    }
}

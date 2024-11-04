package model;

public class Appointment {
    private int id;
    private int pet_id;
    private int vet_id;
    private String date;
    private String time;
    private String typeOfAppointment;
    private String results;

    public Appointment(int id, int pet_id, int vet_id, String date, String time, String results, String typeOfAppointment) {
        this.id = id;
        this.pet_id = pet_id;
        this.vet_id = vet_id;
        this.date = date;
        this.time = time;
        this.results = results;
        this.typeOfAppointment = typeOfAppointment;
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

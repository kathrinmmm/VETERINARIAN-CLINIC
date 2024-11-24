package Model;
import java.util.ArrayList;
import java.util.List;

public class HealthRecord implements HasId{
    private Integer HealthId;
    private int petId;
    private List<Model.Appointment> appointments;
    private List<Pet_Disease> petDiseases;
    public HealthRecord(int HealthId, int petId) {
        this.HealthId = HealthId;
        this.petId = petId;
        this.appointments = new ArrayList<>();
        this.petDiseases = new ArrayList<>();

    }
    public HealthRecord( int petId) {
        this.petId = petId;
        this.appointments = new ArrayList<>();
        this.petDiseases = new ArrayList<>();

    }
    public void setHealthId(int healthId) {
        this.HealthId = healthId;
    }
    public int getPetId() {
        return petId;
    }
    public void setPetId(int petId) {
        this.petId = petId;
    }
    public List<Model.Appointment> getAppointments() {
        return appointments;
    }
    public List<Pet_Disease> getPetDiseases() {
        return petDiseases;
    }

    public void addDisease(Integer petId, Model.Disease disease) {
        int newId = petDiseases.size() + 1;
        Pet_Disease petDisease = new Pet_Disease(newId, petId, disease);
        petDiseases.add(petDisease);
    }

    public void addAppointment(Model.Appointment appointment) {
        this.appointments.add(appointment);
    }

    @Override
    public Integer getId() {
        return HealthId;
    }

    @Override
    public String toString() {
        return
                "HealthRecord Id: " + HealthId +
                        ", PetId: " + petId +
                        ",\n Appointments: " + appointments +
                        ",\n Diseases:  "+ petDiseases;
    }
}

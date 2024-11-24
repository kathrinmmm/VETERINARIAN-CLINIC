package Model;
import Utils.AppointmentType;
import java.util.ArrayList;
import java.util.List;
public class Appointment implements HasId {
    private Integer AppId;
    private int PetId;
    private int VetId;
    private AppointmentType AppointmentType;
    private String date;
    private String time;
    private List<Test> tests;
    private List<Vaccine> vaccines;

    // Constructor complet
    public Appointment(int appId, int petId, int vetId, String date, String time, AppointmentType AppointmentType, List<Test> tests, List<Vaccine> vaccines) {
        this.AppId = appId;
        this.PetId = petId;
        this.VetId = vetId;
        this.date = date;
        this.time = time;
        this.AppointmentType = AppointmentType;
        this.tests = tests == null ? new ArrayList<>() : tests;
        this.vaccines = vaccines == null ? new ArrayList<>() : vaccines;
    }

    // Constructor fără AppId
    public Appointment(int petId, int vetId, String date, String time, AppointmentType AppointmentType, List<Test> tests, List<Vaccine> vaccines) {
        this.PetId = petId;
        this.VetId = vetId;
        this.date = date;
        this.time = time;
        this.AppointmentType = AppointmentType;
        this.tests = tests == null ? new ArrayList<>() : tests;
        this.vaccines = vaccines == null ? new ArrayList<>() : vaccines;
    }

    public List<Test> getTests() {
        return tests;
    }
    public List<Vaccine> getVaccines() {
        return vaccines;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
    public void setVaccines(List<Vaccine> vaccines) {
        this.vaccines = vaccines;
    }

    public Integer getAppId() {
        return AppId;
    }
    public void setAppId(Integer appId) {
        this.AppId = appId;
    }

    public int getPetId() {
        return PetId;
    }

    public int getVetId() {
        return VetId;
    }

    public Utils.AppointmentType getAppointmentType() {
        return AppointmentType;
    }

    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setPetId(int petId) {
        this.PetId = petId;
    }

    public void setVetId(int vetId) {
        this.VetId = vetId;
    }

    public void setAppointmentType(Utils.AppointmentType appointmentType) {
        AppointmentType = appointmentType;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public Integer getId() {
        return AppId;
    }

    @Override
    public String toString() {
        return
                "{Appointment Id: " + AppId +
                        ", PetId: " + PetId +
                        ", VetId: " + VetId +
                        ", AppointmentType: " + AppointmentType +
                        ", Date: '" + date + '\'' +
                        ", Time: '" + time + '\'' +
                        ", \nTests: " + tests +
                        ", \nVaccines: " + vaccines+'}';
    }
}

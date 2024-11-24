package Model;
import Utils.VaccineType;
public class Vaccine implements HasId{
    private VaccineType vaccineType;
    private String name;
    private Integer id;

    public Vaccine(int id,String name,VaccineType vaccineType) {
        this.vaccineType = vaccineType;
        this.name = name;
        this.id = id;
    }

    public Vaccine(String name,VaccineType vaccineType) {
        this.vaccineType = vaccineType;
        this.name = name;
    }
    public VaccineType getVaccineType() {
        return vaccineType;
    }
    public void setVaccineType(VaccineType vaccineType) {
        this.vaccineType = vaccineType;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return
                "Name: '" + name + '\'' +
                        ", Vaccine Type: " + vaccineType;
    }
}

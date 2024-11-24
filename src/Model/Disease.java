package Model;
import Utils.DiseaseType;
public class Disease implements HasId{
    private Integer id;
    private String name;
    private DiseaseType diseaseType;

    public Disease(Integer id,String name,DiseaseType diseaseType) {
        this.id = id;
        this.name = name;
        this.diseaseType=diseaseType;
    }
    public Disease(String name,DiseaseType diseaseType) {
        this.name = name;
        this.diseaseType=diseaseType;
    }
    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setDiseaseType(DiseaseType diseaseType) {
        this.diseaseType = diseaseType;
    }

    public DiseaseType getDiseaseType() {
        return diseaseType;
    }

    @Override
    public String toString() {
        return "Name: "+name;
    }
}

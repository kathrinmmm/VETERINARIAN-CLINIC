package Model;

public class Pet_Disease implements HasId{
    private Integer petid;
    private Model.Disease disease;
    private Integer Id;
    public Pet_Disease(Integer Id, Integer petid, Model.Disease disease) {
        this.Id = Id;
        this.petid = petid;
        this.disease=disease;
    }

    public Integer petid() {
        return petid;
    }

    public Model.Disease getDisease() {
        return disease;
    }

    public void setPetid(Integer petid) {
        this.petid = petid;
    }

    public void setDisease(Model.Disease disease) {
        this.disease = disease;
    }

    public void setId(Integer id) {
        Id = id;
    }

    @Override
    public Integer getId() {
        return Id;
    }

    @Override
    public String toString() {
        return " "+disease;
    }
}

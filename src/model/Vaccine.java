package model;

public class Vaccine {
    private int id;
    private String name;
    private String type;
    private int avilability;
    private int disponibility;

    public Vaccine(int id, String name, String type, int avilability, int disponibility) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.avilability = avilability;
        this.disponibility = disponibility;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getAvilability() {
        return avilability;
    }

    public int getDisponibility() {
        return disponibility;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAvilability(int avilability) {
        this.avilability = avilability;
    }
    public void setDisponibility(int disponibility) {
        this.disponibility = disponibility;
    }
}

package model;

public class Test {
    private int id;
    private String type;
    private int frequency_recomandation;

    public Test(int id, String type, int frequency_recomandation) {
        this.id = id;
        this.type = type;
        this.frequency_recomandation = frequency_recomandation;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getFrequency_recomandaion() {
        return frequency_recomandation;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFrequency_recomandaion(int frequency_recomandaion) {
        this.frequency_recomandation = frequency_recomandaion;
    }
}

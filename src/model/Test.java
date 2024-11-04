package model;

public class Test {
    private int id;
    private String type;
    private int frequency_recomandaion;

    public Test(int id, String type, int frequency_recomandaion) {
        this.id = id;
        this.type = type;
        this.frequency_recomandaion = frequency_recomandaion;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getFrequency_recomandaion() {
        return frequency_recomandaion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFrequency_recomandaion(int frequency_recomandaion) {
        this.frequency_recomandaion = frequency_recomandaion;
    }
}

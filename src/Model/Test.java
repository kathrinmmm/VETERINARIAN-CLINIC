package Model;
import Utils.TestType;
public class Test implements HasId{
    private Integer id;
    private String name;
    private TestType testType;
    public Test(int id, String name,TestType testType) {
        this.id = id;
        this.name = name;
        this.testType = testType;
    }
    public Test( String name,TestType testType) {
        this.testType = testType;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTestType(TestType testType) {
        this.testType = testType;
    }

    public TestType getTestType() {
        return testType;
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
                        ", Test Type: " + testType;
    }
}

package Model;

import Utils.TestType;

/**
 * The {@code Test} class represents a medical test associated with a pet's health check-up.
 * It includes information about the test's name and type. The class also implements the {@code HasId} interface,
 * ensuring each test has a unique identifier.
 */
public class Test implements HasId {

    /**
     * The unique identifier for this {@code Test}.
     */
    private Integer id;

    /**
     * The name of the test.
     */
    private String name;

    /**
     * The type of the test (e.g., diagnostic, routine, etc.).
     */
    private TestType testType;

    /**
     * Constructs a new {@code Test} with the specified ID, name, and test type.
     *
     * @param id the unique identifier for the test
     * @param name the name of the test
     * @param testType the type of the test (e.g., diagnostic, routine, etc.)
     */
    public Test(int id, String name, TestType testType) {
        this.id = id;
        this.name = name;
        this.testType = testType;
    }

    /**
     * Constructs a new {@code Test} with the specified name and test type. The ID will be set later.
     *
     * @param name the name of the test
     * @param testType the type of the test
     */
    public Test(String name, TestType testType) {
        this.name = name;
        this.testType = testType;
    }

    /**
     * Sets the unique identifier for this {@code Test}.
     *
     * @param id the new ID for the test
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the type of the test.
     *
     * @param testType the new test type
     */
    public void setTestType(TestType testType) {
        this.testType = testType;
    }

    /**
     * Retrieves the type of the test.
     *
     * @return the test type
     */
    public TestType getTestType() {
        return testType;
    }

    /**
     * Retrieves the name of the test.
     *
     * @return the test name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the test.
     *
     * @param name the new name for the test
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the unique identifier for this {@code Test}.
     *
     * @return the ID of the test
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Returns a string representation of the {@code Test} object, including the test's name and type.
     *
     * @return a string representation of the test
     */
    @Override
    public String toString() {
        return "Name: '" + name + '\'' +
                ", Test Type: " + testType;
    }
}

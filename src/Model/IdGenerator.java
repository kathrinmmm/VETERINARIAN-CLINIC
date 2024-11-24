package Model;

/**
 * The {@code IdGenerator} class is responsible for generating unique IDs for various
 * entities in the system such as pets, veterinarians, diseases, vaccines, tests, appointments,
 * health records, and notifications. The class ensures that each entity gets a unique and
 * incrementing ID whenever one is needed.
 * <p>
 * This class uses static variables to track the next available ID for each entity type.
 * The IDs are generated sequentially and can be retrieved using the respective methods.
 * </p>
 *
 * @see Pet
 * @see Veterinarian
 * @see Disease
 * @see Vaccine
 * @see Test
 * @see Appointment
 * @see HealthRecord
 * @see Notification
 */
public class IdGenerator {

    // Static variables to keep track of the next available ID for each entity type
    private static int nextPetId = 5;
    private static int nextVetId = 5;
    private static int nextDiseaseId = 20;
    private static int nextVaccineId = 20;
    private static int nextTestId = 20;
    private static int nextAppId = 50;
    private static int nextHRId = 20;
    private static int nextNotifId = 20;

    /**
     * Generates a unique ID for a pet. The ID is incremented each time this method is called.
     *
     * @return the next available pet ID
     */
    public static int generatePetId() {
        return nextPetId++;
    }

    /**
     * Generates a unique ID for a veterinarian. The ID is incremented each time this method is called.
     *
     * @return the next available veterinarian ID
     */
    public static int generateVetId() {
        return nextVetId++;
    }

    /**
     * Generates a unique ID for a disease. The ID is incremented each time this method is called.
     *
     * @return the next available disease ID
     */
    public static int getDiseaseId() {
        return nextDiseaseId++;
    }

    /**
     * Generates a unique ID for a vaccine. The ID is incremented each time this method is called.
     *
     * @return the next available vaccine ID
     */
    public static int getVaccineId() {
        return nextVaccineId++;
    }

    /**
     * Generates a unique ID for a test. The ID is incremented each time this method is called.
     *
     * @return the next available test ID
     */
    public static int getTestId() {
        return nextTestId++;
    }

    /**
     * Generates a unique ID for an appointment. The ID is incremented each time this method is called.
     *
     * @return the next available appointment ID
     */
    public static int getAppId() {
        return nextAppId++;
    }

    /**
     * Generates a unique ID for a health record. The ID is incremented each time this method is called.
     *
     * @return the next available health record ID
     */
    public static int getHRId() {
        return nextHRId++;
    }

    /**
     * Generates a unique ID for a notification. The ID is incremented each time this method is called.
     *
     * @return the next available notification ID
     */
    public static int getNotifId() {
        return nextNotifId++;
    }
}

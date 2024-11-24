package Utils;

/**
 * Enum representing the different types of appointments.
 * <p>
 * This enum is used to categorize appointments based on their purpose or urgency.
 * It helps in distinguishing between routine check-ups, behavior-related visits,
 * dental appointments, emergency treatments, and surgical procedures.
 * </p>
 */
public enum AppointmentType {

    /**
     * Represents a routine check-up appointment.
     * This is typically a general health check for a pet.
     */
    ROUTINE,

    /**
     * Represents an appointment for addressing behavioral issues in a pet.
     * This may include training or therapy sessions.
     */
    BEHAVIOR,

    /**
     * Represents a dental appointment for a pet.
     * This may include dental cleanings, exams, or treatment for dental issues.
     */
    DENTAL,

    /**
     * Represents an emergency appointment.
     * This is for urgent medical conditions that require immediate attention.
     */
    EMERGENCY,

    /**
     * Represents a surgical appointment.
     * This is for procedures requiring surgery, such as spaying or neutering.
     */
    SURGICAL;
}

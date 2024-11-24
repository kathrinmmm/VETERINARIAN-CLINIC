package Utils;

/**
 * Enum representing the types of notifications that can be sent in the system.
 * <p>
 * These types categorize the purpose of a notification, such as appointment cancellations,
 * reminders, confirmations, and vacation notices. Notifications are used to inform
 * users (such as pet owners and veterinarians) about important events or updates.
 * </p>
 */
public enum NotificationType {

    /**
     * Cancellation notification, typically used to inform users about
     * the cancellation of an appointment or service.
     */
    CANCELLATION,

    /**
     * Reminder notification, used to remind users about upcoming appointments
     * or important tasks, such as vaccination or health checkups.
     */
    REMINDER,

    /**
     * Confirmation notification, sent to confirm the scheduling or booking
     * of an appointment or service.
     */
    CONFIRMATION,

    /**
     * Vacation notification, typically used to inform users about changes in
     * availability, such as when a veterinarian is on vacation and unavailable for appointments.
     */
    VACATION;
}

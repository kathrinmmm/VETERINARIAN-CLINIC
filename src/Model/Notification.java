package Model;

import Utils.NotificationType;

/**
 * The {@code Notification} class represents a notification sent to a user in the system.
 * Notifications can have different types (e.g., confirmation, cancellation) and are
 * associated with a specific user.
 * <p>
 * This class holds the details of the notification including its ID, the user to whom
 * it is sent, the title, the date when it was created, and the type of notification.
 * </p>
 */
public class Notification implements HasId {
    private int id;
    private int userId;
    private String title;
    private String date;
    private NotificationType notificationType;

    /**
     * Constructs a new {@code Notification} with the specified values.
     *
     * @param id the unique identifier for the notification
     * @param userId the ID of the user to whom the notification is sent
     * @param title the title of the notification
     * @param date the date when the notification was created
     * @param notificationType the type of the notification (e.g., confirmation, cancellation)
     */
    public Notification(int id, int userId, String title, String date, NotificationType notificationType) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.date = date;
        this.notificationType = notificationType;
    }

    /**
     * Constructs a new {@code Notification} with the specified values, without specifying an ID.
     * The ID will be assigned when the notification is created.
     *
     * @param userId the ID of the user to whom the notification is sent
     * @param title the title of the notification
     * @param date the date when the notification was created
     * @param notificationType the type of the notification (e.g., confirmation, cancellation)
     */
    public Notification(int userId, String title, String date, NotificationType notificationType) {
        this.userId = userId;
        this.title = title;
        this.date = date;
        this.notificationType = notificationType;
    }

    public Notification(int id, int userId, String title, String date, String notificationType) {
        this.id = id;
    }

    /**
     * Gets the unique identifier of the notification.
     *
     * @return the ID of the notification
     */
    @Override
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the notification.
     *
     * @param id the new ID of the notification
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the user to whom the notification is sent.
     *
     * @return the ID of the user
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user to whom the notification will be sent.
     *
     * @param userId the ID of the user
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the title of the notification.
     *
     * @return the title of the notification
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the notification.
     *
     * @param title the new title of the notification
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the date when the notification was created.
     *
     * @return the date of the notification
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date when the notification was created.
     *
     * @param date the new date of the notification
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the type of the notification.
     *
     * @return the type of the notification (e.g., confirmation, cancellation)
     */
    public String getNotificationType() {
        return notificationType;
    }

    /**
     * Sets the type of the notification.
     *
     * @param notificationType the new type of the notification
     */
    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    /**
     * Returns a string representation of the notification, including its ID, user ID,
     * title, date, and type.
     *
     * @return a string representation of the notification
     */
    @Override
    public String toString() {
        return "Notification {" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", notificationType=" + notificationType +
                '}';
    }
}

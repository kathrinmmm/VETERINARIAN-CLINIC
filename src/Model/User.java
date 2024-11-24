package Model;

import java.util.ArrayList;

/**
 * The {@code User} class is an abstract base class that represents a general user in the system.
 * This class holds common properties and methods shared by all user types, such as unique ID, username, password,
 * notifications, and appointments. The class implements the {@code HasId} interface to ensure every user has a unique ID.
 */
public abstract class User implements HasId {

    /**
     * The unique identifier for this user.
     */
    protected Integer Id;

    /**
     * The username of the user.
     */
    protected String username;

    /**
     * The password of the user.
     */
    protected String password;

    /**
     * The name of the user.
     */
    protected String name;

    /**
     * A list of notifications associated with this user.
     */
    protected ArrayList<Model.Notification> notifications;

    /**
     * A list of appointments associated with this user.
     */
    protected ArrayList<Model.Appointment> appointments;

    /**
     * Constructs a new {@code User} with the specified ID, name, username, and password.
     *
     * @param Id the unique identifier for this user
     * @param name the name of the user
     * @param username the username of the user
     * @param password the password for the user
     */
    public User(Integer Id, String name, String username, String password) {
        this.Id = Id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.notifications = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

    /**
     * Retrieves the unique identifier of this user.
     *
     * @return the unique ID of this user
     */
    public Integer getID() {
        return Id;
    }

    /**
     * Sets a new ID for this user.
     *
     * @param Id the new ID to set for this user
     */
    public void setId(Integer Id) {
        this.Id = Id;
    }

    /**
     * Retrieves the username of this user.
     *
     * @return the username of this user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets a new username for this user.
     *
     * @param username the new username for this user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the password of this user.
     *
     * @return the password of this user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets a new password for this user.
     *
     * @param password the new password for this user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the name of this user.
     *
     * @return the name of this user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets a new name for this user.
     *
     * @param name the new name for this user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the list of notifications associated with this user.
     *
     * @return the list of notifications for this user
     */
    public ArrayList<Model.Notification> getNotifications() {
        return notifications;
    }

    /**
     * Sets the list of notifications associated with this user.
     *
     * @param notifications the list of notifications for this user
     */
    public void setNotifications(ArrayList<Model.Notification> notifications) {
        this.notifications = notifications;
    }

    /**
     * Retrieves the list of appointments associated with this user.
     *
     * @return the list of appointments for this user
     */
    public ArrayList<Model.Appointment> getAppointments() {
        return appointments;
    }

    /**
     * Sets the list of appointments associated with this user.
     *
     * @param appointments the list of appointments for this user
     */
    public void setAppointments(ArrayList<Model.Appointment> appointments) {
        this.appointments = appointments;
    }

    /**
     * Retrieves the unique identifier of this user.
     *
     * @return the unique ID of this user
     */
    @Override
    public Integer getId() {
        return this.Id;
    }

    /**
     * Returns a string representation of this {@code User} object, including the ID, name, and username.
     *
     * @return a string representation of the user
     */
    @Override
    public String toString() {
        return "ID: " + Id + ", Name: " + name + ", Username: " + username;
    }
}

package Model;
import java.util.ArrayList;

public abstract class User implements HasId{

    /**
     * The unique identifier for this person.
     */
    protected Integer Id;

    /**
     * The username of the person.
     */
    protected String username;

    /**
     * The password of the user.
     */
    protected String password;
    protected String name;
    protected ArrayList<Model.Notification> notifications;
    protected ArrayList<Model.Appointment> appointments;
    /**
     * Constructs a new {@code Person} with the specified ID, age, and name.
     *
     * @param Id   the unique identifier for this person
     * @param username The username of the person
     * @param password the password of the user
     */
    public User(Integer Id,String name, String username, String password) {
        this.Id = Id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.notifications = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

    /**
     * Retrieves the unique identifier of this person.
     *
     * @return the ID of this person
     */
    public Integer getID() {
        return Id;
    }

    /**
     * Sets a new ID for this person.
     *
     * @param Id the new ID to set for this person
     */
    public void setId(Integer Id) {
        this.Id = Id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Model.Notification> getNotifications() {
        return notifications;
    }

    public ArrayList<Model.Appointment> getAppointments() {
        return appointments;
    }

    public void setNotifications(ArrayList<Model.Notification> notifications) {
        this.notifications = notifications;
    }

    public void setAppointments(ArrayList<Model.Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public Integer getId() {
        return this.Id;
    }


    @Override
    public String toString() {
        return "ID: " + Id +", Name: "+ name +", Username: " + username;
    }}




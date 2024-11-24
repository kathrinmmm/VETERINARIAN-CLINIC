package Model;

import Utils.NotificationType;

public class Notification implements HasId {
    private int id;
    private int userId;
    private String title;
    private String date;
    private NotificationType notificationType;

    public Notification(int id, int userId, String title, String date, NotificationType notificationType) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.date = date;
        this.notificationType = notificationType;
    }
    public Notification(int userId, String title, String date, NotificationType notificationType) {
        this.userId = userId;
        this.title = title;
        this.date = date;
        this.notificationType = notificationType;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }


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

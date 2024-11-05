package service;

import model.Appointment;
import model.Notification;
import model.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NotificationService {
    private List<Notification> notifications;

    public NotificationService() {
        notifications = new ArrayList<>();
    }

    public void sendVaccinationReminder(User user, Appointment appointment) {
        String title = "Memento Vaccinare";
        String description = "Nu uitați să vaccinați animalul de companie!";
        String date = appointment.getDate();

        Notification notification = new Notification(generateId(), title, description, date);
        notifications.add(notification);
        System.out.println("Notificare de vaccinare trimisă: " + notification.getDescription());
    }

    public void sendTestReminder(User user, Appointment appointment) {
        String title = "Memento Test";
        String description = "Nu uitați să efectuați testele pentru animalul de companie!";
        String date = appointment.getDate();

        Notification notification = new Notification(generateId(), title, description, date);
        notifications.add(notification);
        System.out.println("Notificare de test trimisă: " + notification.getDescription());
    }

    public void sendCancellationNotification(User user, Appointment appointment) {
        String title = "Anulare Programare";
        String description = "Programarea cu ID " + appointment.getId() + " a fost anulată.";
        String date = LocalDate.now().toString();

        Notification notification = new Notification(generateId(), title, description, date);
        notifications.add(notification);
        System.out.println("Notificare de anulare trimisă: " + notification.getDescription());
    }

    public void remindUpcomingAppointment(User user, Appointment appointment) {
        String title = "Reamintire Programare";
        String description = "Aveți o programare pe data de " + appointment.getDate() + ".";
        String date = LocalDate.now().toString();

        Notification notification = new Notification(generateId(), title, description, date);
        notifications.add(notification);
        System.out.println("Notificare de reamintire trimisă: " + notification.getDescription());
    }

    private int generateId() {
        return notifications.size() + 1;
    }

    public void listNotifications() {
        if (notifications.isEmpty()) {
            System.out.println("Nu există notificări.");
            return;
        }

        for (Notification notification : notifications) {
            System.out.println("ID: " + notification.getId() + ", Titlu: " + notification.getTitle() +
                    ", Descriere: " + notification.getDescription() + ", Data: " + notification.getDate());
        }
    }
}

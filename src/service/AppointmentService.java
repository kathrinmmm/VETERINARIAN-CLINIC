package service;

import model.Appointment;
import model.User;
import model.Vaccine;
import model.Test;
import model.Veterinarian;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppointmentService {
    private List<Appointment> appointments;
    private NotificationService notificationService;
    private VeterinarianService veterinarianService;
    private Scanner scanner;

    public AppointmentService(NotificationService notificationService, VeterinarianService veterinarianService) {
        this.appointments = new ArrayList<>();
        this.notificationService = notificationService;
        this.veterinarianService = veterinarianService;
        this.scanner = new Scanner(System.in);
    }

    public void addAppointment() {
        System.out.println("Introdu detalii pentru programare:");

        System.out.print("ID Programare: ");
        int id = scanner.nextInt();

        System.out.print("ID Animal (Pet ID): ");
        int petId = scanner.nextInt();

        System.out.println("Selectați un veterinar:");
        List<Veterinarian> veterinarians = veterinarianService.getVeterinarians();
        for (Veterinarian vet : veterinarians) {
            System.out.println(vet);
        }

        System.out.print("Introduceți ID-ul veterinarului ales: ");
        int vetId = scanner.nextInt();
        Veterinarian selectedVeterinarian = veterinarianService.getVeterinarianById(vetId);
        if (selectedVeterinarian == null) {
            System.out.println("Veterinar inexistent.");
            return;
        }

        veterinarianService.displayAvailableDatesAndTimes(vetId);

        LocalDate date = selectDate();


        LocalTime time = selectTime();

        if (!selectedVeterinarian.isAvailable(date, time)) {
            System.out.println("Veterinarul nu este disponibil la data și ora selectată.");
            return;
        }


        scanner.nextLine();

        System.out.print("Tipul programării (ex: Vaccinare, Consultație): ");
        String typeOfAppointment = scanner.nextLine();

        List<Vaccine> vaccines = new ArrayList<>();
        List<Test> tests = new ArrayList<>();

        Appointment appointment = new Appointment(id, petId, vetId, date.toString(), time.toString(), "", typeOfAppointment, vaccines, tests);
        appointments.add(appointment);
        selectedVeterinarian.addAppointment(appointment);
        selectedVeterinarian.removeAvailableTime(date, time);
        System.out.println("Programare adăugată cu succes.");


        notificationService.remindUpcomingAppointment(null, appointment);
    }

    private LocalDate selectDate() {
        System.out.println("Introduceti data programării (yyyy-MM-dd):");
        LocalDate date;
        while (true) {
            try {
                String inputDate = scanner.nextLine();
                date = LocalDate.parse(inputDate);
                if (date.isBefore(LocalDate.now())) {
                    System.out.println("Data nu poate fi în trecut. Te rog, introdu o dată validă.");
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Format de dată invalid. Te rog, introdu data în formatul corect (dd-MM-yyyy).");
            }
        }
        return date;
    }

    private LocalTime selectTime() {
        System.out.println("Introduceti ora programării (HH:mm):");
        LocalTime time;
        while (true) {
            try {
                String inputTime = scanner.nextLine();
                time = LocalTime.parse(inputTime);
                break;
            } catch (Exception e) {
                System.out.println("Format de oră invalid. Te rog, introdu ora în formatul corect (HH:mm).");
            }
        }
        return time;
    }

    public void listAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("Nu există programări.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println("Programare ID: " + appointment.getId() + ", Animal ID: " + appointment.getPet_id() +
                        ", Veterinar ID: " + appointment.getVet_id() + ", Data: " + appointment.getDate() +
                        ", Ora: " + appointment.getTime() + ", Tip: " + appointment.getTypeOfAppointment());
            }
        }
    }
}

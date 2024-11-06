package service;

import model.Appointment;
import model.Vaccine;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class VaccineService {

    private ArrayList<Vaccine> vaccines;
    private Scanner scanner;

    public VaccineService() {
        vaccines = new ArrayList<>(Arrays.asList(
                new Vaccine(1, "Canine Parvovirus Vaccine", "Type1", 10, 10),
                new Vaccine(2, "Rabies Vaccine", "Type2", 15, 15),
                new Vaccine(3, "Feline Leukemia Vaccine", "Type1", 12, 12),
                new Vaccine(4, "Feline Rhinotracheitis Vaccine", "Type2", 8, 8),
                new Vaccine(5, "Leptospirosis Vaccine", "Type3", 5, 5)
        ));
        scanner = new Scanner(System.in);
    }

    public void addVaccine() {
        System.out.print("Enter Vaccine ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over
        System.out.print("Enter Vaccine Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Vaccine Type: ");
        String type = scanner.nextLine();
        System.out.print("Enter Availability: ");
        int availability = scanner.nextInt();
        System.out.print("Enter Disponibility: ");
        int disponibility = scanner.nextInt();

        Vaccine newVaccine = new Vaccine(id, name, type, availability, disponibility);
        vaccines.add(newVaccine);

        System.out.println("Vaccine added successfully: " + newVaccine);
    }

    public void addVaccineToAppointment(Appointment appointment, Vaccine vaccine) {
        appointment.getVaccines().add(vaccine);
        System.out.println("Vaccine added to appointment.");
    }

    public void listVaccinesForAppointment(Appointment appointment) {
        System.out.println("Vaccines for appointment ID " + appointment.getId() + ":");
        for (Vaccine vaccine : appointment.getVaccines()) {
            System.out.println(" - " + vaccine.getName());
        }
    }
}
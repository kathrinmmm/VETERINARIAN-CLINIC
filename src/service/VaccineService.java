package service;

import model.Appointment;
import model.Vaccine;

public class VaccineService {

    public void addVaccineToAppointment(Appointment appointment, Vaccine vaccine) {
        appointment.getVaccines().add(vaccine);
        System.out.println("Vaccin adăugat la programare.");
    }

    public void listVaccinesForAppointment(Appointment appointment) {
        System.out.println("Vaccinuri pentru programare ID " + appointment.getId() + ":");
        for (Vaccine vaccine : appointment.getVaccines()) {
            System.out.println(" - " + vaccine.getName());
        }
    }
}
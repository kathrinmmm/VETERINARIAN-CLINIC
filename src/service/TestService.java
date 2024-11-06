package service;

import Repository.IRepository;
import model.Appointment;
import model.Test;

public class TestService {

    public TestService(IRepository<Test> testRepository) {
    }

    public TestService() {

    }

    public void addTestToAppointment(Appointment appointment, Test test) {
        appointment.getTests().add(test);
        System.out.println("Test adăugat la programare.");
    }

    public void listTestsForAppointment(Appointment appointment) {
        System.out.println("Teste pentru programare ID " + appointment.getId() + ":");
        for (Test test : appointment.getTests()) {
            System.out.println(" - " + test.getType());
        }
    }
}

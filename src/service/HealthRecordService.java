package service;

import model.Appointment;
import model.HealthRecord;
import model.Pet;

import java.util.ArrayList;
import java.util.List;

public class HealthRecordService {
    private List<HealthRecord> healthRecords;

    public HealthRecordService() {
        this.healthRecords = new ArrayList<>();
    }
    public void addHealthRecord(Pet pet) {
        HealthRecord record = new HealthRecord(pet.getUser_id(), new ArrayList<>());
        healthRecords.add(record);
    }
    public HealthRecord getHealthRecordByPet(Pet pet) {
        for (HealthRecord record : healthRecords) {
            if (record.getPetId() == pet.getUser_id()) {
                return record;
            }
        }
        return null;
    }
    public void addAppointmentToHealthRecord(Pet pet, Appointment appointment) {
        HealthRecord record = getHealthRecordByPet(pet);
        if (record != null) {
            record.addAppointment(appointment);
            System.out.println("Programare adaugată la health record: " + pet.getPetName());
        } else {
            System.out.println("nu a fost gasit healthrecord-ul");
        }
    }

    public void displayHealthRecord(Pet pet) {
        HealthRecord record = getHealthRecordByPet(pet);
        if (record != null) {
            System.out.println("Health record pentru: " + pet.getPetName());
            record.getAppointments().forEach(System.out::println);
        } else {
            System.out.println("nu a fost gasit healthrecord-ul");
        }
    }
}

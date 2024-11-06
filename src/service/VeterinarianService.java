package service;

import Repository.IRepository;
import model.Veterinarian;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class VeterinarianService {
    private List<Veterinarian> veterinarians;

    public VeterinarianService(IRepository<Veterinarian> veterinarianRepository) {
        veterinarians = new ArrayList<>();
    }

    public void addVeterinarian(Veterinarian veterinarian) {
        veterinarians.add(veterinarian);
    }

    public List<Veterinarian> getVeterinarians() {
        return veterinarians;
    }

    public Veterinarian getVeterinarianById(int id) {
        for (Veterinarian vet : veterinarians) {
            if (vet.getUser_id() == id) {
                return vet;
            }
        }
        return null;
    }

    public void displayAvailableDatesAndTimes(int vetId) {
        Veterinarian veterinarian = getVeterinarianById(vetId);
        if (veterinarian != null) {
            System.out.println("Veterinar: " + veterinarian.getName());
            List<LocalDate> availableDates = veterinarian.getAvailableDates();
            for (LocalDate date : availableDates) {
                System.out.println("Data disponibilă: " + date);
                List<LocalTime> availableTimes = veterinarian.getAvailableTimes(date);
                if (availableTimes.isEmpty()) {
                    System.out.println("  - Nicio oră disponibilă.");
                } else {
                    System.out.print("  Ore disponibile: ");
                    for (LocalTime time : availableTimes) {
                        System.out.print(time + " ");
                    }
                    System.out.println();
                }
            }
        } else {
            System.out.println("Veterinar inexistent.");
        }
    }
}

package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Disease;

public class DiseaseService {
    private List<Disease> diseases = new ArrayList<>();
    private Map<Integer, Integer> testFrequencies = new HashMap<>();
    private Map<Integer, Integer> vaccineFrequencies = new HashMap<>();

    public DiseaseService() {
        diseases.add(new Disease(1, "Rabies", "Viral"));
        diseases.add(new Disease(2, "Parvovirus", "Viral"));
        diseases.add(new Disease(3, "Feline Leukemia", "Viral"));
        diseases.add(new Disease(4, "Canine Distemper", "Viral"));

        testFrequencies.put(1, 365);
        vaccineFrequencies.put(1, 365);

        testFrequencies.put(2, 365);
        vaccineFrequencies.put(2, 365);

        testFrequencies.put(3, 730);
        vaccineFrequencies.put(3, 730);

        testFrequencies.put(4, 365);
        vaccineFrequencies.put(4, 365);
    }

    public void modifyTestFrequency(int diseaseId, int newFrequency) {
        if (testFrequencies.containsKey(diseaseId)) {
            testFrequencies.put(diseaseId, newFrequency);
            System.out.println("Test frequency for disease ID " + diseaseId + " updated to " + newFrequency + " days.");
        } else {
            System.out.println("Disease ID not found.");
        }
    }

    public void modifyVaccineFrequency(int diseaseId, int newFrequency) {
        if (vaccineFrequencies.containsKey(diseaseId)) {
            vaccineFrequencies.put(diseaseId, newFrequency);
            System.out.println("Vaccine frequency for disease ID " + diseaseId + " updated to " + newFrequency + " days.");
        } else {
            System.out.println("Disease ID not found.");
        }
    }

    public void addDisease(int id, String name, String type) {
        Disease disease = new Disease(id, name, type);
        diseases.add(disease);
        testFrequencies.put(id, 365); // Default frequency value
        vaccineFrequencies.put(id, 365);
        System.out.println("Disease " + name + " added with ID " + id + ".");
    }

    public List<Disease> getAllDiseases() {
        return diseases;
    }

    public int getRecommendedTestFrequency(int diseaseId) {
        return testFrequencies.getOrDefault(diseaseId, -1); // Returns -1 if disease not found
    }

    public int getRecommendedVaccineFrequency(int diseaseId) {
        return vaccineFrequencies.getOrDefault(diseaseId, -1); // Returns -1 if disease not found
    }
}

package service;

import Repository.IRepository;
import model.Disease;
import model.Pet;
import model.Pet_Disease;

import java.util.*;

public class PetDiseaseService {
    private Map<Integer, List<Disease>> petDiseases = new HashMap<>();
    private DiseaseService diseaseService;
    private PetService petService;

    public PetDiseaseService(DiseaseService diseaseService, PetService petService) {
        this.diseaseService = diseaseService;
        this.petService = petService;
    }

    public PetDiseaseService(IRepository<Pet_Disease> petDiseaseRepository) {
    }

    public void addDiseaseToPet(int petId, int diseaseId) {
        Pet pet = petService.findPetById(petId);
        if (pet == null) {
            System.out.println("Animalul cu ID-ul " + petId + " nu a fost găsit.");
            return;
        }

        Disease disease = diseaseService.getAllDiseases().stream()
                .filter(d -> d.getId() == diseaseId)
                .findFirst()
                .orElse(null);

        if (disease == null) {
            System.out.println("Boala cu ID-ul " + diseaseId + " nu a fost găsită.");
            return;
        }

        petDiseases.computeIfAbsent(petId, k -> new ArrayList<>()).add(disease);
        System.out.println("Boala " + disease.getName() + " a fost adăugată pentru animalul de companie " + pet.getPetName() + ".");
    }

    public List<Disease> getDiseasesForPet(int petId) {
        return petDiseases.getOrDefault(petId, Collections.emptyList());
    }

    public void getTestAndVaccineFrequencyForPet(int petId) {
        List<Disease> diseases = getDiseasesForPet(petId);
        if (diseases.isEmpty()) {
            System.out.println("Nu există boli asociate cu acest animal de companie.");
            return;
        }
        System.out.println("Recomandări facere vaccin si test pt animalul cu ID-ul " + petId + ":");
        for (Disease disease : diseases) {
            int testFrequency = diseaseService.getRecommendedTestFrequency(disease.getId());
            int vaccineFrequency = diseaseService.getRecommendedVaccineFrequency(disease.getId());

            System.out.println("Boală: " + disease.getName());
            System.out.println("   - Frecvența testelor: " + (testFrequency > 0 ? testFrequency + " zile" : "Necunoscut"));
            System.out.println("   - Frecvența vaccinurilor: " + (vaccineFrequency > 0 ? vaccineFrequency + " zile" : "Necunoscut"));
        }
    }
}

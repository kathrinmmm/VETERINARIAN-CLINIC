package service;
import model.Disease;
import model.Pet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import Repository.IRepository;
public class PetService {
    private IRepository<Pet> petRepository;
    private List<Pet> pets = new ArrayList<>();
    private Map<String, List<String>> speciesToBreeds;

    public PetService(IRepository<Pet> petRepository) {
        speciesToBreeds = new HashMap<>();
        speciesToBreeds.put("Caine", List.of("Labrador", "Beagle", "Bulldog", "Golden Retriever", "Cocker Spaniol"));
        speciesToBreeds.put("Pisica", List.of("Siamese", "Persană", "Maine Coon", "Bengaleză", "Sfinx"));
        speciesToBreeds.put("Papagal", List.of("Papagal ondulat", "Cacadu", "Papagal gri african", "Perus"));
        speciesToBreeds.put("Iepure", List.of("Iepurele Urias Belgian", "Chinchilla","Albastru Vienez"));
    }

    public PetService() {

    };



    public void registerPet() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introdu datele animalului de companie:");
        System.out.print("ID: ");
        int id = scanner.nextInt();

        scanner.nextLine();
        System.out.print("Nume stăpân: ");
        String ownerFirstName = scanner.nextLine();

        System.out.print("Prenume stăpân: ");
        String ownerLastName = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Nume animal: ");
        String petName = scanner.nextLine();

        System.out.print("Data nașterii (dd-MM-yyyy): ");
        String birthDate = scanner.nextLine();

        String species = selectSpecies(scanner);

        String breed = selectBreed(scanner, species);

        String gender;
        while (true) {
            System.out.print("Gen (M/F): ");
            gender = scanner.nextLine().toUpperCase();
            if (gender.equals("M") || gender.equals("F")) {
                break;
            } else {
                System.out.println("Eroare: Vă rugăm să introduceți 'M' pentru masculin sau 'F' pentru feminin.");
            }
        }

        Pet pet = new Pet(id, ownerFirstName, ownerLastName, email, "username", "password", petName, birthDate, gender, species, breed);
        pets.add(pet);
        System.out.println("Animalul de companie a fost înregistrat cu succes.");
    }

    private String selectSpecies(Scanner scanner) {
        System.out.println("Selectați specia animalului:");
        List<String> speciesList = new ArrayList<>(speciesToBreeds.keySet());
        for (int i = 0; i < speciesList.size(); i++) {
            System.out.println((i + 1) + ". " + speciesList.get(i));
        }

        int choice;
        while (true) {
            System.out.print("Introduceți numărul corespunzător speciei: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice > 0 && choice <= speciesList.size()) {
                return speciesList.get(choice - 1);
            } else {
                System.out.println("Opțiune invalidă. Vă rugăm să selectați un număr din listă.");
            }
        }
    }

    private String selectBreed(Scanner scanner, String species) {
        System.out.println("Selectați rasa animalului:");
        List<String> breeds = speciesToBreeds.get(species);
        for (int i = 0; i < breeds.size(); i++) {
            System.out.println((i + 1) + ". " + breeds.get(i));
        }

        int choice;
        while (true) {
            System.out.print("Introduceți numărul corespunzător rasei: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice > 0 && choice <= breeds.size()) {
                return breeds.get(choice - 1);
            } else {
                System.out.println("Opțiune invalidă. Vă rugăm să selectați un număr din listă.");
            }
        }
    }

    public Pet findPetById(int id) {
        for (Pet pet : pets) {
            if (pet.getUser_id() == id) {
                return pet;
            }
        }
        System.out.println("Animalul cu ID-ul " + id + " nu a fost găsit.");
        return null;
    }

public List<Pet> DisplayAllPets() {
        return pets;
}

}
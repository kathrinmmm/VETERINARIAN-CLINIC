package Model;

public class IdGenerator {
    private static int nextPetId = 5;
    private static int nextVetId = 5;
    private static int nextDiseaseId = 20;
    private static int nextVaccineId = 20;
    private static int nextTestId = 20;
    private static int nextAppId = 50;
    private static int nextHRId = 20;
    private static int nextNotifId = 20;

    public static int generatePetId() {
        return nextPetId++;
    }

    public static int generateVetId() {
        return nextVetId++;
    }
    public static int getDiseaseId(){
        return nextDiseaseId++;
    }
    public static int getVaccineId(){
        return nextVaccineId++;
    }
    public static int getTestId(){
        return nextTestId++;
    }
    public static int getAppId(){
        return nextAppId++;
    }
    public static int getHRId(){
        return nextHRId++;
    }
    public static int getNotifId(){
        return nextNotifId++;
    }
}


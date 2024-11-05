package model;

import java.time.LocalDate;

public class Pet_Disease {
    private int id;
    private Pet pet;
    private Disease disease;
    private LocalDate diagnosisDate;
    private String treatment;

    public Pet_Disease(int id, Pet pet, Disease disease, LocalDate diagnosisDate, String treatment) {
        this.id = id;
        this.pet = pet;
        this.disease = disease;
        this.diagnosisDate = diagnosisDate;
        this.treatment = treatment;
    }

    public int getId() {
        return id;
    }

    public Pet getPet() {
        return pet;
    }

    public Disease getDisease() {
        return disease;
    }

    public LocalDate getDiagnosisDate() {
        return diagnosisDate;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public void setDiagnosisDate(LocalDate diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}

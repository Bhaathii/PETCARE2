package com.example.petcare;

public class Pet {
    private String key, petName, petAge, petSex, petBreed, medicalDates;

    public Pet() {
        // Default constructor required for Firebase
    }

    public Pet(String key, String petName, String petAge, String petSex, String petBreed, String medicalDates) {
        this.key = key;
        this.petName = petName;
        this.petAge = petAge;
        this.petSex = petSex;
        this.petBreed = petBreed;
        this.medicalDates = medicalDates;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetAge() {
        return petAge;
    }

    public void setPetAge(String petAge) {
        this.petAge = petAge;
    }

    public String getPetSex() {
        return petSex;
    }

    public void setPetSex(String petSex) {
        this.petSex = petSex;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getMedicalDates() {
        return medicalDates;
    }

    public void setMedicalDates(String medicalDates) {
        this.medicalDates = medicalDates;
    }
}
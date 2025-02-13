package com.example.petcare;

public class Clinic {
    private String key, clinicName, contactNumber, address, city, description, ownerName;

    public Clinic() {
        // Default constructor required for Firebase
    }

    public Clinic(String key, String clinicName, String contactNumber, String address, String city, String description, String ownerName) {
        this.key = key;
        this.clinicName = clinicName;
        this.contactNumber = contactNumber;
        this.address = address;
        this.city = city;
        this.description = description;
        this.ownerName = ownerName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
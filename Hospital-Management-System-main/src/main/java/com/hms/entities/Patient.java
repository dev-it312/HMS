package com.hms.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;

@Entity
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String gender;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    private String bloodGroup;
    private String phoneNumber;
    private String streetAddress;
    private String city;
    private String state;
    private String postalCode;
    private String emergencyContactName;
    private String relationship;
    private String emergencyPhoneNumber;
    private LocalDate dateOfRegistration;

    @Lob
    private byte[] patientReports;

    // Add password field
    private String password;

    // Constructors
    public Patient() {}

    public Patient(String fullName, String gender, LocalDate dateOfBirth, String bloodGroup, String phoneNumber,
                   String streetAddress, String city, String state, String postalCode, String emergencyContactName,
                   String relationship, String emergencyPhoneNumber, LocalDate dateOfRegistration, byte[] patientReports, String password) {
        this.fullName = fullName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.bloodGroup = bloodGroup;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.emergencyContactName = emergencyContactName;
        this.relationship = relationship;
        this.emergencyPhoneNumber = emergencyPhoneNumber;
        this.dateOfRegistration = dateOfRegistration;
        this.patientReports = patientReports;
        this.password = password;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getEmergencyPhoneNumber() {
        return emergencyPhoneNumber;
    }

    public void setEmergencyPhoneNumber(String emergencyPhoneNumber) {
        this.emergencyPhoneNumber = emergencyPhoneNumber;
    }

    public LocalDate getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(LocalDate dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public byte[] getPatientReports() {
        return patientReports;
    }

    public void setPatientReports(byte[] patientReports) {
        this.patientReports = patientReports;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Patient [id=" + id + ", fullName=" + fullName + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth
                + ", bloodGroup=" + bloodGroup + ", phoneNumber=" + phoneNumber + ", streetAddress=" + streetAddress
                + ", city=" + city + ", state=" + state + ", postalCode=" + postalCode + ", emergencyContactName="
                + emergencyContactName + ", relationship=" + relationship + ", emergencyPhoneNumber=" + emergencyPhoneNumber
                + ", dateOfRegistration=" + dateOfRegistration + ", patientReports=" + Arrays.toString(patientReports)
                + ", password=" + password + "]";
    }
}

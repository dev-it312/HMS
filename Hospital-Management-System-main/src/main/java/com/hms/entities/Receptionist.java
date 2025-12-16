package com.hms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Receptionist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate dob;
    private String email;
    private String password;
    private String qualification;
    private String phoneNumber;
    private String address;

    public Receptionist() {}

    public Receptionist(String name, LocalDate dob, String email, String password, String qualification,
                        String phoneNumber, String address) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.qualification = qualification;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return "Receptionist [id=" + id + ", name=" + name + ", dob=" + dob + ", email=" + email + ", password=" + password
                + ", qualification=" + qualification + ", phoneNumber=" + phoneNumber + ", address=" + address + "]";
    }
}

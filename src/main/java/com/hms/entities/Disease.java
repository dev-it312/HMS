package com.hms.entities;



import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;



@Entity
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @Column(length = 1000)
    private String symptoms;  // Store as comma-separated string
    
    private String specialty;

    // Constructors
    public Disease() {}
    
    public Disease(String name, String symptoms, String specialty) {
        this.name = name;
        this.symptoms = symptoms;
        this.specialty = specialty;
    }

    @Override
	public String toString() {
		return "Disease [id=" + id + ", name=" + name + ", symptoms=" + symptoms + ", specialty=" + specialty + "]";
	}

	// Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSymptoms() { return symptoms; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }
    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
}
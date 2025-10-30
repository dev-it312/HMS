package com.hms.repositories;

import com.hms.entities.Patient;
import com.hms.entities.PatientReport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // You can add custom queries here if necessary.
	
	// Custom method to find a patient by phone number
    Patient findByPhoneNumber(String phoneNumber);
	
	Patient findById(long id);

	void save(PatientReport report);
}

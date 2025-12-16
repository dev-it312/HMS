package com.hms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.entities.Doctor;
import com.hms.entities.Patient;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	
	Doctor findByPhoneNumber(String phoneNumber);
}

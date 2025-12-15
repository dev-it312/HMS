package com.hms.repositories;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.entities.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
	
	List<Prescription> findByPatientId(Long patientId);
	
	
}


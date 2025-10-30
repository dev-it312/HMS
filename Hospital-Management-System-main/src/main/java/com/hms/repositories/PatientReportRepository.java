package com.hms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hms.entities.PatientReport;

@Repository
public interface PatientReportRepository extends JpaRepository<PatientReport, Long> {
    // Custom queries can be added here if necessary
	
	List<PatientReport> findByPatientId(Long patientId);
}


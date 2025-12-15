package com.hms.services;

import java.util.List;

import com.hms.entities.Prescription;

public interface PrescriptionService {

	void savePrescription(Prescription prescription);

	List<Prescription> getPrescriptionsByPatientId(Long patientId);
	

}

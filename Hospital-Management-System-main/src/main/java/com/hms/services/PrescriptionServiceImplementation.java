package com.hms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.entities.Prescription;
import com.hms.repositories.PrescriptionRepository;

@Service
public class PrescriptionServiceImplementation implements PrescriptionService {
	
	@Autowired
    private PrescriptionRepository prescriptionRepository;

	public void savePrescription(Prescription prescription) {
        prescriptionRepository.save(prescription);
    }
	
	
	public List<Prescription> getPrescriptionsByPatientId(Long patientId) {
        return prescriptionRepository.findByPatientId(patientId);
    }
	
	
	

}

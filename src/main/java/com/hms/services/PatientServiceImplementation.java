package com.hms.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hms.entities.Patient;
import com.hms.entities.PatientReport;
import com.hms.repositories.PatientReportRepository;
import com.hms.repositories.PatientRepository;

@Service
public class PatientServiceImplementation implements PatientService{
	
	@Autowired
    private PatientRepository patientRepository;
	
	@Autowired
	PatientRepository patientReportRepository;
	@Autowired
	PatientReportRepository reportRepository;
	
	

    public Patient registerNewPatient(Patient patient) {
        // Any business logic like validation can be added here
        return patientRepository.save(patient);
    }
    
    
    //Patient login
 // Method to find patient by phone number
    public Patient getPatientByPhoneNumber(String phoneNumber) {
        return patientRepository.findByPhoneNumber(phoneNumber);
    }

	
    
    
    
    
   //Searching a patient
    public Optional<Patient> findPatientById(Long id) {
        return patientRepository.findById(id);
    }






	@Override
	public Optional<Patient> getPatientById(Long id) {
		// TODO Auto-generated method stub
		return patientRepository.findById(id);
	}



	
	
	
	public boolean updatePatientDetails(Long patientId, String fullName, String gender, LocalDate dateOfBirth,
            String bloodGroup, String phoneNumber, String streetAddress, String city,
            String state, String postalCode, String emergencyContactName, String relationship,
            String emergencyPhoneNumber) {
			Patient patient = patientRepository.findById(patientId).orElse(null);
			if (patient != null) {
				patient.setFullName(fullName);
				patient.setGender(gender);
				patient.setDateOfBirth(dateOfBirth);
				patient.setBloodGroup(bloodGroup);
				patient.setPhoneNumber(phoneNumber);
				patient.setStreetAddress(streetAddress);
				patient.setCity(city);
				patient.setState(state);
				patient.setPostalCode(postalCode);
				patient.setEmergencyContactName(emergencyContactName);
				patient.setRelationship(relationship);
				patient.setEmergencyPhoneNumber(emergencyPhoneNumber);
				
				patientRepository.save(patient);
						return true;
			}
			return false;
}

	
	
	@Override
	public void uploadPatientReports(Long patientId, MultipartFile[] files) throws IOException {
	    Patient patient = patientRepository.findById(patientId)
	            .orElseThrow(() -> new RuntimeException("Patient not found"));

	    for (MultipartFile file : files) {
	        PatientReport report = new PatientReport();
	        report.setPatient(patient);
	        report.setFileName(file.getOriginalFilename());
	        report.setFileType(file.getContentType());
	        report.setPatientReports(file.getBytes());  // Renamed to 'patientReports'

	        patientReportRepository.save(report);
	    }
	}
	
	
	
	public List<PatientReport> getReportsByPatientId(Long patientId) {
        return reportRepository.findByPatientId(patientId);
    }

	
	
	
	//Delete Patient
	public boolean deletePatientById(Long patientId) {
        if (patientRepository.existsById(patientId)) {
            patientRepository.deleteById(patientId);
            return true;
        }
        return false;
    }
	

	}
    
    
    


    



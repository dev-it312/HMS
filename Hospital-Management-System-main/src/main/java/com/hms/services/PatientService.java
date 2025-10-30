package com.hms.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.hms.entities.Patient;
import com.hms.entities.PatientReport;

public interface PatientService {

	public Patient registerNewPatient(Patient patient);
	
	public Patient getPatientByPhoneNumber(String phoneNumber);

	public Optional<Patient> findPatientById(Long id);

	public Optional<Patient> getPatientById(Long id);


	public boolean updatePatientDetails(Long patientId, String fullName, String gender, LocalDate dateOfBirth,
			String bloodGroup, String phoneNumber, String streetAddress, String city, String state, String postalCode,
			String emergencyContactName, String relationship, String emergencyPhoneNumber);

	public void uploadPatientReports(Long patientId, MultipartFile[] files) throws IOException;

	public List<PatientReport> getReportsByPatientId(Long patientId);

	public boolean deletePatientById(Long patientId);

	


	
	

	

	

}

package com.hms.services;

import com.hms.entities.Doctor;
import com.hms.entities.Patient;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DoctorService {
    Doctor saveDoctor(Doctor doctor);
    List<Doctor> getAllDoctors();
    List<String> getAllSpecializations();  // Add this method to get all specializations
	Doctor getDoctorByPhoneNumber(String phoneNumber);
	boolean updateDoctorDetails(Long doctorId, String name, LocalDate dob, String email, String qualification,
			String specialisation, int yearsOfExperience, String phoneNumber, String address);
	Optional<Doctor> getDoctorById(Long id);
	boolean removeDoctorById(Long doctorId);
}

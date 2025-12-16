package com.hms.services;

import com.hms.entities.Doctor;
import com.hms.entities.Patient;
import com.hms.repositories.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImplementation implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
    
  //Patient login
    // Method to find patient by phone number
       public Doctor getDoctorByPhoneNumber(String phoneNumber) {
           return doctorRepository.findByPhoneNumber(phoneNumber);
       }
       

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public List<String> getAllSpecializations() {
        // Assuming that the specializations are unique
        return doctorRepository.findAll().stream()
                .map(Doctor::getSpecialisation)
                .distinct()
                .collect(Collectors.toList());
    }
    
    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    public boolean updateDoctorDetails(Long id, String name, LocalDate dob, String email, String qualification,
                                       String specialisation, int yearsOfExperience, String phoneNumber, String address) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if (doctorOptional.isPresent()) {
            Doctor doctor = doctorOptional.get();
            doctor.setName(name);
            doctor.setDob(dob);
            doctor.setEmail(email);
            doctor.setQualification(qualification);
            doctor.setSpecialisation(specialisation);
            doctor.setYearsOfExperience(yearsOfExperience);
            doctor.setPhoneNumber(phoneNumber);
            doctor.setAddress(address);
            doctorRepository.save(doctor);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean removeDoctorById(Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

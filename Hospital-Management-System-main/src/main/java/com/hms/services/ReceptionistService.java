package com.hms.services;

import java.time.LocalDate;
import java.util.List;

import java.util.Optional;
import com.hms.entities.Receptionist;

public interface ReceptionistService {
    Receptionist saveReceptionist(Receptionist receptionist);
    List<Receptionist> getAllReceptionists();
    Receptionist getReceptionistByEmail(String email);
	boolean updateReceptionistDetails(Long id, String name, LocalDate dob, String email, String qualification,
			String phoneNumber, String address);
	Optional<Receptionist> getReceptionistById(Long id);
	boolean removeReceptionistById(Long receptionistId); 
}

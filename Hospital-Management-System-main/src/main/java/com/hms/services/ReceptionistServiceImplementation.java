package com.hms.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.entities.Receptionist;
import com.hms.repositories.ReceptionistRepository;

@Service
public class ReceptionistServiceImplementation implements ReceptionistService {

    @Autowired
    private ReceptionistRepository receptionistRepository;
    
    @Autowired
    private PasswordService passwordService;

    @Autowired
    private EmailValidatorService emailValidatorService;

    @Override
    public Receptionist saveReceptionist(Receptionist receptionist) {
        return receptionistRepository.save(receptionist);
    }

    @Override
    public List<Receptionist> getAllReceptionists() {
        return receptionistRepository.findAll();
    }

    @Override
    public Receptionist getReceptionistByEmail(String email) {
        return receptionistRepository.findByEmail(email);  // Find receptionist by email
    }
    
    @Override
    public Optional<Receptionist> getReceptionistById(Long id) {
        return receptionistRepository.findById(id);
    }
    
    public boolean updateReceptionistDetails(Long id, String name, LocalDate dob, String email, String qualification, String phoneNumber, String address) {
        Optional<Receptionist> optionalReceptionist = receptionistRepository.findById(id);
        if (optionalReceptionist.isPresent()) {
            Receptionist receptionist = optionalReceptionist.get();
            receptionist.setName(name);
            receptionist.setDob(dob);
            receptionist.setEmail(email);
            receptionist.setQualification(qualification);
            receptionist.setPhoneNumber(phoneNumber);
            receptionist.setAddress(address);
            receptionistRepository.save(receptionist);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean removeReceptionistById(Long id) {
        if (receptionistRepository.existsById(id)) {
            receptionistRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // Exemple de méthode d'enregistrement avec validation
    public boolean registerReceptionist(Receptionist receptionist) {
        // Validation de l'email
        return (emailValidatorService.validate(receptionist.getEmail()))
        // Validation du mot de passe
        if (!passwordService.isStrong(receptionist.getPassword())) {
            return false;
        }
        // ...enregistrement en base (à compléter selon la logique du projet)...
        return true;
    }
}
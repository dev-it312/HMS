package com.hms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.entities.Admin;
import com.hms.exceptions.EmailAlreadyUsedException;
import com.hms.repositories.AdminRepository;
//import com.hms.repositories.PatientRepository;

@Service
public class AdminServiceImplementation implements AdminService {
	
	@Autowired
	AdminRepository adminRepository;

    @Autowired
    private EmailValidatorService emailValidatorService;
    @Autowired
    private PasswordService passwordService;
	
	// Method to get Admin by email (mailId)
    public Admin getAdminByMailId(String mailId) {
        return adminRepository.findByMailId(mailId); // Return null if admin not found
    }

    // New method to check password with BCrypt
    public boolean checkPassword(Admin admin, String rawPassword) {
        if (admin == null || rawPassword == null) return false;
        return passwordService.matches(rawPassword, admin.getPassword());
    }

    // Méthode pour créer ou mettre à jour un admin avec hachage du mot de passe
    public Admin createOrUpdateAdmin(Admin admin) {
        if (admin == null || admin.getPassword() == null) {
            throw new IllegalArgumentException("Admin ou mot de passe manquant");
        }
        emailValidatorService.validate(admin.getMailId());
        checkEmailUniqueness(admin);
        admin.setPassword(passwordService.hashIfNeeded(admin.getPassword()));
        return adminRepository.save(admin);
    }

    // Vérification de l'unicité de l'email
    private void checkEmailUniqueness(Admin admin) {
        Admin existing = adminRepository.findByMailId(admin.getMailId());
        if (existing != null && (admin.getId() == null || !existing.getId().equals(admin.getId()))) {
            throw new EmailAlreadyUsedException("Email déjà utilisé");
        }
    }

    // Nouvelle méthode pour obtenir tous les admins
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
    
    public void deleteAdminById(Long id) {
        adminRepository.deleteById(id);
    }

}
package com.hms.services;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hms.entities.Admin;
import com.hms.exceptions.EmailAlreadyUsedException;
import com.hms.exceptions.InvalidEmailFormatException;
import com.hms.repositories.AdminRepository;
import com.hms.repositories.PatientRepository;

@Service
public class AdminServiceImplementation implements AdminService {
	
	@Autowired
	AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
	
	// Method to get Admin by email (mailId)
    public Admin getAdminByMailId(String mailId) {
        return adminRepository.findByMailId(mailId); // Return null if admin not found
    }

    // New method to check password with BCrypt
    public boolean checkPassword(Admin admin, String rawPassword) {
        if (admin == null || rawPassword == null) return false;
        return passwordEncoder.matches(rawPassword, admin.getPassword());
    }

    // Méthode pour créer ou mettre à jour un admin avec hachage du mot de passe
    public Admin createOrUpdateAdmin(Admin admin) {
        if (admin == null || admin.getPassword() == null) return null;
        // Vérification du format de l'email
        String email = admin.getMailId();
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (email == null || !Pattern.matches(emailRegex, email)) {
            throw new InvalidEmailFormatException("Vous devez saisir une adresse email valide");
        }
        // Vérification unicité de l'email (en création uniquement)
        Admin existing = adminRepository.findByMailId(email);
        if (existing != null && (admin.getId() == null || !existing.getId().equals(admin.getId())) ) {
            throw new EmailAlreadyUsedException("Email déjà utilisé");
        }
        // Hachage du mot de passe uniquement s'il n'est pas déjà haché
        String password = admin.getPassword();
        if (!password.startsWith("$2a$") && !password.startsWith("$2b$") && !password.startsWith("$2y$")) {
            admin.setPassword(passwordEncoder.encode(password));
        }
        return adminRepository.save(admin);
    }

    // Nouvelle méthode pour obtenir tous les admins
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
    
    public void deleteAdminById(Long id) {
        adminRepository.deleteById(id);
    }

}
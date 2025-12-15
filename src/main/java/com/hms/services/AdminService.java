package com.hms.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hms.entities.Admin;

@Service	
public interface AdminService {
	
	// Méthode pour obtenir un Admin par son email (mailId)
	Admin getAdminByMailId(String mailId);
	
    // Ajout de la méthode pour vérification du mot de passe avec BCrypt
    boolean checkPassword(Admin admin, String rawPassword);
    
    // Méthode pour créer ou mettre à jour un admin avec hachage du mot de passe
    Admin createOrUpdateAdmin(Admin admin);
    
    // Méthode pour lister tous les admins
    List<Admin> getAllAdmins();
    
    // Methode pour supprimer un admin par son ID
    void deleteAdminById(Long id);
}
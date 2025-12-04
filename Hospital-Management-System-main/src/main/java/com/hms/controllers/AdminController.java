package com.hms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hms.entities.Admin;
import com.hms.exceptions.EmailAlreadyUsedException;
import com.hms.exceptions.InvalidEmailFormatException;
import com.hms.services.AdminService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	// Admin Login
	@PostMapping("/adminlogin")
	public String login(
	        @RequestParam("mailId") String mailId,
	        @RequestParam("password") String password,
	        Model model, 
	        HttpSession session) 
	{
        // Nombre max de tentatives
        final int MAX_ATTEMPTS = 3;
        Integer attempts = (Integer) session.getAttribute("adminLoginAttempts");
        
        // Initialysing attempts to zero
        if (attempts == null) attempts = 0;

        // If ATTEMPS locked
        if (attempts >= MAX_ATTEMPTS) {
            model.addAttribute("loginError", "Compte bloqué après 3 tentatives. Veuillez réessayer plus tard.");
            model.addAttribute("attemptsLeft", 0);
            return "adminLogin";
        }

        // Get Admin by email
        Admin admin = adminService.getAdminByMailId(mailId);

        // Store the logged-in admin in the session
        session.setAttribute("loggedInAdmin", admin);

        Admin loggedInAdmin = (Admin) session.getAttribute("loggedInAdmin");

        // Validate admin credentials with BCrypt
        if (admin != null && adminService.checkPassword(admin, password)) {
            // Successful login
            session.removeAttribute("adminLoginAttempts"); // reset tentatives
            return "adminDashboard"; // Redirect to the admin dashboard
        } else {
            attempts++;
            session.setAttribute("adminLoginAttempts", attempts);
            int attemptsLeft = Math.max(0, MAX_ATTEMPTS - attempts);
            if (attempts >= MAX_ATTEMPTS) {
                model.addAttribute("loginError", "Compte bloqué après 3 tentatives. Veuillez réessayer plus tard.");
            } else {
                String msg = attemptsLeft + " tentatives restantes";
                model.addAttribute("loginError", msg);
            }
            model.addAttribute("attemptsLeft", attemptsLeft);
            return "adminLogin"; // Redirect back to login page
        }
	}
	
	//Log out
    @GetMapping("/adminlogout")
    public String logout(HttpSession session) {
        // Invalidate the session
        session.invalidate();

        // Redirect to the login page
        return "adminLogin";
    }

    // Admin Registration (secure with BCrypt)
    @PostMapping("/addAdmin")
    public String addAdmin(@RequestParam("mailId") String mailId,
                          @RequestParam("password") String password,
                          Model model) {
        try {
            Admin admin = new Admin();
            admin.setMailId(mailId);
            admin.setPassword(password); // Le service va hacher le mot de passe
            adminService.createOrUpdateAdmin(admin);
            model.addAttribute("message", "Admin created successfully!");
            return "adminLogin"; // Redirige vers la page de login admin
        } catch (EmailAlreadyUsedException e) {
            model.addAttribute("errorType", "email-used");
            model.addAttribute("errorMessage", e.getMessage());
            return "addAdmin";
        } catch (InvalidEmailFormatException e) {
            model.addAttribute("errorType", "invalid-email");
            model.addAttribute("errorMessage", e.getMessage());
            return "addAdmin";
        }
    }

    // Affiche le formulaire d'ajout d'admin
    @GetMapping("/addAdmin")
    public String showAddAdminForm() {
        return "addAdmin";
    }

    // Affiche le formulaire de modification d'admin
    @GetMapping("/editAdmin")
    public String showEditAdminForm() {
        return "editAdmin";
    }

    // Traite la recherche et la modification d'admin
    @PostMapping("/editAdmin")
    public String searchAdminForEdit(@RequestParam("mailId") String mailId, Model model) {
        Admin admin = adminService.getAdminByMailId(mailId);
        if (admin != null) {
            model.addAttribute("admin", admin);
        } else {
            model.addAttribute("message", "Admin introuvable.");
        }
        return "editAdmin";
    }

    @PostMapping("/updateAdmin")
    public String updateAdmin(@RequestParam("id") Long id,
                             @RequestParam("mailId") String mailId,
                             @RequestParam("password") String password,
                             Model model) {
        Admin admin = new Admin();
        admin.setId(id);
        admin.setMailId(mailId);
        admin.setPassword(password); // Le service va hacher le mot de passe
        adminService.createOrUpdateAdmin(admin);
        model.addAttribute("message", "Admin modifié avec succès !");
        return "editAdmin";
    }

    // Affiche la liste des admins
    @GetMapping("/listAdmins")
    public String showAdminList(Model model) {
        model.addAttribute("admins", adminService.getAllAdmins());
        return "listAdmins";
    }

    // Affiche le formulaire de suppression d'admin
    @GetMapping("/deleteAdmin")
    public String showDeleteAdminForm() {
        return "deleteAdmin";
    }

    // Traite la suppression d'un admin
    @PostMapping("/deleteAdmin")
    public String deleteAdmin(@RequestParam("mailId") String mailId, Model model) {
        Admin admin = adminService.getAdminByMailId(mailId);
        if (admin != null) {
            adminService.deleteAdminById(admin.getId());
            model.addAttribute("message", "Admin supprimé avec succès !");
        } else {
            model.addAttribute("message", "Admin introuvable.");
        }
        return "deleteAdmin";
    }

    // Raz (debug) : réinitialise le compteur de tentatives de login admin
    @GetMapping("/admin/razAttempts")
    public String razAttempts(HttpSession session, Model model) {
        session.removeAttribute("adminLoginAttempts");
        model.addAttribute("message", "Compteur de tentatives réinitialisé.");
        return "redirect:/";
    }
}
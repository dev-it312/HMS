 package com.hms.controllers;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

import com.hms.entities.Receptionist;
import com.hms.services.ReceptionistService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReceptionistController {

    @Autowired
    private ReceptionistService receptionistService;

    @PostMapping("/registerReceptionist")
    public String registerReceptionist(
            @RequestParam("name") String name,
            @RequestParam("dob") String dob,  // Accepting as a string, later converted to LocalDate
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("qualification") String qualification,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("address") String address,
            Model model) {

        LocalDate dateOfBirth = LocalDate.parse(dob);  // Convert String to LocalDate
        Receptionist receptionist = new Receptionist(name, dateOfBirth, email, password, qualification, 
                                                     phoneNumber, address);
        receptionistService.saveReceptionist(receptionist);
        model.addAttribute("message", "Receptionist registered successfully!");
        return "success";  // Replace with your success page
    }
    
 
    @PostMapping("/receptionist/login")
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            Model model, HttpSession session) {

        Receptionist receptionist = receptionistService.getReceptionistByEmail(email);

        if (receptionist != null && receptionist.getPassword().equals(password)) {
            session.setAttribute("receptionist", receptionist);
            return "receptionistDashboard";
        } else {
            model.addAttribute("error", "Invalid email or password. Please try again.");
            return "receptionistLogin"; // Correction du nom du template
        }
    }
    
 // Search Receptionist by ID
    @GetMapping("/searchReceptionist")
    public String searchReceptionist(@RequestParam("id") Long id, Model model) {
        Optional<Receptionist> receptionist = receptionistService.getReceptionistById(id);
        if (receptionist.isPresent()) {
            model.addAttribute("receptionist", receptionist.get());
        } else {
            model.addAttribute("error", "Receptionist not found");
        }
        return "editReceptionist"; // Returns the HTML page for updating receptionist details
    }

    // Update Receptionist Details
    @PostMapping("/updateReceptionist")
    public String updateReceptionistDetails(@RequestParam("id") Long id,
                                            @RequestParam("name") String name,
                                            @RequestParam("dob") LocalDate dob,
                                            @RequestParam("email") String email,
                                            @RequestParam("qualification") String qualification,
                                            @RequestParam("phoneNumber") String phoneNumber,
                                            @RequestParam("address") String address,
                                            RedirectAttributes redirectAttributes) {
        boolean success = receptionistService.updateReceptionistDetails(id, name, dob, email, qualification, phoneNumber, address);

        if (success) {
            redirectAttributes.addFlashAttribute("success", "Receptionist details updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Unable to update receptionist details. Please check the ID.");
        }
        return "editReceptionist"; // Redirect back to the modification page
    }
    
    
    @PostMapping("/removeReceptionist")
    public String removeReceptionist(@RequestParam("receptionist-id") Long receptionistId, 
                                      Model model) {
        boolean success = receptionistService.removeReceptionistById(receptionistId);
        if (success) {
            model.addAttribute("success", "Receptionist removed successfully!");
        } else {
            model.addAttribute("error", "Failed to remove the receptionist. Please check the ID.");
        }
        // Redirect to the page that handles receptionist removal
        return "removeReceptionist";
    }
    
  //Log out
    @GetMapping("/receptionistlogout")
    public String logout(HttpSession session) {
        // Invalidate the session
        session.invalidate();

        // Redirect to the login page
        return "/receptionistLogin";
    }

    
    
    
}
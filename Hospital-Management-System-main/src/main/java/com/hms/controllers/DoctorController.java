package com.hms.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hms.entities.Doctor;
import com.hms.entities.Patient;
import com.hms.services.DoctorService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping("/registerDoctor")
    public String registerDoctor(
            @RequestParam("name") String name,
            @RequestParam("dob") String dob, // Accepting as a string, later converted to LocalDate
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("qualification") String qualification,
            @RequestParam("specialisation") String specialisation,
            @RequestParam("yearsOfExperience") int yearsOfExperience,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("address") String address,
            Model model) {

        LocalDate dateOfBirth = LocalDate.parse(dob); // Convert String to LocalDate
        Doctor doctor = new Doctor(null, name, dateOfBirth, email, password, qualification, specialisation, yearsOfExperience, phoneNumber, address);
        model.addAttribute("message", "Doctor registered successfully!");
        return "success"; // Replace with your success page
    }
    
  //Doctor Login
    @PostMapping("/doctorlogin")
    public String login(
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("password") String password,
            Model model,HttpSession session) {
    	
    	
    	System.out.println(phoneNumber+"    "+password);
    	

        Doctor doctor = doctorService.getDoctorByPhoneNumber(phoneNumber);
        
        System.out.println("LOgin-doctor object"+doctor);
        
        session.setAttribute("loggedInDoctor", doctor);
        
        
        Doctor doc=(Doctor)session.getAttribute("loggedInDoctor");
        System.out.println(doc);
        

        // Validate patient credentials
        if (doctor != null && doctor.getPassword().equals(password)) {
            // Successful login
            return "doctorDashboard"; // Redirect to the patient dashboard
        } else {
            // Login failed
            model.addAttribute("error", "Invalid phone number or password. Please try again.");
            return "doctorLogin"; // Redirect back to login page
        }
    }
    
    
    
    
  //Log out
    @GetMapping("/doctorlogout")
    public String logout(HttpSession session) {
        // Invalidate the session
        session.invalidate();

        // Redirect to the login page
        return "doctorLogin";
    }
    
    
 // Handle Update
 // Search Doctor by ID
 @GetMapping("/searchDoctor")
 public String searchDoctor(@RequestParam("id") Long id, Model model) {
     Optional<Doctor> doctor = doctorService.getDoctorById(id);
     if (doctor.isPresent()) {
         model.addAttribute("doctor", doctor.get());
     } else {
         model.addAttribute("error", "Doctor not found");
     }
     return "editDoctor"; // Returns the HTML page for updating doctor information
 }

 // Update Doctor details
 @PostMapping("/updateDoctor")
 public String updateDoctorDetails(@RequestParam("doctor-id") Long doctorId,
                                   @RequestParam("name") String name,
                                   @RequestParam("dob") LocalDate dob,
                                   @RequestParam("email") String email,
                                   @RequestParam("qualification") String qualification,
                                   @RequestParam("specialisation") String specialisation,
                                   @RequestParam("yearsOfExperience") int yearsOfExperience,
                                   @RequestParam("phoneNumber") String phoneNumber,
                                   @RequestParam("address") String address,
                                   RedirectAttributes redirectAttributes) {
     boolean success = doctorService.updateDoctorDetails(doctorId, name, dob, email, qualification, specialisation, 
                                                         yearsOfExperience, phoneNumber, address);

     if (success) {
         redirectAttributes.addFlashAttribute("success", "Doctor details updated successfully!");
     } else {
         redirectAttributes.addFlashAttribute("error", "Unable to update doctor details. Please check the Doctor ID.");
     }
     return "editDoctor"; // Redirect back to the modification page
 }
 
	//Remove Doctor (POST Request)
	@PostMapping("/removeDoctor")
	public String removeDoctor(@RequestParam("doctor-id") Long doctorId, 
	                         Model redirectAttributes) {
	  boolean success = doctorService.removeDoctorById(doctorId);
	  if (success) {
	      redirectAttributes.addAttribute("success", "Doctor removed successfully!");
	  } else {
	      redirectAttributes.addAttribute("error", "Failed to remove the doctor. Please check the ID.");
	  }
	  // Redirect to a page that lists doctors or shows the remove doctor interface again
	  return "removeDoctor";
	}
	
	
	 // Get Doctor List
    @GetMapping("/viewDoctorList")
    public String viewDoctorList(Model model) {
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "viewDoctorList"; // Returns the HTML page name
    }





}

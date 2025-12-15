package com.hms.controllers;

import com.hms.entities.Patient;
import com.hms.entities.PatientReport;
import com.hms.services.PatientService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;
    
    
    
    
    

    @RequestMapping("/add")
    public String showAddPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "addNewPatient";  // Returning the view (addNewPatient.html)
    }

    @PostMapping("/submitNewPatient")
    public String registerPatient(@ModelAttribute Patient patient) {
    	 System.out.println("Date of Birth: " + patient.getDateOfBirth());

        // Check if the registration date is provided, otherwise set the current date
        if (patient.getDateOfRegistration() == null) {
            patient.setDateOfRegistration(LocalDate.now());
        }

        // Save the patient using the service
        patientService.registerNewPatient(patient);

        // Redirect to a success page or any other page after successful registration
        return "success"; // Redirect to a success page or another view
    }
    
    
    
    
  //Patient Login
    @PostMapping("/patientlogin")
    public String login(
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("password") String password,
            Model model,HttpSession session) {
    	
    	
    	System.out.println(phoneNumber+"    "+password);
    	

        Patient patient = patientService.getPatientByPhoneNumber(phoneNumber);
        
        System.out.println("LOgin-patient object"+patient);
        
        session.setAttribute("loggedInPatient", patient);
        
        
        

        // Validate patient credentials
        if (patient != null && patient.getPassword().equals(password)) {
            // Successful login
            return "patientDashboard"; // Redirect to the patient dashboard
        } else {
            // Login failed
            model.addAttribute("error", "Invalid phone number or password. Please try again.");
            return "patientLogin"; // Redirect back to login page
        }
    }
    
    
    
    
    
    
    
    
    //searching a patient
    @GetMapping("/searchPatient")
    public String searchPatient(@RequestParam("id") Long id, Model model,HttpSession session) {
        Optional<Patient> patient = patientService.findPatientById(id);
        if (patient.isPresent()) {
            model.addAttribute("patient", patient.get());
        } else {
            model.addAttribute("patient", null);
        }
        return "searchPatient"; // Refers to the HTML file name
    }
    
    
    
    
    
 // Handle Update
 /// Search patient by ID
    @GetMapping("/searchPatient1")
    public String searchPatient1(@RequestParam("id") Long id, Model model) {
        Optional<Patient> patient = patientService.getPatientById(id);
        if (patient.isPresent()) {
            model.addAttribute("patient", patient.get());
        } else {
            model.addAttribute("error", "Patient not found");
        }
        return "updatePatientInformation"; // Returns the HTML page for updating patient information
    }


    // Update patient details
    @PostMapping("/updatePatient")
    public String updatePatientDetails(@RequestParam("patient-id") Long patientId,
                                       @RequestParam("fullName") String fullName,
                                       @RequestParam("gender") String gender,
                                       @RequestParam("dateOfBirth") LocalDate dateOfBirth,
                                       @RequestParam("bloodGroup") String bloodGroup,
                                       @RequestParam("phoneNumber") String phoneNumber,
                                       @RequestParam("streetAddress") String streetAddress,
                                       @RequestParam("city") String city,
                                       @RequestParam("state") String state,
                                       @RequestParam("postalCode") String postalCode,
                                       @RequestParam("emergencyContactName") String emergencyContactName,
                                       @RequestParam("relationship") String relationship,
                                       @RequestParam("emergencyPhoneNumber") String emergencyPhoneNumber,
                                       RedirectAttributes redirectAttributes) {
        boolean success = patientService.updatePatientDetails(patientId, fullName, gender, dateOfBirth, bloodGroup,
                                                              phoneNumber, streetAddress, city, state, postalCode,
                                                              emergencyContactName, relationship, emergencyPhoneNumber);

        if (success) {
            redirectAttributes.addFlashAttribute("success", "Patient details updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Unable to update patient details. Please check the Patient ID.");
        }
        return "updatePatientInformation"; // Redirect back to the modification page
    }
    
    
    
    
    
    @PostMapping("/uploadPatientReports")
    public String uploadReports(@RequestParam("patientId") Long patientId,
                                @RequestParam("patientReports") MultipartFile[] files,
                                RedirectAttributes redirectAttributes) {
        try {
            // Call service method to handle file uploads
            patientService.uploadPatientReports(patientId, files);
            redirectAttributes.addFlashAttribute("success", "Reports uploaded successfully.");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Failed to upload reports.");
        }
        // Redirect to a confirmation page or back to the upload page
        return "uploadPatientReports";
    }
    
    
    
    
    
    @GetMapping("/getPatientReports")
    public String getReports(@RequestParam("patientId") Long patientId, Model model) {
        List<PatientReport> reports = patientService.getReportsByPatientId(patientId);
        model.addAttribute("allReports", reports);
        return "searchPatient"; // Returns the Thymeleaf template named "home.html"
    }
    
    
    
    
    //Delete Patient
    @PostMapping("/deletePatient")
    public String deletePatient(@RequestParam("patientId") Long patientId, Model model) {
        boolean isDeleted = patientService.deletePatientById(patientId);
        if (isDeleted) {
            model.addAttribute("message", "Patient record deleted successfully!");
        } else {
            model.addAttribute("message", "Patient record not found or could not be deleted.");
        }
        return "deletePatient"; // Redirect back to the delete page with the message
    }
    
    
    @GetMapping("/viewProfile")
    public String viewProfile(HttpSession session, Model model) {
        // Retrieve patient object from session
        Patient loggedInPatient = (Patient) session.getAttribute("loggedInPatient");
        
        System.out.println("viewProfile-session object"+loggedInPatient);

        if (loggedInPatient != null) {
            // Add patient details to the model
            model.addAttribute("patient", loggedInPatient);
            System.out.println("viewProfile-model object"+loggedInPatient);
            return "viewPatientProfile"; // Return the view for profile page
        } else {
            // If no patient is logged in, redirect to login page
            return "viewPatientProfile";
        }
    }
    
    @GetMapping("/viewPatientReports") 
    public String viewPatientReports(Model model, HttpSession session) {
        Patient patient = (Patient) session.getAttribute("loggedInPatient");

        if (patient == null) {
            // Redirect to login if no patient is logged in
            return "redirect:/patientLogin";
        }

        Long patientId = patient.getId();
        System.out.println("Reports - " + patient);
        System.out.println("Reports - patientId = " + patientId);

        List<PatientReport> reports = patientService.getReportsByPatientId(patientId);

        if (reports == null || reports.isEmpty()) {
        	
        	System.out.println("reports are null");
            model.addAttribute("message", "No reports found for the patient.");
        } else {
            model.addAttribute("allReports", reports);
        }

        return "viewPatientReports"; // Return the view
    }

    //Log out
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Invalidate the session
        session.invalidate();

        // Redirect to the login page
        return "/patientLogin";
    }


}







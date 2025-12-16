package com.hms.controllers;

import com.hms.entities.Doctor;
import com.hms.entities.Patient;
import com.hms.entities.Prescription;
import com.hms.entities.PrescriptionMedicine;
import com.hms.services.PrescriptionService;


import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping("/prescriptions/create")
    public String createPrescription(
            @RequestParam Long patientId,
            
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfIssue,
            @RequestParam List<String> medicineNames,
            @RequestParam List<String> tabletTimings,
            @RequestParam List<String> instructions,
            HttpSession session) {
    	
    	
    	Doctor doc=(Doctor)session.getAttribute("loggedInDoctor");
        System.out.println(doc);
        
        Long doctorId=doc.getId();
        System.out.println(doctorId);

        // Create Prescription
        Prescription prescription = new Prescription();
        prescription.setDateOfIssue(dateOfIssue);
        prescription.setDoctorId(doctorId);
        prescription.setPatientId(patientId);

        // Add Medicines
        List<PrescriptionMedicine> medicines = new ArrayList<>();
        for (int i = 0; i < medicineNames.size(); i++) {
            PrescriptionMedicine medicine = new PrescriptionMedicine();
            medicine.setPrescription(prescription);
            medicine.setMedicineName(medicineNames.get(i));
            medicine.setTabletTimings(tabletTimings.get(i));
            medicine.setInstructions(instructions.get(i));
            medicines.add(medicine);
        }
        prescription.setMedicines(medicines);

        // Save Prescription
        prescriptionService.savePrescription(prescription);
        
        

        return "generatePrescription";
    }
    
    @GetMapping("/prescriptions")
    public String showPrescriptions(@RequestParam("patientId") Long patientId, Model model) {
        List<Prescription> prescriptions = prescriptionService.getPrescriptionsByPatientId(patientId);
        model.addAttribute("prescriptions", prescriptions);
        return "showPreviousPrescriptions";
    }
    
    @GetMapping("/prescriptionsByPatient")
    public String showPrescriptionsByPatient(Model model,HttpSession session) {
    	
    	Patient patient = (Patient) session.getAttribute("loggedInPatient");
    	Long patientId = patient.getId();
    	
    	
        List<Prescription> prescriptions = prescriptionService.getPrescriptionsByPatientId(patientId);
        model.addAttribute("prescriptions", prescriptions);
        return "showPreviousPrescriptionsByPatient";
    }
    
    
}

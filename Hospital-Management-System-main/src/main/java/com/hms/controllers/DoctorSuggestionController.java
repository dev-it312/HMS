package com.hms.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hms.services.DoctorSuggestionService;

@Controller
public class DoctorSuggestionController {

    @Autowired
    private DoctorSuggestionService doctorSuggestionService;

    // Load the chatbot page
    @GetMapping("/doctor-chatbot")
    public String showChatbotPage() {
        return "doctorChatbot";
    }

    // Process the symptoms and suggest a doctor
    @PostMapping("/getDoctorSuggestion")
    public String getDoctorSuggestion(@RequestParam("symptoms") String symptoms, Model model) {
        String suggestion = doctorSuggestionService.getDoctorSuggestion(symptoms);
        model.addAttribute("suggestion", suggestion);
        return "index";
    }
}

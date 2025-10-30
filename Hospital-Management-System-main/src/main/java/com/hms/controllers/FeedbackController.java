package com.hms.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hms.entities.Feedback;
import com.hms.services.FeedbackService;
@Controller
public class FeedbackController {
	
	@Autowired
    private FeedbackService feedbackService;
	
	
	
	
	
	@PostMapping("/submit")
    public String submitFeedback(@ModelAttribute Feedback feedback) {
        feedback.setSubmissionDate(LocalDate.now());
        feedbackService.saveFeedback(feedback);
        return "feedback";
    }
	
	@GetMapping("/reviews")
    public String listFeedbacks(Model model) {
        model.addAttribute("feedbacks", feedbackService.getAllFeedbacks());
        return "reviews";
    }

}

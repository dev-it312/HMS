package com.hms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hms.entities.DoctorSchedule;
import com.hms.services.DoctorScheduleService;

@Controller
public class DoctorScheduleController {
    
    @Autowired
    private DoctorScheduleService doctorScheduleService;
    
    // Assign or Update Schedule (POST Request)
    @PostMapping("/assignUpdateSchedule")
    public String assignOrUpdateSchedule(@RequestParam("doctorId") Long doctorId,
                                         @RequestParam("day") String day,
                                         @RequestParam("fromTime") String fromTime,
                                         @RequestParam("toTime") String toTime,
                                         Model model) {
        
        boolean success = doctorScheduleService.assignOrUpdateSchedule(doctorId, day, fromTime, toTime);
        
        if (success) {
            model.addAttribute("success", "Schedule assigned/updated successfully!");
        } else {
            model.addAttribute("error", "Failed to assign/update the schedule. Please check the Doctor ID and the inputs.");
        }
        
        return "asignOrUpdateSchedules"; // Redirect to the same page for a fresh start
    }
    
    
 // Display all doctor schedules
    @GetMapping("/doctorSchedules")
    public String showDoctorSchedules(Model model) {
        List<DoctorSchedule> schedules = doctorScheduleService.getAllDoctorSchedules();
        model.addAttribute("schedules", schedules);
        return "doctorSchedules";
    }
}

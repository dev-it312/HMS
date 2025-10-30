package com.hms.controllers;

import com.hms.entities.Appointment;
import com.hms.entities.Doctor;
import com.hms.entities.Patient;
import com.hms.services.AppointmentService;
import com.hms.services.DoctorService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    // To show the appointment booking page with all doctors
    @GetMapping("/bookNewAppointment")
    public String showBookingPage(Model model) {
        // Load all doctors to display on the booking page
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "bookNewAppointment"; // Render the booking page (bookNewAppointment.html)
    }

    // To view the list of doctors and render it on the same page
 // To view the list of doctors and render it on the same page
    @GetMapping("/viewDoctorsList")
    public String viewDoctorsList(Model model) {
        // Fetch the list of all doctors and pass it to the model
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "bookNewAppointment"; // Return to the same page (bookNewAppointment.html) with the doctor list
    }


    // To check the doctor's availability for a specific date
    @PostMapping("/checkAvailability")
    public String checkAvailability(@RequestParam("doctor-id") Long doctorId, 
                                     @RequestParam("appointment-date") String dateStr, 
                                     Model model) {
        // Convert the date string to LocalDate
        LocalDate date = LocalDate.parse(dateStr);

        // Get appointments for the specified doctor on the given date
        List<Appointment> appointments = appointmentService.getAppointmentsForDoctor(doctorId, date);

        // Add available appointments to the model
        model.addAttribute("appointments", appointments);
        model.addAttribute("doctorId", doctorId);
        model.addAttribute("appointmentDate", date);

        // Return the updated booking page with available time slots
        return "bookNewAppointment"; 
    }

    // To book an appointment by Receptionist
    @PostMapping("/bookAppointment")
    public String bookAppointment(@RequestParam("fullname") String fullname,
                                  @RequestParam("phone") String phone,
                                  @RequestParam("doctor-id") Long doctorId,
                                  @RequestParam("patient-id") Long patientId,
                                  @RequestParam("appointment-date") String dateStr,
                                  @RequestParam("appointment-time") String timeStr,
                                  @RequestParam("reason") String reason,
                                  Model model) {
        // Convert the date and time to the correct formats
        LocalDate appointmentDate = LocalDate.parse(dateStr);
        LocalTime appointmentTime = LocalTime.parse(timeStr);

        // Create the appointment entity
        Appointment appointment = new Appointment(fullname, phone, appointmentDate, appointmentTime, reason, doctorId, patientId);
        
        System.out.println("appointment "+appointment);

        // Save the appointment in the database
        appointmentService.saveAppointment(appointment);

        // Add a success message to the model
        model.addAttribute("message", "Appointment successfully booked!");

        // Return a success page to confirm the appointment booking
        return "bookNewappointment";  
    }
    
 // To book an appointment by Patient
    @PostMapping("/bookAppointmentByPatient")
    public String bookAppointmentByPatient(@RequestParam("fullname") String fullname,
                                  @RequestParam("phone") String phone,
                                  @RequestParam("doctor-id") Long doctorId,
                                  @RequestParam("appointment-date") String dateStr,
                                  @RequestParam("appointment-time") String timeStr,
                                  @RequestParam("reason") String reason,
                                  Model model,HttpSession session) {
        // Convert the date and time to the correct formats
        LocalDate appointmentDate = LocalDate.parse(dateStr);
        LocalTime appointmentTime = LocalTime.parse(timeStr);
        
        Patient patient = (Patient) session.getAttribute("loggedInPatient");
        Long patientId = patient.getId();

        // Create the appointment entity
        Appointment appointment = new Appointment(fullname, phone, appointmentDate, appointmentTime, reason, doctorId, patientId);
        
        System.out.println("appointment "+appointment);

        // Save the appointment in the database
        appointmentService.saveAppointment(appointment);

        // Add a success message to the model
        model.addAttribute("message", "Appointment successfully booked!");

        // Return a success page to confirm the appointment booking
        return "bookAppointmentByPatient";  
    }
    
 // To view the list of doctors and render it on the same page
    // To view the list of doctors and render it on the same page
       @GetMapping("/viewDoctorsListByPatient")
       public String viewDoctorsListByPatient(Model model) {
           // Fetch the list of all doctors and pass it to the model
           List<Doctor> doctors = doctorService.getAllDoctors();
           model.addAttribute("doctors", doctors);
           return "bookAppointmentByPatient"; // Return to the same page (bookNewAppointment.html) with the doctor list
       }


       // To check the doctor's availability for a specific date
       @PostMapping("/checkAvailabilityByPatient")
       public String checkAvailabilityByPatient(@RequestParam("doctor-id") Long doctorId, 
                                        @RequestParam("appointment-date") String dateStr, 
                                        Model model) {
           // Convert the date string to LocalDate
           LocalDate date = LocalDate.parse(dateStr);

           // Get appointments for the specified doctor on the given date
           List<Appointment> appointments = appointmentService.getAppointmentsForDoctor(doctorId, date);

           // Add available appointments to the model
           model.addAttribute("appointments", appointments);
           model.addAttribute("doctorId", doctorId);
           model.addAttribute("appointmentDate", date);

           // Return the updated booking page with available time slots
           return "bookAppointmentByPatient"; 
       }
       
       
       
       @PostMapping("/viewSchedules")
       public String viewSchedules(  @RequestParam("appointment-date") String dateStr, 
                                        Model model,HttpSession session) {
           // Convert the date string to LocalDate
           LocalDate date = LocalDate.parse(dateStr);
           
           
           Doctor doctor = (Doctor) session.getAttribute("loggedInDoctor");
           
           Long doctorId = doctor.getId();

           // Get appointments for the specified doctor on the given date
           List<Appointment> appointments = appointmentService.getAppointmentsForDoctor(doctorId, date);
           System.out.println(appointments);

           // Add available appointments to the model
           model.addAttribute("appointments", appointments);
           model.addAttribute("doctorId", doctorId);
           model.addAttribute("appointmentDate", date);

           // Return the updated booking page with available time slots
           return "viewSchedules"; 
       }
    
    //modify appoinment controlling
    @GetMapping("/searchAppointment")
    public String searchAppointment(@RequestParam("appointmentId") Long appointmentId, Model model) {
        // Fetch appointment details using appointmentId
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);

        if (appointment != null) {
            model.addAttribute("appointment", appointment);
        } else {
            model.addAttribute("error", "Appointment not found!");
        }
        
        System.out.println(appointment);
        return "modifyAppointment"; // Thymeleaf page to display results
    }

    
 // To display the Modify Appointment page with details
    @GetMapping("/modifyAppointment/{appointmentId}")
    public String showModifyAppointmentForm(@PathVariable Long appointmentId, Model model) {
        // Step 1: Retrieve the appointment from the service or database
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);

        // Step 2: Print the appointment object to the console (for debugging)
        System.out.println("Appointment: " + appointment); // This will print the appointment object to the console

        // Step 3: Add the appointment object to the model
        model.addAttribute("appointment", appointment);

        // Return the view name, so Thymeleaf knows which page to render
        return "modifyAppointment"; // Make sure the view is named 'modifyAppointment.html'
    }



    // To change the doctor for the existing appointment
    @PostMapping("/changeDoctor")
    public String changeDoctor(@RequestParam("appointment-id") Long appointmentId,
                               @RequestParam("new-doctor-id") Long newDoctorId,
                               RedirectAttributes redirectAttributes) {
        boolean success = appointmentService.changeDoctor(appointmentId, newDoctorId);

        if (success) {
            redirectAttributes.addFlashAttribute("success", "Doctor changed successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Unable to change doctor. Please check the Doctor ID.");
        }
        return "modifyAppointment"; // Redirect back to the details page
    }


 // Handle Reschedule Appointment (Post Request)
    @PostMapping("/rescheduleAppointment")
    public String rescheduleAppointment(@RequestParam("appointment-id") Long appointmentId,
                                        @RequestParam("new-date") String newDate,
                                        @RequestParam("new-time") String newTime,
                                        RedirectAttributes redirectAttributes) {
        try {
            // Convert string date and time to LocalDate and LocalTime
            LocalDate date = LocalDate.parse(newDate); // Converts string to LocalDate
            LocalTime time = LocalTime.parse(newTime); // Converts string to LocalTime

            boolean success = appointmentService.rescheduleAppointment(appointmentId, date, time);

            if (success) {
                redirectAttributes.addFlashAttribute("success", "Appointment rescheduled successfully!");
            } else {
                redirectAttributes.addFlashAttribute("error", "Failed to reschedule the appointment.");
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Invalid date or time format.");
        }

        // Redirect back to modify appointment page
        return "modifyAppointment";
    }

 // Cancel Appointment (POST Request)
    @PostMapping("/cancelAppointment")
    public String cancelAppointment(@RequestParam("appointment-id") Long appointmentId, 
                                    RedirectAttributes redirectAttributes) {
        boolean success = appointmentService.cancelAppointment(appointmentId);
        if (success) {
            redirectAttributes.addFlashAttribute("success", "Appointment canceled successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Failed to cancel the appointment.");
        }
        // Redirect to a page that lists appointments or details
        return "modifyAppointment"; // or any other page you'd like to redirect
    }
    
    @PostMapping("/viewAppointments")
    public String viewAppointments(@RequestParam("doctor-id") Long doctorId, 
                                     @RequestParam("appointment-date") String dateStr, 
                                     Model model) {
        // Convert the date string to LocalDate
        LocalDate date = LocalDate.parse(dateStr);

        // Get appointments for the specified doctor on the given date
        List<Appointment> appointments = appointmentService.getAppointmentsForDoctor(doctorId, date);

        // Add available appointments to the model
        model.addAttribute("appointments", appointments);
        model.addAttribute("doctorId", doctorId);
        model.addAttribute("appointmentDate", date);

        // Return the updated booking page with available time slots
        return "viewAppointments"; 
    }
    
    
    @GetMapping("/appointmentHistory")
    public String viewAppointmentHistory(HttpSession session, Model model) {
    	Patient patient = (Patient) session.getAttribute("loggedInPatient");
    	
        Long patientId = patient.getId();
        if (patientId != null) {
            List<Appointment> appointments = appointmentService.getAppointmentHistory(patientId);
            model.addAttribute("appointments", appointments);
        }
        return "appointmentHistory";
    }
    
 // Modify Appointment By Patient
    

        @GetMapping("/searchAppointmentByPatient")
        public String searchAppointment(@RequestParam("appointmentId") Long appointmentId, HttpSession session, Model model) {
            Patient patient = (Patient) session.getAttribute("loggedInPatient");
        	
        	Long patientId = patient.getId();
            if (patientId == null) {
                model.addAttribute("error", "No patient is logged in.");
                return "errorPage"; // Replace with appropriate error page
            }

            Appointment appointment = appointmentService.getAppointmentByPatientAndId(patientId, appointmentId);

            if (appointment != null) {
                model.addAttribute("appointment", appointment);
            } else {
                model.addAttribute("error", "Appointment not found for this patient.");
            }

            return "modifyAppointmentByPatient";
        }

        @GetMapping("/modifyAppointmentByPatient")
        public String showModifyAppointmentForm(@PathVariable Long appointmentId, HttpSession session, Model model) {
        	Patient patient = (Patient) session.getAttribute("loggedInPatient");
        	
        	Long patientId = patient.getId();
            if (patientId == null) {
                model.addAttribute("error", "No patient is logged in.");
                return "errorPage"; // Replace with appropriate error page
            }

            Appointment appointment = appointmentService.getAppointmentByPatientAndId(patientId, appointmentId);

            if (appointment != null) {
                model.addAttribute("appointment", appointment);
            } else {
                model.addAttribute("error", "Appointment not found for this patient.");
            }

            return "modifyAppointmentByPatient";
        }

        @PostMapping("/changeDoctorByPatient")
        public String changeDoctor(@RequestParam("appointment-id") Long appointmentId,
                                   @RequestParam("new-doctor-id") Long newDoctorId,
                                   HttpSession session,
                                   RedirectAttributes redirectAttributes) {
        	Patient patient = (Patient) session.getAttribute("loggedInPatient");
        	
        	Long patientId = patient.getId();
            if (patientId == null) {
                redirectAttributes.addFlashAttribute("error", "No patient is logged in.");
                return "redirect:/errorPage"; // Replace with appropriate error page
            }

            boolean success = appointmentService.changeDoctor(patientId, appointmentId, newDoctorId);

            if (success) {
                redirectAttributes.addFlashAttribute("success", "Doctor changed successfully!");
            } else {
                redirectAttributes.addFlashAttribute("error", "Unable to change doctor. Please check the Doctor ID.");
            }
            return "modifyAppointmentByPatient" ;
        }

        @PostMapping("/rescheduleAppointmentByPatient")
        public String rescheduleAppointment(@RequestParam("appointment-id") Long appointmentId,
                                            @RequestParam("new-date") String newDate,
                                            @RequestParam("new-time") String newTime,
                                            HttpSession session,
                                            RedirectAttributes redirectAttributes) {
        	Patient patient = (Patient) session.getAttribute("loggedInPatient");
        	
        	Long patientId = patient.getId();
            if (patientId == null) {
                redirectAttributes.addFlashAttribute("error", "No patient is logged in.");
                return "redirect:/errorPage"; // Replace with appropriate error page
            }

            try {
                LocalDate date = LocalDate.parse(newDate);
                LocalTime time = LocalTime.parse(newTime);

                boolean success = appointmentService.rescheduleAppointment(patientId, appointmentId, date, time);

                if (success) {
                    redirectAttributes.addFlashAttribute("success", "Appointment rescheduled successfully!");
                } else {
                    redirectAttributes.addFlashAttribute("error", "Failed to reschedule the appointment.");
                }

            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "Invalid date or time format.");
            }

            return "modifyAppointmentByPatient" ;
        }

        @PostMapping("/cancelAppointmentByPatient")
        public String cancelAppointment(@RequestParam("appointment-id") Long appointmentId, 
                                        HttpSession session,
                                        RedirectAttributes redirectAttributes) {
        	Patient patient = (Patient) session.getAttribute("loggedInPatient");
        	
        	Long patientId = patient.getId();
            if (patientId == null) {
                redirectAttributes.addFlashAttribute("error", "No patient is logged in.");
                ; // Replace with appropriate error page
            }

            boolean success = appointmentService.cancelAppointment(patientId, appointmentId);
            if (success) {
                redirectAttributes.addFlashAttribute("success", "Appointment canceled successfully!");
            } else {
                redirectAttributes.addFlashAttribute("error", "Failed to cancel the appointment.");
            }

            return "modifyAppointmentByPatient"; // Redirect to the appointment list page
        }
    

}

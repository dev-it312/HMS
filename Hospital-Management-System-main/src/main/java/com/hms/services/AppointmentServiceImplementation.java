package com.hms.services;

import com.hms.entities.Appointment;
import com.hms.repositories.AppointmentRepository;
import com.hms.repositories.DoctorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImplementation implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAppointmentsForDoctor(Long doctorId, LocalDate date) {
        return appointmentRepository.findByDoctorIdAndAppointmentDate(doctorId, date);
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        return appointment.orElse(null);  // Return the appointment or null if not found
    }

    

    
    
    // Change Doctor Logic
    public boolean changeDoctor(Long appointmentId, Long newDoctorId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);
        if (appointment != null && doctorRepository.existsById(newDoctorId)) {
            appointment.setDoctorId(newDoctorId);
            appointmentRepository.save(appointment);
            return true;
        }
        return false;
    }
    
 // Reschedule Appointment Logic
    public boolean rescheduleAppointment(Long appointmentId, LocalDate newDate, LocalTime newTime) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);
        if (appointment != null) {
            // Update the appointment date and time
            appointment.setAppointmentDate(newDate);
            appointment.setAppointmentTime(newTime);

            // Save the updated appointment to the database
            appointmentRepository.save(appointment);
            return true; // Success
        }
        return false; // Appointment not found
    }
    
 // Cancel Appointment Logic (Soft Delete or Mark Status as Cancelled)
    public boolean cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElse(null);
        if (appointment != null) {
            // Mark appointment as canceled (you could also choose to delete the appointment from DB)
            // For example, you could add a 'status' field in the Appointment class: 'CANCELLED'
            // appointment.setStatus("CANCELLED");
            appointmentRepository.delete(appointment); // Or save with status update
            return true; // Appointment canceled
        }
        return false; // Appointment not found
    }
    
    public List<Appointment> getAppointmentHistory(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }
    
    //Modify Appointment By Patient
    //
    

        public Appointment getAppointmentByPatientAndId(Long patientId, Long appointmentId) {
            return appointmentRepository.findByPatientIdAndId(patientId, appointmentId).orElse(null);
        }

        public boolean changeDoctor(Long patientId, Long appointmentId, Long newDoctorId) {
            Appointment appointment = getAppointmentByPatientAndId(patientId, appointmentId);
            if (appointment != null) {
                appointment.setDoctorId(newDoctorId);
                appointmentRepository.save(appointment);
                return true;
            }
            return false;
        }

        public boolean rescheduleAppointment(Long patientId, Long appointmentId, LocalDate newDate, LocalTime newTime) {
            Appointment appointment = getAppointmentByPatientAndId(patientId, appointmentId);
            if (appointment != null) {
                appointment.setAppointmentDate(newDate);
                appointment.setAppointmentTime(newTime);
                appointmentRepository.save(appointment);
                return true;
            }
            return false;
        }

        public boolean cancelAppointment(Long patientId, Long appointmentId) {
            Appointment appointment = getAppointmentByPatientAndId(patientId, appointmentId);
            if (appointment != null) {
                appointmentRepository.delete(appointment);
                return true;
            }
            return false;
        }
    


}

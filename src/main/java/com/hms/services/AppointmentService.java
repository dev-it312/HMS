package com.hms.services;

import com.hms.entities.Appointment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentService {
    Appointment saveAppointment(Appointment appointment);
    List<Appointment> getAppointmentsForDoctor(Long doctorId, LocalDate date);
    Appointment getAppointmentById(Long id);
    
	boolean changeDoctor(Long appointmentId, Long newDoctorId);
	boolean rescheduleAppointment(Long appointmentId, LocalDate date, LocalTime time);
	boolean cancelAppointment(Long appointmentId);
	List<Appointment> getAppointmentHistory(Long patientId);
	Appointment getAppointmentByPatientAndId(Long patientId, Long appointmentId);
	boolean changeDoctor(Long patientId, Long appointmentId, Long newDoctorId);
	boolean rescheduleAppointment(Long patientId, Long appointmentId, LocalDate date, LocalTime time);
	boolean cancelAppointment(Long patientId, Long appointmentId);
}

package com.hms.entities;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String patientName;
    private String phoneNumber;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private String reason;
    private Long doctorId;
    private Long patientId;
    

    public Appointment() {}

    public Appointment(String patientName, String phoneNumber, LocalDate appointmentDate, 
                       LocalTime appointmentTime, String reason, Long doctorId,Long patientId) {
        this.patientName = patientName;
        this.phoneNumber = phoneNumber;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.reason = reason;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
    

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", patientName=" + patientName + ", phoneNumber=" + phoneNumber
				+ ", appointmentDate=" + appointmentDate + ", appointmentTime=" + appointmentTime + ", reason=" + reason
				+ ", doctorId=" + doctorId + ", patientId=" + patientId + "]";
	}

	
	
    
}

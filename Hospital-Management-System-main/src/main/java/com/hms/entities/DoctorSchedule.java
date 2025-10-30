package com.hms.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DoctorSchedule {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    @JsonBackReference // Prevents infinite recursion
    private Doctor doctor;
    
    private String day;
    
    private String fromTime;
    
    private String toTime;
    
    @Override
	public String toString() {
		return "DoctorSchedule [id=" + id + ", doctor=" + doctor + ", day=" + day + ", fromTime=" + fromTime
				+ ", toTime=" + toTime + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getFromTime() {
		return fromTime;
	}

	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	public String getToTime() {
		return toTime;
	}

	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	public DoctorSchedule(Long id, Doctor doctor, String day, String fromTime, String toTime) {
		super();
		this.id = id;
		this.doctor = doctor;
		this.day = day;
		this.fromTime = fromTime;
		this.toTime = toTime;
	}

	public DoctorSchedule() {
		super();
		// TODO Auto-generated constructor stub
	}

	
    
    
}


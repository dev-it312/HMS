package com.hms.entities;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String doctorName;

    @Column(nullable = false)
    private LocalDate appointmentDate;

    @Column(nullable = false)
    private String rating;

    @Column(length = 1000)
    private String comments;

    @Column(nullable = false)
    private LocalDate submissionDate;

	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Feedback(Long id, String doctorName, LocalDate appointmentDate, String rating, String comments,
			LocalDate submissionDate) {
		super();
		this.id = id;
		this.doctorName = doctorName;
		this.appointmentDate = appointmentDate;
		this.rating = rating;
		this.comments = comments;
		this.submissionDate = submissionDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public LocalDate getSubmissionDate() {
		return submissionDate;
	}

	public void setSubmissionDate(LocalDate submissionDate) {
		this.submissionDate = submissionDate;
	}

	@Override
	public String toString() {
		return "Feedback [id=" + id + ", doctorName=" + doctorName + ", appointmentDate=" + appointmentDate
				+ ", rating=" + rating + ", comments=" + comments + ", submissionDate=" + submissionDate + "]";
	}
    
    

    
}

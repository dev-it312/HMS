package com.hms.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date dateOfIssue;

    @Column(nullable = false)
    private Long doctorId;

    @Column(nullable = false)
    private Long patientId;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PrescriptionMedicine> medicines;
    
    

    public Prescription(Long id, Date dateOfIssue, Long doctorId, Long patientId,
			List<PrescriptionMedicine> medicines) {
		super();
		this.id = id;
		this.dateOfIssue = dateOfIssue;
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.medicines = medicines;
	}

	// Getters and Setters

    public Prescription() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
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

    public List<PrescriptionMedicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<PrescriptionMedicine> medicines) {
        this.medicines = medicines;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "id=" + id +
                ", dateOfIssue=" + dateOfIssue +
                ", doctorId=" + doctorId +
                ", patientId=" + patientId +
                ", medicines=" + medicines +
                '}';
    }
}

package com.hms.entities;

import java.util.Base64;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class PatientReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Patient patient;

    private String fileName;
    private String fileType;

    @Lob
    @Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "LONGBLOB")
    private byte[] patientReports;  // Renamed field to 'patientReports' instead of 'data'
    
    public String getPatientReportsBase64() {
        if (patientReports == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(patientReports);
    }


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getPatientReports() {
        return patientReports;
    }

    public void setPatientReports(byte[] patientReports) {
        this.patientReports = patientReports;
    }
}


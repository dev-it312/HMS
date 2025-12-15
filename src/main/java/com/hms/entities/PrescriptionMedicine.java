package com.hms.entities;



import jakarta.persistence.*;

@Entity
public class PrescriptionMedicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "prescription_id", nullable = false)
    private Prescription prescription;

    @Column(nullable = false)
    private String medicineName;

    @Column(nullable = false)
    private String tabletTimings;

    @Column(nullable = false)
    private String instructions;

	public PrescriptionMedicine(Long id, Prescription prescription, String medicineName, String tabletTimings,
			String instructions) {
		super();
		this.id = id;
		this.prescription = prescription;
		this.medicineName = medicineName;
		this.tabletTimings = tabletTimings;
		this.instructions = instructions;
	}

	public PrescriptionMedicine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getTabletTimings() {
		return tabletTimings;
	}

	public void setTabletTimings(String tabletTimings) {
		this.tabletTimings = tabletTimings;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	@Override
	public String toString() {
		return "PrescriptionMedicine [id=" + id + ", prescription=" + prescription + ", medicineName=" + medicineName
				+ ", tabletTimings=" + tabletTimings + ", instructions=" + instructions + "]";
	}
    
    

    // Getters and Setters
}


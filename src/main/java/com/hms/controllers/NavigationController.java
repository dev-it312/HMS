package com.hms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller 
public class NavigationController {
	
	
	@GetMapping("/openAddNewDoctor")
	public String openAddNewDoctor() {
		return "addNewDoctor";
	}
	
	@GetMapping("/openAddNewReceptionist")
	public String openAddNewReceptionist() {
		return "addNewReceptionist";
	}
	
	@GetMapping("/openReceptionistLogIn")
	public String openReceptionistLogIn() {
		return "receptionistLogin";
	}
	
	@GetMapping("/openAppointmentManagement")
	public String openAppointmentManagement() {
		return "appointmentManagement";
	}
	
	@GetMapping("/openBookNewAppointment")
	public String openBookNewAppointment() {
		return "bookNewAppointment";
	}
	
	@GetMapping("/openModifyAppointment")
	public String openModifyAppointment() {
		return "modifyAppointment";
	}
	
	@GetMapping("/patientManagement")
	public String patientManagement() {
		return "patientManagement";
	}
	
	@GetMapping("/addNewPatient")
	public String addNewPatient() {
		return "addNewPatient";
	}
	
	@GetMapping("/openSearchPatient")
	public String openSearchPatient() {
		return "searchPatient";
	}
	
	
	@GetMapping("/openUpdatePatient")
	public String openUpdatePatient() {
		return "updatePatientInformation";
	
	}
	
	@GetMapping("/openUploadPatientReports")
	public String openUploadPatientReports() {
		return "uploadPatientReports";
	
	}
	
	@GetMapping("/openDeletePatient")
	public String openDeletePatient() {
		return "deletePatient";
	
	}
	
	@GetMapping("/openPatientLogin")
	public String openPatientLogin() {
		return "patientLogin";
	}
	
	@GetMapping("/openPatientInformationManagement")
	public String openPatientInformationManagement() {
		return "patientInformationManagement";
	}
	
	@GetMapping("/openViewPatientProfile")
	public String openViewPatientProfile() {
		return "viewPatientProfile";
	}
	
	@GetMapping("/openDoctorLogin")
	public String openDoctorLogin() {
		return "doctorLogin";
	}
	
	@GetMapping("/openPatientDetails")
	public String openPatientDetails() {
		return "patientDetails";
	}
	
	@GetMapping("/openPrescriptions")
	public String openPrescriptions() {
		return "prescriptions";
	}
	
	@GetMapping("/openGeneratePrescription")
	public String openGeneratePrescription() {
		return "generatePrescription";
	}
	
	@GetMapping("/openShowPreviousPrescriptions")
	public String openShowPreviousPrescriptions() {
		return "showPreviousPrescriptions";
	}
	
	@GetMapping("/openAdminLogin")
	public String openAdminLogin() {
		return "adminLogin";
	}
	
	@GetMapping("/openAddStaff")
	public String openAddStaff() {
		return "addStaff";
	}
	
	@GetMapping("/openStaffManagement")
	public String openStaffManagement() {
		return "staffManagement";
	}
	
	@GetMapping("/openViewOrEditStaffDetails")
	public String openViewOrEditStaffDetails() {
		return "viewOrEditStaffDetails";
	}
	
	@GetMapping("/openEditDoctor")
	public String openEditDoctor() {
		return "editDoctor";
	}
	
	
	@GetMapping("/openEditReceptionist")
	public String openEditReceptionist() {
		return "editReceptionist";
	}
	
	@GetMapping("/openRemoveStaff")
	public String openRemoveStaff() {
		return "removeStaff";
	}
	
	@GetMapping("/openRemoveDoctor")
	public String openRemoveDoctor() {
		return "removeDoctor";
	}
	
	@GetMapping("/openRemoveReceptionist")
	public String openRemoveReceptionist() {
		return "removeReceptionist";
	}
	
	@GetMapping("/openDoctorManagement")
	public String openDoctorManagement() {
		return "doctorManagement";
	}
	
	@GetMapping("/openAsignOrUpdateSchedules")
	public String openAsignOrUpdateSchedules() {
		return "asignOrUpdateSchedules";
	}
	
	
	
	@GetMapping("/openViewAppointments")
	public String openViewAppointments() {
		return "viewAppointments";
	}
	
	@GetMapping("/openAppointmentManagementByPatient")
	public String openAppointmentManagementByPatient() {
		return "appointmentManagementByPatient";
	}
	
	
	
	@GetMapping("/openBookAppointmentByPatient")
	public String openBookAppointmentByPatient() {
		return "bookAppointmentByPatient";
	}
	
	@GetMapping("/openFeedback")
	public String openFeedback() {
		return "feedback";
	}
	
	
	@GetMapping("/openReviews")
	public String openReviews() {
		return "reviews";
	}
	
	@GetMapping("/openViewSchedules")
	public String openViewSchedules() {
		return "viewSchedules";
	}
	

	
	
	
	
	
}


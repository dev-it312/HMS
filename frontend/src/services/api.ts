import axios from 'axios';

const API_BASE_URL = '';  // Le backend Spring Boot tourne sur le même domaine

export interface LoginCredentials {
  mailId: string;
  password: string;
}

export interface Patient {
  firstName: string;
  lastName: string;
  mailId: string;
  contactNumber: string;
  age: number;
  gender: string;
  address: string;
  password?: string;
}

export interface Doctor {
  firstName: string;
  lastName: string;
  mailId: string;
  contactNumber: string;
  specialization: string;
  experience: number;
  password?: string;
}

class ApiService {
  // Auth
  async loginAdmin(credentials: LoginCredentials) {
    const formData = new URLSearchParams();
    formData.append('mailId', credentials.mailId);
    formData.append('password', credentials.password);
    
    return await axios.post(`${API_BASE_URL}/adminlogin`, formData, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
    });
  }

  async loginDoctor(credentials: LoginCredentials) {
    const formData = new URLSearchParams();
    formData.append('mailId', credentials.mailId);
    formData.append('password', credentials.password);
    
    return await axios.post(`${API_BASE_URL}/doctorlogin`, formData, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
    });
  }

  async loginPatient(credentials: LoginCredentials) {
    const formData = new URLSearchParams();
    formData.append('mailId', credentials.mailId);
    formData.append('password', credentials.password);
    
    return await axios.post(`${API_BASE_URL}/patientlogin`, formData, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
    });
  }

  async loginReceptionist(credentials: LoginCredentials) {
    const formData = new URLSearchParams();
    formData.append('mailId', credentials.mailId);
    formData.append('password', credentials.password);
    
    return await axios.post(`${API_BASE_URL}/receptionistlogin`, formData, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
    });
  }

  async logoutAdmin() {
    return await axios.post(`${API_BASE_URL}/adminlogout`);
  }

  async logoutDoctor() {
    return await axios.post(`${API_BASE_URL}/doctorlogout`);
  }

  async logoutPatient() {
    return await axios.post(`${API_BASE_URL}/patientlogout`);
  }

  async logoutReceptionist() {
    return await axios.post(`${API_BASE_URL}/receptionistlogout`);
  }

  // Patient Management
  async addPatient(patient: Patient) {
    const formData = new URLSearchParams();
    Object.entries(patient).forEach(([key, value]) => {
      if (value !== undefined) formData.append(key, value.toString());
    });
    
    return await axios.post(`${API_BASE_URL}/addNewPatient`, formData, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
    });
  }

  async searchPatient(mailId: string) {
    return await axios.get(`${API_BASE_URL}/searchPatient`, {
      params: { mailId },
    });
  }

  async updatePatient(patient: Patient) {
    const formData = new URLSearchParams();
    Object.entries(patient).forEach(([key, value]) => {
      if (value !== undefined) formData.append(key, value.toString());
    });
    
    return await axios.post(`${API_BASE_URL}/updatePatient`, formData, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
    });
  }

  async deletePatient(mailId: string) {
    return await axios.post(`${API_BASE_URL}/deletePatient`, null, {
      params: { mailId },
    });
  }

  // Doctor Management
  async addDoctor(doctor: Doctor) {
    const formData = new URLSearchParams();
    Object.entries(doctor).forEach(([key, value]) => {
      if (value !== undefined) formData.append(key, value.toString());
    });
    
    return await axios.post(`${API_BASE_URL}/addNewDoctor`, formData, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
    });
  }

  async getDoctorList() {
    return await axios.get(`${API_BASE_URL}/viewDoctorList`);
  }

  async getDoctorSchedules() {
    return await axios.get(`${API_BASE_URL}/doctorSchedules`);
  }

  // Appointment Management
  async bookAppointment(appointmentData: any) {
    const formData = new URLSearchParams();
    Object.entries(appointmentData).forEach(([key, value]) => {
      if (value !== undefined && value !== null) formData.append(key, value.toString());
    });
    
    return await axios.post(`${API_BASE_URL}/bookNewappointment`, formData, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
    });
  }

  async getAppointments() {
    return await axios.get(`${API_BASE_URL}/viewAppointments`);
  }

  async modifyAppointment(appointmentData: any) {
    const formData = new URLSearchParams();
    Object.entries(appointmentData).forEach(([key, value]) => {
      if (value !== undefined && value !== null) formData.append(key, value.toString());
    });
    
    return await axios.post(`${API_BASE_URL}/modifyAppointment`, formData, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
    });
  }

  // Reviews & Feedback
  async submitFeedback(feedbackData: any) {
    const formData = new URLSearchParams();
    Object.entries(feedbackData).forEach(([key, value]) => {
      if (value !== undefined && value !== null) formData.append(key, value.toString());
    });
    
    return await axios.post(`${API_BASE_URL}/feedback`, formData, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded',
      },
    });
  }

  async getReviews() {
    return await axios.get(`${API_BASE_URL}/reviews`);
  }
}

export default new ApiService();

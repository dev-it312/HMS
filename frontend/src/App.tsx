import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Layout from './components/Layout';
import DashboardLayout from './components/DashboardLayout';

// Pages
import Home from './pages/Home';
import AdminLogin from './pages/AdminLogin';
import DoctorLogin from './pages/DoctorLogin';
import PatientLogin from './pages/PatientLogin';
import ReceptionistLogin from './pages/ReceptionistLogin';
import AdminDashboard from './pages/AdminDashboard';
import DoctorDashboard from './pages/DoctorDashboard';
import PatientDashboard from './pages/PatientDashboard';
import ReceptionistDashboard from './pages/ReceptionistDashboard';
import StaffManagement from './pages/StaffManagement';
import DoctorManagement from './pages/DoctorManagement';
import PatientManagement from './pages/PatientManagement';
import AppointmentManagement from './pages/AppointmentManagement';
import AddNewPatient from './pages/AddNewPatient';
import AddNewDoctor from './pages/AddNewDoctor';

import './App.css';

function App() {
  return (
    <Router>
      <Routes>
        {/* Routes with Header/Footer */}
        <Route element={<Layout />}>
          <Route path="/" element={<Home />} />
          <Route path="/login/admin" element={<AdminLogin />} />
          <Route path="/login/doctor" element={<DoctorLogin />} />
          <Route path="/login/patient" element={<PatientLogin />} />
          <Route path="/login/receptionist" element={<ReceptionistLogin />} />
        </Route>

        {/* Dashboard Routes */}
        <Route element={<DashboardLayout />}>
          <Route path="/admin/dashboard" element={<AdminDashboard />} />
          <Route path="/doctor/dashboard" element={<DoctorDashboard />} />
          <Route path="/patient/dashboard" element={<PatientDashboard />} />
          <Route path="/receptionist/dashboard" element={<ReceptionistDashboard />} />
          
          {/* Admin routes */}
          <Route path="/admin/staff-management" element={<StaffManagement />} />
          <Route path="/admin/doctor-management" element={<DoctorManagement />} />
          <Route path="/admin/doctor/add" element={<AddNewDoctor />} />
          
          {/* Receptionist routes */}
          <Route path="/receptionist/patient-management" element={<PatientManagement />} />
          <Route path="/receptionist/appointment-management" element={<AppointmentManagement />} />
          <Route path="/receptionist/patient/add" element={<AddNewPatient />} />
        </Route>
      </Routes>
    </Router>
  );
}

export default App;

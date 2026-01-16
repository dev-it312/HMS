import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import '../styles/Dashboard.css';

const PatientDashboard: React.FC = () => {
  const navigate = useNavigate();

  const handleLogout = async () => {
    try {
      await fetch('/patientlogout', { method: 'POST' });
      navigate('/');
    } catch (err) {
      console.error('Logout error:', err);
    }
  };

  return (
    <>
      <h2>Welcome to the Patient Dashboard</h2>
      <ul>
        <li><Link to="/patient/book-appointment">Book Appointment</Link></li>
        <li><Link to="/patient/view-appointments">View Appointments</Link></li>
        <li><Link to="/patient/appointment-history">Appointment History</Link></li>
        <li><Link to="/patient/prescriptions">Previous Prescriptions</Link></li>
        <li><Link to="/patient/feedback">Feedback</Link></li>
        <li><a href="#" onClick={handleLogout}>Log out</a></li>
      </ul>
    </>
  );
};

export default PatientDashboard;

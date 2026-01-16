import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import '../styles/Dashboard.css';

const ReceptionistDashboard: React.FC = () => {
  const navigate = useNavigate();

  const handleLogout = async () => {
    try {
      await fetch('/receptionistlogout', { method: 'POST' });
      navigate('/');
    } catch (err) {
      console.error('Logout error:', err);
    }
  };

  return (
    <>
      <h2>Welcome to the Receptionist Dashboard</h2>
      <ul>
        <li><Link to="/receptionist/patient-management">Patient Management</Link></li>
        <li><Link to="/receptionist/appointment-management">Appointment Management</Link></li>
        <li><Link to="/receptionist/view-doctor-list">View Doctor List</Link></li>
        <li><a href="#" onClick={handleLogout}>Log out</a></li>
      </ul>
    </>
  );
};

export default ReceptionistDashboard;

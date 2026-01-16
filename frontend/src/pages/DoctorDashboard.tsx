import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import '../styles/Dashboard.css';

const DoctorDashboard: React.FC = () => {
  const navigate = useNavigate();

  const handleLogout = async () => {
    try {
      await fetch('/doctorlogout', { method: 'POST' });
      navigate('/');
    } catch (err) {
      console.error('Logout error:', err);
    }
  };

  return (
    <>
      <h2>Welcome to the Doctor Dashboard</h2>
      <ul>
        <li><Link to="/doctor/view-schedules">View Schedules</Link></li>
        <li><Link to="/doctor/patient-details">Patient Details</Link></li>
        <li><a href="#" onClick={handleLogout}>Log out</a></li>
      </ul>
    </>
  );
};

export default DoctorDashboard;

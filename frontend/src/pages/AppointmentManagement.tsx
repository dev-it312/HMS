import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Dashboard.css';

const AppointmentManagement: React.FC = () => {
  return (
    <>
      <h2>Appointment Management</h2>
      <ul>
        <li><Link to="/receptionist/appointment/book">Book New Appointment</Link></li>
        <li><Link to="/receptionist/appointment/modify">Modify Appointment</Link></li>
        <li><Link to="/receptionist/appointment/view">View Appointments</Link></li>
        <li><Link to="/receptionist/dashboard">Back to Dashboard</Link></li>
      </ul>
    </>
  );
};

export default AppointmentManagement;

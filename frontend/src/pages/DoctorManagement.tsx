import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Dashboard.css';

const DoctorManagement: React.FC = () => {
  return (
    <>
      <h2>Doctor Management</h2>
      <ul>
        <li><Link to="/admin/doctor/add">Add New Doctor</Link></li>
        <li><Link to="/admin/doctor/edit">Edit Doctor</Link></li>
        <li><Link to="/admin/doctor/remove">Remove Doctor</Link></li>
        <li><Link to="/admin/doctor/schedules">Doctor Schedules</Link></li>
        <li><Link to="/admin/dashboard">Back to Dashboard</Link></li>
      </ul>
    </>
  );
};

export default DoctorManagement;

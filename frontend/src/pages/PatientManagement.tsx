import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Dashboard.css';

const PatientManagement: React.FC = () => {
  return (
    <>
      <h2>Patient Management</h2>
      <ul>
        <li><Link to="/receptionist/patient/add">Add New Patient</Link></li>
        <li><Link to="/receptionist/patient/search">Search and View Patient Details</Link></li>
        <li><Link to="/receptionist/patient/update">Update Patient Information</Link></li>
        <li><Link to="/receptionist/patient/upload-reports">Upload Patient Reports</Link></li>
        <li><Link to="/receptionist/patient/delete">Delete Patient Information</Link></li>
        <li><Link to="/receptionist/dashboard">Back to Dashboard</Link></li>
      </ul>
    </>
  );
};

export default PatientManagement;

import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Dashboard.css';

const StaffManagement: React.FC = () => {
  return (
    <>
      <h2>Staff Management</h2>
      <ul>
        <li><Link to="/admin/staff/add">Add New Staff</Link></li>
        <li><Link to="/admin/staff/view">View or Edit Staff Details</Link></li>
        <li><Link to="/admin/staff/remove">Remove Staff</Link></li>
        <li><Link to="/admin/dashboard">Back to Dashboard</Link></li>
      </ul>
    </>
  );
};

export default StaffManagement;

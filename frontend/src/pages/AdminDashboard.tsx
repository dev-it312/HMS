import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import '../styles/Dashboard.css';

const AdminDashboard: React.FC = () => {
  const navigate = useNavigate();

  const handleLogout = async () => {
    try {
      await fetch('/adminlogout', { method: 'POST' });
      navigate('/');
    } catch (err) {
      console.error('Logout error:', err);
    }
  };

  return (
    <>
      <h2>Welcome to the Admin Dashboard</h2>
      <ul>
        <li><Link to="/admin/staff-management">Staff Management</Link></li>
        <li><Link to="/admin/doctor-management">Doctor Management</Link></li>
        <li><Link to="/admin/view-appointments">View Appointments</Link></li>
        <li><Link to="/admin/reviews">Reviews</Link></li>
        <li><a href="#" onClick={handleLogout}>Log out</a></li>
      </ul>
    </>
  );
};

export default AdminDashboard;

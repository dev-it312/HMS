import React from 'react';
import { Outlet } from 'react-router-dom';
import '../styles/Dashboard.css';

const DashboardLayout: React.FC = () => {
  return (
    <div className="dashboard-container">
      <Outlet />
    </div>
  );
};

export default DashboardLayout;

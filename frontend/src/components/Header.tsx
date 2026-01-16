import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Header.css';

const Header: React.FC = () => {
  return (
    <>
      <div className="logo-container">
        <img src="/LogoBanner.jpg" alt="Hospital Management System Logo" className="logo-image" />
      </div>
      <header>
        <nav className="navbar">
          <ul>
            <li><Link to="/">Accueil</Link></li>
            <li><a href="#about">À propos</a></li>
            <li><a href="#location">Localisation</a></li>
            <li><a href="#contact">Contact</a></li>
            <li><a href="#doctor-chatbot">Chatbot Médecin</a></li>
            <li className="dropdown">
              <a href="#">Connexion ▼</a>
              <ul className="dropdown-menu">
                <li><Link to="/login/admin">Admin</Link></li>
                <li><Link to="/login/patient">Patient</Link></li>
                <li><Link to="/login/doctor">Médecin</Link></li>
                <li><Link to="/login/receptionist">Réceptionniste</Link></li>
              </ul>
            </li>
            <li className="dropdown">
              <a href="#">Admin ▼</a>
              <ul className="dropdown-menu">
                <li><Link to="/admin/add">Nouveau</Link></li>
                <li><Link to="/admin/edit">Modification</Link></li>
                <li><Link to="/admin/list">Liste</Link></li>
                <li><Link to="/admin/delete">Suppression</Link></li>
                <li><Link to="/admin/razAttempts">Raz (debug)</Link></li>
              </ul>
            </li>
          </ul>
        </nav>
      </header>
    </>
  );
};

export default Header;

import React from 'react';
import { Link } from 'react-router-dom';
import { useTheme } from '../contexts/ThemeContext';
import '../styles/Header.css';

const Header: React.FC = () => {
  const { theme, setTheme } = useTheme();

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
            <li className="dropdown">
              <a href="#">Paramètres ▼</a>
              <ul className="dropdown-menu">
                <li className="dropdown-submenu">
                  <a href="#">Affichage ▸</a>
                  <ul className="dropdown-submenu-content">
                    <li className="dropdown-submenu">
                      <a href="#">Thèmes ▸</a>
                      <ul className="dropdown-submenu-content">
                        <li>
                          <a 
                            href="#" 
                            onClick={(e) => { e.preventDefault(); setTheme('light'); }}
                            className={theme === 'light' ? 'active' : ''}
                          >
                            ☀️ Light Mode
                          </a>
                        </li>
                        <li>
                          <a 
                            href="#" 
                            onClick={(e) => { e.preventDefault(); setTheme('dark'); }}
                            className={theme === 'dark' ? 'active' : ''}
                          >
                            🌙 Dark Mode
                          </a>
                        </li>
                        <li>
                          <a 
                            href="#" 
                            onClick={(e) => { e.preventDefault(); setTheme('high-contrast'); }}
                            className={theme === 'high-contrast' ? 'active' : ''}
                          >
                            🎨 High Contrast
                          </a>
                        </li>
                        <li className="separator"></li>
                        <li>
                          <Link to="/theme-management">⚙️ Theme Management</Link>
                        </li>
                      </ul>
                    </li>
                  </ul>
                </li>
              </ul>
            </li>
          </ul>
        </nav>
      </header>
    </>
  );
};

export default Header;

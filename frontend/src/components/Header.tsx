import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { useTheme } from '../contexts/ThemeContext';
import { availableThemes } from '../config/themes';
import '../styles/Header.css';

const Header: React.FC = () => {
  const { theme, setTheme } = useTheme();
  const [showAppearanceSubmenu, setShowAppearanceSubmenu] = useState(false);
  const [showOtherSubmenu, setShowOtherSubmenu] = useState(false);

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
                <li 
                  className="dropdown-submenu"
                  onMouseEnter={() => setShowAppearanceSubmenu(true)}
                  onMouseLeave={() => setShowAppearanceSubmenu(false)}
                >
                  <a href="#">Apparence ▸</a>
                  {showAppearanceSubmenu && (
                    <ul className="submenu">
                      {availableThemes.map((themeConfig) => (
                        <li key={themeConfig.id}>
                          <a 
                            href="#"
                            className={theme === themeConfig.id ? 'active' : ''}
                            onClick={(e) => {
                              e.preventDefault();
                              setTheme(themeConfig.id);
                            }}
                            title={themeConfig.description}
                          >
                            {themeConfig.icon} {themeConfig.name} {theme === themeConfig.id && '✓'}
                          </a>
                        </li>
                      ))}
                    </ul>
                  )}
                </li>
                <li 
                  className="dropdown-submenu"
                  onMouseEnter={() => setShowOtherSubmenu(true)}
                  onMouseLeave={() => setShowOtherSubmenu(false)}
                >
                  <a href="#">Autres ▸</a>
                  {showOtherSubmenu && (
                    <ul className="submenu">
                      <li><a href="#">Option 1</a></li>
                      <li><a href="#">Option 2</a></li>
                    </ul>
                  )}
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

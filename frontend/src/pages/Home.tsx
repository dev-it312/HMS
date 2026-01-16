// comment

import React from 'react';
import { Link } from 'react-router-dom';
import '../styles/Home.css';

const Home: React.FC = () => {
  return (
    <>
      <h1>Bienvenue sur le système de gestion hospitalière</h1>
      <p>Gérez facilement les patients, les rendez-vous, les médecins et le personnel. Accédez à toutes les fonctionnalités depuis cette page d'accueil moderne et intuitive.</p>
      <div className="quick-access">
        <Link to="/login/patient">Espace Patient</Link>
        <Link to="/login/doctor">Espace Médecin</Link>
        <Link to="/login/receptionist">Espace Réceptionniste</Link>
        <Link to="/login/admin">Espace Admin</Link>
        <Link to="/book-appointment">Prendre un rendez-vous</Link>
        <a href="#doctor-chatbot">Chatbot Médecin</a>
      </div>
    </>
  );
};

export default Home;

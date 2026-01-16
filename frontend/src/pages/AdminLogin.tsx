import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Form.css';

const AdminLogin: React.FC = () => {
  const [mailId, setMailId] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [attemptsLeft] = useState<number | null>(null);
  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError('');

    try {
      const response = await fetch('/adminlogin', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `mailId=${encodeURIComponent(mailId)}&password=${encodeURIComponent(password)}`,
      });

      if (response.ok) {
        navigate('/admin/dashboard');
      } else {
        setError('Identifiants invalides');
        // Gérer les tentatives restantes si nécessaire
      }
    } catch (err) {
      setError('Erreur de connexion. Veuillez réessayer.');
    }
  };

  return (
    <div className="form-container">
      <h2>Admin Login</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="mailId">Email</label>
        <input
          type="text"
          id="mailId"
          name="mailId"
          placeholder="Enter your email"
          value={mailId}
          onChange={(e) => setMailId(e.target.value)}
          required
        />
        <label htmlFor="password">Password</label>
        <input
          type="password"
          id="password"
          name="password"
          placeholder="Enter your password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <div className="button-row">
          <button type="button" className="cancel-btn" onClick={() => navigate('/')}>
            Cancel
          </button>
          <button
            type="submit"
            className="login-btn"
            disabled={attemptsLeft === 0}
          >
            Login
          </button>
        </div>
      </form>
      {attemptsLeft !== null && attemptsLeft > 0 && (
        <p className="info-message">
          {attemptsLeft} tentatives restantes
        </p>
      )}
      {attemptsLeft === 0 && (
        <p className="error-message">
          Compte bloqué après 3 tentatives. Veuillez réessayer plus tard.
        </p>
      )}
      {error && <p className="error-message">{error}</p>}
    </div>
  );
};

export default AdminLogin;

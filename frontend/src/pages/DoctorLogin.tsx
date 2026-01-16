import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Form.css';

const DoctorLogin: React.FC = () => {
  const [mailId, setMailId] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError('');

    try {
      const response = await fetch('/doctorlogin', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `mailId=${encodeURIComponent(mailId)}&password=${encodeURIComponent(password)}`,
      });

      if (response.ok) {
        navigate('/doctor/dashboard');
      } else {
        setError('Identifiants invalides');
      }
    } catch (err) {
      setError('Erreur de connexion. Veuillez réessayer.');
    }
  };

  return (
    <div className="form-container">
      <h2>Doctor Login</h2>
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
          <button type="submit" className="login-btn">
            Login
          </button>
        </div>
      </form>
      {error && <p className="error-message">{error}</p>}
    </div>
  );
};

export default DoctorLogin;

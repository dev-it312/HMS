import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Form.css';

const AddNewDoctor: React.FC = () => {
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    mailId: '',
    contactNumber: '',
    specialization: '',
    experience: '',
    password: ''
  });
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setMessage('');

    try {
      const response = await fetch('/addNewDoctor', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams(formData as any).toString(),
      });

      if (response.ok) {
        setMessage('Médecin ajouté avec succès !');
        setTimeout(() => navigate('/admin/doctor-management'), 2000);
      } else {
        setMessage('Erreur lors de l\'ajout du médecin.');
      }
    } catch (err) {
      setMessage('Erreur de connexion.');
    }
  };

  return (
    <div className="form-container">
      <h2>Add New Doctor</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="firstName">First Name</label>
        <input
          type="text"
          id="firstName"
          name="firstName"
          value={formData.firstName}
          onChange={handleChange}
          required
        />

        <label htmlFor="lastName">Last Name</label>
        <input
          type="text"
          id="lastName"
          name="lastName"
          value={formData.lastName}
          onChange={handleChange}
          required
        />

        <label htmlFor="mailId">Email</label>
        <input
          type="email"
          id="mailId"
          name="mailId"
          value={formData.mailId}
          onChange={handleChange}
          required
        />

        <label htmlFor="contactNumber">Contact Number</label>
        <input
          type="text"
          id="contactNumber"
          name="contactNumber"
          value={formData.contactNumber}
          onChange={handleChange}
          required
        />

        <label htmlFor="specialization">Specialization</label>
        <input
          type="text"
          id="specialization"
          name="specialization"
          value={formData.specialization}
          onChange={handleChange}
          required
        />

        <label htmlFor="experience">Experience (years)</label>
        <input
          type="number"
          id="experience"
          name="experience"
          value={formData.experience}
          onChange={handleChange}
          required
        />

        <label htmlFor="password">Password</label>
        <input
          type="password"
          id="password"
          name="password"
          value={formData.password}
          onChange={handleChange}
          required
        />

        <div className="button-row">
          <button type="button" className="cancel-btn" onClick={() => navigate(-1)}>
            Cancel
          </button>
          <button type="submit" className="submit-btn">
            Add Doctor
          </button>
        </div>
      </form>
      {message && <p className={message.includes('succès') ? 'success-message' : 'error-message'}>{message}</p>}
    </div>
  );
};

export default AddNewDoctor;

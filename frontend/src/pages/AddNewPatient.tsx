import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Form.css';

const AddNewPatient: React.FC = () => {
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    mailId: '',
    contactNumber: '',
    age: '',
    gender: '',
    address: '',
    password: ''
  });
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setMessage('');

    try {
      const response = await fetch('/addNewPatient', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams(formData as any).toString(),
      });

      if (response.ok) {
        setMessage('Patient ajouté avec succès !');
        setTimeout(() => navigate('/receptionist/patient-management'), 2000);
      } else {
        setMessage('Erreur lors de l\'ajout du patient.');
      }
    } catch (err) {
      setMessage('Erreur de connexion.');
    }
  };

  return (
    <div className="form-container">
      <h2>Add New Patient</h2>
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

        <label htmlFor="age">Age</label>
        <input
          type="number"
          id="age"
          name="age"
          value={formData.age}
          onChange={handleChange}
          required
        />

        <label htmlFor="gender">Gender</label>
        <select
          id="gender"
          name="gender"
          value={formData.gender}
          onChange={handleChange}
          required
        >
          <option value="">Select Gender</option>
          <option value="Male">Male</option>
          <option value="Female">Female</option>
          <option value="Other">Other</option>
        </select>

        <label htmlFor="address">Address</label>
        <textarea
          id="address"
          name="address"
          value={formData.address}
          onChange={handleChange}
          rows={3}
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
            Add Patient
          </button>
        </div>
      </form>
      {message && <p className={message.includes('succès') ? 'success-message' : 'error-message'}>{message}</p>}
    </div>
  );
};

export default AddNewPatient;

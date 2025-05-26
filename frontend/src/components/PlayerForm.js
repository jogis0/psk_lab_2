import React, { useState } from 'react';

const PlayerForm = ({ onSubmit, initialData = null }) => {
  const [formData, setFormData] = useState(initialData || {
    firstName: '',
    lastName: '',
    age: '',
    height: '',
    weight: '',
    country: '',
    jerseyNumber: ''
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(formData);
    if (!initialData) {
      setFormData({
        firstName: '',
        lastName: '',
        age: '',
        height: '',
        weight: '',
        country: '',
        jerseyNumber: ''
      });
    }
  };

  return (
    <form onSubmit={handleSubmit} className="player-form">
      <div className="form-group">
        <input
          type="text"
          name="firstName"
          value={formData.firstName}
          onChange={handleChange}
          placeholder="First Name"
          required
        />
      </div>
      <div className="form-group">
        <input
          type="text"
          name="lastName"
          value={formData.lastName}
          onChange={handleChange}
          placeholder="Last Name"
          required
        />
      </div>
      <div className="form-group">
        <input
          type="number"
          name="age"
          value={formData.age}
          onChange={handleChange}
          placeholder="Age"
          required
        />
      </div>
      <div className="form-group">
        <input
          type="number"
          name="height"
          value={formData.height}
          onChange={handleChange}
          placeholder="Height (cm)"
          required
        />
      </div>
      <div className="form-group">
        <input
          type="number"
          name="weight"
          value={formData.weight}
          onChange={handleChange}
          placeholder="Weight (kg)"
          required
        />
      </div>
      <div className="form-group">
        <input
          type="text"
          name="country"
          value={formData.country}
          onChange={handleChange}
          placeholder="Country"
          required
        />
      </div>
      <div className="form-group">
        <input
          type="number"
          name="jerseyNumber"
          value={formData.jerseyNumber}
          onChange={handleChange}
          placeholder="Jersey Number"
          required
        />
      </div>
      <button type="submit">{initialData ? 'Update Player' : 'Create Player'}</button>
    </form>
  );
};

export default PlayerForm; 
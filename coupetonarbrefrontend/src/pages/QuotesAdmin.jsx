import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';

function QuotesAdmin() {
  const [clients, setClients] = useState([]);
  const [updateFormData, setUpdateFormData] = useState({
    clientId: '',
    firstName: '',
    lastName: '',
    email: '',
    phoneNumber: '',
    address: '',
  });

  useEffect(() => {
    axios.get('http://localhost:8080/users/clients')
      .then(response => setClients(response.data))
      .catch(error => console.error('Error fetching clients:', error));
  }, []);

  const handleUpdateFormChange = (e) => {
    setUpdateFormData({
      ...updateFormData,
      [e.target.name]: e.target.value,
    });
  };

  const handleUpdateButtonClick = () => {
    const { clientId, ...updateData } = updateFormData;

    // Send a PUT request to update the client
    axios.put(`http://localhost:8080/users/clients/${clientId}`, updateData)
      .then(response => {
        // Handle the response, e.g., update state or show a success message
        console.log('Client updated successfully:', response.data);
      })
      .catch(error => console.error('Error updating client:', error));
  };

  return (
    <div id='nav-container'>
      <Navbar />

      {/* Display the list of clients */}
      <ul>
        {clients.map(client => (
          <li key={client.clientId}>
            {client.clientId} {client.firstName} {client.lastName} 
          </li>
        ))}
      </ul>

      {/* Form for updating a client */}
      <form>
        <label>
          Client ID:
          <input
            type="text"
            name="clientId"
            value={updateFormData.clientId}
            onChange={handleUpdateFormChange}
          />
        </label>
        <label>
          First Name:
          <input
            type="text"
            name="firstName"
            value={updateFormData.firstName}
            onChange={handleUpdateFormChange}
          />
        </label>
        <label>
          Last Name:
          <input
            type="text"
            name="lastName"
            value={updateFormData.lastName}
            onChange={handleUpdateFormChange}
          />
        </label>
        <label>
          Email:
          <input
            type="text"
            name="email"
            value={updateFormData.email}
            onChange={handleUpdateFormChange}
          />
        </label>
        <label>
          Phone Number:
          <input
            type="text"
            name="phoneNumber"
            value={updateFormData.phoneNumber}
            onChange={handleUpdateFormChange}
          />
        </label>
        <label>
          Address:
          <input
            type="text"
            name="address"
            value={updateFormData.address}
            onChange={handleUpdateFormChange}
          />
        </label>
        <button type="button" onClick={handleUpdateButtonClick}>
          Update Client
        </button>
      </form>
    </div>
  );
}

export default QuotesAdmin;

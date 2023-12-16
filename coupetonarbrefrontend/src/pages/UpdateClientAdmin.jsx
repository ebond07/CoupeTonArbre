import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';
import { useParams } from 'react-router-dom';
import '../styles/UpdateClientAdmin.css'; // Import your CSS file for styling
import Footer from '../components/Footer';


function UpdateClientAdmin() {
  const { clientId } = useParams();
  const [selectedClientDetails, setSelectedClientDetails] = useState(null);
  const [updateFormData, setUpdateFormData] = useState({
    clientId: '',
    firstName: '',
    lastName: '',
    email: '',
    phoneNumber: '',
    address: '',
  });

  useEffect(() => {
    axios.get(`http://localhost:8080/users/clients/${clientId}`)
      .then(response => {
        setSelectedClientDetails(response.data);
        setUpdateFormData({
          clientId: response.data.clientId,
          firstName: response.data.firstName,
          lastName: response.data.lastName,
          email: response.data.email,
          phoneNumber: response.data.phoneNumber,
          address: response.data.address,
        });
      })
      .catch(error => {
        console.error('Error:', error);
      });
  }, [clientId]);

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
        window.location.href = `/accounts`;

      })
      .catch(error => console.error('Error updating client:', error));
  };

  return (
    <div id='nav-container'>
      <Navbar />

      <div className='text-align center'>
        <h1> Update Client</h1>
      </div>
      <br></br>

      <div>
      {/* Conditionally render the form */}
      {selectedClientDetails && (
        <form className="update-client-form">
          
          <div className="form-group">
            <label>First Name:</label>
            <input
              type="text"
              name="firstName"
              placeholder={selectedClientDetails.firstName}
              value={updateFormData.firstName}
              onChange={handleUpdateFormChange}
            />
          </div>
          <div className="form-group">
            <label>Last Name:</label>
            <input
              type="text"
              name="lastName"
              value={updateFormData.lastName}
              onChange={handleUpdateFormChange}
            />
          </div>
          <div className="form-group">
            <label>Email:</label>
            <input
              type="text"
              name="email"
              value={updateFormData.email}
              onChange={handleUpdateFormChange}
            />
          </div>
          <div className="form-group">
            <label>Phone Number:</label>
            <input
              type="text"
              name="phoneNumber"
              value={updateFormData.phoneNumber}
              onChange={handleUpdateFormChange}
            />
          </div>
          <div className="form-group">
            <label>Address:</label>
            <input
              type="text"
              name="address"
              value={updateFormData.address}
              onChange={handleUpdateFormChange}
            />
          </div>
          <button type="button" onClick={handleUpdateButtonClick}>
            Update Client
          </button>
        </form>
      )}
      </div>
      <br></br>

        <div>
        <Footer />
      </div>
    </div>
    
  );
}

export default UpdateClientAdmin;

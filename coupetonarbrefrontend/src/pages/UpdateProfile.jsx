import React, { useState, useEffect } from 'react';
import Helmet from 'react-helmet'
import axios from 'axios';
import ClientNavbar from '../components/ClientNavbar';
import { useParams } from 'react-router-dom';
import '../styles/UpdateClientAdmin.css'; 
import Footer from '../components/Footer';
import { useAuth } from '../security/Components/AuthProvider';



function UpdateProfile() {
    const auth = useAuth();

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
    axios .get('http://localhost:8080/users/client', {
        headers: {
          // @ts-ignore
          'X-XSRF-TOKEN': auth.getXsrfToken(),
        },
      })
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
    axios.put(`http://localhost:8080/users/client`, updateData ,  {
        headers: {
          // @ts-ignore
          'X-XSRF-TOKEN': auth.getXsrfToken(),
        },
      })
      .then(response => {
        console.log('Profile updated successfully:', response.data);
        window.location.href = `/profile`;

      })
      .catch(error => console.error('Error updating client:', error));
  };

  return (
    <div>
      <Helmet>
        <title>Update Profile - CTA</title>
      </Helmet>
      <div id='nav-container'>
        <ClientNavbar />

        <div className='text-align center'>
          <h1> Update Your Information</h1>
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
            <button className='button' type="button" onClick={handleUpdateButtonClick}>
              Save
            </button>
          </form>
        )}
        </div>
        <br></br>

          <div>
          <Footer />
        </div>
      </div>
    </div>
  );
}

export default UpdateProfile;

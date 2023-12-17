import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import '../styles/AccountsAdmin.css';


function AccountsAdmin() {
  const [clients, setClients] = useState([]);
  const [selectedClientId, setSelectedClientId] = useState(null);
  const [selectedClientDetails, setSelectedClientDetails] = useState(null);

  useEffect(() => {
    axios.get('http://localhost:8080/users/clients')
      .then(response => {
        setClients(response.data);
      })
      .catch(error => {
        console.error('Error:', error);
      });
  }, []);

  const handleDelete = (clientId) => {
    if (typeof clientId === 'undefined') {
      console.error('Client ID is undefined. Cannot delete.');
      return;
    }

    const isConfirmed = window.confirm('Are you sure you want to delete this client?');

    if (!isConfirmed) {
      return;
    }

    console.log('Deleting client with id:', clientId);

    axios.delete(`http://localhost:8080/users/clients/${clientId}`)
      .then(response => {
        console.log('Delete successful:', response);
        axios.get('http://localhost:8080/users/clients')
          .then(response => {
            setClients(response.data);
          })
          .catch(error => {
            console.error('Error fetching updated clients:', error);
          });
      })
      .catch(error => {
        console.error('Error:', error);
      });
  };

  useEffect(() => {
    if (selectedClientId !== null) {
      axios.get(`http://localhost:8080/users/clients/${selectedClientId}`)
        .then(response => {
          setSelectedClientDetails(response.data);
        })
        .catch(error => {
          console.error('Error:', error);
        });
    }
  }, [selectedClientId]);

  const handleClientClick = (clientId) => {
    setSelectedClientId((prevClientId) => (prevClientId === clientId ? null : clientId));
  };

  const handleUpdate = (clientId) => {

    window.location.href = `/updateClientAdmin/${clientId}`;

  };

  const closeDetails = () => {
    setSelectedClientId(null);
  };

  return (
    <div>
      <div id='nav-container'>
        <Navbar />
      </div>

      <div>
        <h1>Clients</h1>
      </div>

      <table>
        <thead>
          <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Phone Number</th>
            <th>Address</th>
          </tr>
        </thead>
        <tbody>
          {clients.map((client) => (
            <React.Fragment key={client.clientId}>
              <tr>
                <td onClick={() => handleClientClick(client.clientId)}>
                  {client.firstName}
                </td>
                <td onClick={() => handleClientClick(client.clientId)}>
                  {client.lastName}</td>
                <td onClick={() => handleClientClick(client.clientId)}>
                    {client.email}</td>
                <td onClick={() => handleClientClick(client.clientId)}>
                  {client.phoneNumber}</td>
                <td onClick={() => handleClientClick(client.clientId)}>
                  {client.address}</td>
                <td>
                  <button className='buttons delete-button' onClick={() => handleDelete(client.clientId)}>
                    Delete
                  </button>
                </td>
                <td>
                  <button className='buttons edit-button' onClick={() => handleUpdate(client.clientId)}>
                    Edit
                  </button>
                </td>
              </tr>
              {selectedClientId === client.clientId && selectedClientDetails && (
                <tr>
                  <td colSpan="5">
                    <div className="additional-details">
                      <h2>Selected Client Details:</h2>
                      <p>Client ID: {selectedClientDetails.clientId}</p>
                      <p>Name: {selectedClientDetails.firstName} {selectedClientDetails.lastName}</p>
                      <p>Email: {selectedClientDetails.email}</p>
                      <p>Phone Number: {selectedClientDetails.phoneNumber}</p>
                      <p>Address: {selectedClientDetails.address}</p>
                      <button className='buttons close-button' onClick={closeDetails}>Close</button>
                    </div>
                  </td>
                </tr>
              )}
            </React.Fragment>
          ))}
        </tbody>
      </table>

      <div>
        <Footer />
      </div>
    </div>
  );
};

export default AccountsAdmin;

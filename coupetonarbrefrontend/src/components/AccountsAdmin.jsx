import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Navbar from './Navbar';
import Footer from './Footer';

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
        setClients(clients => clients.filter(client => client.id !== clientId));
      })
      .catch(error => {
        console.error('Error:', error);
      });
  };

  useEffect(() => {
    const fetchClients = () => {
      axios.get('http://localhost:8080/users/clients')
        .then(response => {
          setClients(response.data);
        })
        .catch(error => {
          console.error('Error:', error);
        });
    };

    fetchClients();
  }, [clients]);



  useEffect(() => {
    // Fetch details when selectedClientId changes
    if (selectedClientId) {
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
    setSelectedClientId(clientId);
  };

  const closeModal = () => {
    setSelectedClientId(null);
  };

  return (
    <div>
      <div id='nav-container'>
        <Navbar />
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
          {clients.map(client => (
            <tr key={client.clientId}>
              <td onClick={() => handleClientClick(client.clientId)} style={{ cursor: 'pointer', color: 'blue' }}>{client.firstName}</td>
              <td>{client.lastName}</td>
              <td>{client.email}</td>
              <td>{client.phoneNumber}</td>
              <td>{client.address}</td>
              <td>
                <button onClick={() => handleDelete(client.clientId)}>Delete</button>
              </td>

            </tr>
          ))}
        </tbody>
      </table>

      {selectedClientDetails && (
        <div className="modal-overlay">
          <div className="modal-content">
            <span className="close-modal" onClick={closeModal}>&times;</span>
            {/* Display additional details about the selected client */}
            <h2>Selected Client Details:</h2>
            <p>Client ID: {selectedClientDetails.clientId}</p>
            <p>Additional Details: {selectedClientDetails.address}</p>
            {/* Add more details as needed */}
          </div>
        </div>
      )}

      <div>
        <Footer />
      </div>
    </div>
  );
}

export default AccountsAdmin;

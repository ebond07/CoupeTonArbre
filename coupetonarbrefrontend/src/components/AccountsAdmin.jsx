import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Navbar from './Navbar';
import Footer from './Footer';

function AccountsAdmin() {
  const [clients, setClients] = useState([]);

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
              <td>{client.firstName}</td>
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
      <div>
        <Footer />
      </div>
    </div>
  );
}

export default AccountsAdmin;

import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Navbar from './Navbar';

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
            <tr key={client.id}>
              <td>{client.firstName}</td>
              <td>{client.lastName}</td>
              <td>{client.email}</td>
              <td>{client.phoneNumber}</td>
              <td>{client.address}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default AccountsAdmin;

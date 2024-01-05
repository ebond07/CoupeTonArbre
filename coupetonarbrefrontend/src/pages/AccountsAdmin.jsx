import React, { useState, useEffect } from 'react';
import { Helmet } from 'react-helmet';
import axios from 'axios';
import AdminNavbar from '../components/AdminNavbar';
import Footer from '../components/Footer';
import '../styles/AccountsAdmin.css';

import {useAuth} from "../security/Components/AuthProvider";



function AccountsAdmin() {
  const [clients, setClients] = useState([]);
  const [selectedClientId, setSelectedClientId] = useState(null);
  const [selectedClientDetails, setSelectedClientDetails] = useState(null);
  const [sortOrder, setSortOrder] = useState('asc');
  const [sortedField, setSortedField] = useState('firstName');
  const [showAddClientForm, setShowAddClientForm] = useState(false);
  const [newClient, setNewClient] = useState({
    firstName: '',
    lastName: '',
    email: '',
    phoneNumber: '',
    address: '',
  });

  const auth = useAuth();

  useEffect(() => {
    // @ts-ignore
    if (!auth.isAuthenticated) {
      window.location.href = 'http://localhost:3000/';
    }
  }, []);


  const getAllAccounts = async () => {
    axios.get("http://localhost:8080/users/clients", {
        headers: {
            // @ts-ignore
            'X-XSRF-TOKEN': auth.getXsrfToken()
        }
    })
        .then(r => {
            // if (r.status === 200) {
                console.log("hello")
                setClients(r.data)

            // }
        })
        .catch(e => {
            // @ts-ignore
            // auth.authError(e.response.status)
        })
}

  useEffect(() => {
    getAllAccounts().then(r => {
      console.log("Hellop")
  })
  }, []);

  const handleAddClient = () => {
    axios.post('http://localhost:8080/users/clients', newClient)
      .then(response => {
        setClients([...clients, response.data]);
        setNewClient({ firstName: '', lastName: '', email: '', phoneNumber: '', address: '' });
        setShowAddClientForm(false);
      })
      .catch(error => {
        console.error('Error adding client:', error);
      });
  };

  const handleFormChange = (event) => {
    let value = event.target.value;
  
    if (event.target.name === 'phoneNumber') {
      value = value.replace(/\D/g, '');
  
      const match = value.match(/^(\d{3})(\d{3})(\d{4})$/);
      if (match) {
        value = `(${match[1]}) ${match[2]}-${match[3]}`;
      }
    }
  
    setNewClient({ ...newClient, [event.target.name]: value });
  };
  

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
        setClients(clients.filter(client => client.clientId !== clientId));
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

  const handleFieldClick = (field) => {
    setSortOrder(sortOrder === 'asc' ? 'desc' : 'asc');
    setSortedField(field);
    setSelectedClientId(null);
  };

  const sortedClients = [...clients].sort((a, b) => {
    const fieldValueA = a[sortedField];
    const fieldValueB = b[sortedField];

    if (fieldValueA < fieldValueB) {
      return sortOrder === 'asc' ? -1 : 1;
    }
    if (fieldValueA > fieldValueB) {
      return sortOrder === 'asc' ? 1 : -1;
    }
    return 0;
  });

  const handleUpdate = (clientId) => {

    window.location.href = `/admin/updateClientAdmin/${clientId}`;

  };

  const closeDetails = () => {
    setSelectedClientId(null);
  };

  return (
    <div>
      <Helmet>
        <title>Client Accounts - CTA</title>
      </Helmet>
      <div id='nav-container'>
        <AdminNavbar />
      </div>

      <div className="header-container">
        <h1>Clients</h1>
        <div className="add-client-button">
          <button onClick={() => setShowAddClientForm(true)}>Add Client</button>
        </div>


      {showAddClientForm && (
        <div className="popup-background">
          <div className="popup-container">
            <h1>Add a new client</h1>
            <br></br><br></br><br></br>
            <input name="firstName" value={newClient.firstName} onChange={handleFormChange} placeholder="First Name" />
            <input name="lastName" value={newClient.lastName} onChange={handleFormChange} placeholder="Last Name" />
            <input name="email" value={newClient.email} onChange={handleFormChange} placeholder="Email" />
            <input name="phoneNumber" value={newClient.phoneNumber} onChange={handleFormChange} placeholder="Phone Number" />
            <input name="address" value={newClient.address} onChange={handleFormChange} placeholder="Full Address" />
            <button onClick={handleAddClient}>Submit New Client</button>
            <button onClick={() => setShowAddClientForm(false)}>Cancel</button>
          </div>
        </div>
      )}

        <table>
          <thead>
            <tr>
              <th className='sortable' onClick={() => handleFieldClick('firstName')}>
                First Name {sortedField === 'firstName' && (sortOrder === 'asc' ? '▲' : '▼')}
              </th>
              <th className='sortable' onClick={() => handleFieldClick('lastName')}>
                Last Name {sortedField === 'lastName' && (sortOrder === 'asc' ? '▲' : '▼')}
              </th>
              <th className='sortable' onClick={() => handleFieldClick('email')}>
                Email {sortedField === 'email' && (sortOrder === 'asc' ? '▲' : '▼')}
              </th>
              <th>Phone Number</th>
              <th>Address</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {sortedClients.map((client) => (
              <React.Fragment key={client.clientId}>
                <tr onClick={() => handleClientClick(client.clientId)}>
                  <td>{client.firstName}</td>
                  <td>{client.lastName}</td>
                  <td>{client.email}</td>
                  <td>{client.phoneNumber}</td>
                  <td>{client.address}</td>
                  <td>
                    <button className='buttons delete-button' onClick={() => handleDelete(client.clientId)}>Delete</button>
                    <button className='buttons edit-button' onClick={() => handleUpdate(client.clientId)}>Edit</button>
                  </td>
                </tr>
                {selectedClientId === client.clientId && selectedClientDetails && (
                  <tr>
                    <td colSpan="6">
                      <div className="additional-details">
                        <h2>{selectedClientDetails.firstName} {selectedClientDetails.lastName}'s Details:</h2>
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
      </div>

      <div>
        <Footer />
      </div>
    </div>
  );
}

export default AccountsAdmin;

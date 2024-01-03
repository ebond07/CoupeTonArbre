// Import at the top of your CreateQuoteRequestAdmin.js file
import React, { useState, useEffect } from 'react';
import Helmet from 'react-helmet';
import axios from 'axios';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import TimePicker from 'rc-time-picker';
import 'rc-time-picker/assets/index.css';
import moment from 'moment'; // Don't forget to import moment
import AdminNavbar from '../components/AdminNavbar';
import { useParams } from 'react-router-dom';
import '../styles/CreateQuoteRequest.css';
import Footer from '../components/Footer';
import { useAuth } from '../security/Components/AuthProvider';

const format = 'h:mm a';

// Map between user-friendly service options and enum values
const serviceOptionsMapping = {
  'Hedge Trimming': 'HedgeTrimming',
  'Tree Trimming': 'TreeTrimming',
  'Tree Branch Removal': 'TreeBranchRemoval',
  'Small Tree Removal': 'SmallTreeRemoval',
  'Hedge Trimming and Tree Trimming': 'HedgeTrimmingAndTreeTrimming',
  'Hedge Trimming and Tree Branch Removal': 'HedgeTrimmingAndTreeBranchRemoval',
  'Tree Trimming and Tree Branch Removal': 'TreeTrimmingAndTreeBranchRemoval',
  'Hedge Trimming and Tree Trimming and Tree Branch Removal': 'HedgeTrimmingAndTreeTrimmingAndTreeBranchRemoval',
  'Hedge Trimming and Small Tree Removal': 'HedgeTrimmingAndSmallTreeRemoval',
  'Tree Trimming and Small Tree Removal': 'TreeTrimmingAndSmallTreeRemoval',
  'Hedge Trimming and Tree Trimming and Small Tree Removal': 'HedgeTrimmingAndTreeTrimmingAndSmallTreeRemoval',
  'Hedge Trimming and Tree Branch Removal and Small Tree Removal': 'HedgeTrimmingAndTreeBranchRemovalAndSmallTreeRemoval',
  'Tree Trimming and Tree Branch Removal and Small Tree Removal': 'TreeTrimmingAndTreeBranchRemovalAndSmallTreeRemoval',
  'Hedge Trimming and Tree Trimming and Tree Branch Removal and Small Tree Removal': 'HedgeTrimmingAndTreeTrimmingAndTreeBranchRemovalAndSmallTreeRemoval',
};

function CreateQuoteRequestAdmin() {
  const [clients, setClients] = useState([]);
  const [filteredClients, setFilteredClients] = useState([]); // New state for filtered clients
  const [selectedClientId, setSelectedClientId] = useState('');
  const [selectedClientDetails, setSelectedClientDetails] = useState(null);
  const [searchTerm, setSearchTerm] = useState(''); // New state for search term
  const auth = useAuth();

  const [formData, setFormData] = useState({
    description: '',
    service: '',
    date: new Date(),
    time: new Date(),
  });

  const { clientId } = useParams();

  useEffect(() => {
    axios.get("http://localhost:8080/users/clients", {
      headers: {
        // @ts-ignore
        'X-XSRF-TOKEN': auth.getXsrfToken()
      }
    })
      .then(r => {
        setClients(r.data);
        setFilteredClients(r.data); // Initialize filteredClients with all clients
      })
      .catch(e => {
        console.error('Error:', e);
      });
  }, [auth]);

  useEffect(() => {
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

  const handleClientChange = (event) => {
    const clientId = event.target.value;
    setSelectedClientId(clientId);
  };

  const handleInputChange = (name, value) => {
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleTimeChange = (value) => {
    setFormData({
      ...formData,
      time: value && value.format(format),
    });
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    const requestBody = {
      clientId: selectedClientId,
      clientFirstName: selectedClientDetails.firstName,
      clientLastName: selectedClientDetails.lastName,
      ...formData,
    };

    axios.post('http://localhost:8080/quoteRequests', requestBody)
      .then(response => {
        console.log(response.data);
      })
      .catch(error => {
        console.error('Error:', error);
      });
  };

  const handleSearchTermChange = (event) => {
    setSearchTerm(event.target.value);
    filterClients(event.target.value);
  };

  const filterClients = (term) => {
    const filtered = clients.filter(client =>
      client.firstName.toLowerCase().includes(term.toLowerCase()) ||
      client.lastName.toLowerCase().includes(term.toLowerCase())
    );
    setFilteredClients(filtered);
  };

  const renderClientOptions = () => {
    return filteredClients.map(client => (
      <option key={client.clientId} value={client.clientId}>
        {client.firstName} {client.lastName}
      </option>
    ));
  };

  const renderServiceOptions = () => {
    const serviceOptions = Object.keys(serviceOptionsMapping);

    return serviceOptions.map(option => (
      <option key={option} value={serviceOptionsMapping[option]}>
        {option}
      </option>
    ));
  };

  return (
    <div>
      <Helmet>
        <title>New Quote Request - CTA</title>
      </Helmet>
      <div id='nav-container'>
        <AdminNavbar />

        <div className='text-align center'>
          <h1 className='heading'>Create Quote Request</h1>
        </div>

        <div>
          <form onSubmit={handleSubmit} className='form-styles'>
            <label className='form-label'>Select Client:</label>
            <input
              type="text"
              name="search"
              className='form-input'
              value={searchTerm}
              onChange={handleSearchTermChange}
              placeholder='Filter Client By Name'
  
            />

            {/* <label className='form-label'>Select Client:</label> */}
            <select className='form-select' value={selectedClientId} onChange={handleClientChange}>
              <option value="" disabled>Select a client</option>
              {renderClientOptions()}
            </select>

            <label className='form-label'>Description:</label>
            <input type="text" name="description" className='form-input' value={formData.description} onChange={(e) => handleInputChange('description', e.target.value)} required />

            <label className='form-label'>Service:</label>
            <select name="service" className='form-select' value={formData.service} onChange={(e) => handleInputChange('service', e.target.value)} required>
              <option value="" disabled>Select a Service</option>
              {renderServiceOptions()}
            </select>

            <label className='form-label'>Date:</label>
            <DatePicker className='form-date' selected={formData.date} onChange={(date) => handleInputChange('date', date)} />

            <label className='form-label'>Time:</label>
            <TimePicker
              showSecond={false}
              defaultValue={formData.time && moment(formData.time, format)}
              onChange={handleTimeChange}
              format={format}
              use12Hours
              className='form-time'
            />

            <button className='form-button' type="submit">Submit</button>
          </form>
        </div>

        <div>
          <Footer />
        </div>
      </div>
    </div>
  );
}

export default CreateQuoteRequestAdmin;

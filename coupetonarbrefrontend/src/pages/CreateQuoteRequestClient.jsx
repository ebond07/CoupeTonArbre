// Import at the top of your CreateQuoteRequestAdmin.js file
import React, { useState, useEffect } from 'react';
import Helmet from 'react-helmet';
import axios from 'axios';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import TimePicker from 'rc-time-picker';
import 'rc-time-picker/assets/index.css';
import moment from 'moment'; // Don't forget to import moment
import ClientNavbar from '../components/ClientNavbar';
import { useParams } from 'react-router-dom';
import '../styles/CreateQuoteRequest.css';
import Footer from '../components/Footer';
import { useAuth } from '../security/Components/AuthProvider';


const format = 'h:mm a';

function CreateQuoteRequestClient() {
  const auth = useAuth();


  const [formData, setFormData] = useState({
    description: '',
    service: '',
    date: new Date(),
    time: new Date(),
  });





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
      ...formData,
    };

    axios.post('http://localhost:8080/quoteRequests/client', requestBody, {
      headers: {
          // @ts-ignore
          'X-XSRF-TOKEN': auth.getXsrfToken()
      }
  })
      .then(response => {
        console.log(response.data);
      })
      .catch(error => {
        console.error('Error:', error);
      });
  };


  const renderServiceOptions = () => {
    const serviceOptions = [
      'HedgeTrimming',
      'TreeTrimming',
      'TreeBranchRemoval',
      'SmallTreeRemoval',
      'HedgeTrimmingAndTreeTrimming',
      'HedgeTrimmingAndTreeBranchRemoval',
      'TreeTrimmingAndTreeBranchRemoval',
      'HedgeTrimmingAndTreeTrimmingAndTreeBranchRemoval',
      'HedgeTrimmingAndSmallTreeRemoval',
      'TreeTrimmingAndSmallTreeRemoval',
      'HedgeTrimmingAndTreeTrimmingAndSmallTreeRemoval',
      'HedgeTrimmingAndTreeBranchRemovalAndSmallTreeRemoval',
      'TreeTrimmingAndTreeBranchRemovalAndSmallTreeRemoval',
      'HedgeTrimmingAndTreeTrimmingAndTreeBranchRemovalAndSmallTreeRemoval',
    ];

    return serviceOptions.map(option => (
      <option key={option} value={option}>
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
        <ClientNavbar />

        <div className='text-align center'>
          <h1 className='heading'>Create Quote Request</h1>
        </div>

        <div>
          <form onSubmit={handleSubmit} className='form-styles'>
           

           
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

export default CreateQuoteRequestClient;

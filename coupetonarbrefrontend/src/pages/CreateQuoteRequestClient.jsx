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

  const isDateValid = (date) => {
    return moment(date).isSameOrAfter(moment(), 'day');
  };



  const isTimeValid = () => {
    const dayOfWeek = moment(formData.date).day();

    //weekdays
    if (dayOfWeek >= 1 && dayOfWeek <= 5) {
      return [0, 1, 2, 3, 4, 5, 6, 7, 20, 21, 22, 23];
    }
    //saturday
     else if (dayOfWeek === 6) {
      return [0, 1, 2, 3, 4, 5, 6, 7, ,8,18, 19, 20, 21, 22, 23];
    } 
    //sunday
    else if (dayOfWeek === 0) {
      return [0, 1, 2, 3, 4, 5, 6, 7, 8, 17, 18, 19, 20, 21, 22, 23];
    }

    return false;
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
            <DatePicker
              className='form-date'
              selected={formData.date}
              onChange={(date) => handleInputChange('date', date)}
              minDate={moment()}
              filterDate={isDateValid}
              //winter (no work)
              excludeDateIntervals={[
                { start: new Date("2024-11-1"), end: new Date("2025-04-1") },
              ]}
              //holidays
              excludeDates={[new Date("2024-4-1"), new Date("2024-7-1"),new Date("2024-10-15"),new Date("2024-9-2") ]}

            />

            <label className='form-label'>Time:</label>
            <TimePicker
              showSecond={false}
              defaultValue={formData.time && moment(formData.time, format)}
              onChange={handleTimeChange}
              format={format}
              use12Hours
              className='form-time'
              allowEmpty={false}
              min={moment().hours(9).minutes(0)}
              max={moment().hours(19).minutes(59)}
              disabledHours={() => isTimeValid()}
              disabledMinutes={() => []}
              disabledSeconds={() => []}
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

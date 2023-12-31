import React, { useState, useEffect } from 'react';
import Helmet from 'react-helmet';
import axios from 'axios';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import TimePicker from 'rc-time-picker';
import 'rc-time-picker/assets/index.css';
import moment from 'moment';
import AdminNavbar from '../components/AdminNavbar';
import { useParams } from 'react-router-dom';
import '../styles/CreateQuoteRequest.css';
import Footer from '../components/Footer';
import { useAuth } from '../security/Components/AuthProvider';

const format = 'h:mm a';

const CreateQuoteRequestAdmin = () => {
  //storing all clients
  const [clients, setClients] = useState([]);
  //filtering clients
  const [filteredClients, setFilteredClients] = useState([]);
  //storing the selected client
  const [selectedClientId, setSelectedClientId] = useState('');
  //storing the details of the selected client
  const [selectedClientDetails, setSelectedClientDetails] = useState(null);
  //filter 
  const [searchTerm, setSearchTerm] = useState('');
  const auth = useAuth();

  //storing the existing quote requests
  const [existingQuoteRequests, setExistingQuoteRequests] = useState([]);

  //storing data from form
  const [formData, setFormData] = useState({
    description: '',
    service: '',
    date: new Date(),
    time: new Date(),
  });

  const { clientId } = useParams();

   //Mapping the enum names to better named options
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

  useEffect(() => {
    // @ts-ignore
    if (!auth.isAuthenticated) {
      window.location.href = 'http://localhost:3000/';
    }
  }, []);


  useEffect(() => {
    // Fetch existing quote requests
    axios.get("http://localhost:8080/quoteRequests", {
      headers: {
        // @ts-ignore
        'X-XSRF-TOKEN': auth.getXsrfToken()
      }
    })
      .then(response => {
        setExistingQuoteRequests(response.data);
      })
      .catch(error => {
        console.error('Error fetching existing quote requests:', error);
      });
  }, []);

  
 
  //Getting and storing all the clients in the system
  useEffect(() => {
    axios.get("http://localhost:8080/users/clients", {
      headers: {
        // @ts-ignore
        'X-XSRF-TOKEN': auth.getXsrfToken()
      }
    })
      .then(r => {
        setClients(r.data);
        setFilteredClients(r.data);
      })
      .catch(e => {
        console.error('Error:', e);
      });
  }, [auth]);

  //Gets the selected client and stores its details
  useEffect(() => {
    if (selectedClientId) {
      axios.get(`http://localhost:8080/users/clients/${selectedClientId}`, {
        headers: {
          // @ts-ignore
          'X-XSRF-TOKEN': auth.getXsrfToken()
        }
      })
        .then(response => {
          setSelectedClientDetails(response.data);
        })
        .catch(error => {
          console.error('Error:', error);
        });
    }
  }, [selectedClientId]);


  //handles when the client is changed to change the selected client
  const handleClientChange = (event) => {
    const clientId = event.target.value;
    setSelectedClientId(clientId);
  };

  // handling the information changed in the form
  const handleInputChange = (name, value) => {
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  // handling if time was changed
  const handleTimeChange = (value) => {
    setFormData({
      ...formData,
      time: value && value.format(format),
    });
  };

  //Creating a quote request with form data and selected client information
  const handleSubmit = (event) => {
    event.preventDefault();

    const requestBody = {
      clientId: selectedClientId,
      clientFirstName: selectedClientDetails.firstName,
      clientLastName: selectedClientDetails.lastName,
      address: selectedClientDetails.address,
      ...formData,
    };

    axios.post('http://localhost:8080/quoteRequests', requestBody, {
      headers: {
        // @ts-ignore
        'X-XSRF-TOKEN': auth.getXsrfToken()
      }
    })
      .then(response => {
        console.log(response.data);
        window.location.href = 'http://localhost:3000/';

      })
      .catch(error => {
        console.error('Error:', error);
      });
  };

  //Handling change in filtering
  const handleSearchTermChange = (event) => {
    setSearchTerm(event.target.value);
    filterClients(event.target.value);
  };

  //Filtering functionality
  const filterClients = (term) => {
    const filtered = clients.filter(client =>
      client.firstName.toLowerCase().includes(term.toLowerCase()) ||
      client.lastName.toLowerCase().includes(term.toLowerCase())
    );
    setFilteredClients(filtered);
  };

  //Dropdown for clients
  const renderClientOptions = () => {
    return filteredClients.map(client => (
      <option key={client.clientId} value={client.clientId}>
        {client.firstName} {client.lastName}
      </option>
    ));
  };

  //Dropdown for services
  const renderServiceOptions = () => {
    const serviceOptions = Object.keys(serviceOptionsMapping);

    return serviceOptions.map(option => (
      <option key={option} value={serviceOptionsMapping[option]}>
        {option}
      </option>
    ));
  };

  //Checks if the day is valid
  const isDateValid = (date) => {
    return moment(date).isSameOrAfter(moment(), 'day');
  };


  //Returns a list of hours that should be disabled
  const closedHours = () => {
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

 //Returns time slots that are already taken
  const isTimeTaken = () => {
    const chosenDay = moment(formData.date).dayOfYear();
    const chosenYear = moment(formData.date).year();

  
    console.log("Chosen day is " + chosenDay);
  
    // Initialize the array to store taken hours and minutes
    const takenTimeSlots = [];
  
    // Iterate through all existing quote requests
    existingQuoteRequests.forEach(request => {
      const requestYear = moment(request.date).year();
      let requestDay = moment(request.date).dayOfYear();
      requestDay += 1; // Adjust day because dayOfYear is 1-based
      const requestTime = moment(request.time, format);
  
      console.log("Already created days are " + requestDay);
      console.log("Hours and minute are " + requestTime.hour() + " " + requestTime.minute());
  
      if (chosenDay === requestDay && chosenYear === requestYear) {
        console.log("They are the same day");
  
        // Add the taken hour and minute to the array
        takenTimeSlots.push({
          hour: requestTime.hour(),
          minute: requestTime.minute(),
        });
      }
    });
  
    console.log(takenTimeSlots);
  
    return takenTimeSlots;
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
            <select className='form-select' id='client-select' value={selectedClientId} onChange={handleClientChange}>
              <option value="" >Select a client</option>
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
              onChange={handleTimeChange}
              format={format}
              use12Hours
              className='form-time'
              allowEmpty={false}
              min={moment().hours(9).minutes(0)}
              max={moment().hours(19).minutes(59)}
              minuteStep={15}

             
               disabledHours={() => closedHours()}
              
              disabledMinutes={(hour) => isTimeTaken().filter(slot => slot.hour === hour).map(slot => slot.minute)}
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

export default CreateQuoteRequestAdmin;

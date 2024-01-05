import React, { useState, useEffect } from 'react';
import Helmet from 'react-helmet';
import axios from 'axios';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import TimePicker from 'rc-time-picker';
import 'rc-time-picker/assets/index.css';
import moment from 'moment'; 
import ClientNavbar from '../components/ClientNavbar';
import { useParams } from 'react-router-dom';
import '../styles/CreateQuoteRequest.css';
import Footer from '../components/Footer';
import { useAuth } from '../security/Components/AuthProvider';


const format = 'h:mm a';

function CreateQuoteRequestClient() {

  const auth = useAuth();

  //storing the existing quote requests (Dates)
  const [existingQuoteRequests, setExistingQuoteRequests] = useState([]);

  //storing the data from the form
  const [formData, setFormData] = useState({
    description: '',
    service: '',
    date: new Date(),
    time: new Date(),
  });

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

  const [hasProfile, setHasProfile] = useState(false);

  //Check if user is logged in and has profile
  useEffect(() => {
    // @ts-ignore
    if (!auth.isAuthenticated) {
      window.location.href = 'http://localhost:3000/';
    }

    checkIfProfileExists().then((r) => {
      console.log(r);
    });
  }, []);

  const checkIfProfileExists = async () => {
    //check if user exists
    await axios
      .get('http://localhost:8080/users/client?simpleCheck=true', {
        headers: {
          // @ts-ignore
          'X-XSRF-TOKEN': auth.getXsrfToken(),
        },
      })
      .then((r) => {
        console.log(r.status);
        if (r.status === 200) {
          setHasProfile(true);
          console.log('Does he have a profile? ' + hasProfile);
          //check if user has profile
          axios
            .get('http://localhost:8080/users/client', {
              headers: {
                // @ts-ignore
                'X-XSRF-TOKEN': auth.getXsrfToken(),
              },
            })
            .then((r) => {
              if (r.status === 200) {
                setHasProfile(true);
              }
            })
            .catch((e) => {
              console.log(e);
            });
        } else {
          //redirect to create profile page
          setHasProfile(false);
          window.location.href = 'http://localhost:3000/profile';

        }
      })
      .catch((e) => {
        //redirect to create profile page
        console.log(e);
        setHasProfile(false);
        window.location.href = 'http://localhost:3000/profile';

        
      });
  };


  useEffect(() => {
    // Fetching existing quote requests dates
    axios.get("http://localhost:8080/quoteRequests/dates", {
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



  //creating a new quote request
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
        window.location.href = 'http://localhost:3000/';

      })
      .catch(error => {
        console.error('Error:', error);
      });
  };


// dropdown for services
  const renderServiceOptions = () => {
    const serviceOptions = Object.keys(serviceOptionsMapping);

    return serviceOptions.map(option => (
      <option key={option} value={serviceOptionsMapping[option]}>
        {option}
      </option>
    ));
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

  //making sure day is valid
  const isDateValid = (date) => {
    return moment(date).isSameOrAfter(moment(), 'day');
  };

//returning an array of hours that should be disabled based on day of week
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

 //returning the time slots that are already taken for that day
  const isTimeTaken = () => {

    // day chosen in form
    const chosenDay = moment(formData.date).dayOfYear();
    //year chosen in form
    const chosenYear = moment(formData.date).year();

  
    // Initialize the array to store taken hours and minutes
    const takenTimeSlots = [];
  
    // Iterate through all existing quote requests
    existingQuoteRequests.forEach(request => {
      const requestYear = moment(request.date).year();

      // storing day from existing quote request
      let requestDay = moment(request.date).dayOfYear();
      requestDay += 1; // Adjust day because dayOfYear is 1-based

      //storing time from existing quote request
      const requestTime = moment(request.time, format);
  
      //Checking if date chosen from user is a day that has existing quote requests during it
      if (chosenDay === requestDay && chosenYear === requestYear) {
  
        // Add the taken hour and minute to the array
        takenTimeSlots.push({
          hour: requestTime.hour(),
          minute: requestTime.minute(),
        });
      }
    });
  
  
    return takenTimeSlots;
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
              onChange={handleTimeChange}
              format={format}
              use12Hours
              className='form-time'
              allowEmpty={false}
              min={moment().hours(9).minutes(0)}
              max={moment().hours(19).minutes(59)}
              minuteStep={15}

             //disabling hours that CTA is closed
              disabledHours={() => closedHours()}
              
              //disabling time slots that are already taken
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

export default CreateQuoteRequestClient;

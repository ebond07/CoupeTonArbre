import { Helmet } from 'react-helmet';
import ClientNavbar from '../components/ClientNavbar';
import Footer from '../components/Footer';
import axios from 'axios';
import { useAuth } from '../security/Components/AuthProvider';
import Cookies from 'js-cookie';
import React, { useEffect, useState } from 'react';
import swal from 'sweetalert';
import '../styles/Profile.css'; 

function Profile() {
  const auth = useAuth();
  const [hasProfile, setHasProfile] = useState(false);

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
          axios
            .get('http://localhost:8080/users/client', {
              headers: {
                // @ts-ignore
                'X-XSRF-TOKEN': auth.getXsrfToken(),
              },
            })
            .then((r) => {
              if (r.status === 200) {
                console.log(r.data);
                document.getElementById('firstNameText').textContent = r.data.firstName;
                document.getElementById('lastNameText').textContent = r.data.lastName;
                document.getElementById('emailText').textContent = r.data.email;
                document.getElementById('phoneNumberText').textContent = r.data.phoneNumber;
                document.getElementById('addressText').textContent = r.data.address;
              }
            })
            .catch((e) => {
              console.log(e);
            });
        } else {
          setHasProfile(false);
          
        }
      })
      .catch((e) => {
        console.log(e);
        setHasProfile(false);
        
      });
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    const data = new FormData(event.currentTarget);
    const body = {
      firstName: data.get('firstName'),
      lastName: data.get('lastName'),
      email: data.get('email'),
      phoneNumber: data.get('phoneNumber'),
      address: data.get('address'),
    };

    axios
      .post('http://localhost:8080/users/client', body, {
        headers: {
          // @ts-ignore
          'X-XSRF-TOKEN': auth.getXsrfToken(),
        },
      })
      .then((r) => {
        if (r.status === 201) {
          console.log(r.data);
          Cookies.set('email', r.data.email);
          swal('Profile Created Successfully', '', 'success');
          setHasProfile(true);
          setTimeout(() => {
            window.location.href = 'http://localhost:3000/profile';
          }, 2000);
        }
      })
      .catch((e) => {
        let errors = '';

        if (e.response.data.errors !== undefined) {
          // @ts-ignore
          e.response.data.errors.forEach((error) => {
            errors += error.defaultMessage + '\n';
          });
        }

        swal('Error!', 'A or multiple errors present !\n' + errors, 'error').then((r) => {
          // @ts-ignore
          auth.authError(e.response.status);
        });
      });
  };

  const handleUpdateButtonClick = () => {
    window.location.href = `/profile/update`;

  }

  const renderFormFields = () => {
    if (hasProfile) {
      return (
        <>
          <div>
            <h1> Your Profile </h1>
          </div>
          <div className="profile-client-form">
            <div className="row w-75 m-auto">
              <label>
                First Name: <span id="firstNameText" className="info-text"></span>
              </label>
            </div>
            <div className="row w-75 m-auto">
              <label>
                Last Name: <span id="lastNameText" className="info-text"></span>
              </label>
            </div>
            <div className="row w-75 m-auto">
              <label>
                Email: <span id="emailText" className="info-text"></span>
              </label>
            </div>
            <div className="row w-75 m-auto">
              <label>
                Phone: <span id="phoneNumberText" className="info-text"></span>
              </label>
            </div>
            <div className="row w-75 m-auto">
              <label>
                Address: <span id="addressText" className="info-text"></span>
              </label>
            </div>
            <div>
            <button className="update-btn" type="button" onClick={handleUpdateButtonClick}>
              Update Your Information
            </button>

            </div>
          </div>
        </>
      );
    } else {
      return (
        <>
          <div className='title-info'>
            <h1>Hello and Welcome to CTA!</h1>
            <p>To get started, please fill out the following form with your information</p>
          </div>
          <br></br>
          <div className="profile-create-form">
            <div className="row w-75 m-auto">
              <label>
                First Name:
                <input
                  onKeyDown={(e) => {
                    e.key === 'Enter' && e.preventDefault();
                  }}
                  type="text"
                  name="firstName"
                  className="form-control mb-2 input-field"
                  placeholder="First Name"
                  id="firstName"
                />
              </label>
            </div>
            <div className="row w-75 m-auto">
              <label>
                Last Name:
                <input
                  onKeyDown={(e) => {
                    e.key === 'Enter' && e.preventDefault();
                  }}
                  type="text"
                  name="lastName"
                  className="form-control mb-2 input-field"
                  placeholder="Last Name"
                  id="lastName"
                />
              </label>
            </div>
            <div className="row w-75 m-auto">
              <label>
                Email:
                <input
                  onKeyDown={(e) => {
                    e.key === 'Enter' && e.preventDefault();
                  }}
                  type="text"
                  name="email"
                  className="form-control mb-2 input-field"
                  placeholder="Email"
                  id="email"
                  required={true}
                />
              </label>
            </div>
            <div className="row w-75 m-auto">
              <label>
                Phone:
                <input
                  onKeyDown={(e) => {
                    e.key === 'Enter' && e.preventDefault();
                  }}
                  type="text"
                  name="phoneNumber"
                  className="form-control mb-2 input-field"
                  placeholder="Phone"
                  id="phoneNumber"
                />
              </label>
            </div>
            <div className="row w-75 m-auto">
              <label>
                Address:
                <input
                  onKeyDown={(e) => {
                    e.key === 'Enter' && e.preventDefault();
                  }}
                  type="text"
                  name="address"
                  className="form-control mb-2 input-field"
                  placeholder="Address"
                  id="address"
                  required={true}
                />
              </label>
            </div>
            <div className="row w-25 m-auto" style={{ margin: 'auto', width: '50%', marginTop: '20px', marginBottom: '120px' , marginLeft: '190px'}}>
              <button className="save-btn" type="submit">
                Submit
              </button>
            </div>
          </div>
        </>
      );
    }
  };

  return (
    <div>
      <Helmet>
        <title>Profile - CTA</title>
      </Helmet>
      <div id="nav-container">
        <ClientNavbar />
        <div>
          <form onSubmit={handleSubmit}>
            {renderFormFields()}
          </form>
        </div>
        <Footer />
      </div>
    </div>
  );
}

export default Profile;

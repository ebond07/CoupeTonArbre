import React from 'react';
import { Helmet } from 'react-helmet';
import AdminNavbar from '../components/AdminNavbar';
import ClientNavbar from '../components/ClientNavbar';
import MainNavbar from '../components/MainNavbar';

import {useAuth} from "../security/Components/AuthProvider";
import Footer from '../components/Footer';
import '../styles/HomePage.css'

function HomePage() {

  const auth = useAuth();

  return (
    <div>
      <Helmet>
        <title>Home - Coupe Ton Arbre</title>
      </Helmet>

      {
        !auth.isAuthenticated &&
        <div id='nav-container'>
          <MainNavbar />
        </div>
      }

      {
        // @ts-ignore
        auth.userRoles().includes("Admin") &&
        <div id='nav-container'>
          <AdminNavbar />
        </div>
      }

      {
        // @ts-ignore
        auth.userRoles().includes("Client") &&
        <div id='nav-container'>
          <ClientNavbar />
        </div>
      }
      
      <div className='header'>
        <h1>4 Steps Until The Job Is Done</h1>
      </div>
      <div className='steps-container'>
        <div className='step step1'>
          <div className='step-number'>1</div>
          <div className='step-content'>
          <h2>Create an Account/Login</h2>
          <p>
            In order to access our services you must first create your account and then log in.
          </p>
        </div>
        </div>
        <div className='step step2'>
          <div className='step-number'>2</div>
          <div className='step-content'>
          <h2>Request A Quote</h2>
          <p>
            Upon signup, access our quote request page, choose a convenient time, and count on us to be there!
          </p>
          </div>
        </div>
        <div className='step step3'>
          <div className='step-number'>3</div>
          <div className='step-content'>
          <h2>Schedule An Appointment</h2>
          <p>
            After one of our employees gives you a quote, you can now schedule 
            an appointment with the data and time that aligns best with your schedule!
          </p>
        </div>
        </div>
        <div className='step step4'>
          <div className='step-number'>4</div>
          <div className='step-content'>
          <h2>Pay After Service</h2>
          <p>
            Once we finish up the job, one of our employees will allow you to pay instantly
            and on site!
          </p>
        </div>
        </div>
      </div>
      <br/>
      <br/>
      <br/>
      <br/>
      <div>
        <Footer />
      </div>
    </div>
  );
}

export default HomePage;

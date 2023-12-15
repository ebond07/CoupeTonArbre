import React from 'react';
import Navbar from './Navbar';
import Footer from './Footer';

function AppointmentsAdmin() {
  return (
    <div>
      <div id='nav-container'>
        <Navbar />
      </div>
      
      <h2>Welcome to the Appointments Page</h2>
      <p>This is the content of the appointments page.</p>

      <div>
        <Footer />
      </div>
    </div>
  );
}

export default AppointmentsAdmin;

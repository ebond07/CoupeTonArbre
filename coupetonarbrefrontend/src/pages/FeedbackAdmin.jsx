import React from 'react';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
function FeedbackAdmin() {
  return (
    <div>
      <div id='nav-container'>
        <Navbar />
      </div>
      
      <h2>Welcome to the Feedback Page</h2>
      <p>This is the content of the feedback page.</p>
      <div>
        <Footer />
      </div>
    </div>
  );
}

export default FeedbackAdmin;

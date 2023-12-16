
import React from 'react';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
function QuotesAdmin() {
  return (
    <div>
      <div id='nav-container'>
        <Navbar />
      </div>

      <h2>Welcome to the Quotes Page</h2>
      <p>This is the content of the quote page.</p>
      <div>
        <Footer />
      </div>
    </div>
  );
}

export default QuotesAdmin;
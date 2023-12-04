import React from 'react';
import Navbar from './Navbar';

function HomePage() {
  return (
    <div>
      <div id='nav-container'>
        <Navbar />
      </div>
      
      <h2>Welcome to the Home Page</h2>
      <p>This is the content of the home page.</p>
    </div>
  );
}

export default HomePage;

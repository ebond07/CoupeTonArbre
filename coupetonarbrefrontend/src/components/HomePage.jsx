import React from 'react';

function HomePage() {
  return (
    <div>
      <div id='nav-container'>
        <nav>
          <ul>
            <li><a href='HomePage.jsx'>Home</a></li>
            <li><a href=''>Appointments</a></li>
            <li><a href='HomePage.jsx'>Quote Requests</a></li>
            <li><a href='AccountsAdmin.jsx'>Accounts</a></li>
            <li><a href='HomePage.jsx'>Feedback</a></li>
            <li><a href='HomePage.jsx'>Log out</a></li>
          </ul>
        </nav>
      </div>
      
      <h2>Welcome to the Home Page</h2>
      <p>This is the content of the home page.</p>
    </div>
  );
}

export default HomePage;

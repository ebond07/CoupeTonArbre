import React from 'react';

function Navbar() {
  return (
    <nav>
      <ul>
        <li><a href='HomePage.jsx'>Home</a></li>
        <li><a href='AppointmentsAdmin.jsx'>Appointments</a></li>
        <li><a href='QuotesAdmin.jsx'>Quote Requests</a></li>
        <li><a href='AccountsAdmin.jsx'>Accounts</a></li>
        <li><a href='FeedbackAdmin.jsx'>Feedback</a></li>
        <li>Log out</li>
      </ul>
    </nav>
  );
}

export default Navbar;

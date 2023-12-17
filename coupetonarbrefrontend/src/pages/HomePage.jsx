import React from 'react';
import { Helmet } from 'react-helmet';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';

function HomePage() {
  return (
    <div>
      <Helmet>
        <title>Home - Coupe Ton Arbre</title>
      </Helmet>
      <div id='nav-container'>
        <Navbar />
      </div>
      
      <h2>Welcome to the Home Page</h2>
      <p>This is the content of the home page.</p>
      <br/> 
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
      <br/>
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

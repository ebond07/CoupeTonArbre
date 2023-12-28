import React from 'react';
import { Helmet } from 'react-helmet';
import AdminNavbar from '../components/AdminNavbar';
import ClientNavbar from '../components/ClientNavbar';
import MainNavbar from '../components/MainNavbar';

import {useAuth} from "../security/Components/AuthProvider";
import Footer from '../components/Footer';

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

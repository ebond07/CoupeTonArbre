import { Link } from 'react-router-dom';
import React from 'react';

import background from "../images/navbackgroundDark.png";
import styles from "../styles/Navbar.module.css"
import GlobalStyles from '../styles/GlobalStyles';
import CTALogo from "../images/ctaLogo.png";

import {useAuth} from "../security/Components/AuthProvider";



function Navbar() {
  const auth = useAuth()

  return (
    <>
    <GlobalStyles />


   <nav style={{ backgroundImage: `url(${background})` }} className={styles.nav}>

    <img src={CTALogo} alt="CTA Logo" className={styles.CTALogo} />

     <ul className={styles.ul}>
       <li><Link to='/' className={styles.li}>Home</Link></li>
       {
        // @ts-ignore
        auth.userRoles().includes("Admin") &&
        <li><Link to='/appointments' className={styles.li}>Appointments</Link></li>

        }

        {
        // @ts-ignore
        auth.userRoles().includes("Admin") &&
        <li><Link to='/quotes' className={styles.li}>Quote Requests</Link></li>
        }
       {
        // @ts-ignore
        auth.userRoles().includes("Admin") &&
        <li><Link to='/accounts' className={styles.li}>Accounts</Link></li>
        }

        {
        // @ts-ignore
        auth.userRoles().includes("Admin") &&
        <li><Link to='/feedback' className={styles.li}>Feedback</Link></li>
        }

       <li><Link to='/profile' className={styles.li}>Profile</Link></li>
       <li><Link to="http://localhost:8080/oauth2/authorization/okta" className={styles.li}>Login</Link></li>

       <li className={styles.li}>Log out</li>
     </ul>
   </nav>
   </>
  );
 }

export default Navbar;
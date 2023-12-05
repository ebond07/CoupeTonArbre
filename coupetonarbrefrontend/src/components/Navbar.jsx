import { Link } from 'react-router-dom';
import React from 'react';

import background from "../images/navbackground.png";
import styles from "../styles/Navbar.module.css"

function Navbar() {
  return (
   <nav style={{ backgroundImage: `url(${background})` }} className={styles.nav}>
     <ul className={styles.ul}>
       <li><Link to='/' className={styles.li}>Home</Link></li>
       <li><Link to='/appointments' className={styles.li}>Appointments</Link></li>
       <li><Link to='/quotes' className={styles.li}>Quote Requests</Link></li>
       <li><Link to='/accounts' className={styles.li}>Accounts</Link></li>
       <li><Link to='/feedback' className={styles.li}>Feedback</Link></li>
       <li className={styles.li}>Log out</li>
     </ul>
   </nav>
  );
 }

export default Navbar;
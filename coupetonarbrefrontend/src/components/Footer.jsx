import React from "react";
import styles from '../styles/Footer.module.css'
import background from "../images/hedgeWallDark1.png";
import CTALogo from "../images/ctaLogoZoomed.png";

const Footer = () => {
    return (
        <div className={styles.footer} style={{ backgroundImage: `url(${background})`}}>
            <div className={styles.links}>
                <div className={styles.links_div_img}>
                    <img src={CTALogo} alt="CTA Logo" className={styles.CTALogo} />
                </div>
                <div className={styles.links_div}>
                    <h4>Website</h4>
                    <a className={styles.links_a} href="localhost:3000">
                        <p>Home</p>
                    </a>
                    <a className={styles.links_a} href="localhost:3000">
                        <p>Our Work</p>
                    </a>
                    <a className={styles.links_a} href="localhost:3000">
                        <p>My Profile</p>
                    </a>
                    <a className={styles.links_a} href="localhost:3000">
                        <p>FR</p>
                    </a>
                </div>
                <div className={styles.links_div}>
                    <h4>Services We Offer</h4>
                    <p>Hedge, Shrub & Cone Trimming</p>
                    <p>Hedge & Tree Pruning</p>
                </div>
                <div className={styles.links_div}>
                    <h4>Contact</h4>
                    <a className={styles.links_a} href="https://maps.app.goo.gl/vkK4JbvrPjtQ4qeeA">
                        <p>7816 Champlain BLVD, Lasalle</p>
                    </a>
                    <a className={styles.links_a} href="tel:4385046055">
                        <p>(438) 504-6055</p>
                    </a>
                </div>
                <div className={styles.links_div}>
                    <h4>Hours</h4>
                    <p>Week: 8 A.M. - 8 P.M.</p>
                    <p>Saturday: 9 A.M. - 6 P.M.</p>
                    <p>Sunday: 9 A.M. - 5 P.M.</p>
                </div>
            </div>

            <hr></hr>

            <div className={styles.footer_below}>
                <div className={styles.footer_copyright}>
                    <p>
                        @{new Date().getFullYear()} Coupe Ton Arbre. All rights reserved.
                    </p>
                </div>
            </div>
        </div>
    );
};

export default Footer;

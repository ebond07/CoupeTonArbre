import React from "react";
import {
    Box,
    FooterContainer,
    Row,
    Column,
    FooterLink,
    Heading,
} from "../styles/FooterStyles";
import styles from "../styles/Footer.module.css";
import background from "../images/footerBackground.png";
import CTALogo from "../images/ctaLogo.png";

const Footer = () => {
    return (
        <>
            <Box style={{ background: `url(${background})` }}>
                <FooterContainer>
                    {/* Add Row and CTALogo */}
                    <Row>
                        {/* CTALogo */}
                        <img src={CTALogo} alt="CTA Logo" className={styles.ctaLogo} />
                        
                        {/* Columns */}
                        <Column>
                            <Heading>Website</Heading>
                            <FooterLink href="localhost:3000">
                                Home
                            </FooterLink>
                            <FooterLink href="localhost:3000">
                                Our Work
                            </FooterLink>
                            <FooterLink href="localhost:3000">
                                My Profile
                            </FooterLink>
                        </Column>
                        <Column>
                            <Heading>Services We Offer</Heading>
                            <p className={styles.p}>Hedge, Shrub and Cone Trimming</p>
                            <p className={styles.p}>Hedge and Tree Pruning</p>
                        </Column>
                        <Column>
                            <Heading>Information</Heading>
                            <FooterLink href="https://maps.app.goo.gl/LTn9qqvowwBVMdkm8">
                                7816 Champlain BLVD, Lasalle, QC H8P 3V9
                            </FooterLink>
                            <p className={styles.p}>438 504-6055</p>
                        </Column>
                        <Column>
                            <Heading>Hours</Heading>
                            <p className={styles.p}>Monday - Friday: 8 A.M. - 8 P.M.</p>
                            <p className={styles.p}>Saturday: 9 A.M. - 6 P.M.</p>
                            <p className={styles.p}>Sunday: 9 A.M. - 5 P.M.</p>
                        </Column>
                    </Row>
                </FooterContainer>
            </Box>
        </>
    );
};

export default Footer;

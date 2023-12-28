import { Helmet } from 'react-helmet';
import ClientNavbar from '../components/ClientNavbar';
import Footer from '../components/Footer';
function Profile(){
    return (
        <div>
            <Helmet>
                <title>Profile - CTA</title>
            </Helmet>
            <div id='nav-container'>
                <ClientNavbar />
                <h2>Welcome to the Profile Page</h2>
                <p>This is the content of the profile page.</p>
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
                <Footer />
            </div>
        </div>
    )
}

export default Profile
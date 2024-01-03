import { BrowserRouter, Route, Routes } from 'react-router-dom';

import HomePage from './pages/HomePage';
import AccountsAdmin from './pages/AccountsAdmin';
import AppointmentsAdmin from './pages/AppointmentsAdmin';
import QuotesAdmin from './pages/QuotesAdmin';
import FeedbackAdmin from './pages/FeedbackAdmin';
import Profile from './pages/Profile';
import UpdateClientAdmin from './pages/UpdateClientAdmin';
import UpdateProfile from './pages/UpdateProfile';
import CreateQuoteRequestAdmin from './pages/CreateQuoteRequestAdmin';
import CreateQuoteRequestClient from './pages/CreateQuoteRequestClient';



import {AuthProvider} from "./security/Components/AuthProvider";
import VerifyRedirect from "./security/verifyRedirect";



function App() {
  
  return (
    <div className="App">
       <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/admin/appointments" element={<AppointmentsAdmin />} />
          <Route path="/admin/quotes" element={<QuotesAdmin />} />
          <Route path="/admin/accounts" element={<AccountsAdmin />} />
          <Route path="/admin/feedback" element={<FeedbackAdmin />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/profile/update" element={<UpdateProfile />} />

          <Route path="/verify" element={<VerifyRedirect />} />

          <Route path="/admin/updateClientAdmin/:clientId" element={<UpdateClientAdmin />} />

          <Route path="/admin/newQuoteRequest" element={<CreateQuoteRequestAdmin />} />
          <Route path="/client/newQuoteRequest" element={<CreateQuoteRequestClient />} />



        </Routes>
      </BrowserRouter>
      </AuthProvider>
    </div>
  );
}

export default App;

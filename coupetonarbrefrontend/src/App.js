import { BrowserRouter, Route, Routes } from 'react-router-dom';

import HomePage from './pages/HomePage';
import AccountsAdmin from './pages/AccountsAdmin';
import AppointmentsAdmin from './pages/AppointmentsAdmin';
import QuotesAdmin from './pages/QuotesAdmin';
import FeedbackAdmin from './pages/FeedbackAdmin';
import Profile from './pages/Profile';
import UpdateClientAdmin from './pages/UpdateClientAdmin';

import {AuthProvider} from "./security/Components/AuthProvider";
import VerifyRedirect from "./security/verifyRedirect";



function App() {
  
  return (
    <div className="App">
       <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/appointments" element={<AppointmentsAdmin />} />
          <Route path="/quotes" element={<QuotesAdmin />} />
          <Route path="/accounts" element={<AccountsAdmin />} />
          <Route path="/feedback" element={<FeedbackAdmin />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/verify" element={<VerifyRedirect />} />

          <Route path="/updateClientAdmin/:clientId" element={<UpdateClientAdmin />} />

        </Routes>
      </BrowserRouter>
      </AuthProvider>
    </div>
  );
}

export default App;

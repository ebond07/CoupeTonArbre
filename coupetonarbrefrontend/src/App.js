import { BrowserRouter, Route, Routes } from 'react-router-dom';

import HomePage from './components/HomePage';
import AccountsAdmin from './components/AccountsAdmin';
import AppointmentsAdmin from './components/AppointmentsAdmin';
import QuotesAdmin from './components/QuotesAdmin';
import FeedbackAdmin from './components/FeedbackAdmin';
import Profile from './components/Profile';
import UpdateClientAdmin from './pages/UpdateClientAdmin';

function App() {
  
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/appointments" element={<AppointmentsAdmin />} />
          <Route path="/quotes" element={<QuotesAdmin />} />
          <Route path="/accounts" element={<AccountsAdmin />} />
          <Route path="/feedback" element={<FeedbackAdmin />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/updateClientAdmin/:clientId" element={<UpdateClientAdmin />} />

          {/* <Route path="/register" element={<RegisterPage />} />
          <Route path="/" element={<LandingPage />} /> */}
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;

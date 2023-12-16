import { BrowserRouter, Route, Routes } from 'react-router-dom';

import HomePage from './pages/HomePage';
import AccountsAdmin from './pages/AccountsAdmin';
import AppointmentsAdmin from './pages/AppointmentsAdmin';
import QuotesAdmin from './pages/QuotesAdmin';
import FeedbackAdmin from './pages/FeedbackAdmin';
import Profile from './pages/Profile';
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

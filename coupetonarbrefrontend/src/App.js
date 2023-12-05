import { BrowserRouter, Route, Routes } from 'react-router-dom';

import HomePage from './components/HomePage';
import AccountsAdmin from './components/AccountsAdmin';
import AppointmentsAdmin from './components/AppointmentsAdmin';
import QuotesAdmin from './components/QuotesAdmin';
import FeedbackAdmin from './components/FeedbackAdmin';

function App() {
  
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/HomePage.jsx" element={<HomePage />} />
          <Route path="/AppointmentsAdmin.jsx" element={<AppointmentsAdmin />} />
          <Route path="/QuotesAdmin.jsx" element={<QuotesAdmin />} />
          <Route path="/AccountsAdmin.jsx" element={<AccountsAdmin />} />
          <Route path="/FeedbackAdmin.jsx" element={<FeedbackAdmin />} />
          {/* <Route path="/register" element={<RegisterPage />} />
          <Route path="/" element={<LandingPage />} /> */}
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;

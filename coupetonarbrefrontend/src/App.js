import { useRoutes } from 'react-router-dom';
import HomePage from './components/HomePage';
import AccountsAdmin from './components/AccountsAdmin';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

function App() {
  
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/AccountsAdmin.jsx" element={<AccountsAdmin />} />
          {/* <Route path="/register" element={<RegisterPage />} />
          <Route path="/" element={<LandingPage />} /> */}
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;

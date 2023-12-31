import React from 'react';
import ReactDOM from 'react-dom';

import './index.css';
import { Auth0Provider } from '@auth0/auth0-react';
import App from './App';
import reportWebVitals from './reportWebVitals';
import axios from "axios";

axios.defaults.withCredentials = true;

ReactDOM.render(
  <React.StrictMode>
    <Auth0Provider
        domain="dev-4hcoszrtvq0f1jnk.us.auth0.com"
        clientId="EIc8cVj2k1DY1My8oSpdgQhK8Utjk443"
        authorizationParams={{
          audience: '',
          redirect_uri: window.location.origin
        }}
    >
      <App />
    </Auth0Provider>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();

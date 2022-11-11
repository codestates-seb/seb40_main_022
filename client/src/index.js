import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import Globalstyle from './style/Globalstyle';
import {BrowserRouter} from 'react-router-dom';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <Globalstyle />
      <App />
    </BrowserRouter>
  </React.StrictMode>
);

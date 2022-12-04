import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import { BrowserRouter } from 'react-router-dom';
import { Provider } from 'react-redux';
import store from './redux/store/store';
import App from './App';
import Globalstyle from './style/Globalstyle';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
    <Provider store={store}>
      <Globalstyle />
      <App />
    </Provider>
  </BrowserRouter>,
);

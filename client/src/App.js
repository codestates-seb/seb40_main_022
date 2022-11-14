import './App.css';
import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Main from './pages/main/Main';
import QnaAsk from './pages/Qna/QnaAsk';
import Login from './pages/Login/Login';
import SignUp from './pages/SignUp/SignUp';
import Dailypost from './components/dailypost/dailypost';
import Lank from './pages/Lank/Lank';

function App() {
  return (
    <Routes>
      <Route path="/" element={<Main />} />
      <Route path="/qnaask" element={<QnaAsk />} />
      <Route path="/login" element={<Login />} />
      <Route path="/signup" element={<SignUp />} />
      <Route path="/dailypost" element={<Dailypost />} />
      <Route path="/lank" element={<Lank />} />
    </Routes>
  );
}

export default App;

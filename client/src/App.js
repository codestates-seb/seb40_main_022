import './App.css';
import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Main from './pages/main/Main';
import Calendar from './pages/calendar/Calendar';
import Detail from './pages/calendar/Detail';
import Login from './pages/Login/Login';
import SignUp from './pages/SignUp/SignUp';
import Dailypost from './pages/dailypost/dailypost';
import Lank from './pages/Lank/Lank';
import QnaList from './pages/Qna/QnaList';
import QnaDetail from './pages/Qna/QnaDetail';
import QnaAsk from './pages/Qna/QnaAsk';

function App() {
  return (
    <Routes>
      <Route path="/" element={<Main />} />
      <Route path="/qnaask" element={<QnaAsk />} />
      <Route path="/login" element={<Login />} />
      <Route path="/signup" element={<SignUp />} />
      <Route path="/dailypost" element={<Dailypost />} />
      <Route path="/lank" element={<Lank />} />
      <Route path="/qna" element={<QnaList />} />
      <Route path="/record" element={<Calendar />} />
      <Route path="/detail" element={<Detail />} />
      <Route path="/qnadetail" element={<QnaDetail />} />
    </Routes>
  );
}

export default App;

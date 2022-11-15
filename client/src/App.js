import './App.css';
import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Main from './pages/main/Main';
import Calendar from './pages/calendar/Calendar';
import Detail from './pages/calendar/Detail';
import QnaAsk from './pages/qna/QnaAsk';
import Login from './pages/login/Login';
import SignUp from './pages/signUp/SignUp';
import Dailypost from './pages/dailypost/dailypost';
import Lank from './pages/lank/Lank';
import QnaList from './pages/qna/QnaList';
import QnaDetail from './pages/qna/QnaDetail';

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

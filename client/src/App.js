import './App.css';
import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Main from './pages/main/Main';
<<<<<<< HEAD
import QnaAsk from './pages/qna/QnaAsk';
import Login from './pages/login/Login';
import SignUp from './pages/signUp/SignUp';
import Dailypost from './pages/dailypost/dailypost';
import Lank from './pages/lank/Lank';
import QnaList from './pages/qna/QnaList';
import Calendar from './pages/calendar/Calendar';
import Detail from './pages/calendar/Detail';
=======
import QnaAsk from './pages/Qna/QnaAsk';
import Login from './pages/Login/Login';
import SignUp from './pages/SignUp/SignUp';
import Dailypost from './components/dailypost/dailypost';
import Lank from './pages/Lank/Lank';
import QnaList from './pages/Qna/QnaList';
import QnaDetail from './pages/Qna/QnaDetail';
>>>>>>> 5af3fc769e01d7582026ef859c3dfa79f01fd2f1

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
<<<<<<< HEAD
      <Route path="/record" element={<Calendar />} />
      <Route path="/detail" element={<Detail />} />
=======
      <Route path="/qnadetail" element={<QnaDetail />} />
>>>>>>> 5af3fc769e01d7582026ef859c3dfa79f01fd2f1
    </Routes>
  );
}

export default App;

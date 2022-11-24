import './App.css';
import React, { useEffect } from 'react';
import { Routes, Route } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import Main from './pages/main/Main';
import MainEdit from './pages/dailypost/DailyEdit';
import Calendar from './pages/calendar/Calendar';
import Detail from './pages/calendar/Detail';
import Login from './pages/login/Login';
import SignUp from './pages/signup/SignUp';
import Dailypost from './pages/dailypost/dailypost';
import Lank from './pages/lank/Lank';
import QnaList from './pages/qna/QnaList';
import QnaDetail from './pages/qna/QnaDetail';
import QnaAsk from './pages/qna/QnaAsk';
import Mypage from './pages/mypage/Mypage';
import ProfileEdit from './pages/profileedit/index';
import QnaUpdate from './pages/qna/QnaUpdate';
import { ReLodingLogin } from './redux/action/LoginAsync';

function App() {
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(ReLodingLogin());
  }, []);
  return (
    <Routes>
      <Route path="/" element={<Main />} />
      <Route path="/dailypost/edit/:id" element={<MainEdit />} />
      <Route path="/qnaask" element={<QnaAsk />} />
      <Route path="/login" element={<Login />} />
      <Route path="/signup" element={<SignUp />} />
      <Route path="/mypage" element={<Mypage />} />
      <Route path="/mypage/edit" element={<ProfileEdit />} />
      <Route path="/dailypost" element={<Dailypost />} />
      <Route path="/lank" element={<Lank />} />
      <Route path="/qna" element={<QnaList />} />
      <Route path="/record" element={<Calendar />} />
      <Route path="/detail" element={<Detail />} />
      <Route path="/qnadetail/:id" element={<QnaDetail />} />
      <Route path="/qnaupdate/:id" element={<QnaUpdate />} />
    </Routes>
  );
}

export default App;

import './App.css';
import React, { useEffect, Suspense } from 'react';
import { Routes, Route } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import Loading from './components/Loading/Loading';
import { ReLodingLogin } from './redux/action/LoginAsync';

const MainEdit = React.lazy(() => import('./pages/dailypost/DailyEdit'));
const Calendar = React.lazy(() => import('./pages/calendar/Calendar'));
const Detail = React.lazy(() => import('./pages/calendar/Detail'));
const Login = React.lazy(() => import('./pages/login/Login'));
const SignUp = React.lazy(() => import('./pages/signup/SignUp'));
const Dailypost = React.lazy(() => import('./pages/dailypost/dailypost'));
const Lank = React.lazy(() => import('./pages/lank/Lank'));
const QnaList = React.lazy(() => import('./pages/qna/QnaList'));
const QnaDetail = React.lazy(() => import('./pages/qna/QnaDetail'));
const QnaAsk = React.lazy(() => import('./pages/qna/QnaAsk'));
const Mypage = React.lazy(() => import('./pages/mypage/Mypage'));
const ProfileEdit = React.lazy(() => import('./pages/profileedit/index'));
const QnaUpdate = React.lazy(() => import('./pages/qna/QnaUpdate'));
const UserProfile = React.lazy(() => import('./pages/userprofile/index'));
const Update = React.lazy(() => import('./pages/calendar/Update'));
const Main = React.lazy(() => import('./pages/main/Main'));

function App() {
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(ReLodingLogin());
  }, []);
  return (
    <Suspense fallback={<Loading />}>
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/dailypost/edit/:id" element={<MainEdit />} />
        <Route path="/qnaask" element={<QnaAsk />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/mypage" element={<Mypage />} />
        <Route path="/members/:id" element={<UserProfile />} />
        <Route path="/mypage/edit" element={<ProfileEdit />} />
        <Route path="/dailypost" element={<Dailypost />} />
        <Route path="/lank" element={<Lank />} />
        <Route path="/qna" element={<QnaList />} />
        <Route path="/record" element={<Calendar />} />
        <Route path="/detail" element={<Detail />} />
        <Route path="/detail/:id" element={<Update />} />
        <Route path="/qnadetail/:id" element={<QnaDetail />} />
        <Route path="/qnaupdate/:id" element={<QnaUpdate />} />
      </Routes>
    </Suspense>
  );
}

export default App;

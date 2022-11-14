import './App.css';
import { Routes, Route } from 'react-router-dom';
import QnaList from './pages/Qna/QnaList';
import QnaAsk from './pages/Qna/QnaAsk';

function App() {
  return (
    <Routes>
      <Route path="/" element={<QnaList />} />
      <Route path="/qnaask" element={<QnaAsk />} />
    </Routes>
  );
}

// Pull하기 전에 QnaList 를 Main으로 바꾸고 임포트지우기

export default App;

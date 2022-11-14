import './App.css';
import { Routes, Route } from 'react-router-dom';
import Main from './pages/main/Main';
import QnaAsk from './pages/Qna/QnaAsk';

function App() {
  return (
    <Routes>
      <Route path="/" element={<Main />} />
      <Route path="/qnaask" element={<QnaAsk />} />
    </Routes>
  );
}

export default App;

import React from 'react';
import { useNavigate } from 'react-router-dom';
import Header from '../../components/header/Header';
import Footer from '../../components/footer/Footer';
import {
  QnaPostBack,
  QnaPost,
  PostTitle,
  PostContent,
  PostTag,
  PostSubmit,
} from './QnaAskstyle';

function QnaAsk() {
  const navigate = useNavigate();
  return (
    <QnaPostBack>
      <Header />
      <QnaPost>
        <PostTitle>
          <div>
            <h2>제목</h2>
            <input />
          </div>
        </PostTitle>
        <PostContent>
          <div>
            <h2>내용</h2>
            <textarea
              className={`block whitespace-pre-wrap w-full bg-white text-gray-700 border border-black py-2 px-2 mb-3 leading-tight focus:border focus:border-pz-pt-1 `}
            />
          </div>
        </PostContent>
        <PostTag>
          <div>
            <h2>태그</h2>
            <button className="oneButton">운동</button>
            <button>식단</button>
            <button>영양소</button>
            <button>헬스</button>
            <button>습관</button>
          </div>
        </PostTag>
        <PostSubmit>
          <button>등록</button>
          <button onClick={() => navigate('/qna')}>취소</button>
        </PostSubmit>
      </QnaPost>
      <Footer />
    </QnaPostBack>
  );
}

export default QnaAsk;

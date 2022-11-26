import React, { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import Header from '../../components/header/Header';
import Footer from '../../components/footer/Footer';
import {
  QnaPost,
  QnaPostBack,
  PostContent,
  PostSubmit,
  PostTag,
  PostTitle,
} from './QnaUpdatestyle';
import { QnaAsynclistPatch } from '../../redux/action/QnaAsync';

function QnaUpdate() {
  const taglist = ['운동', '식단', '영양소', '헬스', '습관', '신고'];
  const list = useSelector(state => state.qnalist.list);
  console.log(list);
  const Id = useParams();
  const dispatch = useDispatch();
  const [title, setTitle] = useState(list[+Id.id].title);
  const [content, setContent] = useState(list[+Id.id].summary);
  const [tag, setTag] = useState(list[+Id.id].tag);
  const formdata = new FormData();
  formdata.append('title', title);
  formdata.append('content', content);
  formdata.append('tag', tag);
  const dataUp = [formdata, list[+Id.id].questionId];
  const navigate = useNavigate();
  return (
    <QnaPostBack>
      <Header />
      <QnaPost>
        <PostTitle>
          <div>
            <h2>제목</h2>
            <input value={title} onChange={e => setTitle(e.target.value)} />
          </div>
        </PostTitle>
        <PostContent>
          <div>
            <h2>내용</h2>
            <textarea
              className={`block whitespace-pre-wrap w-full bg-white text-gray-700 border border-black py-2 px-2 mb-3 leading-tight focus:border focus:border-pz-pt-1 `}
              value={content}
              onChange={e => {
                setContent(e.target.value);
              }}
            />
          </div>
        </PostContent>
        <PostTag>
          <div>
            <h2>태그</h2>
            {taglist &&
              taglist.map(data => {
                return (
                  <button
                    className={tag === data ? 'oneButton' : null}
                    onClick={e => {
                      setTag(e.target.innerText);
                    }}
                  >
                    {data}
                  </button>
                );
              })}
          </div>
        </PostTag>
        <PostSubmit>
          <button
            onClick={() => {
              dispatch(QnaAsynclistPatch(dataUp));
              navigate('/qna');
            }}
          >
            등록
          </button>
          <button onClick={() => navigate('/qna')}>취소</button>
        </PostSubmit>
      </QnaPost>
      <Footer />
    </QnaPostBack>
  );
}

export default QnaUpdate;

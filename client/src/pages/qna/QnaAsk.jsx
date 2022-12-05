import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import uuidv4 from 'react-uuid';
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
import { QnaAsynclistPost } from '../../redux/action/QnaAsync';

function QnaAsk() {
  const taglist = ['식단', '영양소', '헬스', '습관', '신고'];
  const navigate = useNavigate();
  const [tag, setTag] = useState('');
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const dispatch = useDispatch();
  const formdata = new FormData();
  const ac = localStorage.getItem('Authorization');

  formdata.append('title', title);
  formdata.append('content', content);
  formdata.append('tag', tag);
  const handleSubmit = () => {
    if (!ac) {
      alert('로그인 후 이용할 수 있습니다.');
    } else if (ac && title.length <= 2) {
      alert('제목을 3글자 이상 입력해 주세요');
    } else if (ac && content.length <= 4) {
      alert('내용을 5글자 이상 입력해 주세요');
    } else {
      dispatch(QnaAsynclistPost({ formdata }));
      navigate('/questions');
    }
  };

  return (
    <QnaPostBack>
      <Header />
      <QnaPost>
        <PostTitle>
          <div>
            <h2>제목</h2>
            <input
              value={title}
              placeholder="제목을 입력하세요."
              onChange={e => {
                setTitle(e.target.value);
              }}
            />
          </div>
        </PostTitle>
        <PostContent>
          <div>
            <h2>내용</h2>
            <textarea
              className={`block whitespace-pre-wrap w-full bg-white text-gray-700 border border-black py-2 px-2 mb-3 leading-tight focus:border focus:border-pz-pt-1 `}
              value={content}
              placeholder="내용을 입력하세요."
              onChange={e => {
                setContent(e.target.value);
              }}
            />
          </div>
        </PostContent>
        <PostTag>
          <div className="tagcontainer">
            {taglist &&
              taglist.map(data => {
                return (
                  <button
                    className={tag === data ? 'oneButton' : null}
                    onClick={e => {
                      setTag(e.target.textContent);
                    }}
                    key={uuidv4}
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
              handleSubmit();
            }}
          >
            등록
          </button>
          <button onClick={() => navigate('/questions')}>취소</button>
        </PostSubmit>
      </QnaPost>
      <Footer />
    </QnaPostBack>
  );
}

export default QnaAsk;

import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import Header from '../../components/header/Header';
import Footer from '../../components/footer/Footer';
import {
  Detail,
  Headerwrap,
  DetailAnswer,
  DetailBack,
  DetailComment,
  DetailNDB,
  DetailSubmit,
  DetailTitle,
  AnswerNDB,
  DetailUpdate,
  DetailDelete,
  DetailButton,
} from './QnaDetailStyle';
import {
  QnaDetaillistdelete,
  QnaDetailCommentAsync,
  QnaDetailAsync,
  QnaanswerDetaildelete,
} from '../../redux/action/QnaAsync';

function QnaDetail() {
  const [content, setContent] = useState('');
  const list = useSelector(state => state.qnalist.list);
  const answer = useSelector(state => state.qnalist.answers.answers);
  const Id = useParams();
  const data = list[+Id.id].questionId;
  const dispatch = useDispatch();
  const Upanswer = [data, content];
  const handleAnswer = () => {
    dispatch(QnaDetailCommentAsync(Upanswer))
      .unwrap()
      .then(() => {
        setTimeout(dispatch(QnaDetailAsync({ data })), 2000);
      });
  };
  useEffect(() => {
    dispatch(QnaDetailAsync({ data }));
  }, []);

  return (
    <Detail>
      <Headerwrap>
        <Header />
      </Headerwrap>
      <DetailBack>
        <DetailTitle>
          <h2>{list[+Id.id].title}</h2>
          <h3>{list[+Id.id].title}</h3>
          <div />
          <section>
            <DetailNDB>
              <div>
                <h4>{list[+Id.id].member.username}</h4>
                <h4>{list[+Id.id].createdAt}</h4>
              </div>
              <button>{list[+Id.id].tag}</button>
            </DetailNDB>
            <DetailButton>
              <DetailUpdate>
                <Link to={`/qnaupdate/${+Id.id}`} className="qnaupdate">
                  <h3>수정</h3>
                </Link>
              </DetailUpdate>
              <DetailDelete>
                <Link
                  to="/qna"
                  className="qnadelete"
                  onClick={() => {
                    dispatch(QnaDetaillistdelete(data));
                  }}
                >
                  <h3>삭제</h3>
                </Link>
              </DetailDelete>
            </DetailButton>
          </section>
        </DetailTitle>
        <DetailAnswer>
          {answer &&
            answer.map(ansdata => {
              return (
                <>
                  <h2>답변 {ansdata.length}</h2>
                  <h3>{ansdata.content}</h3>
                  <AnswerNDB>
                    <div>
                      <h4>{ansdata.answerWriter.username}</h4>
                      <h4>{ansdata.createdAt}</h4>
                    </div>
                    <button
                    // className={ansdata.accepted ? 'accepted' : 'noaccepted'}
                    >
                      {ansdata.accepted ? 'V' : '채택'}
                    </button>
                    <div>
                      <button>수정</button>
                      <button
                        onClick={() => {
                          const answerid = [data, ansdata.answerWriter.id];
                          console.log(answerid);
                          dispatch(QnaanswerDetaildelete(answerid));
                        }}
                      >
                        삭제
                      </button>
                    </div>
                  </AnswerNDB>
                </>
              );
            })}
        </DetailAnswer>
        <DetailComment>
          <h2>답변 작성</h2>
          <textarea
            type="text"
            placeholder="답변을 입력해주세요!"
            value={content}
            onChange={e => {
              setContent(e.target.value);
            }}
          />
        </DetailComment>
        <DetailSubmit
          onClick={() => {
            handleAnswer();
          }}
        >
          등록
        </DetailSubmit>
      </DetailBack>
      <Footer />
    </Detail>
  );
}

export default QnaDetail;

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
  QnaanswerAccept,
} from '../../redux/action/QnaAsync';

function QnaDetail() {
  const list = useSelector(state => state.qnalist.list);
  const answer = useSelector(state => state.qnalist.answers.answers);
  const [content, setContent] = useState('');
  const Id = useParams();
  const data = list[+Id.id].questionId;
  const dispatch = useDispatch();
  const Upanswer = [data, content];
  const detaillist = useSelector(state => state.qnalist.detail);

  // 날짜 바꾸기
  function leftPad(value) {
    if (value >= 10) {
      return value;
    }
    return `0${value}`;
  }

  function toStringByFormatting(source, delimiter = '-') {
    const year = source.getFullYear();
    const month = leftPad(source.getMonth() + 1);
    const day = leftPad(source.getDate());

    return [year, month, day].join(delimiter);
  }

  const handleAnswer = () => {
    dispatch(QnaDetailCommentAsync(Upanswer));
    setContent('');
  };
  const handleAccept = id => {
    dispatch(QnaanswerAccept(id));
  };
  const handleDelete = id => {
    dispatch(QnaanswerDetaildelete(id));
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
          <h2>{detaillist && detaillist.title}</h2>
          <h3>{detaillist && detaillist.content}</h3>
          <div />
          <section>
            <DetailNDB>
              <div>
                <h4>
                  {detaillist && detaillist.questionWriter !== undefined
                    ? detaillist.questionWriter.username
                    : null}
                </h4>
                <h4>{detaillist && detaillist.createdAt}</h4>
              </div>
              <button>{detaillist && detaillist.tag}</button>
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
              const AcId = [data, ansdata.answerId];
              return (
                <>
                  <h2>답변 {ansdata.length}</h2>

                  <h4>{ansdata.content}</h4>
                  <AnswerNDB>
                    <div>
                      <h4>{ansdata.answerWriter.username}</h4>
                      <h4>
                        {' '}
                        {toStringByFormatting(new Date(ansdata.createdAt))}
                      </h4>
                    </div>
                    <button
                      onClick={() => {
                        handleAccept(AcId);
                      }}
                    >
                      {ansdata.accepted ? 'V' : '채택'}
                    </button>
                    <div>
                      <button onClick={() => {}}>수정</button>
                      <button
                        onClick={() => {
                          handleDelete(AcId);
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

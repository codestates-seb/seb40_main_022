import React, { useEffect } from 'react';
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
  // AnswerNDB,
  DetailUpdate,
  DetailDelete,
  DetailButton,
} from './QnaDetailStyle';
import { QnaAsynclist, QnaDetaillistdelete } from '../../redux/action/QnaAsync';

function QnaDetail() {
  const list = useSelector(state => state.qnalist.list);
  const ac = useSelector(state => state.authToken.accessToken);
  const re = useSelector(state => state.authToken.token);
  const Id = useParams();
  const data = [list[+Id.id].questionId, ac, re];
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(QnaAsynclist());
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
          {/* {list[+Id.id].answers.map(data => {
            return (
              <>
                <h2>답변 {list[+Id.id].answers.length}</h2>
                <h3>{data.content}</h3>
                <AnswerNDB>
                  <div>
                    <h4>{data.answerWriter.username}</h4>
                    <h4>{data.createdAt}</h4>
                  </div>
                  <button className={data.accepted ? 'accepted' : 'noaccepted'}>
                    {data.accepted ? 'V' : null}
                  </button>
                </AnswerNDB>
              </>
            );
          })} */}
        </DetailAnswer>
        <DetailComment>
          <h2>답변 작성</h2>
          <textarea type="text" placeholder="답변을 입력해주세요!" />
        </DetailComment>
        <DetailSubmit>등록</DetailSubmit>
      </DetailBack>
      <Footer />
    </Detail>
  );
}

export default QnaDetail;

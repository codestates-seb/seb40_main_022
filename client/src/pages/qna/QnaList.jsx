import { Link, useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { useEffect } from 'react';
import Header from '../../components/header/Header';
import searchIcon from '../../images/searchIcon.png';
import Footer from '../../components/footer/Footer';
import {
  QnABack,
  Qna,
  QnaBan,
  QnaTitle,
  QnaSearch,
  QnaContent,
  QnaRadio,
} from './QnaStyle';
import { QnaAsynclist } from '../../redux/action/QnaAsync';

function QnaList() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const list = useSelector(state => state.qnalist.list);
  console.log(list);
  useEffect(() => {
    dispatch(QnaAsynclist());
  }, []);

  return (
    <QnABack>
      <Header />
      <Qna>
        <QnaBan />
        <QnaTitle>
          <h1>QnA</h1>
          <button
            onClick={() => {
              navigate('/qnaask');
            }}
          >
            질문
          </button>
        </QnaTitle>
        <QnaSearch>
          <input id="SearchIn" placeholder="찾으시는 질문이 있으신가요?" />
          <label htmlFor="SearchIn">
            <img src={searchIcon} alt="검색아이콘" className="search" />
          </label>
        </QnaSearch>
        <QnaRadio>
          <label>
            <input type="radio" id="newest" name="contact" defaultChecked />
            <span>최신 순</span>
          </label>
          <label>
            <input type="radio" id="popularity" name="contact" />
            <span>인기 순</span>
          </label>
        </QnaRadio>
        <QnaContent>
          {list &&
            list.map((data, idx) => {
              return (
                <div className="qnabox">
                  <article>
                    <div>
                      <Link to={`/qnadetail/${idx}`} className="titlename">
                        {data.title}
                      </Link>
                      <h3>{data.summary}</h3>
                    </div>
                    <span>
                      <h3 className="answerfont">답변 : {data.answerCount}</h3>
                      <h3>{data.member.username}</h3>
                      <h3>{data.createdAt}</h3>
                      <button>{data.tag}</button>
                    </span>
                  </article>
                  <span />
                </div>
              );
            })}
        </QnaContent>
      </Qna>
      <Footer />
    </QnABack>
  );
}

export default QnaList;

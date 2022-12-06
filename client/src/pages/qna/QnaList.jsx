import { Link, useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { useEffect, useState } from 'react';
import uuidv4 from 'react-uuid';
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
import { QnaAsynclist, QnaSearchreload } from '../../redux/action/QnaAsync';
import Pagination from './Pagination';

function QnaList() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const list = useSelector(state => state.qnalist.list);
  const questiondata = useSelector(state => state.qnalist.search);
  const items = useSelector(state => state.qnalist.pageInfo);
  const [search, setSearch] = useState('');
  const [sort, setSort] = useState(0);
  const [result, setResult] = useState(false);
  const [currentPage, setCurrentPage] = useState(1);
  const datasearch = [search, sort, currentPage];

  const currentPageHandler = p => {
    setCurrentPage(p);
    dispatch(QnaAsynclist(p));
    const searchlist = [search, sort, p];
    dispatch(QnaSearchreload(searchlist));
  };
  useEffect(() => {
    dispatch(QnaAsynclist(currentPage));
  }, []);

  const handleSearch = () => {
    if (search === '') {
      setResult(false);
    } else {
      setResult(true);
    }
    dispatch(QnaSearchreload(datasearch));
  };

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

  return (
    <QnABack>
      <Header />
      <QnaBan />
      <Qna>
        <QnaTitle>
          <h1>QnA</h1>
          <button
            onClick={() => {
              navigate('/questions/postup');
            }}
          >
            질문
          </button>
        </QnaTitle>
        <QnaSearch>
          <input
            id="SearchIn"
            type="text"
            placeholder="찾으시는 질문이 있으신가요?"
            onChange={e => setSearch(e.target.value)}
          />
          <button onClick={() => handleSearch()}>
            <img src={searchIcon} alt="검색아이콘" className="search" />
          </button>
        </QnaSearch>
        <div className="content">
          <QnaRadio>
            <label>
              <input
                type="radio"
                id="newest"
                name="contact"
                defaultChecked
                onClick={() => setSort(0)}
              />
              <span>관련도 순</span>
            </label>
            <label>
              <input
                type="radio"
                id="newest"
                name="contact"
                onClick={() => setSort(1)}
              />
              <span>최신 순</span>
            </label>
            <label>
              <input
                type="radio"
                id="popularity"
                name="contact"
                onClick={() => setSort(2)}
              />
              <span>인기 순</span>
            </label>
          </QnaRadio>
          <QnaContent>
            {result
              ? questiondata.map((data, idx) => {
                  return (
                    <div className="qnabox" key={uuidv4()}>
                      <article>
                        <div>
                          <Link to={`/questions/${idx}`} className="titlename">
                            {data.title}
                          </Link>
                          <h3>{data.summary}</h3>
                        </div>
                        <span>
                          <h3 className="answerfont">
                            답변 : {data.answerCount}
                          </h3>
                          <h3>{data.member.username}</h3>
                          <h3>{data.createdAt}</h3>
                          <button>{data.tag}</button>
                        </span>
                      </article>
                    </div>
                  );
                })
              : list &&
                list.map((data, idx) => {
                  return (
                    <div className="qnabox" key={uuidv4()}>
                      <article>
                        <div>
                          <Link to={`/questions/${idx}`} className="titlename">
                            {data.title}
                          </Link>
                          <h3>{data.summary}</h3>
                        </div>
                        <span>
                          <h3 className="answerfont">
                            답변 : {data.answerCount}
                          </h3>
                          <h3>{data.member.username}</h3>
                          <h3>
                            {' '}
                            {toStringByFormatting(new Date(data.createdAt))}
                          </h3>
                          <button>{data.tag}</button>
                        </span>
                      </article>
                      <span />
                    </div>
                  );
                })}
          </QnaContent>
          <Pagination
            currentPage={currentPage}
            currentPageHandler={currentPageHandler}
            items={items}
          />
        </div>
      </Qna>
      <Footer />
    </QnABack>
  );
}

export default QnaList;

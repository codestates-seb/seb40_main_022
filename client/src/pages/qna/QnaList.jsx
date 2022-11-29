import { Link, useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { useEffect, useState } from 'react';
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
  const [search, setSearch] = useState('');
  const [sort, setSort] = useState('recent');
  const [result, setResult] = useState(false);

  // const [size, setSize] = useState(10);
  // const [currentPage, setCurrentPage] = useState(1);
  // const [paginationLength, setPaginationLength] = useState(3);

  // const sizeHandler = per => setSize(per);
  // const currentPageHandler = p => setCurrentPage(p);

  const datasearch = [search, sort];
  useEffect(() => {
    dispatch(QnaAsynclist());
  }, []);

  const handleSearch = () => {
    if (search === '') {
      setResult(false);
    } else {
      setResult(true);
    }
    dispatch(QnaSearchreload(datasearch));
  };

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
        <QnaRadio>
          <label>
            <input
              type="radio"
              id="newest"
              name="contact"
              defaultChecked
              onClick={() => setSort('recent')}
            />
            <span>최신 순</span>
          </label>
          <label>
            <input
              type="radio"
              id="popularity"
              name="contact"
              onClick={() => setSort('hot')}
            />
            <span>인기 순</span>
          </label>
        </QnaRadio>
        <QnaContent>
          {result
            ? questiondata.map((data, idx) => {
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
                        <h3 className="answerfont">
                          답변 : {data.answerCount}
                        </h3>
                        <h3>{data.member.username}</h3>
                        <h3>{data.createdAt}</h3>
                        <button>{data.tag}</button>
                      </span>
                    </article>
                    <span />
                  </div>
                );
              })
            : list &&
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
                        <h3 className="answerfont">
                          답변 : {data.answerCount}
                        </h3>
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
        <Pagination />
      </Qna>
      <Footer />
    </QnABack>
  );
}

export default QnaList;

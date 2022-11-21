import { Link, useNavigate } from 'react-router-dom';
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

function QnaList() {
  const navigate = useNavigate();
  const list = [
    {
      title: '오늘 렛풀다운을 했는데 잘 먹지 않네요....',
      content:
        '바벨을 자기 어깨너비보다 약간 넓게 잡고, 살짝 데드리프트 식으로 약간의 가동 범위만 주었다가 바로 양쪽 겹갑골을 조인다는 느낌으로 등 상부를 강하게 ....',
      Totalans: '답변 2',
      username: '헬린이',
      today: '2022.11.11',
      tag: '운동',
    },
    {
      title: '오늘 렛풀다운을 했는데 잘 먹지 않네요....',
      content:
        '바벨을 자기 어깨너비보다 약간 넓게 잡고, 살짝 데드리프트 식으로 약간의 가동 범위만 주었다가 바로 양쪽 겹갑골을 조인다는 느낌으로 등 상부를 강하게 ....',
      Totalans: '답변 2',
      username: '헬린이',
      today: '2022.11.11',
      tag: '운동',
    },
    {
      title: '오늘 렛풀다운을 했는데 잘 먹지 않네요....',
      content:
        '바벨을 자기 어깨너비보다 약간 넓게 잡고, 살짝 데드리프트 식으로 약간의 가동 범위만 주었다가 바로 양쪽 겹갑골을 조인다는 느낌으로 등 상부를 강하게 ....',
      Totalans: '답변 2',
      username: '헬린이',
      today: '2022.11.11',
      tag: '운동',
    },
    {
      title: '오늘 렛풀다운을 했는데 잘 먹지 않네요....',
      content:
        '바벨을 자기 어깨너비보다 약간 넓게 잡고, 살짝 데드리프트 식으로 약간의 가동 범위만 주었다가 바로 양쪽 겹갑골을 조인다는 느낌으로 등 상부를 강하게 ....',
      Totalans: '답변 2',
      username: '헬린이',
      today: '2022.11.11',
      tag: '운동',
    },
  ];

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
            list.map(data => {
              return (
                <div className="qnabox">
                  <article>
                    <div>
                      <Link to="/qnadetail" className="titlename">
                        {data.title}
                      </Link>
                      <h3>{data.content}</h3>
                    </div>
                    <span>
                      <h3 className="answerfont">{data.Totalans}</h3>
                      <h3>{data.username}</h3>
                      <h3>{data.today}</h3>
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

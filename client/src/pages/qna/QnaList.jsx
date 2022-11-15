import { Link, useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import QnaBanner from '../../images/QnaBanner.jpg';
import QnAImg from '../../images/qnaImg.jpg';
import facebook from '../../images/facebook.png';
import instagram from '../../images/instagram.png';
import twitter from '../../images/twitter.png';
import Header from '../../components/header/Header';

const QnABack = styled.main`
  width: 100%;
  height: auto;
`;

const Qna = styled.main`
  max-width: 1200px;
  width: 100%;
  height: auto;
  margin: 0 auto;
  padding-top: 80px;
`;

const QnaBan = styled.div`
  width: 100%;
  background: url(${QnaBanner});
  height: 150px;
  background-size: cover;
  background-repeat: no-repeat;
  background-position: 50% 38%;
`;

const QnaTitle = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-between;
  margin-top: 50px;
  > h1 {
    font-weight: 600;
  }
  > button {
    font-size: var(--font-18);
    background-color: var(--logored);
    width: 150px;
    height: 60px;
    color: white;
    border: none;
    border-radius: 20px;
    cursor: pointer;
  }
`;

const QnaSearch = styled.input`
  width: 750px;
  height: 50px;
  border: 1px solid #959595;
  border-radius: 50px;
  margin-top: 30px;
`;

const QnaRadio = styled.div`
  width: 100%;
  margin: 30px 0;
  font-size: var(--font-23);
  line-height: 2rem;
  padding: 2em 0.4em;
  vertical-align: middle;
  > label {
    margin-right: 15px;
    > input {
      margin-right: 10px;
      border: max(2px, 0.1em) solid gray;
    }
  }
`;

const QnaContent = styled.section`
  width: 100%;
  display: flex;
  margin-bottom: 40px;
  > :nth-child(1) {
    width: 70%;
    > div {
      display: block;
      width: 780px;
      .titlename {
        font-size: var(--font-24);
        font-weight: bold;
        text-decoration: none;
        color: black;
      }
      > h3 {
        font-size: var(--font-18);
        font-weight: 300;
        opacity: 0.7;
        margin: 25px 0;
      }
    }
    > span {
      display: flex;
      > h3 {
        font-size: var(--font-15);
        font-weight: 600;
        margin-right: 25px;
        margin-top: 5px;
        cursor: pointer;
      }
      > button {
        background-color: var(--tagyellow);
        border: none;
        width: 50px;
        height: 30px;
        font-weight: 600;
        cursor: pointer;
      }
    }
  }
  > span {
    margin-left: 170px;
    width: 215px;
    height: 150px;
    background-image: url(${QnAImg});
    background-size: cover;
    background-repeat: no-repeat;
    background-position: 50% 55%;
  }
`;

const QnaFoo = styled.section`
  width: 100%;
  margin-top: 100px;
`;

const Footi = styled.footer`
  width: 100%;
  background-color: var(--footerblack);
  > div {
    max-width: 1200px;
    padding-top: 155px;
    margin: 0 auto;
    display: flex;
    justify-content: space-between;
    @media screen and (max-width: 700px) {
      padding-top: 0px;
      padding: 30px;
      height: 210px;
      flex-direction: column;
    }
    .footer-left {
      .footer-images {
        > img {
          margin-right: 15px;
          margin-bottom: 10px;
        }
      }
      .footer-callenge {
        font-style: italic;
        font-weight: 700;
        color: var(--footerfont);
      }
    }
    .footer-right {
      display: flex;
      flex-direction: column;
      .right-one {
        margin-bottom: 5px;
      }
      > span {
        font-weight: 700;
        color: var(--footerfont);
      }
    }
  }
`;

function QnaList() {
  const navigate = useNavigate();

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
            Ask Question
          </button>
        </QnaTitle>
        <QnaSearch />
        <QnaRadio>
          <label>
            <input type="radio" id="최신 순" name="contact" value="최신 순" />
            <span>최신 순</span>
          </label>
          <label>
            <input type="radio" id="인기 순" name="contact" value="인기 순" />
            <span>인기 순</span>
          </label>
        </QnaRadio>
        <QnaContent>
          <article>
            <div>
              <Link to="/qnadetail" className="titlename">
                오늘 렛풀다운을 했는데 잘 먹지 않네요....
              </Link>
              <h3>
                바벨을 자기 어깨너비보다 약간 넓게 잡고, 살짝 데드리프트 식으로
                약간의 가동 범위만 주었다가 바로 양쪽 겹갑골을 조인다는 느낌으로
                등 상부를 강하게 ....
              </h3>
            </div>
            <span>
              <h3>답변 2</h3>
              <h3>헬린이</h3>
              <h3>2022.11.11</h3>
              <button>운동</button>
            </span>
          </article>
          <span />
        </QnaContent>
        <QnaContent>
          <article>
            <div>
              <Link to="/qnadetail" className="titlename">
                오늘 렛풀다운을 했는데 잘 먹지 않네요....
              </Link>
              <h3>
                바벨을 자기 어깨너비보다 약간 넓게 잡고, 살짝 데드리프트 식으로
                약간의 가동 범위만 주었다가 바로 양쪽 겹갑골을 조인다는 느낌으로
                등 상부를 강하게 ....
              </h3>
            </div>
            <span>
              <h3>답변 2</h3>
              <h3>헬린이</h3>
              <h3>2022.11.11</h3>
              <button>운동</button>
            </span>
          </article>
          <span />
        </QnaContent>
        <QnaContent>
          <article>
            <div>
              <Link to="/qnadetail" className="titlename">
                오늘 렛풀다운을 했는데 잘 먹지 않네요....
              </Link>
              <h3>
                바벨을 자기 어깨너비보다 약간 넓게 잡고, 살짝 데드리프트 식으로
                약간의 가동 범위만 주었다가 바로 양쪽 겹갑골을 조인다는 느낌으로
                등 상부를 강하게 ....
              </h3>
            </div>
            <span>
              <h3>답변 2</h3>
              <h3>헬린이</h3>
              <h3>2022.11.11</h3>
              <button>운동</button>
            </span>
          </article>
          <span />
        </QnaContent>
        <QnaContent>
          <article>
            <div>
              <Link to="/qnadetail" className="titlename">
                오늘 렛풀다운을 했는데 잘 먹지 않네요....
              </Link>
              <h3>
                바벨을 자기 어깨너비보다 약간 넓게 잡고, 살짝 데드리프트 식으로
                약간의 가동 범위만 주었다가 바로 양쪽 겹갑골을 조인다는 느낌으로
                등 상부를 강하게 ....
              </h3>
            </div>
            <span>
              <h3>답변 2</h3>
              <h3>헬린이</h3>
              <h3>2022.11.11</h3>
              <button>운동</button>
            </span>
          </article>
          <span />
        </QnaContent>
      </Qna>
      <QnaFoo>
        <Footi>
          <div>
            <div className="footer-left">
              <div className="footer-images">
                <img src={facebook} alt="facebook-logo" />
                <img src={instagram} alt="instagram-logo" />
                <img src={twitter} alt="twitter.png" />
              </div>
              <span className="footer-callenge">
                Copyright @ 2022 Fit Challenge
              </span>
            </div>
            <div className="footer-right">
              <span className="right-one">팀장 | 최윤우</span>
              <span>
                팀원 | 한승진, 고하나, 고정훈, 김신재, 방인석, 조현식{' '}
              </span>
            </div>
          </div>
        </Footi>
      </QnaFoo>
    </QnABack>
  );
}

export default QnaList;

import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import rcddetailbtn from '../../images/rcddetailbtn.png';
import rcddecordminus from '../../images/rcddecordminus.png';

const DetailBox = styled.div`
  width: 100%;
  height: 100%;
`;
const DetailMain = styled.main`
  padding-top: 60px;
  max-width: 500px;
  width: 100%;
  margin: 0 auto;
  .InputBox {
    width: 100%;
    height: 200px;
    margin-top: 20px;
    background-color: #f6f6f6;
    border-radius: 10px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    .InBoxs {
      width: 100%;
      height: 30px;
      margin: 5px 0px;
      display: flex;
      align-items: center;
      .title {
        width: 70px;
        margin-left: 20px;
      }
      > input {
        width: 75%;
        padding: 5px;
        border: none;
        outline: none;
        border-radius: 20px;
        box-shadow: var(--box-shadow);
      }
    }
    .boxsfooter {
      width: 100%;
      height: 30px;
      margin: 5px 0px;
      display: flex;
      justify-content: space-between;
      align-items: center;
      > button {
        cursor: pointer;
        width: 120px;
        height: 30px;
        color: white;
        background-color: var(--logored);
        border-radius: 20px;
        border: none;
        box-shadow: var(--box-shadow);
        margin-right: 35px;
      }
      .Inselect {
        width: 200px;
        .title {
          width: 70px;
          margin-left: 20px;
          margin-right: 42px;
        }
        > select {
          width: 80px;
          padding: 5px;
          border: none;
          outline: none;
          border-radius: 20px;
          box-shadow: var(--box-shadow);
        }
      }
    }
  }
  .setHead {
    margin-top: 10px;
    padding: 30px 0px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    > button {
      cursor: pointer;
      border: none;
      background-color: #fff;
      > img {
        width: 36px;
        height: 36px;
      }
    }
  }
  .setInfo {
    display: flex;
    flex-direction: column;
    > div {
      width: 100%;
      height: 50px;
      display: flex;
      align-items: center;
      justify-content: space-around;
      margin-bottom: 10px;
      border: 1px solid red;
      border-radius: 10px;
      background-color: var(--logored);
      color: white;
      font-weight: 600;
    }
  }
  .buttons {
    width: 100%;
    height: 40px;
    > button {
      width: 50px;
      height: 30px;
      margin: 6px;
      border: none;
      cursor: pointer;
      box-shadow: var(--box-shadow);
      :active {
        background-color: #fcddb0;
      }
    }
  }
  .subcan {
    width: 100%;
    height: 40px;
    margin-top: 20px;
    margin-bottom: 40px;
    display: flex;
    justify-content: end;
    > button {
      cursor: pointer;
      width: 120px;
      height: 30px;
      border: none;
      background-color: var(--logored);
      color: white;
      font-weight: 600;
      border-radius: 20px;
      box-shadow: var(--box-shadow);
    }
    .submit {
      margin-right: 10px;
    }
  }
`;
function Detail() {
  const list = [
    { title: '랫풀다운', time: 20, set: '20회/5세트' },
    { title: '랫풀다운', time: 20, set: '20회/5세트' },
    { title: '랫풀다운', time: 20, set: '20회/5세트' },
    { title: '랫풀다운', time: 20, set: '20회/5세트' },
    { title: '랫풀다운', time: 20, set: '20회/5세트' },
    { title: '랫풀다운', time: 20, set: '20회/5세트' },
    { title: '랫풀다운', time: 20, set: '20회/5세트' },
  ];
  const btns = ['등', '가슴', '어깨', '하체', '팔', '전신', '유산소', '기타'];
  const navigate = useNavigate();
  const [clicked, setClicked] = useState(false);
  return (
    <DetailBox>
      <Header />
      <DetailMain>
        {clicked ? (
          <section className="InputBox">
            <div className="InBoxs">
              <span className="title">운동</span>
              <input />
            </div>
            <div className="InBoxs">
              <span className="title">운동시간</span>
              <input />
            </div>
            <div className="InBoxs">
              <span className="title">세트수</span>
              <input />
            </div>
            <div className="boxsfooter">
              <div className="Inselect">
                <span className="title">운동</span>
                <select>
                  {btns &&
                    btns.map(data => {
                      return <option>{data}</option>;
                    })}
                </select>
              </div>
              <button>등록</button>
            </div>
          </section>
        ) : null}
        <section className="setHead">
          <h2>운동 상세 정보</h2>
          <button
            onClick={() => {
              setClicked(!clicked);
            }}
          >
            <img
              src={clicked ? rcddecordminus : rcddetailbtn}
              alt="추가 버튼"
            />
          </button>
        </section>
        <section className="setInfo">
          {list &&
            list.map(data => {
              return (
                <div>
                  <span>{data.title}</span>
                  <span>{data.time}</span>
                  <span>{data.set}</span>
                </div>
              );
            })}
        </section>
        <section className="buttons">
          {btns &&
            btns.map(data => {
              return <button>{data}</button>;
            })}
        </section>
        <section className="subcan">
          <button className="submit">등록</button>
          <button
            className="cancle"
            onClick={() => {
              navigate('/record');
            }}
          >
            취소
          </button>
        </section>
      </DetailMain>
      <Footer />
    </DetailBox>
  );
}

export default Detail;

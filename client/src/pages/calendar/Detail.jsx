import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { DetailBox, DetailMain } from './Style';
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import rcddetailbtn from '../../images/rcddetailbtn.png';
import rcddecordminus from '../../images/rcddecordminus.png';

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

import styled from 'styled-components';
import FullCalendar from '@fullcalendar/react';
import dayGridPlugin from '@fullcalendar/daygrid';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import crown from '../../images/crown.png';

const MainBox = styled.main`
  width: 100%;
  margin-bottom: 50px;
`;
const CalBox = styled.section`
  margin: 0 auto;
  padding-top: 70px;
  max-width: 900px;
  width: 100%;
  .calen {
    margin-bottom: 10px;
    width: 100%;
    height: 820px;
  }
  .calendar {
    margin: 0 auto;
    max-width: 900px;
    width: 100%;
    height: 750px;
    .fc-day-sun a {
      color: red;
    }

    /* 토요일 날짜: 파란색 */
    .fc-day-sat a {
      color: blue;
    }
  }
  .calevent {
    height: 100px;
  }
  .userbox {
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    bottom: 0px;
    .deletebtn {
      display: flex;
      justify-content: end;
      margin-top: 10px;
      > button {
        width: 110px;
        height: 50px;
        border: none;
        box-shadow: var(--box-shadow);
        font-size: var(--font-14);
        border-radius: 10px;
        background-color: var(--logored);
        color: white;
        cursor: pointer;
        font-weight: 700;
      }
    }
    .userInfoBox {
      display: grid;
      grid-column-gap: 100px;
      grid-template-columns: auto auto;
      @media screen and (max-width: 900px) {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
      }
      .box1,
      .box2 {
        .name1 {
          text-align: center;
          margin-bottom: 10px;
          font-weight: 700;
        }
        .name2 {
          text-align: center;
          margin-top: 10px;
          margin-bottom: 15px;
          font-weight: 700;
        }
        .userdata1 {
          width: 400px;
          height: 150px;
          display: flex;
          padding: 30px;
          justify-content: space-between;
          align-items: top;
          border: none;
          background-color: var(--logored);
          color: white;
          border-radius: 5px;
          box-shadow: var(--box-shadow);
          cursor: pointer;
          > span {
            font-size: var(--font-16);
            font-weight: 600;
          }
        }
        .userdata2 {
          width: 400px;
          height: 150px;
          display: flex;
          padding: 30px;
          justify-content: space-between;
          align-items: top;
          border: none;
          background-color: var(--boxblue);
          color: white;
          border-radius: 5px;
          box-shadow: var(--box-shadow);
          cursor: pointer;
          > span {
            font-size: var(--font-16);
            font-weight: 600;
          }
        }
      }
    }
  }
  .delmodal {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #fff;
    width: 300px;
    border-radius: 5px;
    box-shadow: var(--box-shadow);
    z-index: 99;
    text-align: center;
    .delmodalhead {
      margin: auto 0;
      padding: 10px;
      background-color: #d9d9d9;
    }
    .contents {
      padding: 10px;
      display: grid;
      grid-row-gap: 10px;
      .content {
        font-size: var(--font-20);
      }
      .contentbox {
        font-size: var(--font-16);
        color: red;
      }
      .btns {
        display: grid;
        grid-template-columns: auto auto;
        place-items: center;
        padding: 10px;
        > button {
          width: 100px;
          height: 40px;
          font-size: var(--font-13);
          border: none;
          border-radius: 30px;
          font-weight: 600;
          cursor: pointer;
          box-shadow: var(--box-shadow);
        }
        .yes {
          color: white;
          background-color: var(--logored);
        }
        .no {
          color: white;
          background-color: var(--buttongray);
        }
      }
    }
  }
`;

function Calendar() {
  const navigate = useNavigate();
  const [Clicked, setClicked] = useState(false);
  return (
    <>
      <Header />
      <MainBox>
        <CalBox>
          {Clicked ? (
            <div className="delmodal">
              <div className="delmodalhead">
                <h1>알림</h1>
              </div>
              <div className="contents">
                <div className="content">아직 대결이 종료되지 않았습니다.</div>
                <h2>대결을 중단하시겠습니까?</h2>
                <div className="contentbox">
                  주의: 대결 중단시 100 point가 차감되고 <br /> 7일간 대결을
                  하실 수 없습니다.
                </div>
                <div className="btns">
                  <button className="yes">중단</button>
                  <button className="no" onClick={() => setClicked(!Clicked)}>
                    취소
                  </button>
                </div>
              </div>
            </div>
          ) : null}
          <div className="calen">
            <FullCalendar
              viewClassNames="calendar"
              dayCellClassNames="calevent"
              defaultView="dayGridMonth"
              plugins={[dayGridPlugin]}
              contentHeight="600"
              locale="ko"
              events={[
                {
                  title: '1:40:30',
                  start: '2022-11-14',
                  backgroundColor: '#82cbc4',
                },
                {
                  title: '1:10:30',
                  start: '2022-11-14',
                  backgroundColor: '#fd8a6a',
                },
                {
                  title: '승리',
                  start: '2022-11-14',
                  backgroundColor: '#17a8f1',
                },
              ]}
            />
          </div>
          <article className="userbox">
            <div className="deletebtn">
              <button onClick={() => setClicked(!Clicked)}>대결 중단</button>
            </div>
            <div className="userInfoBox">
              <div className="box2">
                <div className="name1">
                  헬창남
                  <img src={crown} alt="승자이미지" />
                </div>
                <button
                  className="userdata1"
                  onClick={() => {
                    navigate('/detail');
                  }}
                >
                  <span>날짜 : 11/14</span>
                  <span>운동 시간 : 10:00 ~ 12:00</span>
                </button>
              </div>
              <div className="box2">
                <div className="name2">헬잘알</div>
                <button className="userdata2">
                  <span>날짜 : 11/14</span>
                  <span>운동 시간 : 10:00 ~ 12:00</span>
                </button>
              </div>
            </div>
          </article>
        </CalBox>
      </MainBox>
      <Footer />
    </>
  );
}

export default Calendar;

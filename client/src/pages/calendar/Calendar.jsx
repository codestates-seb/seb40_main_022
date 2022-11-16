import styled from 'styled-components';
import FullCalendar from '@fullcalendar/react';
import dayGridPlugin from '@fullcalendar/daygrid';
import { useNavigate } from 'react-router-dom';
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import crown from '../../images/crown.png';

const MainBox = styled.main`
  width: 100%;
  height: 1200px;
  /* @media screen and (max-width: 900px) {
    height: 1500px;
  } */
`;
const CalBox = styled.section`
  margin: 0 auto;
  padding-top: 70px;
  max-width: 900px;
  width: 100%;
  height: 1100px;
  position: relative;
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
    position: absolute;
    display: flex;
    justify-content: space-between;
    bottom: 0px;
    @media screen and (max-width: 900px) {
      flex-direction: column;
    }
    .userInfoBox {
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      .name1 {
        margin-bottom: 10px;
        font-weight: 700;
      }
      .name2 {
        margin-top: 10px;
        margin-bottom: 10px;
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
`;

function Calendar() {
  const navigate = useNavigate();

  return (
    <>
      <Header />
      <MainBox>
        <CalBox>
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
          <article className="userbox">
            <div className="userInfoBox">
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
            <div className="userInfoBox">
              <div className="name2">헬잘알</div>
              <button className="userdata2">
                <span>날짜 : 11/14</span>
                <span>운동 시간 : 10:00 ~ 12:00</span>
              </button>
            </div>
          </article>
        </CalBox>
      </MainBox>
      <Footer />
    </>
  );
}

export default Calendar;

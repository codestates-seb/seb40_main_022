import FullCalendar from '@fullcalendar/react';
import dayGridPlugin from '@fullcalendar/daygrid';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import crown from '../../images/crown.png';
import { MainBox, CalBox } from './Style';

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
                  title: '1:40:30 999kg',
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

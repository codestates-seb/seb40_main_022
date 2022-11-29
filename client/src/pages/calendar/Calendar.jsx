import FullCalendar from '@fullcalendar/react';
import dayGridPlugin from '@fullcalendar/daygrid';
import { Link, useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import crown from '../../images/crown.png';
import { MainBox, CalBox } from './Style';
import { RecordListAsync, RecordListGet } from '../../redux/action/RecordAsync';

function Calendar() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const member = useSelector(state => state.record.List.member);
  const opponent = useSelector(state => state.record.List.opponent);
  const getlist = useSelector(state => state.record.GetList.member);
  const getopponent = useSelector(state => state.record.GetList.opponent);
  console.log(member, opponent, getlist, getopponent);
  const [Clicked, setClicked] = useState(false);
  const memberId =
    member && member.length !== 0 ? member[member.length - 1].recordId : null;
  useEffect(() => {
    const TodayMonth = new Date().getMonth() + 1;
    dispatch(RecordListAsync(TodayMonth));
    dispatch(RecordListGet(memberId));
  }, []);
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
              <button
                onClick={() => navigate('/detail')}
                className="healthaddbutton"
              >
                운동 기록
              </button>
              <button
                onClick={() => setClicked(!Clicked)}
                className="canclebutton"
              >
                대결 중단
              </button>
            </div>
            <div className={opponent ? 'userInfoBox' : 'userInfoBox2'}>
              <div className="box2">
                <div className="name1">
                  <img src={crown} alt="승자이미지" />
                  {getlist && getlist.member.username}
                </div>
                <Link to={`/detail/${memberId}`} className="userdata1">
                  <div className="oneday">
                    <span>날짜 : {getlist && getlist.date.slice(5)}</span>
                    <span>
                      운동 시간 : {getlist && getlist.startTime}~{' '}
                      {getlist && getlist.endTime}
                    </span>
                  </div>
                  {getlist &&
                    getlist.sports.map((data, idx) => {
                      return idx < 3 ? (
                        <div className="dayover">
                          <span>{data.bodyPart}</span>
                          <span>{data.name}</span>
                          <span>
                            {data.set}세트/{data.count}회
                          </span>
                        </div>
                      ) : (
                        <div className="dayover">...</div>
                      );
                    })}
                </Link>
              </div>
              {opponent !== null ? (
                <div className="box2">
                  <div className="name2">
                    {getopponent && getopponent.member.username}
                  </div>
                  <button className="userdata2">
                    {opponent &&
                      opponent.map(data => {
                        return (
                          <>
                            <span>날짜 : {data.date}</span>
                            <span>운동 시간 : {data.timeRecord}</span>
                          </>
                        );
                      })}
                  </button>
                </div>
              ) : null}
            </div>
          </article>
        </CalBox>
      </MainBox>
      <Footer />
    </>
  );
}

export default Calendar;

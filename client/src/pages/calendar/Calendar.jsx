import FullCalendar from '@fullcalendar/react';
import dayGridPlugin from '@fullcalendar/daygrid';
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import crown from '../../images/crown.png';
import { MainBox, CalBox } from './Style';
import {
  RecordListAsync,
  RecordListGet,
  ChallengeDelete,
} from '../../redux/action/RecordAsync';

function Calendar() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const member = useSelector(state => state.record.List.member);
  const opponent = useSelector(state => state.record.List.opponent);
  const challId = useSelector(state => state.record.List.challengeId);
  const getlist = useSelector(state => state.record.GetList.member);
  const getopponent = useSelector(state => state.record.GetList.opponent);
  console.log(member, opponent, getlist, getopponent);
  const [Clicked, setClicked] = useState(false);
  const memberId =
    member && member.length !== 0 ? member[member.length - 1].recordId : null;
  useEffect(() => {
    const TodayMonth = new Date().getMonth() + 1;
    dispatch(RecordListAsync(TodayMonth))
      .unwrap()
      .then(() => setTimeout(dispatch(RecordListGet(memberId)), 1000));
  }, []);
  const copydata = [
    { timeRecord: '00:30:33', volume: '666', date: '2022-11-28' },
    { timeRecord: '00:40:33', volume: '777', date: '2022-11-29' },
    { timeRecord: '00:50:33', volume: '888', date: '2022-11-30' },
    { timeRecord: '00:55:33', volume: '999', date: '2022-12-1' },
  ];
  const datelist = copydata.map(data => {
    return {
      title: `${data.timeRecord} ${data.volume}kg`,
      start: data.date,
      backgroundColor: '#fd8a6a',
    };
  });
  datelist.push(
    copydata.map(data => {
      return {
        title: `${data.timeRecord} ${data.volume}kg`,
        start: data.date,
        backgroundColor: '#82cbc4',
      };
    }),
  );
  console.log(datelist);
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
                  <button
                    className="yes"
                    onClick={() => dispatch(ChallengeDelete(challId))}
                  >
                    중단
                  </button>
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
                datelist[0],
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
                onClick={() => {
                  setClicked(!Clicked);
                }}
                className="canclebutton"
              >
                대결 중단
              </button>
            </div>
            <div className={opponent ? 'userInfoBox' : 'userInfoBox2'}>
              <div className="box2">
                <div className="name1">
                  <img src={crown} alt="승자이미지" />
                  {getlist ? getlist.member.username : null}
                </div>
                {member && member !== undefined ? (
                  <button
                    className="userdata1"
                    onClick={() => {
                      navigate(`/detail/${memberId}`);
                    }}
                  >
                    <div className="oneday">
                      <span>
                        날짜 :{' '}
                        {member[0] !== undefined
                          ? member[0].date.slice(5)
                          : null}
                      </span>
                      <span>
                        운동 시간 :{' '}
                        {member[0] !== undefined ? member[0].timeRecord : null}
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
                  </button>
                ) : null}
              </div>
              {opponent !== undefined && opponent ? (
                <div className="box2">
                  <div className="name2">
                    {getopponent && getopponent.member.username}
                  </div>
                  {opponent ? (
                    <button className="userdata2">
                      <div className="oneday">
                        <span>
                          날짜 :{' '}
                          {opponent[0] !== undefined
                            ? opponent[0].date.slice(5)
                            : null}
                        </span>
                        <span>
                          운동 시간 :{' '}
                          {opponent[0] !== undefined
                            ? opponent[0].timeRecord
                            : null}
                        </span>
                      </div>
                      {getopponent &&
                        getopponent.sports.map((data, idx) => {
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
                    </button>
                  ) : null}
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

import FullCalendar from '@fullcalendar/react';
import dayGridPlugin from '@fullcalendar/daygrid';
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import uuidv4 from 'react-uuid';
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
  const [Clicked, setClicked] = useState(false);
  const memberId =
    member && member.length !== 0 ? member[member.length - 1].recordId : null;
  const curr = new Date();
  const offset = curr.getTimezoneOffset() * 60000;
  const dateOffset = new Date(curr.getTime() - offset);
  const today = dateOffset.toISOString().slice(0, 10);
  useEffect(() => {
    const TodayMonth = new Date().getMonth() + 1;
    dispatch(RecordListAsync(TodayMonth));
    dispatch(RecordListGet(memberId));
  }, [memberId]);
  const datelist =
    member &&
    member.map(data => {
      return {
        title: `${data.timeRecord} ${data.volume}kg`,
        start: data.date,
        backgroundColor: '#fd8a6a',
      };
    });
  if (opponent !== null && opponent !== undefined) {
    opponent.map(data => {
      return datelist.push({
        title: `${data.timeRecord} ${data.volume}kg`,
        start: data.date,
        backgroundColor: '#82cbc4',
      });
    });
  }
  if (opponent && member !== null && member !== undefined) {
    member.map(data => {
      return datelist.push({
        title: data.result,
        start: data.date,
        backgroundColor: '#17a8f1',
      });
    });
  }

  return (
    <>
      <Header />
      <MainBox>
        <CalBox>
          {Clicked ? (
            <div className="delmodal">
              <div className="delmodalhead">
                <h1>??????</h1>
              </div>
              <div className="contents">
                <div className="content">?????? ????????? ???????????? ???????????????.</div>
                <h2>????????? ?????????????????????????</h2>
                <div className="contentbox">
                  ??????: ?????? ????????? 10 point??? ???????????????.
                </div>
                <div className="btns">
                  <button
                    className="yes"
                    onClick={() => {
                      dispatch(ChallengeDelete(challId));
                      setClicked(!Clicked);
                      window.location.href = '/records';
                    }}
                  >
                    ??????
                  </button>
                  <button className="no" onClick={() => setClicked(!Clicked)}>
                    ??????
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
              events={datelist}
            />
          </div>
          <article className="userbox">
            <div className="deletebtn">
              {getlist && getlist.date === today ? null : (
                <button
                  onClick={() => navigate('/records/postup')}
                  className="healthaddbutton"
                >
                  ?????? ??????
                </button>
              )}
              {challId !== null ? (
                <button
                  onClick={() => {
                    setClicked(!Clicked);
                  }}
                  className="canclebutton"
                >
                  ?????? ??????
                </button>
              ) : null}
            </div>
            <div
              className={
                getlist && getlist && getopponent
                  ? 'userInfoBox'
                  : 'userInfoBox2'
              }
            >
              {member && member.length > 0 ? (
                <div className={member && member ? 'box1' : 'nobox'}>
                  <div
                    className={
                      member && member !== undefined ? 'name1' : 'noname'
                    }
                  >
                    {getlist && getlist.result === 'WIN' ? (
                      <img src={crown} alt="???????????????" />
                    ) : null}
                    {getlist ? getlist.member.username : null}
                  </div>
                  {member && member !== undefined ? (
                    <button
                      className="userdata1"
                      onClick={() => {
                        navigate(`/records/${memberId}/edit`);
                      }}
                    >
                      <div className="oneday">
                        <span>
                          ?????? :{' '}
                          {member[member.length - 1] !== undefined
                            ? member[member.length - 1].date.slice(5)
                            : null}
                        </span>
                        <span>
                          ?????? ?????? :{' '}
                          {member[member.length - 1] !== undefined
                            ? member[member.length - 1].timeRecord
                            : null}
                        </span>
                      </div>
                      {getlist &&
                        getlist.sports.map((data, idx) => {
                          return idx < 3 ? (
                            <div className="dayover" key={uuidv4()}>
                              <span>{data.bodyPart}</span>
                              <span>{data.name}</span>
                              <span>
                                {data.set}??????/{data.count}???
                              </span>
                            </div>
                          ) : null;
                        })}
                    </button>
                  ) : null}
                </div>
              ) : null}
              {getopponent && getopponent !== undefined ? (
                <div className="box2">
                  <div className="name2">
                    {getopponent && getopponent.result === 'WIN' ? (
                      <img src={crown} alt="???????????????" />
                    ) : null}
                    {getopponent && getopponent.member.username}
                  </div>
                  {opponent ? (
                    <button className="userdata2">
                      <div className="oneday">
                        <span>
                          ?????? :{' '}
                          {opponent[opponent.length - 1] !== undefined
                            ? opponent[opponent.length - 1].date.slice(5)
                            : null}
                        </span>
                        <span>
                          ?????? ?????? :{' '}
                          {opponent[opponent.length - 1] !== undefined
                            ? opponent[opponent.length - 1].timeRecord
                            : null}
                        </span>
                      </div>
                      {getopponent &&
                        getopponent.sports.map((data, idx) => {
                          return idx < 3 ? (
                            <div className="dayover" key={uuidv4}>
                              <span>{data.bodyPart}</span>
                              <span>{data.name}</span>
                              <span>
                                {data.set}??????/{data.count}???
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

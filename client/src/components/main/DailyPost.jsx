import { useState, useEffect, useSelector } from 'react';
import { useDispatch } from 'react-redux';
import daily from '../../images/daily.jpg';
import heart from '../../images/Heart.svg';
import heartFill from '../../images/heart_fill.svg';
import DailyCmtInput from './DailyCmtInput';
import { DailyForm, DailyItem } from './MainStyle';
import { asyncPost } from '../../redux/action/MainAsync';

export default function DailyPost() {
  const data = useSelector(state => state);
  console.log(data);
  const dispatch = useDispatch();
  const [fav, setFav] = useState(false);
  const [isComment, setIsComment] = useState(false);

  useEffect(() => {
    dispatch(asyncPost());
  }, [dispatch]);

  return (
    <DailyForm>
      {[...Array(5)].map(() => {
        return (
          <DailyItem>
            <article className="Img">
              <img className="dailyImg" src={daily} alt="daily" />
            </article>
            <article className="DailyInfo">
              <div className="Info">
                <div className="DailyTags">
                  <span>#오운완</span>
                  <span>#3대 500인증</span>
                </div>
                <div className="DailyMemo">
                  <span className="memo">오늘도 득근</span>
                  <span className="more">더보기</span>
                </div>
                <div className="act">
                  <span className="date">2022.11.09</span>
                  <span>
                    <button
                      onClick={() => setIsComment(!isComment)}
                      className="comments"
                    >
                      댓글 3개
                    </button>
                  </span>
                  <span className="favorite">
                    <button onClick={() => setFav(!fav)}>
                      {fav ? (
                        <img className="heart" src={heartFill} alt="heart" />
                      ) : (
                        <img className="heart" src={heart} alt="heart" />
                      )}
                      <span>28</span>
                    </button>
                  </span>
                </div>
              </div>
              <span className="userInfo">
                <img className="user" src={daily} alt="daily" />
                <span>운동인</span>
              </span>
            </article>
            {isComment ? (
              <div className="commentdiv">
                {[...Array(3)].map(() => {
                  return (
                    <div className="comment">
                      <span className="cmtUserImg">
                        <img className="user" src={daily} alt="daily" />
                      </span>
                      <div className="cmtContent">
                        <div className="cmtUserName">운동인</div>
                        <div>너무 멋있어요!</div>
                      </div>
                    </div>
                  );
                })}
                <DailyCmtInput />
              </div>
            ) : null}
          </DailyItem>
        );
      })}
    </DailyForm>
  );
}

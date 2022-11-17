import styled from 'styled-components';
import { useState } from 'react';
import daily from '../../images/daily.jpg';
import heart from '../../images/Heart.svg';
import heartFill from '../../images/heart_fill.svg';
import DailyCmtInput from './DailyCmtInput';

export const DailyForm = styled.div`
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 10px;
`;

export const DailyItem = styled.div`
  background-color: var(--black-050);
  border-radius: 5px;
  padding: 2%;
  margin: 5% 0;

  .commentdiv {
    display: flex;
    flex-direction: column;
    margin-top: 30px;
    background-color: var(--white);
    border-radius: 5px;
    padding: 3%;
    width: 100%;
    animation: modal-bg-show 0.5s;

    .comment {
      display: flex;
      align-items: center;
      padding: 10px;
      img {
        cursor: pointer;
        width: 45px;
        height: 45px;
        border-radius: 50%;
        object-fit: cover;
        margin-right: 10px;
      }
      .cmtUserName {
        font-weight: bold;
      }
    }
  }

  button {
    border: none;
    cursor: pointer;
  }

  .Img {
    width: 100%;
    position: relative;
  }

  .dailyImg {
    width: 800px;
  }

  .favorite {
    cursor: pointer;
    .heart {
      width: 18px;
      height: 18px;
      padding: 3px 3px 0 0;
    }
  }

  .DailyInfo {
    margin-top: 20px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    /* position: relative; */

    span {
      margin-right: 20px;
      cursor: pointer;
    }

    div {
      margin: 10px;
    }

    .DailyTags {
      font-weight: bold;
    }

    .memo {
      font-weight: bold;
      cursor: text;
    }

    .date {
      cursor: text;
    }

    .userInfo {
      display: flex;
      flex-direction: column;
      align-items: center;
      padding: 10px;
      border-radius: 5px;
      height: 110px;
      background-color: var(--white);
      font-weight: bold;

      .user {
        cursor: pointer;
        width: 60px;
        height: 60px;
        border-radius: 50%;
        object-fit: cover;
      }

      span {
        padding-top: 5px;
        margin-right: 0px;
      }
    }

    /* @media screen and (max-width: 720px) {
      font-size: var(--font-11);
    } */
  }
`;

export default function DailyPost() {
  const [fav, setFav] = useState(false);
  const [isComment, setIsComment] = useState(false);
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

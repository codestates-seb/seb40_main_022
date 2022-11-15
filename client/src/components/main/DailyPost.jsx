import styled from 'styled-components';
import daily from '../../images/daily.svg';
import heart from '../../images/Heart.svg';

export const DailyForm = styled.div`
  position: relative;
  display: flex;
  flex-direction: column;
  margin-top: 10px;
`;

export const DailyItem = styled.div`
  background-color: var(--black-050);
  border-radius: 5px;
  padding: 2%;
  margin: 5% 0;

  .Img {
    width: 100%;
    position: relative;
  }

  .dailyImg {
    width: 100%;
  }

  .favorite {
    .heart {
      width: 18px;
      height: 18px;
    }
  }

  .DailyInfo {
    margin-top: 20px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    position: relative;
    cursor: pointer;

    span {
      margin-right: 20px;
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

    @media screen and (max-width: 720px) {
      font-size: var(--font-11);
    }
  }
`;

export default function DailyPost() {
  return (
    <DailyForm>
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
              <span className="comment">댓글 12개</span>
              <span className="favorite">
                <img className="heart" src={heart} alt="heart" />
                28
              </span>
            </div>
          </div>
          <span className="userInfo">
            <img className="user" src={daily} alt="daily" />
            <span>운동인</span>
          </span>
        </article>
      </DailyItem>
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
              <span className="comment">댓글 12개</span>
              <span className="favorite">
                <img className="heart" src={heart} alt="heart" />
                28
              </span>
            </div>
          </div>
          <span className="userInfo">
            <img className="user" src={daily} alt="daily" />
            <span>운동인</span>
          </span>
        </article>
      </DailyItem>
    </DailyForm>
  );
}

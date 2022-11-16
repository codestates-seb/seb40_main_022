import styled from 'styled-components';
import { Link } from 'react-router-dom';
import daily from '../../images/daily.jpg';
import dailyAdd from '../../images/daily_add.svg';

export const Top = styled.div`
  position: relative;
  display: flex;
  justify-content: center;
  align-items: stretch;
  height: 200px;
  outline: none;
  overflow-y: hidden;
  overflow-x: hidden;

  /* @media screen and (max-width: 1310px) {
    display: none;
  } */
`;

export const Content = styled.div`
  margin: 20px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-weight: bold;

  .imgprofile {
    width: 130px;
    height: 130px;
    border: 5px solid var(--logored);
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: 50%;
    > img {
      cursor: pointer;
      width: 110px;
      height: 110px;
      border-radius: 50%;
      object-fit: cover;
      position: absolute;
      top: 35px;
    }
  }

  .imgprofile.dailynew {
    > img {
      &:hover {
        filter: opacity(0.4) drop-shadow(0 0 0 #fc6666);
      }
    }
  }
`;

export default function TopList() {
  return (
    <Top>
      {[...Array(4)].map(() => {
        return (
          <Content>
            <div className="imgprofile">
              <img src={daily} alt="daily" />
            </div>
            <span>운동인</span>
          </Content>
        );
      })}
      <Content>
        <Link to="/dailypost">
          <div className="imgprofile dailynew">
            <img src={dailyAdd} alt="dailyAdd" />
          </div>
        </Link>
        <span>새 게시물</span>
      </Content>
    </Top>
  );
}

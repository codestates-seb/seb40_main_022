import styled from 'styled-components';
import daily from '../../images/daily.svg';

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
`;

export default function TopList() {
  return (
    <Top>
      <Content>
        <div className="circle" />
        <div className="imgprofile">
          <img src={daily} alt="daily" />
        </div>
        <span>운동인</span>
      </Content>
      <Content>
        <div className="circle" />
        <div className="imgprofile">
          <img src={daily} alt="daily" />
        </div>
        <span>운동인</span>
      </Content>
      <Content>
        <div className="circle" />
        <div className="imgprofile">
          <img src={daily} alt="daily" />
        </div>
        <span>운동인</span>
      </Content>
      <Content>
        <div className="circle" />
        <div className="imgprofile">
          <img src={daily} alt="daily" />
        </div>
        <span>운동인</span>
      </Content>
      <Content>
        <div className="circle" />
        <div className="imgprofile">
          <img src={daily} alt="daily" />
        </div>
        <span>운동인</span>
      </Content>
    </Top>
  );
}

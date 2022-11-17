import React from 'react';
import styled from 'styled-components';
import UserProfile from '../../images/userprofile.png';

const LankBody = styled.section`
  margin-top: 100px;
`;
const Lankcontent = styled.section`
  display: flex;
  justify-content: space-between;
  border-bottom: 1px solid #c1c1c1;
  width: 100%;
  height: 100px;
  > h1 {
    line-height: 70px;
    font-weight: bolder;
    font-size: 24px;
  }
  .cont-picture {
    width: 80px;
    height: 80px;
    border-radius: 40px;
    background-image: url(${UserProfile});
    background-size: cover;
    background-repeat: no-repeat;
    background-position: 80% 100%;
    margin-right: 20px;
  }
  > button {
    width: 100px;
    height: 55px;
    background-color: var(--logored);
    border-radius: 11px;
    color: white;
    border: none;
    font-weight: bolder;
    margin-top: 7px;
    cursor: pointer;
    font-size: var(--font-18);
  }
  > h4 {
    line-height: 70px;
    font-size: var(--font-16);
    font-weight: 600;
  }
  > :nth-child(7) {
    margin-left: 90px;
    margin-right: 90px;
  }
`;
function LankContent() {
  return (
    <LankBody>
      <Lankcontent>
        <h1>1</h1>
        <div className="cont-picture" />
        <h4 className="cont-name">데이빗</h4>
        <h4 className="cont-height">신장 180cm</h4>
        <h4 className="cont-weight">몸무게 140kg</h4>
        <h4 className="cont-exp">경력 10년</h4>
        <h4 className="cont-point">1283점</h4>
        <button>대결신청</button>
      </Lankcontent>
    </LankBody>
  );
}

export default LankContent;

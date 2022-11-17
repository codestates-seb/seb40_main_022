import React from 'react';
import { LankBody, Lankcontent } from './LankContentstyle';

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

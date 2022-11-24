import React, { useState } from 'react';
import ChallengeReq from '../../components/modal/ChallengeReq';
import { LankBody, Lankcontent } from './LankContentstyle';

function LankContent() {
  const [challengeReq, setChallengeReq] = useState(false);
  return (
    <LankBody>
      <Lankcontent>
        <h1>1</h1>
        <div className="cont-picture" />
        <h4 className="cont-name">데이빗</h4>
        <h4 className="cont-height">신장 180cm</h4>
        <h4 className="cont-weight">몸무게 140kg</h4>
        <h4 className="cont-exp">경력 10년</h4>
        <h4 className="cont-point">1283점(3대 990)</h4>
        <button onClick={() => setChallengeReq(true)} className="challenge">
          대결신청
        </button>
        <ChallengeReq
          open={challengeReq}
          close={() => setChallengeReq(false)}
        />
      </Lankcontent>
    </LankBody>
  );
}

export default LankContent;

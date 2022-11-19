import React, { useState } from 'react';
import userProfile from '../../images/daily.jpg';
import {
  Notice,
  NoticeSection,
  ModalHeader,
  ModalMain,
  ModalList,
} from './modalstyle';
import Challenge from './Challenge';

function Modal() {
  const [challenge, setChallenge] = useState(false);
  return (
    <Notice>
      <NoticeSection>
        <ModalHeader>알림</ModalHeader>
        <ModalMain>
          {[...Array(3)].map(() => {
            return (
              <ModalList
                onClick={() => setChallenge(true)}
                className="challenge"
              >
                <img
                  className="userProfile"
                  src={userProfile}
                  alt="userProfile"
                />
                <div className="content">
                  <div className="fightday">
                    <h3>대결 신청</h3>
                    <span>2022.11.10</span>
                  </div>
                  <div>헬스남님이 대결을 신청하셨습니다.</div>
                </div>
              </ModalList>
            );
          })}
          <Challenge open={challenge} close={() => setChallenge(false)} />
        </ModalMain>
      </NoticeSection>
    </Notice>
  );
}

export default Modal;

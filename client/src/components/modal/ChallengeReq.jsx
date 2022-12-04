import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import vs from '../../images/vs.svg';
import { Wrapper, ModalSection } from './modalstyle';
import {
  ModalHeader,
  ModalMain,
  VsInfo,
  User,
  Buttons,
} from './ChallengeStyle';
import { LankChallenge } from '../../redux/action/LankAsync';
import { MypageGet } from '../../redux/action/MypageAsync';

export default function ChallengeReq(props) {
  const { open, close, id } = props;
  const dispatch = useDispatch();
  const userdb = useSelector(state => state.challenge.pageInfo.responses);
  const vsUserdb = userdb && userdb.filter(el => el.memberId === id);
  const myData = useSelector(state => state.mypage.member);

  useEffect(() => {
    dispatch(MypageGet());
  }, []);

  const handleClick = () => {
    dispatch(LankChallenge(id));
    close();
  };
  return (
    <Wrapper>
      <div className={open ? 'openModal modal' : 'modal'}>
        {open ? (
          <ModalSection>
            <ModalHeader>
              <button className="close" onClick={close}>
                &times;
              </button>
            </ModalHeader>
            <ModalMain>
              <VsInfo>
                <User>
                  <span>
                    <img
                      className="userProfile"
                      src={myData.profileImage}
                      alt="userProfile"
                    />
                  </span>
                  <span className="userName">{myData.userName}</span>
                  <span className="userInfo">
                    <div>신장 {myData.height}cm</div>
                    <div>몸무게 {myData.weight}kg</div>
                  </span>
                </User>
                <span className="vsIcon">
                  <img src={vs} alt="vsIcon" />
                </span>
                <User>
                  <span>
                    <img
                      className="userProfile"
                      src={vsUserdb[0].profileImage}
                      alt="userProfile"
                    />
                  </span>
                  <span className="userName">{vsUserdb[0].userName}</span>
                  <span className="userInfo">
                    <div>
                      신장
                      {vsUserdb && vsUserdb[0].height ? vsUserdb[0].height : 0}
                      cm
                    </div>
                    <div>
                      몸무게
                      {vsUserdb && vsUserdb[0].weight ? vsUserdb[0].weight : 0}
                      kg
                    </div>
                  </span>
                </User>
              </VsInfo>
              <Buttons>
                <button
                  className="accBut"
                  onClick={() => {
                    handleClick();
                  }}
                >
                  대결 신청
                </button>
              </Buttons>
            </ModalMain>
          </ModalSection>
        ) : null}
      </div>
    </Wrapper>
  );
}

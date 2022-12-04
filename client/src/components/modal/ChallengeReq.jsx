<<<<<<< HEAD
import React, { useEffect } from 'react';
=======
>>>>>>> 76f5b50277e45f815d063b3690686af9ee9c9bf7
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
<<<<<<< HEAD
import { MypageGet } from '../../redux/action/MypageAsync';
=======
import Profile from '../../images/Profile.png';
>>>>>>> 76f5b50277e45f815d063b3690686af9ee9c9bf7

export default function ChallengeReq(props) {
  const { open, close, id } = props;
  const dispatch = useDispatch();
<<<<<<< HEAD
  const userdb = useSelector(state => state.challenge.pageInfo.responses);
  const vsUserdb = userdb && userdb.filter(el => el.memberId === id);
  const myData = useSelector(state => state.mypage.member);

  useEffect(() => {
    dispatch(MypageGet());
  }, []);

=======
  const userdb = useSelector(state => state);
>>>>>>> 76f5b50277e45f815d063b3690686af9ee9c9bf7
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
<<<<<<< HEAD
                      src={myData.profileImage}
                      alt="userProfile"
                    />
                  </span>
                  <span className="userName">{myData.userName}</span>
                  <span className="userInfo">
                    <div>신장 {myData.height}cm</div>
                    <div>몸무게 {myData.weight}kg</div>
=======
                      src={
                        userdb.applicantImage ? userdb.applicantImage : Profile
                      }
                      alt="userProfile"
                    />
                  </span>
                  <span className="userName">{userdb.applicantName}</span>
                  <span className="userInfo">
                    <div>신장 {userdb.applicantHeight}cm</div>
                    <div>몸무게 {userdb.applicantWeight}kg</div>
                    <div>중량 {userdb.applicantWeight}kg</div>
>>>>>>> 76f5b50277e45f815d063b3690686af9ee9c9bf7
                  </span>
                </User>
                <span className="vsIcon">
                  <img src={vs} alt="vsIcon" />
                </span>
                <User>
                  <span>
                    <img
                      className="userProfile"
<<<<<<< HEAD
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
=======
                      src={
                        userdb.applicantImage ? userdb.applicantImage : Profile
                      }
                      alt="userProfile"
                    />
                  </span>
                  <span className="userName">{userdb.applicantName}</span>
                  <span className="userInfo">
                    <div>신장 {userdb.applicantHeight}cm</div>
                    <div>몸무게 {userdb.applicantWeight}kg</div>
                    <div>중량 {userdb.applicantWeight}kg</div>
>>>>>>> 76f5b50277e45f815d063b3690686af9ee9c9bf7
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

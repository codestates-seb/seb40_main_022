import { useDispatch, useSelector } from 'react-redux';
import { useEffect } from 'react';
import vs from '../../images/vs.svg';
import Profile from '../../images/Profile.png';
import { Wrapper, ModalSection } from './modalstyle';
import {
  ModalHeader,
  ModalMain,
  VsInfo,
  User,
  Buttons,
} from './ChallengeStyle';
import {
  ChallengeAccept,
  EnemyUserInfo,
  ChallengeDelete,
} from '../../redux/action/LankAsync';

export default function Challenge(props) {
  const { open, close, id } = props;
  const dispatch = useDispatch();

  const userdb = useSelector(state => state.challenge.userInfo);

  const handledelete = () => {
    close();
    dispatch(ChallengeDelete(id));
  };
  useEffect(() => {
    const url = [id];
    dispatch(EnemyUserInfo(url));
  }, [id]);
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
                      src={
                        userdb && userdb.counterpartImage
                          ? userdb.counterpartImage
                          : Profile
                      }
                      alt="userProfile"
                    />
                  </span>
                  <span className="userName">
                    {userdb && userdb.counterpartName}
                  </span>
                  <span className="userInfo">
                    <div>신장 {userdb && userdb.counterpartHeight}cm</div>
                    <div>몸무게 {userdb && userdb.counterpartWeight}kg</div>
                    <div>중량 {userdb && userdb.counterpartWeight}kg</div>
                  </span>
                </User>
                <span className="vsIcon">
                  <img src={vs} alt="vsIcon" />
                </span>
                <User>
                  <span>
                    <img
                      className="userProfile"
                      src={
                        userdb && userdb.applicantImage
                          ? userdb.applicantImage
                          : Profile
                      }
                      alt="userProfile"
                    />
                  </span>
                  <span className="userName">
                    {userdb && userdb.applicantName}
                  </span>
                  <span className="userInfo">
                    <div>신장 {userdb && userdb.applicantHeight}cm</div>
                    <div>몸무게 {userdb && userdb.applicantWeight}kg</div>
                    <div>중량 {userdb && userdb.applicantWeight}kg</div>
                  </span>
                </User>
              </VsInfo>
              <Buttons>
                <button
                  className="accBut"
                  onClick={() => {
                    dispatch(ChallengeAccept(id));
                    close();
                  }}
                >
                  대결 수락
                </button>
                <button
                  className="decBut"
                  onClick={() => {
                    handledelete();
                  }}
                >
                  대결 거절
                </button>
              </Buttons>
            </ModalMain>
          </ModalSection>
        ) : null}
      </div>
    </Wrapper>
  );
}

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

export default function ChallengeReq(props) {
  const { open, close, id } = props;
  const dispatch = useDispatch();
  const userdb = useSelector(state => state.challenge.userInfo);

  const handleClick = () => {
    dispatch(LankChallenge(id));
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
                      src={userdb.applicantImage}
                      alt="userProfile"
                    />
                  </span>
                  <span className="userName">운동인1</span>
                  <span className="userInfo">
                    <div>신장 178cm</div>
                    <div>몸무게 87kg</div>
                    <div>중량 350kg</div>
                  </span>
                </User>
                <span className="vsIcon">
                  <img src={vs} alt="vsIcon" />
                </span>
                <User>
                  <span>
                    <img
                      className="userProfile"
                      src={userdb.applicantImage}
                      alt="userProfile"
                    />
                  </span>
                  <span className="userName">운동인2</span>
                  <span className="userInfo">
                    <div>신장 183cm</div>
                    <div>몸무게 91kg</div>
                    <div>중량 410kg</div>
                  </span>
                </User>
              </VsInfo>
              <Buttons>
                <button
                  className="accBut"
                  onClick={() => {
                    handleClick();
                    close();
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

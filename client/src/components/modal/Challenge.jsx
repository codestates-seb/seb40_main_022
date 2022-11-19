import userProfile from '../../images/daily.jpg';
import vs from '../../images/vs.svg';
import { Wrapper, ModalSection } from './modalstyle';
import {
  ModalHeader,
  ModalMain,
  VsInfo,
  User,
  Buttons,
} from './ChallengeStyle';

export default function Challenge(props) {
  const { open, close } = props;
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
                      src={userProfile}
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
                      src={userProfile}
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
                <button className="accBut">대결 수락</button>
                <button className="decBut" onClick={close}>
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

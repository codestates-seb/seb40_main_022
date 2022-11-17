import styled from 'styled-components';
import userProfile from '../../images/daily.jpg';
import vs from '../../images/vs.svg';
import { Wrapper, ModalSection } from './modalstyle';

export const ModalHeader = styled.section`
  position: relative;

  > button {
    position: absolute;
    border: none;
    top: 10px;
    right: 10px;
    width: 30px;
    font-size: 21px;
    font-weight: 700;
    text-align: center;
    color: #999;
    background-color: transparent;
    :hover {
      cursor: pointer;
      color: black;
    }
  }
`;

export const ModalMain = styled.main`
  padding: 16px;
`;

export const VsInfo = styled.article`
  margin: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
`;

export const User = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 40px;

  .userProfile {
    width: 80px;
    height: 80px;
    border-radius: 50%;
    object-fit: cover;
  }

  .userName {
    padding: 5px;
    font-weight: bold;
  }

  .userInfo {
    margin-top: 10px;
    font-size: var(--font-13);
  }
`;

export const Buttons = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;

  > button {
    padding: 10px;
    cursor: pointer;
    font-weight: bold;
    width: 80%;
    color: var(--white);
    border: none;
    border-radius: 50px;
    box-shadow: var(--box-shadow);
    margin-bottom: 15px;
  }
  .accBut {
    background-color: var(--logored);
    :hover {
      background-color: #fa8a8a;
    }
  }

  .decBut {
    background-color: var(--buttongray);
    :hover {
      background-color: #cfcfcf;
    }
  }
`;

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

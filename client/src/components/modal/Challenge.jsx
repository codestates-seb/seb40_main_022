import { useDispatch, useSelector } from 'react-redux';
import { useEffect } from 'react';
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
import { ChallengeAccept, EnemyUserInfo } from '../../redux/action/LankAsync';

export default function Challenge(props) {
  const { open, close, id } = props;
  const dispatch = useDispatch();
  const userdata = useSelector(
    state => state.challenge.data.notificationResponses,
  );
  // const [userId, setUserId] = useState(7);
  // const dataId = userdata[0].id;
  const userdb = useSelector(state => state.challenge.memeber);
  console.log(userdata, userdb);
  const handleAccept = () => {
    dispatch(ChallengeAccept(id));
  };
  useEffect(() => {
    // if (id !== null) {
    //   const dataId = userdata[id].id;
    //   setUserId(userdata[id].id);
    //   console.log(userdata, id, userdata[id].id, dataId);
    // }
    dispatch(EnemyUserInfo(7));
  }, []);
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
                <button className="accBut" onClick={() => handleAccept()}>
                  대결 수락
                </button>
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

import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import userProfile from '../../images/daily.jpg';
import {
  Notice,
  NoticeSection,
  ModalHeader,
  ModalMain,
  ModalList,
} from './modalstyle';
import Challenge from './Challenge';
import {
  Notifications,
  Notificationsallam,
} from '../../redux/action/LankAsync';

function Modal() {
  const [challenge, setChallenge] = useState(false);
  const [acceptId, setAcceptId] = useState(null);
  const dispatch = useDispatch();
  const notification = useSelector(
    state => state.challenge.data.notificationResponses,
  );
  const handleClick = id => {
    setAcceptId(id);
    dispatch(Notificationsallam(id));
  };
  useEffect(() => {
    dispatch(Notifications());
  }, []);
  return (
    <Notice>
      <NoticeSection>
        <ModalHeader>알림</ModalHeader>
        <ModalMain>
          {notification &&
            notification.map(data => {
              return (
                <ModalList
                  onClick={() => {
                    setChallenge(true);
                    handleClick(data.url);
                  }}
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
                      <span>{data.createdAt}</span>
                    </div>
                    <div>{data.content}</div>
                  </div>
                </ModalList>
              );
            })}
          <Challenge
            open={challenge}
            close={() => setChallenge(false)}
            id={acceptId}
          />
        </ModalMain>
      </NoticeSection>
    </Notice>
  );
}

export default Modal;

import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import mockupProfile from '../../images/mockupProfile.png';
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

  const modalTitle = el => {
    if (el.includes('중단')) {
      return '중단';
    }
    if (el.includes('거절')) {
      return '거절';
    }
    if (el.includes('수락')) {
      return '수락';
    }
    return '신청';
  };

  const handleClick = (url, id) => {
    setAcceptId(url);
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
            notification
              .slice()
              .reverse()
              .map(data => {
                return (
                  <ModalList
                    onClick={() => {
                      setChallenge(true);
                      handleClick(data.url, data.id);
                    }}
                    className="challenge"
                    disabled={
                      data.content.includes('수락') ||
                      data.content.includes('거절') ||
                      data.content.includes('중단')
                    }
                  >
                    <img
                      className="userProfile"
                      src={mockupProfile}
                      alt="userProfile"
                    />
                    <div className="content">
                      <div className="fightday">
                        <h3>대결 {modalTitle(data.content)}</h3>
                        <span>{data.createdAt.slice(0, 10)}</span>
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

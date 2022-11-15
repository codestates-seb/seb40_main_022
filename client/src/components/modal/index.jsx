import React from 'react';
import userProfile from '../../images/userprofil.png';
import {
  Wrapper,
  ModalSection,
  ModalHeader,
  ModalHeaderBtn,
  ModalMain,
  ModalList,
} from './modalstyle';

function Modal(props) {
  const { open, close, header } = props;

  return (
    <Wrapper>
      <div className={open ? 'openModal modal' : 'modal'}>
        {open ? (
          <ModalSection>
            <ModalHeader>
              {header}
              <ModalHeaderBtn className="close" onClick={close}>
                &times;
              </ModalHeaderBtn>
            </ModalHeader>
            <ModalMain>
              <ModalList>
                <img src={userProfile} alt="userProfile" />
                <div className="content">
                  <div className="fightday">
                    <h3>대결 신청</h3>
                    <span>2022.11.10</span>
                  </div>
                  <div>헬스남님이 대결을 신청하셨습니다.</div>
                </div>
              </ModalList>
              <ModalList>
                <img src={userProfile} alt="userProfile" />
                <div className="content">
                  <div className="fightday">
                    <h3>대결 신청</h3>
                    <span>2022.11.10</span>
                  </div>
                  <div>헬스남님이 대결을 신청하셨습니다.</div>
                </div>
              </ModalList>
            </ModalMain>
          </ModalSection>
        ) : null}
      </div>
    </Wrapper>
  );
}

export default Modal;

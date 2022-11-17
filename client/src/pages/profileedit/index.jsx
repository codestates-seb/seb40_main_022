import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCaretDown } from '@fortawesome/free-solid-svg-icons';
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import {
  Wrapper,
  ImageBox,
  ProfileBox,
  ProfileGrid,
  ProfileInput,
  BtnBox,
} from './style';
import userProfile from '../../images/userprofile.png';

function ProfileEdit() {
  return (
    <Wrapper>
      <Header />
      <div>
        <ImageBox>
          <img src={userProfile} alt="userProfile" />
        </ImageBox>
        <ProfileBox>
          <ProfileGrid>
            <div>이름</div>
            <ProfileInput />
            <div>직업</div>
            <ProfileInput />
            <div>주소</div>
            <ProfileInput />
            <div className="checkbox">
              <div className="checkLeft">
                <span>성별</span>
                <div>
                  <label>
                    <input
                      type="radio"
                      name="sex"
                      value="man"
                      checked="checked"
                    />
                    <span>남성</span>
                  </label>
                  <label>
                    <input type="radio" name="sex" value="woman" />
                    <span>여성</span>
                  </label>
                </div>
              </div>
              <div className="checkright">
                <div>분할</div>
                <div className="container">
                  <input id="dropdown" type="checkbox" />
                  <label className="dropdownLabel" htmlFor="dropdown">
                    <div />
                    <FontAwesomeIcon icon={faCaretDown} className="caretIcon" />
                  </label>
                  <div className="content">
                    <div className="contents">
                      <button>2분할</button>
                      <button>3분할</button>
                      <button>4분할</button>
                      <button>5분할</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div>나이</div>
            <ProfileInput />
            <div>키</div>
            <ProfileInput />
            <div>몸무게</div>
            <ProfileInput />
            <div>중량</div>
            <ProfileInput />
          </ProfileGrid>
          <BtnBox>
            <button className="set-btn">완료</button>
            <button>취소</button>
          </BtnBox>
        </ProfileBox>
      </div>
      <Footer />
    </Wrapper>
  );
}

export default ProfileEdit;

import React, { useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCaretDown, faGear } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';
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
import daily from '../../images/daily.jpg';

function ProfileEdit() {
  const [select, setSelect] = useState('');
  const navigate = useNavigate();
  return (
    <Wrapper>
      <Header />
      <div>
        <ImageBox>
          <img src={daily} alt="userProfile" />
          <FontAwesomeIcon icon={faGear} className="setting" />
        </ImageBox>
        <ProfileBox>
          <ProfileGrid>
            <div className="boxname">이름</div>
            <ProfileInput />
            <div className="boxname">직업</div>
            <ProfileInput />
            <div className="boxname">주소</div>
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
                    <div>{select}</div>
                    <FontAwesomeIcon icon={faCaretDown} className="caretIcon" />
                  </label>
                  <div className="content">
                    <div className="contents">
                      <button
                        onClick={e => {
                          setSelect(e.target.innerHTML);
                        }}
                      >
                        2분할
                      </button>
                      <button
                        onClick={e => {
                          setSelect(e.target.innerHTML);
                        }}
                      >
                        3분할
                      </button>
                      <button
                        onClick={e => {
                          setSelect(e.target.innerHTML);
                        }}
                      >
                        4분할
                      </button>
                      <button
                        onClick={e => {
                          setSelect(e.target.innerHTML);
                        }}
                      >
                        5분할
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div className="boxname">나이</div>
            <ProfileInput />
            <div className="boxname">키</div>
            <ProfileInput />
            <div className="boxname">몸무게</div>
            <ProfileInput />
            <div className="boxname">중량</div>
            <ProfileInput />
          </ProfileGrid>
          <BtnBox>
            <button className="set-btn">완료</button>
            <button onClick={() => navigate('/mypage')}>취소</button>
          </BtnBox>
        </ProfileBox>
      </div>
      <Footer />
    </Wrapper>
  );
}

export default ProfileEdit;

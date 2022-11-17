import React from 'react';
import styled from 'styled-components';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  faGear,
  faDumbbell,
  faHeart,
  faLightbulb,
  faTrash,
} from '@fortawesome/free-solid-svg-icons';
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import userProfile from '../../images/userprofile.png';
import myimage from '../../images/qnaImg.jpg';

const Wrapper = styled.div`
  width: 100%;
  > div {
    padding-top: 60px;
    height: 1000px;
    max-width: 1200px;
    margin: 0 auto;
    display: flex;
    align-items: center;
    flex-direction: column;
  }
`;

const ProfileBox = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 300px;
  height: 300px;
  margin-top: 70px;
  border: 1px solid Red;

  .circle {
    width: 130px;
    height: 130px;
    border: 5px solid var(--logored);
    border-radius: 50%;
  }

  > img {
    width: 110px;
    height: 110px;
    border-radius: 50%;
    object-fit: cover;
    position: absolute;
    top: 210px;
  }
`;

const NameBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  margin-left: 40px;
  .setting {
    margin-left: 20px;
  }
`;

const FollowBox = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const RecordBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 200px;
  > div {
    display: flex;
    justify-content: space-between;
  }
`;

const RecordBtn = styled.button`
  height: 45px;
  color: white;
  background-color: #fc6666;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0.8em;
  border-radius: 3px;
  border: none;
  cursor: pointer;
`;

const PictureBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  width: 100%;
  height: 400px;
`;

function Mypage() {
  return (
    <Wrapper>
      <Header />
      <div>
        <ProfileBox>
          <div className="circle" />
          <img src={userProfile} alt="userProfile" />
        </ProfileBox>
        <NameBox>
          <div>유저 이름</div>
          <FontAwesomeIcon icon={faGear} className="setting" />
        </NameBox>
        <FollowBox>
          <div>게시물 22</div>
          <div>팔로워 11</div>
          <div>팔로우 133</div>
        </FollowBox>
        <RecordBox>
          <FontAwesomeIcon icon={faDumbbell} />
          <div>450kg</div>
          <FontAwesomeIcon icon={faHeart} />
          <div>132</div>
          <FontAwesomeIcon icon={faLightbulb} />
          <div>680</div>
          <RecordBtn>운동 기록</RecordBtn>
        </RecordBox>
        <hr width="100%" />
        <div />
        <PictureBox>
          <div>
            <img src={myimage} alt="userProfile" />
            <FontAwesomeIcon icon={faTrash} />
          </div>
          <div>
            <img src={myimage} alt="userProfile" />
            <FontAwesomeIcon icon={faTrash} />
          </div>
        </PictureBox>
      </div>
      <Footer />
    </Wrapper>
  );
}

export default Mypage;

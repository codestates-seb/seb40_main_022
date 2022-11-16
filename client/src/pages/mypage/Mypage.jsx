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
  margin-top: 100px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  .circle {
    width: 130px;
    height: 130px;
    border: 5px solid var(--logored);
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    > img {
      width: 110px;
      height: 110px;
      border-radius: 50%;
      object-fit: cover;
    }
  }
`;

const NameBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  margin-left: 40px;
  margin-top: 10px;
  .username {
    font-size: var(--font-20);
    font-weight: 600;
  }
  .setting {
    margin-left: 20px;
  }
`;

const FollowBox = styled.div`
  margin-top: 30px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  > div {
    width: 100px;
    font-size: var(--font-15);
    font-weight: 600;
    text-align: center;
  }
`;

const RecordBox = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
  width: 100%;
  margin-top: 70px;
  margin-bottom: 30px;
  .boxs {
    display: flex;
    .box {
      width: 100px;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: space-between;
      font-size: var(--font-20);
      font-weight: 600;
    }
  }
  > button {
    right: 10px;
    position: absolute;
    color: white;
    background-color: #fc6666;
    border-radius: 3px;
    border: none;
    cursor: pointer;
    height: 45px;
    width: 110px;
    font-size: var(--font-20);
    font-weight: 700;
  }
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
          <div className="circle">
            <img src={userProfile} alt="userProfile" />
          </div>
        </ProfileBox>
        <NameBox>
          <div className="username">유저 이름</div>
          <FontAwesomeIcon icon={faGear} className="setting" />
        </NameBox>
        <FollowBox>
          <div>게시물 22</div>
          <div>팔로워 11</div>
          <div>팔로우 133</div>
        </FollowBox>
        <RecordBox>
          <div className="boxs">
            <div className="box">
              <FontAwesomeIcon icon={faDumbbell} />
              450kg
            </div>
            <div className="box">
              <FontAwesomeIcon icon={faHeart} />
              132
            </div>
            <div className="box">
              <FontAwesomeIcon icon={faLightbulb} />
              680
            </div>
          </div>
          <button>운동 기록</button>
        </RecordBox>
        <hr className="" />
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

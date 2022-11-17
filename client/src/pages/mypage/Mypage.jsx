import React, { useState } from 'react';
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
  .delmodal {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #fff;
    width: 300px;
    border-radius: 5px;
    box-shadow: var(--box-shadow);
    z-index: 99;
    > div {
      display: flex;
      align-items: center;
      justify-content: center;
    }
    .contentbox {
      height: 100px;
      font-size: var(--font-16);
    }
    .btns {
      height: 50px;
      column-gap: 30px;
      > button {
        width: 60px;
        height: 25px;
        font-size: var(--font-13);
        border: none;
        border-radius: 5px;
        font-weight: 600;
        cursor: pointer;
        box-shadow: var(--box-shadow);
      }
      .yes {
        color: white;
        background-color: var(--logored);
      }
      .no {
        color: white;
        background-color: var(--buttongray);
      }
    }
  }
  .box {
    padding-top: 60px;
    max-width: 1200px;
    margin: 0 auto;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
  }
  .line {
    width: 100%;
    border: 0;
    background-color: #000;
    height: 2px;
    margin-bottom: 40px;
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
  display: grid;
  grid-row-gap: 10px;
  grid-template-rows: auto;
  grid-template-columns: auto auto auto auto;
  width: 100%;
  margin-bottom: 10px;
  > div {
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    > img {
      width: 250px;
      height: 250px;
    }
    > button {
      cursor: pointer;
      border: none;
      margin-left: 80%;
      margin-top: 10px;
      display: flex;
      justify-content: end;
    }
  }
`;

function Mypage() {
  const list = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  const [Clicked, setClicked] = useState(false);

  return (
    <Wrapper>
      <Header />
      {Clicked ? (
        <div className="delmodal">
          <div className="contentbox">정말 삭제하시겠습니까?</div>
          <div className="btns">
            <button className="yes">예</button>
            <button className="no" onClick={() => setClicked(!Clicked)}>
              아니요
            </button>
          </div>
        </div>
      ) : null}
      <div className="box">
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
        <hr className="line" />
        <PictureBox>
          {list &&
            list.map(data => {
              return (
                <div key={data}>
                  <img src={myimage} alt="userProfile" />
                  <button onClick={() => setClicked(!Clicked)}>
                    <FontAwesomeIcon icon={faTrash} />
                  </button>
                </div>
              );
            })}
        </PictureBox>
      </div>
      <Footer />
    </Wrapper>
  );
}

export default Mypage;

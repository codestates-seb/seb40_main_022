import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
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
import {
  Wrapper,
  ProfileBox,
  NameBox,
  FollowBox,
  RecordBox,
  PictureBox,
} from './style';
import daily from '../../images/daily.jpg';
import myimage from '../../images/qnaImg.jpg';

function Mypage() {
  const list = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  const [Clicked, setClicked] = useState(false);
  const navigate = useNavigate();

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
            <img src={daily} alt="userProfile" />
          </div>
        </ProfileBox>
        <NameBox>
          <div className="username">유저 이름</div>
          <Link to="/mypage/edit" className="profEdit">
            <FontAwesomeIcon icon={faGear} className="setting" />
          </Link>
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
          <button onClick={() => navigate('/record')}>운동 기록</button>
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

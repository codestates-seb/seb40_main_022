import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  faDumbbell,
  faTrophy,
  faPersonRunning,
  faTrash,
} from '@fortawesome/free-solid-svg-icons';
import { MypageGet } from '../../redux/action/MypageAsync';
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
  const [btnClick, setBtnClick] = useState(false);

  const navigate = useNavigate();
  const dispatch = useDispatch();
  const ac = useSelector(state => state.authToken.accessToken);
  const re = useSelector(state => state.authToken.token);
  const data = [ac, re];
  // const ld = useSelector(state => state);

  useEffect(() => {
    dispatch(MypageGet(data));
  }, [data]);

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
      {btnClick ? (
        <div className="delmodal">
          <div className="contentbox">정말 탈퇴하시겠습니까?</div>
          <div className="btns">
            <button className="yes">예</button>
            <button className="no" onClick={() => setBtnClick(!btnClick)}>
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
        </NameBox>
        <FollowBox>
          <div>게시물 22</div>
          <div>포인트 120</div>
        </FollowBox>
        <RecordBox>
          <div className="boxs">
            <div className="box">
              <FontAwesomeIcon icon={faDumbbell} />
              450kg
            </div>
            <div className="box">
              <FontAwesomeIcon icon={faTrophy} />
              132위
            </div>
            <div className="box">
              <FontAwesomeIcon icon={faPersonRunning} />
              30일
            </div>
          </div>
          <button className="editBtn" onClick={() => navigate('/mypage/edit')}>
            정보 수정
          </button>
          <button onClick={() => setBtnClick(!btnClick)}>회원 탈퇴</button>
        </RecordBox>
        <hr className="line" />
        <PictureBox>
          {list &&
            list.map(listdata => {
              return (
                <div key={listdata}>
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

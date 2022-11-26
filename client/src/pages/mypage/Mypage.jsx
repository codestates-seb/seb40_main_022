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
import { MypageGet, MyPostDelete } from '../../redux/action/MypageAsync';
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
import myimage from '../../images/qnaImg.jpg';

function Mypage() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const list = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  const [Clicked, setClicked] = useState(false);
  const member = useSelector(state => state.mypage.member);
  const dailyPosts = useSelector(state => state.mypage.dailyPosts);
  const activity = useSelector(state => state.mypage.activity);

  console.log(member, dailyPosts, activity);
  useEffect(() => {
    dispatch(MypageGet());
  }, []);

  return (
    <Wrapper>
      <Header />
      {Clicked ? (
        <div className="delmodal">
          <div className="contentbox">정말 탈퇴하시겠습니까?</div>
          <div className="btns">
            <button
              className="yes"
              onClick={() => {
                dispatch(MyPostDelete());
                navigate('/');
              }}
            >
              예
            </button>
            <button className="no" onClick={() => setClicked(!Clicked)}>
              아니요
            </button>
          </div>
        </div>
      ) : null}
      <div className="box">
        <ProfileBox>
          <div className="circle">
            <img src={member.profileImage} alt="userProfile" />
          </div>
        </ProfileBox>
        <NameBox>
          <div className="username">{member.userName}</div>
        </NameBox>
        <FollowBox>
          <div>게시물 1</div>
          <div>포인트 {activity.point}</div>
        </FollowBox>
        <RecordBox>
          <div className="boxs">
            <div className="box">
              <FontAwesomeIcon icon={faDumbbell} />
              {activity.kilogram}kg
            </div>
            <div className="box">
              <FontAwesomeIcon icon={faTrophy} />
              132위
            </div>
            <div className="box">
              <FontAwesomeIcon icon={faPersonRunning} />
              {activity.dayCount}일
            </div>
          </div>
          <button className="editBtn" onClick={() => navigate('/mypage/edit')}>
            정보 수정
          </button>
          <button onClick={() => setClicked(!Clicked)}>회원 탈퇴</button>
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

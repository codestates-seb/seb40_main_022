import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  faDumbbell,
  faTrophy,
  faCalendarDays,
} from '@fortawesome/free-solid-svg-icons';
import { PictureBox } from './PictureBox';
import { MypageGet, MyIdDelete } from '../../redux/action/MypageAsync';
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import { Wrapper, ProfileBox, NameBox, FollowBox, RecordBox } from './style';

function Mypage() {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const data = useSelector(state => state.mypage);
  const member = useSelector(state => state.mypage.member);
  const activity = useSelector(state => state.mypage.activity);
  const [btnClick, setBtnClick] = useState(false);
  useEffect(() => {
    dispatch(MypageGet());
  }, []);

  return (
    <Wrapper>
      <Header />
      {btnClick ? (
        <div className="delmodal">
          <div className="contentbox">정말 탈퇴하시겠습니까?</div>
          <div className="btns">
            <button
              className="yes"
              onClick={() => {
                dispatch(MyIdDelete());
                navigate('/');
              }}
            >
              예
            </button>
            <button className="no" onClick={() => setBtnClick(!btnClick)}>
              아니요
            </button>
          </div>
        </div>
      ) : null}
      <div className="box">
        <ProfileBox>
          <div className="circle">
            <img
              src={member && member.profileImage ? member.profileImage : null}
              alt="userProfile"
            />
          </div>
        </ProfileBox>
        <NameBox>
          <div className="username">
            {member && member.userName ? member.userName : null}
          </div>
        </NameBox>
        <FollowBox>
          <div>게시물 {data && data.postCounts ? data.postCounts : 0}</div>
          <div> {member && member.height ? member.height : 0}cm</div>
          <div> {member && member.weight ? member.weight : 0}kg</div>
        </FollowBox>
        <RecordBox>
          <div className="boxs">
            <div className="box">
              <FontAwesomeIcon icon={faDumbbell} />
              {activity && activity.kilogram ? activity.kilogram : 0}kg
            </div>
            <div className="box">
              <FontAwesomeIcon icon={faCalendarDays} />
              {activity && activity.dayCount ? activity.dayCount : 0}일
            </div>
            <div className="box">
              <FontAwesomeIcon icon={faTrophy} />
              {activity && activity.rank ? Math.round(activity.rank) : 0}등
            </div>
            <div className="box">
              <div>Point</div>
              {activity && activity.point ? Math.round(activity.point) : 0}점
            </div>
          </div>
          <button
            className="editBtn"
            onClick={() => navigate('/members/mypage/edit')}
          >
            정보 수정
          </button>
          <button onClick={() => setBtnClick(!btnClick)}>회원 탈퇴</button>
        </RecordBox>
        <hr className="line" />
        <PictureBox />
      </div>
      <Footer />
    </Wrapper>
  );
}

export default Mypage;

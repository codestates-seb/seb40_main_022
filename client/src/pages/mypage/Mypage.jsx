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
import daily from '../../images/daily.jpg';
import myimage from '../../images/qnaImg.jpg';

function Mypage() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const ac = useSelector(state => state.authToken.accessToken);
  const re = useSelector(state => state.authToken.token);
  const list = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  const [Clicked, setClicked] = useState(false);
  const data = [ac, re];

  useEffect(() => {
    dispatch(MypageGet(data));
  }, []);

  const listdata1 = useSelector(state => state.mypage.data);
  console.log(listdata1);

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
          <div className="username">{listdata1.userName}</div>
        </NameBox>
        <FollowBox>
          <div>게시물 1</div>
          <div>포인트 </div>
        </FollowBox>
        <RecordBox>
          <div className="boxs">
            <div className="box">
              <FontAwesomeIcon icon={faDumbbell} />
              kg
            </div>
            <div className="box">
              <FontAwesomeIcon icon={faTrophy} />
              132위
            </div>
            <div className="box">
              <FontAwesomeIcon icon={faPersonRunning} />
              1일
            </div>
          </div>
          <button className="editBtn" onClick={() => navigate('/mypage/edit')}>
            정보 수정
          </button>
          <button
            onClick={() => {
              dispatch(MyPostDelete(data));
              navigate('/');
            }}
          >
            회원 탈퇴
          </button>
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

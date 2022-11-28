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
import {
  MypageGet,
  // MypageScroll,
  MyIdDelete,
} from '../../redux/action/MypageAsync';
import { asyncPostDel } from '../../redux/action/MainAsync';
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

function Mypage() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  // const [Clicked, setClicked] = useState(false);
  const [btnClick, setBtnClick] = useState(false);
  const mdata = useSelector(state => state.mypage);
  const member = useSelector(state => state.mypage.member);
  const dailyPosts = useSelector(state => state.mypage.dailyPosts);
  const activity = useSelector(state => state.mypage.activity);
  // const lastPostId = useSelector(
  //   state => state.mypage.dailyPosts.items[2].postId,
  // );

  // const [postlist, setPostlist] = useState();
  useEffect(() => {
    dispatch(MypageGet());
  }, []);

  const handleDelPost = id => {
    dispatch(asyncPostDel(id));
    window.location.reload();
  };

  return (
    <Wrapper>
      <Header />
      {/* {Clicked ? (
        <div className="delmodal">
          <div className="contentbox">게시글을 삭제하시겠습니까?</div>
          <div className="btns">
            <button
              className="yes"
              onClick={() => {
                handleDelPost(list.post.postId);
              }}
            >
              예
            </button>
            <button className="no" onClick={() => setClicked(!Clicked)}>
              아니요
            </button>
          </div>
        </div>
      ) : null} */}
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
            <img src={member.profileImage} alt="userProfile" />
          </div>
        </ProfileBox>
        <NameBox>
          <div className="username">{member.userName}</div>
        </NameBox>
        <FollowBox>
          <div>게시물 {mdata.postCounts || 0}</div>
          <div> {member.height ? member.height : 0}cm</div>
          <div> {member.weight ? member.weight : 0}kg</div>
        </FollowBox>
        <RecordBox>
          <div className="boxs">
            <div className="box">
              <FontAwesomeIcon icon={faDumbbell} />
              {activity.kilogram}kg
            </div>
            <div className="box">
              <FontAwesomeIcon icon={faTrophy} />
              {activity.point ? activity.point : 0}점 73위
            </div>
            <div className="box">
              <FontAwesomeIcon icon={faPersonRunning} />
              {activity.dayCount ? activity.dayCount : 0}일
            </div>
          </div>
          <button className="editBtn" onClick={() => navigate('/mypage/edit')}>
            정보 수정
          </button>
          <button onClick={() => setBtnClick(!btnClick)}>회원 탈퇴</button>
        </RecordBox>
        <hr className="line" />
        <PictureBox>
          {dailyPosts.items &&
            dailyPosts.items
              .filter(el => typeof el.image === 'string')
              .map(listdata => {
                return (
                  <div key={listdata.postId}>
                    <img src={listdata.image} alt="" />
                    <button
                      onClick={() => {
                        handleDelPost(listdata.postId);
                      }}
                    >
                      <FontAwesomeIcon icon={faTrash} />
                    </button>
                  </div>
                );
              })}
          <button
            className="scroll"
            onClick={() => {
              // dispatch(MypageScroll(lastPostId));
            }}
          >
            +
          </button>
        </PictureBox>
      </div>
      <Footer />
    </Wrapper>
  );
}

export default Mypage;

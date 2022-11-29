import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
// import { useInView } from 'react-intersection-observer';
import { useNavigate, useParams } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  faDumbbell,
  faTrophy,
  faPersonRunning,
  faTrash,
} from '@fortawesome/free-solid-svg-icons';
import {
  MypageGet,
  MypageScroll,
  MyIdDelete,
  UserProfileGet,
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

function UserProfile() {
  const { params } = useParams();
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(UserProfileGet(params));
  }, []);

  const navigate = useNavigate();
  // const [Clicked, setClicked] = useState(false);
  const [btnClick, setBtnClick] = useState(false);
  const data = useSelector(state => state.mypage);
  const member = useSelector(state => state.mypage.data.member);
  const dailyPosts = useSelector(state => state.mypage.data.dailyPosts);
  const activity = useSelector(state => state.mypage.data.activity);
  const lastMyPost = dailyPosts && dailyPosts[dailyPosts.length - 1];

  //   const [postlist, setPostlist] = useState([dailyPosts]);
  // console.log(postlist);

  // const [page, setPage] = useState(1);
  // const [loading, setLoading] = useState(false);
  // const [ref, inView] = useInView();

  // const getItems = useCallback(() => {
  //   setLoading(true);
  //   dispatch(MypageScroll(dailyPosts[2].postId));
  //   setPostlist([...postlist, dailyPosts]);
  //   setLoading(false);
  // }, [page]);

  // useEffect(() => {
  //   getItems();
  // }, [getItems]);

  // useEffect(() => {
  //   if (inView && !loading) {
  //     setPage(prevState => prevState + 1);
  //   }
  // }, [inView, loading]);

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
          <div>게시물 {data.postCounts || 0}</div>
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
          {/* <button className="editBtn" onClick={() => navigate('/mypage/edit')}>
            정보 수정
          </button>
          <button onClick={() => setBtnClick(!btnClick)}>회원 탈퇴</button> */}
        </RecordBox>
        <hr className="line" />
        <button
          onClick={() => {
            dispatch(MypageGet());
          }}
        >
          -
        </button>
        <PictureBox>
          {dailyPosts &&
            dailyPosts
              .filter(el => typeof el.image === 'string')
              .map(post => {
                return (
                  <div key={post.postId}>
                    <img src={post.image} alt="" />
                    <button
                      onClick={() => {
                        handleDelPost(post.postId);
                      }}
                    >
                      <FontAwesomeIcon icon={faTrash} />
                    </button>
                  </div>
                );
              })}
          {/* <div ref={ref} /> */}
          <button
            onClick={() => {
              dispatch(MypageScroll(lastMyPost.postId));
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

export default UserProfile;

// {dailyPosts &&
//   dailyPosts
//     // .filter(el => typeof el.image === 'string')
//     .map(list => {
//       return (
//         <div>
//           {list &&
//             list.map(data => {
//               return (
//                 <div key={data.postId}>
//                   <img src={data.image} alt="" />
//                   <button
//                     onClick={() => {
//                       handleDelPost(data.postId);
//                     }}
//                   >
//                     <FontAwesomeIcon icon={faTrash} />
//                   </button>
//                 </div>
//               );
//             })}
//         </div>
//       );
//     })}

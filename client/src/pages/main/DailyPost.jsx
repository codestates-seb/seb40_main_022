import { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { useInView } from 'react-intersection-observer';
import uuidv4 from 'react-uuid';
import { useDispatch, useSelector } from 'react-redux';
import DailyInfomation from './DailyInfo';
import DailyImg from './DailyImg';
import dailyAdd from '../../images/daily_add.svg';
import { DailyForm, DailyItem, Top, Content } from './MainStyle';
import Loader from './Loader';
import { MainSearchAsync } from '../../redux/action/MainAsync';
import { searchclose } from '../../redux/reducer/MainSlice';

export default function DailyPost() {
  const [postList, setPostList] = useState([]);
  const lastPost = postList && postList[postList.length - 1];
  const [isLoaded, setIsLoaded] = useState(false);
  const searchtag = useSelector(state => state.dailypost.search);
  const searchList = useSelector(state => state.dailypost.searchList.items);
  const searchload = useSelector(state => state.dailypost.searchload);
  const [slist, setSlist] = useState([]);
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const newPost = () => {
    if (!localStorage.getItem('Authorization')) {
      alert('로그인 후 이용할 수 있습니다.');
    } else {
      navigate('/dailyposts/postup');
    }
  };
  // console.log(searchList, slist, searchload);
  const [ref, inView] = useInView();
  useEffect(() => {
    if (searchtag !== '' && searchtag !== undefined) {
      setIsLoaded(true);
      const data = [searchtag, ''];
      dispatch(MainSearchAsync(data))
        .unwrap()
        .then(() => {
          if (searchList !== undefined) {
            setSlist([]);
            setSlist(searchList);
            setIsLoaded(false);
          }
        })
        .then(() => {
          dispatch(searchclose());
        });
    }
  }, [searchtag, searchload]);

  useEffect(() => {
    const getPost = async () => {
      const res = await axios.get(
        `${process.env.REACT_APP_API_URL}/dailyPosts`,
      );
      const post = await res.data.items;
      setPostList([post]);
    };
    getPost();
  }, []);

  useEffect(() => {
    if (searchList) {
      const lastPostId = slist[slist.length - 1].post.postId;
      if (searchList && searchList.length >= 4 && lastPostId > 1 && inView) {
        const data = [searchtag, lastPostId];
        setIsLoaded(true);
        setTimeout(() => {
          dispatch(MainSearchAsync(data))
            .unwrap()
            .then(() => {
              if (searchList !== undefined) {
                setSlist([...slist, ...searchList]);
                setIsLoaded(false);
              }
            });
        }, 1000);
      }
    } else {
      const lastPostId =
        lastPost && lastPost[lastPost.length - 1] !== undefined
          ? lastPost[lastPost.length - 1].post.postId
          : null;
      if (lastPost && lastPostId > 1 && lastPost.length >= 4 && inView) {
        setIsLoaded(true);
        setTimeout(() => {
          axios
            .get(
              `${process.env.REACT_APP_API_URL}/dailyPosts?lastPostId=${lastPostId}`,
            )
            .then(res => {
              if (res.data.items !== undefined) {
                setPostList([...postList, res.data.items]);
                setIsLoaded(false);
              }
            });
        }, 1000);
      }
    }
  }, [inView]);

  return (
    <>
      <Top>
        {searchtag
          ? slist.slice(0, 4).map(el => {
              return (
                <Content key={uuidv4}>
                  <div className="imgprofile">
                    <button
                      onClick={() => {
                        navigate(`/members/${el.member.userId}`);
                      }}
                      className="top_user"
                    >
                      <img src={el.member.profileImage} alt="userProfile" />
                    </button>
                  </div>
                  <span className="userName">{el.member.username}</span>
                </Content>
              );
            })
          : postList[0] &&
            postList[0].map(el => {
              return (
                <Content key={uuidv4}>
                  <div className="imgprofile">
                    <button
                      onClick={() => {
                        navigate(`/members/${el.member.userId}`);
                      }}
                      className="top_user"
                    >
                      <img src={el.member.profileImage} alt="userProfile" />
                    </button>
                  </div>
                  <span className="userName">{el.member.username}</span>
                </Content>
              );
            })}
        <Content>
          <button className="imgprofile dailynew" onClick={() => newPost()}>
            <img src={dailyAdd} alt="dailyAdd" />
          </button>
          <span>새 게시물</span>
        </Content>
      </Top>
      <DailyForm>
        {searchtag
          ? slist.map(all => {
              return (
                <div className="list" key={uuidv4}>
                  <DailyItem key={all.post.postId}>
                    <DailyImg el={all} />
                    <DailyInfomation el={all} index={all.post.postId} />
                  </DailyItem>
                </div>
              );
            })
          : postList &&
            postList.map(all => {
              return (
                <div className="list" key={uuidv4}>
                  {all &&
                    all.map(list => {
                      return (
                        <DailyItem key={list.post.postId}>
                          <DailyImg el={list} />
                          <DailyInfomation el={list} index={list.post.postId} />
                        </DailyItem>
                      );
                    })}
                </div>
              );
            })}
        <div ref={ref}>{isLoaded && <Loader />}</div>
      </DailyForm>
    </>
  );
}

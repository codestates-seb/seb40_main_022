import { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { useInView } from 'react-intersection-observer';
import DailyInfomation from './DailyInfo';
import DailyImg from './DailyImg';
import dailyAdd from '../../images/daily_add.svg';
import { DailyForm, DailyItem, Top, Content } from './MainStyle';
import Loader from './Loader';

export default function DailyPost() {
  const [postList, setPostList] = useState([]);
  const lastPost = postList && postList[postList.length - 1];
  const [isLoaded, setIsLoaded] = useState(false);
  // const [bol, setBol] = useState(true);

  // const handleansbol = () => {
  //   console.log(filteredCmt);
  //   setBol(false);
  // };

  const navigate = useNavigate();

  const newPost = () => {
    if (!localStorage.getItem('Authorization')) {
      alert('로그인 후 이용할 수 있습니다.');
    } else {
      navigate('/dailypost');
    }
  };

  const [ref, inView] = useInView();

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
    const lastPostId =
      lastPost && lastPost[lastPost.length - 1] !== undefined
        ? lastPost[lastPost.length - 1].post.postId
        : null;
    if (lastPost && lastPostId > 1 && lastPost.length >= 3 && inView) {
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
  }, [inView]);

  return (
    <>
      <Top>
        {postList[0] &&
          postList[0].map(el => {
            return (
              <Content>
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
        {postList &&
          postList.map(all => {
            return (
              <div className="list">
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

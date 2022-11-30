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
  console.log(postList);
  const lastPost = postList && postList[postList.length - 1];
  const [isLoaded, setIsLoaded] = useState(false);

  // const [bol, setBol] = useState(true);

  // const handleansbol = () => {
  //   console.log(filteredCmt);
  //   setBol(false);
  // };

  const navigate = useNavigate();
  const newPost = () => {
    // if (!ac) {
    //   alert('로그인 후 이용할 수 있습니다.');
    // } else {
    navigate('/dailypost');
    // }
  };

  const [ref, inView] = useInView();

  useEffect(() => {
    const getPost = async () => {
      const res = await axios.get(`/dailyPosts`);
      const post = await res.data.items;
      setPostList([post]);
    };
    getPost();
  }, []);

  useEffect(() => {
    const lastPostId = lastPost && lastPost[lastPost.length - 1].post.postId;
    if (lastPost && lastPostId > 1 && inView) {
      setIsLoaded(true);
      setTimeout(() => {
        axios.get(`/dailyPosts?lastPostId=${lastPostId}`).then(res => {
          setPostList([...postList, res.data.items]);
          setIsLoaded(false);
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
                  <img src={el.member.profileImage} alt="userProfile" />
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
                <div ref={ref} />
              </div>
            );
          })}
        <div ref={ref}>{isLoaded && <Loader />}</div>
      </DailyForm>
    </>
  );
}

import { useEffect, useState } from 'react';
import axios from 'axios';
import { useInView } from 'react-intersection-observer';
import DailyInfomation from './DailyInfo';
import DailyImg from './DailyImg';
import { DailyForm, DailyItem } from './MainStyle';
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
  );
}

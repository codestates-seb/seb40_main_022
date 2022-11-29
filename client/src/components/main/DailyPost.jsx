import { useEffect, useState } from 'react';
import {
  // useDispatch,
  useSelector,
} from 'react-redux';
import axios from 'axios';
// import { useInView } from 'react-intersection-observer';
// import DailyCmt from './DailyCmt';
import DailyInfomation from './DailyInfo';
import DailyImg from './DailyImg';
import { DailyForm, DailyItem } from './MainStyle';
// import {
//   // asyncPost,
//   // asyncPostDel,
//   asyncPostScroll,
//   // asyncPostCmtEdit,
// } from '../../redux/action/MainAsync';

export default function DailyPost() {
  const data = useSelector(state => state.dailypost.data.items);
  console.log(data);

  // const dispatch = useDispatch();

  const [postList, setPostList] = useState([]);
  console.log(postList);

  const lastPost = postList && postList[postList.length - 1];
  console.log(lastPost);

  // const [loading, setLoading] = useState(true);
  // setPostList([...postList, data]);

  // const [bol, setBol] = useState(true);

  // const handleansbol = () => {
  //   console.log(filteredCmt);
  //   setBol(false);
  // };

  // useEffect(() => {
  //   dispatch(asyncPostScroll());
  // }, []);
  // const [ref, inView] = useInView();
  useEffect(() => {
    // setLoading(true);
    const getPost = async () => {
      const res = await axios.get(`/dailyPosts`);
      const post = await res.data.items;
      setPostList([post]);
      // if (postList.length !== 0 && lastPost && lastPost.post.postId >= 1 && inView) {
      //   dispatch(asyncPostScroll(lastPost.post.postId));
      //   setPostList([...postList, post]);
      // }
      // setLoading(false);
    };
    getPost();
    // const lastPost = postList[0];
    // console.log(lastPost);
  }, []);

  // useEffect(() => {
  //   if (
  //     postList.length !== 0 &&
  //     lastPost &&
  //     lastPost.post.postId >= 1 &&
  //     inView
  //   ) {
  //     dispatch(asyncPostScroll(lastPost[2].post.postId));
  //     // setPostList([...postList, postList]);
  //   }
  // }, [inView]);

  // const handleDelPost = id => {
  //   dispatch(asyncPostDel(id));
  //   window.location.reload();
  // };

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
              {/* <div ref={ref} /> */}
            </div>
          );
        })}
      {lastPost ? (
        <button
          onClick={() => {
            const lastPostId =
              lastPost && lastPost[lastPost.length - 1].post.postId;
            axios.get(`/dailyPosts?lastPostId=${lastPostId}`).then(res => {
              setPostList([...postList, res.data.items]);
            });
          }}
        >
          ++++++++++++++++
        </button>
      ) : null}
    </DailyForm>
  );
}

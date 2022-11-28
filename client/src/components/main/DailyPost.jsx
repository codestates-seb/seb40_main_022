import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import ImageSlider, { Slide } from 'react-auto-image-slider';

// import DailyCmt from './DailyCmt';
import DailyInfomation from './DailyInfo';
import { DailyForm, DailyItem } from './MainStyle';
import {
  asyncPost,
  asyncPostDel,
  asyncPostScroll,
  // asyncPostCmtEdit,
} from '../../redux/action/MainAsync';

export default function DailyPost() {
  const data = useSelector(state => state.dailypost.data.items);
  console.log(data);

  const lastPost = data && data[data.length - 1];
  console.log(lastPost);

  const dispatch = useDispatch();

  const [postList, setPostList] = useState([data]);
  console.log(postList);
  // setPostList([...postList, data]);

  // const [bol, setBol] = useState(true);

  // const handleansbol = () => {
  //   console.log(filteredCmt);
  //   setBol(false);
  // };

  useEffect(() => {
    dispatch(asyncPost());
  }, []);

  const handleDelPost = id => {
    dispatch(asyncPostDel(id));
    window.location.reload();
  };

  return (
    <DailyForm>
      {data &&
        data
          // .slice(0)
          // .reverse()
          .map(list => {
            // console.log(list.member.profileImage);
            return (
              <DailyItem>
                <article className="Img" list={list} key={list.post.postId}>
                  <ImageSlider effectDelay={500} autoPlayDelay={3000}>
                    {list.pictures &&
                      list.pictures.map(el => {
                        return (
                          <Slide>
                            <img className="dailyImg" src={el} alt="daily" />
                          </Slide>
                        );
                      })}
                  </ImageSlider>
                </article>
                <div>
                  <Link
                    to={`/dailypost/edit/${list.post.postId}`}
                    className="buttons"
                  >
                    수정
                  </Link>
                  <button
                    onClick={() => {
                      handleDelPost(list.post.postId);
                    }}
                  >
                    삭제
                  </button>
                </div>
                <DailyInfomation el={list} index={list.post.postId} />
              </DailyItem>
            );
          })}
      <button
        onClick={() => {
          dispatch(asyncPostScroll(lastPost.post.postId));
          setPostList([...postList, data]);
          console.log(lastPost.post.postId);
        }}
      >
        ++++++++++++++++
      </button>
    </DailyForm>
  );
}

import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import ImageSlider, { Slide } from 'react-auto-image-slider';
import daily from '../../images/daily.jpg';
import heart from '../../images/Heart.svg';
import heartFill from '../../images/heart_fill.svg';
import DailyCmt from './DailyCmt';
import { DailyForm, DailyItem } from './MainStyle';
import {
  asyncPost,
  asyncPostDel,
  asyncLike,
  asyncLikeundo,
  // asyncPostCmtEdit,
} from '../../redux/action/MainAsync';

export default function DailyPost() {
  const data = useSelector(state => state.dailypost.data.items);
  const dispatch = useDispatch();
  const [fav, setFav] = useState(false);
  const [isComment, setIsComment] = useState(false);
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
            return (
              <DailyItem>
                <article className="Img">
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
                <article className="DailyInfo">
                  <div className="Info">
                    <div className="DailyTags">
                      {list.tags &&
                        list.tags.map(tag => {
                          return <span>{`#${tag}`}</span>;
                        })}
                    </div>
                    <div className="DailyMemo">
                      <p className="memo">{list.post.content}</p>
                    </div>
                    <div className="act">
                      <span className="date">{list.post.createdAt}</span>
                      <span>
                        <button
                          onClick={() => setIsComment(!isComment)}
                          className="comments"
                        >
                          댓글
                          {list.post.commentCount ? list.post.commentCount : 0}
                          개
                        </button>
                      </span>
                      <span className="favorite">
                        <button
                          onClick={() => {
                            setFav(!fav);
                            if (fav === false) {
                              dispatch(asyncLikeundo(list.post.postId));
                            } else {
                              dispatch(asyncLike(list.post.postId));
                            }
                          }}
                        >
                          {fav ? (
                            <img
                              className="heart"
                              src={heartFill}
                              alt="heart"
                            />
                          ) : (
                            <img className="heart" src={heart} alt="heart" />
                          )}
                          <span>
                            {list.post.likeCount ? list.post.likeCount : 0}
                          </span>
                        </button>
                      </span>
                    </div>
                  </div>
                  <div className="userInfo">
                    <img
                      className="user"
                      src={
                        list.member.profileImage
                          ? list.member.profileImage
                          : daily
                      }
                      alt="daily"
                    />
                    <span>
                      {list.member.username ? list.member.username : null}
                    </span>
                  </div>
                </article>
                {isComment ? <DailyCmt /> : null}
              </DailyItem>
            );
          })}
    </DailyForm>
  );
}

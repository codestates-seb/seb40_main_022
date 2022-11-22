import { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import ImageSlider, { Slide } from 'react-auto-image-slider';
import heart from '../../images/Heart.svg';
import heartFill from '../../images/heart_fill.svg';
import DailyCmtInput from './DailyCmtInput';
import { DailyForm, DailyItem } from './MainStyle';
import {
  asyncPost,
  asyncPostDel,
  asyncPostCmt,
  asyncPostCmtDel,
  // asyncPostCmtEdit,
} from '../../redux/action/MainAsync';

export default function DailyPost() {
  const data = useSelector(state => state.dailypost.data);
  // const cmtData = useSelector(state => state.dailypost.comment);
  const dispatch = useDispatch();
  // const [fav, setFav] = useState(false);
  const [isComment, setIsComment] = useState(false);
  const id = useParams();

  const filteredCmt = useSelector(state =>
    state.dailypost.comment.filter(el => {
      return el.commentId === +id.id;
    }),
  );

  const handleansbol = () => {
    console.log(filteredCmt);
    // setBol(false);
  };

  const handleCmtDel = commentId => {
    dispatch(asyncPostCmtDel(commentId));
  };

  useEffect(() => {
    dispatch(asyncPost());
    dispatch(asyncPostCmt());
  }, [dispatch, filteredCmt]);

  const handleDelPost = postId => {
    dispatch(asyncPostDel({ postId }));
    window.location.reload();
  };

  return (
    <DailyForm>
      {data &&
        data.map(list => {
          return (
            <DailyItem>
              <article className="Img">
                <ImageSlider effectDelay={500} autoPlayDelay={3000}>
                  {list.pictures &&
                    list.pictures.map(img => {
                      return (
                        <Slide>
                          <img className="dailyImg" src={img} alt="daily" />
                        </Slide>
                      );
                    })}
                </ImageSlider>
              </article>
              <div>
                <Link to={`/dailypost/edit/${list.id}`} className="buttons">
                  수정
                </Link>
                <button
                  onClick={() => {
                    handleDelPost(list.id);
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
                    <div className="memo">{list.post.content}</div>
                    {/* advanced */}
                    {/* <span className="more">더보기</span> */}
                  </div>
                  <div className="act">
                    <span className="date">{list.post.createdAt}</span>
                    <span>
                      <button
                        onClick={() => setIsComment(!isComment)}
                        className="comments"
                      >
                        댓글 {list.post.commentCount}개
                      </button>
                    </span>
                    <span className="favorite">
                      <button onClick={() => !list.likeState}>
                        {list.likeState ? (
                          <img className="heart" src={heartFill} alt="heart" />
                        ) : (
                          <img className="heart" src={heart} alt="heart" />
                        )}
                        <span>{list.post.likeCount}</span>
                      </button>
                    </span>
                  </div>
                </div>
                <span className="userInfo">
                  <img
                    className="user"
                    src={list.member.profileImage}
                    alt="daily"
                  />
                  <span>{list.member.username}</span>
                </span>
              </article>
              {isComment ? (
                <div className="commentdiv">
                  {filteredCmt &&
                    filteredCmt.map(comment => {
                      return (
                        <div className="comment">
                          <span className="cmtUserImg">
                            <img
                              className="user"
                              src={comment.profileImage}
                              alt="daily"
                            />
                          </span>
                          <div className="cmtContent">
                            <div className="cmtUserName">
                              {comment.userName}
                            </div>
                            <div>{comment.content}</div>
                          </div>
                          <button
                            onClick={() => {
                              handleansbol();
                            }}
                          >
                            수정
                          </button>
                          <button
                            onClick={() => handleCmtDel(comment.commentId)}
                          >
                            삭제
                          </button>
                        </div>
                      );
                    })}
                  <DailyCmtInput />
                </div>
              ) : null}
            </DailyItem>
          );
        })}
    </DailyForm>
  );
}

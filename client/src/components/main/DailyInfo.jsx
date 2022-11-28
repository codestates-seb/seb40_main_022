import { useState } from 'react';
import { useDispatch } from 'react-redux';
import heart from '../../images/Heart.svg';
import heartFill from '../../images/heart_fill.svg';
import {
  asyncLike,
  asyncLikeundo,
  // asyncPostCmtEdit,
} from '../../redux/action/MainAsync';
import DailyCmt from './DailyCmt';

export default function DailyInfo({ el, index }) {
  const dispatch = useDispatch();
  const [fav, setFav] = useState(false);
  const [isComment, setIsComment] = useState(false);

  return (
    <div>
      <article className="DailyInfo">
        <div className="Info">
          <div className="DailyTags">
            {el.tags &&
              el.tags.map(tag => {
                return <span>{`#${tag}`}</span>;
              })}
          </div>
          <div className="DailyMemo">
            <p className="memo">{el.post.content}</p>
          </div>
          <div className="act">
            <span className="date">{el.post.createdAt}</span>
            <span>
              <button
                onClick={() => {
                  setIsComment(!isComment);
                }}
                className="comments"
              >
                댓글
                {el.post.commentCount ? el.post.commentCount : 0}개
              </button>
            </span>
            <span className="favorite">
              <button
                onClick={() => {
                  setFav(!fav);
                  if (fav === false) {
                    dispatch(asyncLikeundo(el.post.postId));
                  } else {
                    dispatch(asyncLike(el.post.postId));
                  }
                }}
              >
                {fav ? (
                  <img className="heart" src={heartFill} alt="heart" />
                ) : (
                  <img className="heart" src={heart} alt="heart" />
                )}
                <span>{el.post.likeCount ? el.post.likeCount : 0}</span>
              </button>
            </span>
          </div>
        </div>
        <div className="userInfo">
          <img className="user" src={el.member.profileImage} alt="daily" />
          <span>{el.member.username ? el.member.username : null}</span>
        </div>
      </article>
      {isComment ? <DailyCmt index={index} /> : null}
    </div>
  );
}
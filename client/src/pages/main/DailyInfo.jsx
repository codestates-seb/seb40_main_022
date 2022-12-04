import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import uuidv4 from 'react-uuid';
import heart from '../../images/Heart.svg';
import heartFill from '../../images/heart_fill.svg';
import comment from '../../images/comment.svg';
import {
  asyncLike,
  asyncLikeundo,
  // asyncPostCmtEdit,
} from '../../redux/action/MainAsync';
import DailyCmt from './DailyCmt';

export default function DailyInfo({ el, index }) {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [fav, setFav] = useState(el.likeSate);
  const [isComment, setIsComment] = useState(false);

  const handleFavorite = () => {
    setFav(!fav);
    if (fav === true) {
      dispatch(asyncLike(el.post.postId));
    } else {
      dispatch(asyncLikeundo(el.post.postId));
    }
  };

  return (
    <div>
      <article className="DailyInfo">
        <div className="Info">
          <div className="DailyTags">
            {el.tags &&
              el.tags.map(tag => {
                return <span key={uuidv4}>{`#${tag}`}</span>;
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
                <img className="commentIcon" src={comment} alt="comment" />
                {el.post.commentCount ? el.post.commentCount : 0}
              </button>
            </span>
            <span className="favorite">
              <button
                onClick={() => {
                  handleFavorite();
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
          <button
            className="cont-picture"
            onClick={() => {
              navigate(`/members/${el.member.userId}`);
            }}
          >
            <img className="user" src={el.member.profileImage} alt="daily" />
          </button>
          <span>{el.member.username ? el.member.username : null}</span>
        </div>
      </article>
      {isComment ? <DailyCmt index={index} /> : null}
    </div>
  );
}

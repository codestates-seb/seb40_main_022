import { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useParams } from 'react-router-dom';
import daily from '../../images/daily.jpg';
import dailyAdd from '../../images/daily_add.svg';
import { AddComment, CommentInput } from './MainStyle';
import {
  asyncPostCmtUp,
  asyncPostCmt,
  asyncPostCmtDel,
} from '../../redux/action/MainAsync';

export default function DailyCmt() {
  const dispatch = useDispatch();
  const [answervalue, setAnswervalue] = useState('');
  const id = useParams();
  const postdata = useSelector(state => {
    state.dailypost.data.filter(el => el.id === +id.id);
  });

  const postCmt = useSelector(state =>
    state.dailypost.comment.filter(el => {
      return el.commentId === +id.id;
    }),
  );

  // const cmtData = useSelector(state => state.dailypost.comment);

  const handleanswer = (commentId, content) => {
    const answerdata = [commentId, content];
    dispatch(asyncPostCmtUp(answerdata));
    setAnswervalue('');
  };

  const handleCmtDel = commentId => {
    dispatch(asyncPostCmtDel(commentId));
  };

  const handleans = e => {
    setAnswervalue(e.target.value);
  };

  useEffect(() => {
    return () => dispatch(asyncPostCmt());
  }, [dispatch, postCmt]);

  return (
    <div className="commentdiv">
      <AddComment>
        <span className="cmtUserImg">
          <img className="user" src={daily} alt="daily" />
        </span>
        <CommentInput
          placeholder="comment"
          value={answervalue}
          onChange={e => handleans(e)}
        />
        <button
          onClick={() => {
            handleanswer(postdata[0].id, answervalue);
          }}
        >
          <img className="add" src={dailyAdd} alt="dailyAdd" />
        </button>
      </AddComment>
      {postCmt &&
        postCmt
          .slice(0)
          .reverse()
          .map(comment => {
            return (
              <div className="comment" key={comment.id}>
                <span className="cmtUserImg">
                  <img
                    className="user"
                    src={
                      // comment.profileImage
                      //   ? comment.profileImage
                      //   : null
                      daily
                    }
                    alt="daily"
                  />
                </span>
                <div className="cmtContent">
                  <div className="cmtUserName">
                    {/* {comment.userName ? comment.userName : null} */}
                    운동인
                  </div>
                  <div>{comment.content}</div>
                </div>
                <button
                // onClick={() => {
                //   handleansbol();
                // }}
                >
                  수정
                </button>
                <button onClick={() => handleCmtDel(comment.id)}>삭제</button>
              </div>
            );
          })}
    </div>
  );
}

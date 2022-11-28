import { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import daily from '../../images/daily.jpg';
import dailyAdd from '../../images/daily_add.svg';
import edit from '../../images/edit.svg';
import del from '../../images/delete.svg';

import { AddComment, CommentInput } from './MainStyle';
import {
  asyncPostCmtUp,
  asyncPostCmt,
  asyncPostCmtDel,
} from '../../redux/action/MainAsync';

export default function DailyCmt({ index }) {
  const dispatch = useDispatch();
  const [answervalue, setAnswervalue] = useState('');
  const [cmtEditBut, setCmtEditBut] = useState(false);
  // const [editAnwer, setEditAnswer] = useState('');
  const ac = localStorage.getItem('Authorization');
  const cmtData = useSelector(state => state.dailypost.comment.items);
  console.log(cmtData);
  // const [cmtList, SetCmtList]=useState(cmtData);

  const handleAnswer = e => {
    e.preventDefault();
    if (!ac) {
      alert('로그인 후 이용할 수 있습니다.');
    } else if (ac && answervalue.length < 5) {
      alert('5자 이상 입력해주세요');
    } else if (ac && answervalue.length >= 5) {
      dispatch(asyncPostCmtUp({ answervalue, index }));
      setAnswervalue('');
    }
  };

  const handleCmtDel = commentId => {
    dispatch(asyncPostCmtDel({ index, commentId }));
  };

  const handleans = e => {
    setAnswervalue(e.target.value);
  };

  useEffect(
    () => {
      dispatch(asyncPostCmt(index));
    },
    [
      // dispatch, cmtData
    ],
  );

  return (
    <div className="commentdiv">
      <AddComment>
        <span className="cmtUserImg">
          <img className="user" src={daily} alt="daily" />
        </span>
        <CommentInput
          placeholder="comment"
          maxLength={30}
          value={answervalue}
          onChange={e => handleans(e)}
        />
        <button
          onClick={e => {
            handleAnswer(e);
          }}
        >
          <img className="add" src={dailyAdd} alt="dailyAdd" />
        </button>
      </AddComment>
      {cmtData &&
        cmtData.map(comment => {
          return (
            <div className="comment">
              <div className="cmtContent">
                <span className="cmtUserImg">
                  <img
                    className="user"
                    src={comment.profileImage ? comment.profileImage : null}
                    alt="daily"
                  />
                </span>
                <div className="id_content">
                  <div className="cmtUserName">
                    {comment.userName ? comment.userName : null}
                  </div>
                  <div className="content">
                    {comment.content && cmtEditBut ? (
                      <input
                        value={comment.content}
                        // onChange={e => setEditAnswer(e.target.value)}
                      />
                    ) : (
                      comment.content
                    )}
                  </div>
                </div>
              </div>
              <div className="buttons">
                <button onClick={() => setCmtEditBut(!cmtEditBut)}>
                  <img className="edit" src={edit} alt="edit" />
                </button>
                <button onClick={() => handleCmtDel(comment.commentId)}>
                  <img className="delete" src={del} alt="delete" />
                </button>
              </div>
            </div>
          );
        })}
    </div>
  );
}

import { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useParams } from 'react-router-dom';
import daily from '../../images/daily.jpg';
import dailyAdd from '../../images/daily_add.svg';
import { AddComment, CommentInput } from './MainStyle';
import { asyncPostCmtUp } from '../../redux/action/MainAsync';

export default function DailyCmtInput() {
  const id = useParams();
  const dispatch = useDispatch();
  const [answervalue, setAnswervalue] = useState('');

  const data = useSelector(state => {
    state.dailypost.data.filter(el => el.id === +id.id);
  });

  const handleanswer = (commentId, content) => {
    const answerdata = [commentId, content];
    dispatch(asyncPostCmtUp(answerdata));
    setAnswervalue('');
  };

  const handleans = e => {
    setAnswervalue(e.target.value);
  };

  return (
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
          handleanswer(data[0].id, answervalue);
        }}
      >
        <img className="add" src={dailyAdd} alt="dailyAdd" />
      </button>
    </AddComment>
  );
}

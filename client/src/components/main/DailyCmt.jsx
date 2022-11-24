import { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import daily from '../../images/daily.jpg';
import dailyAdd from '../../images/daily_add.svg';
import { AddComment, CommentInput } from './MainStyle';
import {
  // asyncPostCmtUp,
  asyncPostCmt,
  asyncPostCmtDel,
} from '../../redux/action/MainAsync';

export default function DailyCmt() {
  const dispatch = useDispatch();
  const [answervalue, setAnswervalue] = useState('');

  const postdata = useSelector(state => state.dailypost.data);
  const cmtData = useSelector(state => state.dailypost.comment);
  const dataA = [];

  postdata.forEach(el => {
    dataA.push(
      cmtData.filter(ele => {
        return ele.contentId === el.id;
      }),
    );
  });

  console.log(dataA);

  const handleCmtDel = commentId => {
    dispatch(asyncPostCmtDel(commentId));
  };

  const handleans = e => {
    setAnswervalue(e.target.value);
  };

  useEffect(() => {
    return () => dispatch(asyncPostCmt());
  }, []);

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
        // onClick={() => {
        //   handleanswer(postdata[0].id, answervalue);
        // }}
        >
          <img className="add" src={dailyAdd} alt="dailyAdd" />
        </button>
      </AddComment>
      {dataA &&
        dataA
          .slice(0)
          .reverse()
          .map(comment => {
            return (
              <div>
                {comment &&
                  comment.map(data => {
                    return (
                      <div className="comment">
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
                          {data.content}
                        </div>
                        <button>수정</button>
                        <button onClick={() => handleCmtDel(data.id)}>
                          삭제
                        </button>
                      </div>
                    );
                  })}
              </div>
            );
          })}
    </div>
  );
}

import { useState, useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import dailyAdd from '../../images/daily_add.svg';
import edit from '../../images/edit.svg';
import del from '../../images/delete.svg';
import { AddComment, CommentInput } from './MainStyle';
import { asyncPostCmtUp, asyncPostCmtDel } from '../../redux/action/MainAsync';

export default function DailyCmt({ index }) {
  const dispatch = useDispatch();
  const [answervalue, setAnswervalue] = useState('');
  const [cmtEditBut, setCmtEditBut] = useState(false);
  // const [editAnwer, setEditAnswer] = useState('');
  const ac = localStorage.getItem('Authorization');
  const [cmtList, setCmtList] = useState([]);
  const lookCmt = cmtList && cmtList[cmtList.length - 1];
  const lastCmt = lookCmt && lookCmt[lookCmt.length - 1];
  const navigate = useNavigate();

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

  useEffect(() => {
    const getPostCmt = async () => {
      const res = await axios.get(
        `${process.env.REACT_APP_API_URL}/dailyPosts/${index}/comments`,
      );
      const cmt = await res.data.items;
      setCmtList([cmt]);
    };
    getPostCmt();
  }, []);

  const plusBut = async () => {
    const listUp = [index, lastCmt.commentId];
    await axios
      .get(
        `${process.env.REACT_APP_API_URL}/dailyPosts/${listUp[0]}/comments?lastCommentId=${listUp[1]}`,
      )
      .then(res => {
        setCmtList([...cmtList, res.data.items]);
      });
  };

  return (
    <div className="commentdiv">
      <AddComment>
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
      {cmtList &&
        cmtList.map(comment => {
          return (
            <div>
              {comment &&
                comment.map(all => {
                  return (
                    <div className="comment">
                      <div className="cmtContent">
                        <span className="cmtUserImg">
                          <button
                            onClick={() => {
                              navigate(`/members/${all.memberId}`);
                            }}
                            className="cont-picture"
                          >
                            <img
                              className="user"
                              src={all.profileImage}
                              alt="daily"
                            />
                          </button>
                        </span>
                        <div className="id_content">
                          <div className="cmtUserName">{all.userName}</div>
                          <div className="content">
                            {all.content && cmtEditBut ? (
                              <input
                                value={all.content}
                                // onChange={e => setEditAnswer(e.target.value)}
                              />
                            ) : (
                              all.content
                            )}
                          </div>
                        </div>
                      </div>
                      <div className="buttons">
                        <button onClick={() => setCmtEditBut(!cmtEditBut)}>
                          <img className="edit" src={edit} alt="edit" />
                        </button>
                        <button
                          onClick={() => handleCmtDel(all.commentId, index)}
                        >
                          <img className="delete" src={del} alt="delete" />
                        </button>
                      </div>
                    </div>
                  );
                })}
            </div>
          );
        })}
      <div className="cmtListAdd">
        {lastCmt && lastCmt.commentId > 1 && cmtList[0].length >= 5 ? (
          <button
            onClick={() => {
              plusBut();
            }}
          >
            <img className="add" src={dailyAdd} alt="dailyAdd" />
          </button>
        ) : null}
      </div>
    </div>
  );
}

import { useState, useEffect } from 'react';
import {
  useDispatch,
  // useSelector
} from 'react-redux';
import axios from 'axios';
import daily from '../../images/daily.jpg';
import dailyAdd from '../../images/daily_add.svg';
import edit from '../../images/edit.svg';
import del from '../../images/delete.svg';
import { AddComment, CommentInput } from './MainStyle';
import {
  asyncPostCmtUp,
  // asyncPostCmt,
  // asynCmtScroll,
  asyncPostCmtDel,
} from '../../redux/action/MainAsync';

export default function DailyCmt({ index }) {
  const dispatch = useDispatch();
  const [answervalue, setAnswervalue] = useState('');
  const [cmtEditBut, setCmtEditBut] = useState(false);
  // const [editAnwer, setEditAnswer] = useState('');
  const ac = localStorage.getItem('Authorization');
  // const cmtData = useSelector(state => state.dailypost.comment.items);
  // console.log(cmtData);

  const [cmtList, setCmtList] = useState([]);
  const lookCmt = cmtList && cmtList[cmtList.length - 1];
  const lastCmt = lookCmt && lookCmt[lookCmt.length - 1];
  console.log(lastCmt);

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
      const res = await axios.get(`/dailyPosts/${index}/comments`);
      const cmt = await res.data.items;
      setCmtList([cmt]);
      // if (postList.length !== 0 && lastPost && lastPost.post.postId >= 1 && inView) {
      //   dispatch(asyncPostScroll(lastPost.post.postId));
      //   setPostList([...postList, post]);
      // }
      // setLoading(false);
      console.log(res);
    };
    getPostCmt();
    // const lastPost = postList[0];
    // console.log(lastPost);
    // () => {
    //   dispatch(asyncPostCmt(index));
    // },
    // [
    //   // dispatch, cmtData
    // ],
  }, []);

  const plusBut = () => {
    const listUp = [index, lastCmt.commentId];
    // dispatch(asynCmtScroll(listUp));
    // setCmtList([...cmtList, cmtData]);
    axios
      .get(`/dailyPosts/${listUp[0]}/comments?lastCommentId=${listUp[1]}`)
      .then(res => {
        setCmtList([...cmtList, res.data.items]);
        console.log(res);
      });
  };

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
                          <img
                            className="user"
                            src={all.profileImage}
                            alt="daily"
                          />
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
        {lastCmt && lastCmt.commentId > 1 ? (
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

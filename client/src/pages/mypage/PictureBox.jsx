import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { useDispatch } from 'react-redux';
import axios from 'axios';
import { useInView } from 'react-intersection-observer';
import uuidv4 from 'react-uuid';
// import { useNavigate } from 'react-router-dom';
import edit from '../../images/edit.svg';
import del from '../../images/delete.svg';
import Loader from '../main/Loader';
import { asyncPostDel } from '../../redux/action/MainAsync';

const Pictures = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-bottom: 50px;

  .postList {
    display: flex;
    align-items: center;
    width: 1215px;
    margin: 100px 0 100px 0;
  }
  .imgbox {
    display: flex;
    flex-direction: column;
    align-items: flex-end;
    padding: 5px;
    > img {
      width: 400px;
      height: 400px;
    }

    .buttons {
      > button {
        cursor: pointer;
        background: var(--white);
        border: none;
        width: 20px;
        height: 20px;
        border-radius: 50%;
        box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.25);
        margin: 5px;
        > img {
          width: 20px;
          height: 20px;
        }
      }
    }
  }
`;

export function PictureBox() {
  const dispatch = useDispatch();

  const [isLoaded, setIsLoaded] = useState(false);
  const [ref, inView] = useInView();
  const [postList, setPostList] = useState([]);
  const lastPost = postList && postList[postList.length - 1];

  // const navigate = useNavigate();
  useEffect(() => {
    const getPost = async () => {
      axios
        .get(`${process.env.REACT_APP_API_URL}/members/myPage`, {
          headers: {
            Authorization: localStorage.getItem('Authorization'),
            RefreshToken: localStorage.getItem('RefreshToken'),
          },
        })
        .then(res => {
          const post = res.data.dailyPosts.items;
          setPostList([post]);
        });
    };
    getPost();
  }, []);

  useEffect(() => {
    const lastPostId =
      lastPost && lastPost[lastPost.length - 1] !== undefined
        ? lastPost[lastPost.length - 1].postId
        : null;
    if (lastPost && lastPostId > 1 && inView) {
      setIsLoaded(true);
      setTimeout(() => {
        axios
          .get(
            `${process.env.REACT_APP_API_URL}/members/myPage?lastPostId=${lastPostId}`,
            {
              headers: {
                Authorization: localStorage.getItem('Authorization'),
                RefreshToken: localStorage.getItem('RefreshToken'),
              },
            },
          )
          .then(res => {
            const newPost = res.data.dailyPosts.items;
            setPostList([...postList, newPost]);
            setIsLoaded(false);
          });
      }, 1000);
    }
  }, [inView]);

  const handleDelPost = id => {
    dispatch(asyncPostDel(id));
    window.location.reload();
  };
  return (
    <Pictures>
      {postList &&
        postList.map(list => {
          return (
            <div className="postList" key={uuidv4}>
              {list &&
                list
                  .filter(el => el.image !== null)
                  .map(el => {
                    return (
                      <div key={el.postId} className="imgbox">
                        <img src={el.image} alt="" />
                        <div className="buttons">
                          <button
                            onClick={() =>
                              // navigate(`/dailyposts/${el.postId}`)
                              alert('구현 중입니다.')
                            }
                          >
                            <img className="edit" src={edit} alt="edit" />
                          </button>
                          <button
                            onClick={() => {
                              handleDelPost(el.postId);
                            }}
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
      <div ref={ref}>{isLoaded && <Loader />}</div>
    </Pictures>
  );
}

export default PictureBox;

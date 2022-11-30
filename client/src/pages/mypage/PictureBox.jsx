import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { useDispatch } from 'react-redux';
import axios from 'axios';
import { useInView } from 'react-intersection-observer';
// import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
// import faTrash from '@fortawesome/free-solid-svg-icons';
import Loader from '../../components/main/Loader';
import { asyncPostDel } from '../../redux/action/MainAsync';

export const Pictures = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin-bottom: 50px;

  /* display: grid;
  grid-row-gap: 30px;
  grid-template-rows: auto;
  grid-template-columns: auto auto auto;
  width: 100%;
  margin-bottom: 10px; */

  > div {
    width: 85vw;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: row;
    margin-bottom: 50px;
  }
  .imgbox {
    display: flex;
    flex-direction: column;
    width: 80%;

    > img {
      width: 300px;
      height: 300px;
    }
    > button {
      cursor: pointer;
      width: 40px;
      margin-top: 10px;
    }
  }
`;

export function PictureBox() {
  const dispatch = useDispatch();

  const [isLoaded, setIsLoaded] = useState(false);
  const [ref, inView] = useInView();
  const [postList, setPostList] = useState([]);
  const lastPost = postList && postList[postList.length - 1];

  useEffect(() => {
    const getPost = async () => {
      axios
        .get('/members/myPage', {
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
    const lastPostId = lastPost && lastPost[lastPost.length - 1].postId;
    if (lastPost && lastPostId > 1 && inView) {
      setIsLoaded(true);
      setTimeout(() => {
        axios
          .get(`/members/myPage?lastPostId=${lastPostId}`, {
            headers: {
              Authorization: localStorage.getItem('Authorization'),
              RefreshToken: localStorage.getItem('RefreshToken'),
            },
          })
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
        postList
          // .filter(el => typeof el.image === 'string')
          .map(list => {
            return (
              <div>
                {list &&
                  list.map(el => {
                    return (
                      <div key={el.postId} className="imgbox">
                        <img src={el.image} alt="" />
                        <button
                          onClick={() => {
                            handleDelPost(el.postId);
                          }}
                        >
                          삭제
                          {/* <FontAwesomeIcon icon={faTrash} /> */}
                        </button>
                      </div>
                    );
                  })}
                <div ref={ref} />
              </div>
            );
          })}
      <div ref={ref}>{isLoaded && <Loader />}</div>
    </Pictures>
  );
}

import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import { useInView } from 'react-intersection-observer';
import Loader from '../main/Loader';

const Pictures = styled.div`
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

function UserPicture() {
  const Id = +useParams().id;

  const [isLoaded, setIsLoaded] = useState(false);
  const [ref, inView] = useInView();
  const [postList, setPostList] = useState([]);
  const lastPost = postList && postList[postList.length - 1];

  useEffect(() => {
    const getPost = async () => {
      axios.get(`/members/${Id}`).then(res => {
        const post = res.data.dailyPosts.items;
        setPostList([post]);
      });
    };
    getPost();
  }, []);

  useEffect(() => {
    const lastPostId = lastPost && lastPost[lastPost.length - 1].postId;
    const lastId = [Id, lastPostId];
    if (lastPost && lastPostId > 1 && inView) {
      setIsLoaded(true);
      setTimeout(() => {
        axios.get(`/members/${lastId[0]}?lastPostId=${lastId[1]}`).then(res => {
          const newPost = res.data.dailyPosts.items;
          setPostList([...postList, newPost]);
          setIsLoaded(false);
        });
      }, 1000);
    }
  }, [inView]);

  return (
    <Pictures>
      {postList &&
        postList.map(list => {
          return (
            <div>
              {list &&
                list.map(el => {
                  return (
                    <div key={el.postId} className="imgbox">
                      <img src={el.image} alt="" />
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

export default UserPicture;

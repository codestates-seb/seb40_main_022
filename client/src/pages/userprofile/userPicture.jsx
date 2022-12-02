import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import { useInView } from 'react-intersection-observer';
import Loader from '../main/Loader';

const UserPictures = styled.div`
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
`;

function UserPicture() {
  const Id = +useParams().id;

  const [isLoaded, setIsLoaded] = useState(false);
  const [ref, inView] = useInView();
  const [postList, setPostList] = useState([]);
  const lastPost = postList && postList[postList.length - 1];

  useEffect(() => {
    const getPost = async () => {
      axios.get(`${process.env.REACT_APP_API_URL}/members/${Id}`).then(res => {
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
    const lastId = [Id, lastPostId];
    if (lastPost && lastPostId > 1 && inView) {
      setIsLoaded(true);
      setTimeout(() => {
        axios
          .get(
            `${process.env.REACT_APP_API_URL}/members/${lastId[0]}?lastPostId=${lastId[1]}`,
          )
          .then(res => {
            const newPost = res.data.dailyPosts.items;
            setPostList([...postList, newPost]);
            setIsLoaded(false);
          });
      }, 1000);
    }
  }, [inView]);

  return (
    <UserPictures>
      {postList &&
        postList.map(list => {
          return (
            <div>
              {list &&
                list
                  .filter(el => el.image !== null)
                  .map(el => {
                    return (
                      <div key={el.postId} className="imgbox">
                        <img src={el.image} alt="" />
                      </div>
                    );
                  })}
            </div>
          );
        })}
      <div ref={ref}>{isLoaded && <Loader />}</div>
    </UserPictures>
  );
}

export default UserPicture;

import React from 'react';
import styled from 'styled-components';

const QnaPostBack = styled.div`
  width: 100%;
  height: 100vh;
  margin: 0;
  padding: 0;
  background-color: #d6e3e3;
  border: 1px solid #d6e3e3;
`;
const QnaPost = styled.main`
  width: 680px;
  height: 45%;
  margin: auto;
  border: 3px solid white;
  background-color: white;
  margin-top: 300px;
`;

const PostTitle = styled.section`
  height: 90px;
  display: flex;
  justify-content: center;
  margin-top: 40px;
  > div {
    > h2 {
      font-size: var(--font-16);
    }
    > input {
      width: 520px;
      height: 34px;
    }
  }
`;

const PostContent = styled.section`
  height: 270px;
  border: 1px solid red;
  display: flex;
  justify-content: center;
  > div {
    > h2 {
      font-size: var(--font-16);
    }
    > input {
      width: 520px;
      height: 220px;
    }
  }
`;
const PostTag = styled.section`
  display: flex;
  width: 65%;
  height: 100px;
  justify-content: center;
  > div {
    > h2 {
      font-size: var(--font-16);
    }
    .oneButton {
      background-color: var(--tagyellow);
    }
    > button {
      margin-top: 20px;
      margin-right: 5px;
      width: 50px;
      height: 30px;
      border: none;
      background-color: #f6f6f6;
      font-size: var(--font-13);
      font-weight: 500;
      cursor: pointer;
    }
  }
`;

const PostSubmit = styled.section`
  display: flex;
  height: 100px;
  justify-content: center;
  margin-top: 30px;
  > :nth-child(1) {
    width: 120px;
    height: 50px;
    background-color: var(--logored);
    border: none;
    color: white;
    font-weight: bold;
    margin-right: 30px;
    border-radius: 20px;
    cursor: pointer;
  }
  > :nth-child(2) {
    width: 120px;
    height: 50px;
    background-color: #aeaeae;
    border: none;
    color: white;
    font-weight: bold;
    border-radius: 20px;
    cursor: pointer;
  }
`;
function QnaAsk() {
  return (
    <QnaPostBack>
      <QnaPost>
        <PostTitle>
          <div>
            <h2>제목</h2>
            <input />
          </div>
        </PostTitle>
        <PostContent>
          <div>
            <h2>내용</h2>
            <input />
          </div>
        </PostContent>
        <PostTag>
          <div>
            <h2>태그</h2>
            <button className="oneButton">운동</button>
            <button>식단</button>
            <button>영양소</button>
            <button>헬스</button>
            <button>습관</button>
          </div>
        </PostTag>
        <PostSubmit>
          <button>등록</button>
          <button>취소</button>
        </PostSubmit>
      </QnaPost>
    </QnaPostBack>
  );
}

export default QnaAsk;

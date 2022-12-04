import styled from 'styled-components';

export const QnaPostBack = styled.div`
  width: 100%;
  height: auto;
  margin: 0;
  padding: 0;
  background-color: #d6e3e3;
  border: 1px solid #d6e3e3;
`;
export const QnaPost = styled.main`
  width: 680px;
  height: 600px;
  margin: 270px auto;
  border: 3px solid white;
  background-color: white;
  margin-top: 300px;
`;

export const PostTitle = styled.section`
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
      padding: 10px;
      outline: none;
      border: none;
      padding: 10px;
      box-shadow: inset var(--box-shadow);
      outline: none;
      border-radius: 5px;
      &::placeholder {
        color: var(--black-200);
        font-weight: bold;
      }
    }
  }
`;

export const PostContent = styled.section`
  height: 270px;
  display: flex;
  justify-content: center;
  > div {
    > h2 {
      font-size: var(--font-16);
    }
    > textarea {
      width: 520px;
      height: 220px;
      padding: 10px;
      resize: none;
      outline: none;
      border: none;
      padding: 10px;
      height: 200px;
      box-shadow: inset var(--box-shadow);
      outline: none;
      border-radius: 5px;
      &::placeholder {
        color: var(--black-200);
        font-weight: bold;
      }
    }
  }
`;
export const PostTag = styled.section`
  display: flex;
  width: 73%;
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
      box-shadow: var(--box-shadow);
      cursor: pointer;
    }
  }
`;

export const PostSubmit = styled.section`
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
    box-shadow: var(--box-shadow);
    :hover {
      background-color: #fa8a8a;
    }
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
    box-shadow: var(--box-shadow);
    :hover {
      background-color: #cfcfcf;
    }
  }
`;

import styled from 'styled-components';
import QnaBanner from '../../images/QnaBanner.jpg';

export const QnABack = styled.main`
  width: 100%;
`;

export const Qna = styled.main`
  max-width: 1200px;
  width: 100%;
  height: auto;
  margin: 0 auto;
  padding-top: 80px;

  .content {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
  }
`;

export const QnaBan = styled.div`
  width: 100%;
  background: url(${QnaBanner});
  height: 150px;
  background-size: cover;
  background-repeat: no-repeat;
  background-position: 50% 38%;
`;

export const QnaTitle = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-between;
  margin-top: 50px;
  > h1 {
    font-weight: 600;
  }
  > button {
    font-size: var(--font-16);
    font-weight: 700;
    background-color: var(--logored);
    width: 100px;
    height: 50px;
    color: white;
    border: none;
    border-radius: 20px;
    cursor: pointer;
    :hover {
      background-color: #fa8a8a;
    }
  }
`;

export const QnaSearch = styled.div`
  margin-top: 30px;
  position: relative;
  display: flex;
  #SearchIn {
    width: 750px;
    padding: 15px;
    border: 1px solid #959595;
    border-radius: 50px;
    outline: none;
    box-shadow: var(--box-shadow);
  }
  > label {
    display: flex;
    align-items: center;
    > img {
      width: 28px;
      height: 28px;
      position: absolute;
      right: 460px;
    }
  }
`;

export const QnaRadio = styled.div`
  width: 830px;
  margin: 30px 0;
  font-size: var(--font-23);
  line-height: 2rem;
  padding: 2em 0.4em;
  > label {
    margin-right: 15px;
    > input {
      accent-color: black;
      margin-right: 10px;
      border: max(2px, 0.1em) solid gray;
    }
  }
`;

export const QnaContent = styled.section`
  /* width: 100%; */
  display: flex;
  flex-direction: column;
  .qnabox {
    display: flex;
    margin-bottom: 40px;
    /* width: 68%; */
    /* background-color: var(--black-025); */
    border-bottom: 1px solid var(--black-300);
    padding: 20px;
    > article {
      width: 70%;
      > div {
        display: block;
        width: 780px;
        .titlename {
          font-size: var(--font-18);
          font-weight: bold;
          text-decoration: none;
          color: black;
        }
        > h3 {
          font-size: var(--font-18);
          font-weight: 300;
          opacity: 0.7;
          margin: 25px 0;
        }
      }
      > span {
        display: flex;
        > h3 {
          font-size: var(--font-12);
          font-weight: 600;
          margin-right: 25px;
          margin-top: 5px;
          cursor: pointer;
        }
        > button {
          background-color: var(--tagyellow);
          border: none;
          width: 50px;
          height: 30px;
          font-weight: 600;
          cursor: pointer;
          box-shadow: var(--box-shadow);
        }
      }
    }
  }
`;

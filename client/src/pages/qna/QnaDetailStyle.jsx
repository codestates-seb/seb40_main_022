import styled from 'styled-components';
import QnAImg from '../../images/qnaImg.jpg';

export const Detail = styled.div`
  width: 100%;
`;
export const Headerwrap = styled.main`
  width: 100%;
  padding-bottom: 40px;
`;
export const DetailBack = styled.article`
  max-width: 1200px;
  margin: 100px auto;
`;
export const DetailTitle = styled.section`
  margin: 0 auto;
  border-bottom: 1px solid #b8b8b8;
  margin: 40px 0;
  display: block;
  > h2 {
    height: auto;
    font-size: var(--font-24);
    font-weight: 800;
  }
  > h3 {
    font-size: var(--font-18);
    margin: 60px 0;
    line-height: 1.7rem;
    opacity: 0.7;
    font-weight: 500;
  }
  > div {
    background-image: url(${QnAImg});
    background-size: cover;
    width: 215px;
    height: 150px;
    background-repeat: no-repeat;
    background-position: 50% 55%;
    margin: 40px 0;
  }
  > section {
    display: flex;
    justify-content: space-between;
  }
`;

export const DetailUpdate = styled.div`
  > .qnaupdate {
    width: 120px;
    height: 50px;
    border-radius: 20px;
    background-color: var(--logored);
    color: white;
    font-weight: bold;
    border: none;
    float: right;
    cursor: pointer;
    text-decoration: none;
    box-shadow: var(--box-shadow);
    :hover {
      background-color: #fa8a8a;
    }
    > h3 {
      text-align: center;
      margin-top: 14px;
    }
  }
`;

export const DetailNDB = styled.span`
  display: flex;
  margin-bottom: 50px;
  > div {
    display: flex;
    margin-right: 20px;
    h4 {
      font-size: var(--font-15);
      line-height: 2rem;
      opacity: 0.6;
      margin-right: 10px;
    }
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
`;

export const DetailAnswer = styled.div`
  padding-top: 40px;
  > h2 {
    font-size: var(--font-20);
  }
  > h3 {
    margin: 40px 0;
    font-size: var(--font-18);
    line-height: 1.7rem;
    opacity: 0.7;
    font-weight: 500;
  }
`;

export const AnswerNDB = styled.span`
  display: flex;
  margin-bottom: 50px;

  > div {
    display: flex;
    margin-right: 10px;
    > h4 {
      margin-right: 10px;
      font-size: var(--font-15);
      line-height: 2rem;
      opacity: 0.6;
    }
  }

  > button {
    background-color: var(--logored);
    border: none;
    border-radius: 30px;
    color: white;
    font-weight: bolder;
    width: 30px;
    height: 30px;
    font-weight: 600;
  }
`;

export const DetailComment = styled.section`
  > h2 {
    font-size: var(--font-20);
    font-weight: 600;
    margin-bottom: 40px;
  }
  > textarea {
    width: 1200px;
    border-radius: 22px;
    border: 1px solid #bababa;
    font-size: var(--font-18);
    padding: 50px 20px 40px;
    outline: none;
    resize: none;
    overflow-y: hidden;
  }
`;

export const DetailSubmit = styled.button`
  width: 120px;
  height: 50px;
  border-radius: 20px;
  background-color: var(--logored);
  color: white;
  font-weight: bold;
  border: none;
  margin-top: 25px;
  float: right;
  font-size: var(--font-19);
  box-shadow: var(--box-shadow);
  cursor: pointer;
  :hover {
    background-color: #fa8a8a;
  }
`;

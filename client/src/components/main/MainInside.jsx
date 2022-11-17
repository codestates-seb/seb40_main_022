import styled from 'styled-components';
import { useState } from 'react';
import TopList from './TopList';
import DailyPost from './DailyPost';
import search from '../../images/search.svg';
import Challenge from '../modal/Challenge';
import ChallengeReq from '../modal/ChallengeReq';

export const Inside = styled.section`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  .searchInput {
    display: flex;
    justify-content: center;
    position: fixed;
    top: 20px;
    background-color: var(--white);
    width: 850px;
    z-index: 8;

    form {
      width: 78%;
      margin: 20px 0;
      position: relative;

      .searchIcon {
        position: absolute;
        cursor: pointer;
        margin-top: 63px;
        margin-left: -45px;
        width: 25px;
        height: 25px;
      }
    }
  }
`;

export const MainForm = styled.article`
  display: flex;
  justify-content: center;
  position: relative;
  /* top: 80px; */
  /* width: 70%; */
  background-color: var(--white);
  border-radius: 5px;
  box-shadow: var(--box-shadow);
  /* background-color: var(--black-025); */
  padding: 0 2vw;
`;

export const MainSearch = styled.input`
  width: 100%;
  height: 50px;
  border: none;
  box-shadow: inset var(--box-shadow);
  border-radius: 50px;
  outline: 1px solid var(--black-100);
  margin-top: 50px;
  padding-left: 30px;
  font-size: var(--font-20);

  &::placeholder {
    color: var(--black-300);
  }
`;

export const ContentForm = styled.div`
  /* position: absolute; */
  margin-top: 180px;
  /* left: 50px;
  right: 50px; */
`;

export default function MainInside() {
  const [challenge, setChallenge] = useState(false);
  const [challengeReq, setChallengeReq] = useState(false);

  return (
    <Inside>
      <div className="searchInput">
        <form action="search">
          <MainSearch placeholder="태그를 입력하세요" />
          <img className="searchIcon" src={search} alt="search" />
        </form>
      </div>
      <MainForm>
        <ContentForm>
          <button onClick={() => setChallengeReq(true)} className="challenge">
            대결 신청
          </button>
          <button onClick={() => setChallenge(true)} className="challenge">
            대결 모달
          </button>
          <Challenge open={challenge} close={() => setChallenge(false)} />
          <ChallengeReq
            open={challengeReq}
            close={() => setChallengeReq(false)}
          />
          <TopList />
          <DailyPost />
        </ContentForm>
      </MainForm>
    </Inside>
  );
}

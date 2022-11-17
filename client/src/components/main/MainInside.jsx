import { useState } from 'react';
import TopList from './TopList';
import DailyPost from './DailyPost';
import search from '../../images/search.svg';
import ChallengeReq from '../modal/ChallengeReq';
import { Inside, MainForm, MainSearch, ContentForm } from './MainStyle';

export default function MainInside() {
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

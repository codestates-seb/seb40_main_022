import DailyPost from './DailyPost';
import search from '../../images/search.svg';
import { Inside, MainForm, MainSearch, ContentForm } from './MainStyle';

export default function MainInside() {
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
          <DailyPost />
        </ContentForm>
      </MainForm>
    </Inside>
  );
}

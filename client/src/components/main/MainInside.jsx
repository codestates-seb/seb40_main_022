import styled from 'styled-components';
import TopList from './TopList';
import DailyPost from './DailyPost';
import search from '../../images/search.svg';

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
    width: 70%;
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
  width: 70%;
  height: 1000vh;
  background-color: var(--white);
  border-radius: 5px;
  box-shadow: var(--box-shadow);
  /* background-color: var(--black-025); */
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
  position: absolute;
  top: 180px;
  left: 50px;
  right: 50px;
`;

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
          <TopList />
          <DailyPost />
        </ContentForm>
      </MainForm>
    </Inside>
  );
}

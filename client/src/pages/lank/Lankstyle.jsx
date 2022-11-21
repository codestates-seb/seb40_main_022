import styled from 'styled-components';

export const LankBox = styled.div`
  width: 100%;
`;
export const LankMain = styled.main`
  max-width: 1200px;
  margin: 0 auto;
  width: 100%;
  height: auto;
  padding-top: 60px;
  margin-bottom: 100px;
  .lankhead {
    display: flex;
    justify-content: space-between;
    margin-top: 100px;
    margin-bottom: 30px;
    > div {
      display: flex;
      align-items: center;
      justify-content: center;
      margin-right: 100px;
      > button {
        border: none;
        background-color: #fff;
        .search {
          cursor: pointer;
          width: 30px;
          height: 30px;
        }
      }
    }
    > h2 {
      color: black;
      font-size: var(--font-28);
      font-weight: 700;
    }
    .lankTab {
      display: flex;
      > li {
        list-style: none;
        > button {
          width: 70px;
          height: 30px;
        }
      }
    }
  }
`;

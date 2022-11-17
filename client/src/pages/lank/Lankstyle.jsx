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
    > h2 {
      color: black;
      font-size: var(--font-28);
      font-weight: 700;
      margin-right: 380px;
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
    > label {
      > img {
        width: 30px;
        height: 30px;
        margin-top: 10px;
        cursor: pointer;
      }
    }
  }
`;

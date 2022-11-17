import styled from 'styled-components';

const Head = styled.header`
  width: 100%;
  height: 60px;
  display: flex;
  justify-content: center;
  background-color: var(--backwhite);
  box-shadow: 0 1px 2px 0px var(--headgray);
  position: fixed;
  z-index: 9;
  .mainHead {
    max-width: 1200px;
    width: 100%;
    display: flex;
    align-items: center;
    justify-content: space-between;
    position: relative;
    .logo {
      text-decoration: none;
      cursor: pointer;
      display: flex;
      align-items: center;
      .logoname {
        margin-left: 20px;
        font-size: var(--font-27);
        color: var(--logored);
        font-style: italic;
        font-weight: 600;
      }
    }
    .Rightheader {
      display: flex;
      align-items: center;
      > .loginbut,
      .logoutbut {
        text-decoration: none;
        cursor: pointer;
        padding: 5px 20px;
        color: var(--headgray);
        :hover {
          border-radius: 20px;
          background-color: var(--headgray);
          color: var(--backwhite);
        }
      }
      > button {
        border: none;
        background-color: var(--backwhite);
        position: relative;
        .sideb {
          cursor: pointer;
          margin-left: 10px;
          width: 25px;
          position: relative;
        }
      }

      .noticeModal {
        margin: 0 20px;
        cursor: pointer;
        position: relative;
        width: 20px;
        height: 20px;
      }
    }
  }
`;
export default Head;

import styled from 'styled-components';

const SignMain = styled.main`
  background-color: var(--backcolor);
  width: 100%;
  height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  .SignTitle {
    font-size: var(--font-24);
    margin-bottom: 20px;
  }
  > input {
    margin-top: 15px;
    width: 400px;
    border-radius: 10px;
    border: none;
    box-shadow: var(--box-shadow);
    padding: 15px;
    outline: none;
  }
  > .buttons {
    width: 390px;
    display: flex;
    align-items: center;
    justify-content: space-between;
    .Ouathbutton {
      display: flex;
      flex-direction: column;
    }
    .SignButton {
      cursor: pointer;
      margin-top: 30px;
      color: white;
      background-color: var(--logored);
      border-radius: 20px;
      font-size: var(--font-18);
      font-weight: 600;
      width: 130px;
      height: 60px;
      border: none;
      box-shadow: var(--box-shadow);
      :hover {
        background-color: #fa8a8a;
      }
    }
  }
  .ErrorMsg {
    color: red;
    font-size: var(--font-12);
  }
`;

export default SignMain;

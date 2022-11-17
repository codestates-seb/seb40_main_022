import styled from 'styled-components';
import loginback from '../../images/loginback.png';

const LoginStyle = styled.main`
  .loginbox {
    width: 100%;
    height: 100vh;
    padding-top: 60px;
    display: flex;
    align-items: center;
    justify-content: center;
    background: url(${loginback});
    background-position: center;
    background-size: cover;
    background-repeat: no-repeat;
    .logosection {
      width: 400px;
      height: 500px;
      margin-left: 700px;
      border-radius: 20px;
      display: flex;
      align-items: center;
      flex-direction: column;
      background-color: #fff;
      @media screen and (max-width: 1100px) {
        margin-left: 0px;
      }
      > span {
        margin-top: 10px;
        display: flex;
        align-items: center;
        justify-content: center;
        width: 100%;
        height: 60px;
        font-size: var(--font-24);
      }
      > input {
        width: 250px;
        border-radius: 10px;
        border: none;
        padding: 10px;
        box-shadow: var(--box-shadow);
        outline: none;
        margin-top: 30px;
      }

      .LoginButton {
        cursor: pointer;
        box-shadow: 0px 2px 3px 2px #cbcbcb;
        width: 150px;
        background-color: #e8e8e8;
        color: #9b9b9b;
        border: none;
        font-size: var(--font-18);
        padding: 10px;
        margin-top: 30px;
      }
    }
  }
  .footerbox {
    width: 100%;
    height: 200px;
    background-color: #333;
  }
`;

export default LoginStyle;

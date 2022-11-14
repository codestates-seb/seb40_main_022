import styled from 'styled-components';

const Ouathbutton = styled.div`
  .goolobutton,
  .githubbutton,
  .fabookbutton {
    cursor: pointer;
    border-radius: 5px;
    display: flex;
    align-items: center;
    justify-content: center;
    width: 220px;
    height: 38px;
    font-size: var(--font-13);
    margin-top: 10px;
    > img {
      margin-right: 10px;
    }
  }
  .goolobutton {
    background-color: #fff;
    color: #3b4045;
    margin-top: 30px;
  }
  .githubbutton {
    background-color: #2f3337;
    color: #fff;
  }
  .fabookbutton {
    background-color: #385499;
    color: #fff;
  }
`;

export default Ouathbutton;

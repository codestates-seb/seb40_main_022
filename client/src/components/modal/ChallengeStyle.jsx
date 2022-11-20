import styled from 'styled-components';

export const ModalHeader = styled.section`
  position: relative;

  > button {
    position: absolute;
    border: none;
    top: 10px;
    right: 10px;
    width: 30px;
    font-size: 21px;
    font-weight: 700;
    text-align: center;
    color: #999;
    background-color: transparent;
    :hover {
      cursor: pointer;
      color: black;
    }
  }
`;

export const ModalMain = styled.main`
  padding: 16px;
`;

export const VsInfo = styled.article`
  margin: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
`;

export const User = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  margin: 40px;

  .userProfile {
    width: 80px;
    height: 80px;
    border-radius: 50%;
    object-fit: cover;
  }

  .userName {
    padding: 5px;
    font-weight: bold;
  }

  .userInfo {
    margin-top: 10px;
    font-size: var(--font-13);
  }
`;

export const Buttons = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;

  > button {
    padding: 10px;
    cursor: pointer;
    font-weight: bold;
    width: 80%;
    color: var(--white);
    border: none;
    border-radius: 50px;
    box-shadow: var(--box-shadow);
    margin-bottom: 15px;
  }
  .accBut {
    background-color: var(--logored);
    :hover {
      background-color: #fa8a8a;
    }
  }

  .decBut {
    background-color: var(--buttongray);
    :hover {
      background-color: #cfcfcf;
    }
  }
`;

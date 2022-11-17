import styled from 'styled-components';

export const Wrapper = styled.div`
  width: 100%;
  > div {
    padding-top: 100px;
    height: 1000px;
    max-width: 1200px;
    margin: 0 auto;
    display: flex;
    align-items: center;
    flex-direction: column;
  }
`;

export const ImageBox = styled.div`
  margin: 0.5rem;
  img {
    width: 200px;
    height: 200px;
    object-fit: cover;
  }
`;

export const ProfileBox = styled.div`
  display: flex;
  justify-content: center;
  flex-direction: column;
  border: 1px solid black;
  width: 450px;
  height: 650px;

  // 라디오 버튼
  .checkbox {
    display: flex;
    justify-content: space-between;

    .checkLeft {
      display: flex;
      flex-direction: column;
    }
    .checkright {
      display: flex;
      flex-direction: column;
    }
  }
  label {
    font-size: 20px;
    line-height: 2rem;
    padding: 0.2em 0.4em;
  }

  span {
    vertical-align: middle;
  }

  [type='radio'] {
    vertical-align: middle;
    appearance: none;
    border: max(2px, 0.1em) solid gray;
    border-radius: 50%;
    width: 1.25em;
    height: 1.25em;
    transition: border 0.5s ease-in-out;
  }

  [type='radio']:checked {
    border: 0.4em solid tomato;
  }

  [type='radio']:focus-visible {
    outline-offset: max(2px, 0.1em);
    outline: max(2px, 0.1em) dotted tomato;
  }

  [type='radio']:hover {
    box-shadow: 0 0 0 max(4px, 0.2em) lightgray;
    cursor: pointer;
  }

  // 드롭다운
  .container {
    width: 100px;
    box-shadow: 0 4px 5px 0 #00000026;
    position: relative;
    z-index: 10;
  }
  #dropdown {
    left: 0;
    visibility: hidden;
    position: absolute;
  }
  .dropdownLabel {
    display: flex;
    justify-content: space-between;
    padding: 12px;
  }
  .content {
    display: none;
    position: absolute;
    width: 100%;
    left: 0;
    background: white;
    box-shadow: 0 4px 5px 0 #00000026;
  }
  #dropdown:checked + label + div {
    display: block;
    border-top: 1px solid #00000026;
  }

  .caretIcon {
    transition: transform 250ms ease-out;
  }
  #dropdown:checked + label > .caretIcon {
    transform: rotate(-180deg);
  }
  .content .contents {
    display: flex;
    flex-direction: column;
    padding: 12px;
    margin: 0.1rem 0;
    > button {
      height: 40px;
      width: 80px;
      font-size: var(--font-20);
      border: none;
      background-color: #fff;
      cursor: pointer;
      :hover {
        background-color: #333;
        color: #fff;
      }
    }
  }
`;

export const ProfileGrid = styled.div`
  display: flex;
  justify-content: center;
  flex-direction: column;
  /* border: 1px solid black; */
  width: 320px;
  height: 600px;
  margin-left: 70px;
  margin-top: 30px;
  .checkbox {
    display: flex;
  }
`;

export const ProfileInput = styled.input`
  width: 300px;
  height: 30px;
  border-top: none;
  border-right: none;
  border-left: none;
  outline: none;
  padding-left: 10px;
  margin-bottom: 10px;
`;

export const BtnBox = styled.div`
  width: 400px;
  margin: 25px;
  /* border: 1px solid black; */
  display: flex;
  justify-content: space-around;

  button {
    background-color: gray;
    color: white;
    border-radius: 3px;
    border: none;
    cursor: pointer;
    height: 45px;
    width: 110px;
    font-size: var(--font-20);
    font-weight: 700;
  }
  .set-btn {
    background-color: #fc6666;
  }
`;

import styled from 'styled-components';

export const MainBox = styled.main`
  width: 100%;
  margin-bottom: 50px;
`;
export const CalBox = styled.section`
  margin: 0 auto;
  padding-top: 70px;
  max-width: 900px;
  width: 100%;
  .calen {
    margin-bottom: 10px;
    width: 100%;
    height: 820px;
  }
  .calendar {
    margin: 0 auto;
    max-width: 900px;
    width: 100%;
    height: 750px;
    .fc-day-sun a {
      color: red;
    }

    /* 토요일 날짜: 파란색 */
    .fc-day-sat a {
      color: blue;
    }
  }
  .calevent {
    height: 100px;
  }
  .userbox {
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    bottom: 0px;
    .deletebtn {
      display: flex;
      justify-content: end;
      margin-top: 10px;
      > button {
        width: 110px;
        height: 50px;
        border: none;
        box-shadow: var(--box-shadow);
        font-size: var(--font-14);
        border-radius: 10px;
        background-color: var(--logored);
        color: white;
        cursor: pointer;
        font-weight: 700;
      }
    }
    .userInfoBox {
      display: grid;
      grid-column-gap: 100px;
      grid-template-columns: auto auto;
      @media screen and (max-width: 900px) {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
      }
      .box1,
      .box2 {
        .name1 {
          text-align: center;
          margin-bottom: 10px;
          font-weight: 700;
        }
        .name2 {
          text-align: center;
          margin-top: 10px;
          margin-bottom: 15px;
          font-weight: 700;
        }
        .userdata1 {
          width: 400px;
          height: 150px;
          display: flex;
          padding: 30px;
          justify-content: space-between;
          align-items: top;
          border: none;
          background-color: var(--logored);
          color: white;
          border-radius: 5px;
          box-shadow: var(--box-shadow);
          cursor: pointer;
          > span {
            font-size: var(--font-16);
            font-weight: 600;
          }
        }
        .userdata2 {
          width: 400px;
          height: 150px;
          display: flex;
          padding: 30px;
          justify-content: space-between;
          align-items: top;
          border: none;
          background-color: var(--boxblue);
          color: white;
          border-radius: 5px;
          box-shadow: var(--box-shadow);
          cursor: pointer;
          > span {
            font-size: var(--font-16);
            font-weight: 600;
          }
        }
      }
    }
  }
  .delmodal {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #fff;
    width: 350px;
    border-radius: 5px;
    box-shadow: var(--box-shadow);
    z-index: 99;
    text-align: center;
    .delmodalhead {
      margin: auto 0;
      padding: 10px;
      background-color: #d9d9d9;
    }
    .contents {
      padding: 10px;
      display: grid;
      grid-row-gap: 10px;
      .content {
        font-size: var(--font-20);
      }
      .contentbox {
        font-size: var(--font-16);
        color: red;
      }
      .btns {
        display: grid;
        grid-template-columns: auto auto;
        place-items: center;
        padding: 10px;
        > button {
          width: 100px;
          height: 40px;
          font-size: var(--font-13);
          border: none;
          border-radius: 30px;
          font-weight: 600;
          cursor: pointer;
          box-shadow: var(--box-shadow);
        }
        .yes {
          color: white;
          background-color: var(--logored);
        }
        .no {
          color: white;
          background-color: var(--buttongray);
        }
      }
    }
  }
`;
export const DetailBox = styled.div`
  width: 100%;
  height: 100%;
`;
export const DetailMain = styled.main`
  padding-top: 60px;
  max-width: 500px;
  width: 100%;
  margin: 0 auto;
  .InputBox {
    width: 100%;
    height: 200px;
    margin-top: 20px;
    background-color: #f6f6f6;
    border-radius: 10px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    .InBoxs {
      width: 100%;
      height: 30px;
      margin: 5px 0px;
      display: flex;
      align-items: center;
      .title {
        width: 70px;
        margin-left: 20px;
      }
      > input {
        width: 75%;
        padding: 5px 5px 5px 10px;
        border: none;
        outline: none;
        border-radius: 20px;
        box-shadow: var(--box-shadow);
      }
    }
    .boxsfooter {
      width: 100%;
      height: 30px;
      margin: 5px 0px;
      display: flex;
      justify-content: space-between;
      align-items: center;
      > button {
        cursor: pointer;
        width: 120px;
        height: 30px;
        color: white;
        background-color: var(--logored);
        border-radius: 20px;
        border: none;
        box-shadow: var(--box-shadow);
        margin-right: 35px;
      }
      .Inselect {
        width: 200px;
        .title {
          width: 70px;
          margin-left: 20px;
          margin-right: 42px;
        }
        > select {
          width: 80px;
          padding: 5px;
          border: none;
          outline: none;
          border-radius: 20px;
          box-shadow: var(--box-shadow);
        }
      }
    }
  }
  .setHead {
    margin-top: 10px;
    padding: 30px 0px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    > button {
      cursor: pointer;
      border: none;
      background-color: #fff;
      > img {
        width: 36px;
        height: 36px;
      }
    }
  }
  .setInfo {
    display: flex;
    flex-direction: column;
    > div {
      width: 100%;
      height: 50px;
      display: flex;
      align-items: center;
      justify-content: space-around;
      margin-bottom: 10px;
      border: 1px solid red;
      border-radius: 10px;
      background-color: var(--logored);
      color: white;
      font-weight: 600;
    }
  }
  .buttons {
    width: 100%;
    height: 40px;
    > button {
      width: 50px;
      height: 30px;
      margin: 6px;
      border: none;
      cursor: pointer;
      box-shadow: var(--box-shadow);
      :active {
        background-color: #fcddb0;
      }
    }
  }
  .subcan {
    width: 100%;
    height: 40px;
    margin-top: 20px;
    margin-bottom: 40px;
    display: flex;
    justify-content: end;
    > button {
      cursor: pointer;
      width: 120px;
      height: 30px;
      border: none;
      background-color: var(--logored);
      color: white;
      font-weight: 600;
      border-radius: 20px;
      box-shadow: var(--box-shadow);
    }
    .submit {
      margin-right: 10px;
    }
  }
`;

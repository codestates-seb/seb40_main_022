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
        :hover {
          background-color: #fa8a8a;
        }
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
          :hover {
            background-color: #fa8a8a;
          }
        }
        .no {
          color: white;
          background-color: var(--buttongray);
          :hover {
            background-color: #cfcfcf;
          }
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
  .Times {
    width: 100%;
    display: flex;
    flex-direction: column;
    margin-top: 50px;
    .startbox {
      display: flex;
      align-items: center;
      justify-content: center;
      margin-bottom: 10px;
      .timelabel {
        padding: 10px;
        font-size: var(--font-18);
      }
      .endtimelabel {
        margin-left: 18px;
        margin-right: 10px;
        font-size: var(--font-18);
      }
      #starttime {
        width: 250px;
        padding: 5px 10px;
        border-radius: 20px;
        border: none;
        box-shadow: var(--box-shadow);
        outline: none;
      }
      .Timebutton {
        margin-left: 10px;
        border: none;
        background-color: #fff;
        cursor: pointer;
        > img:hover {
          filter: opacity(0.4) drop-shadow(0 0 0 var(--logored));
        }
      }
      .imgadd {
        display: none;
      }
    }
  }
  .Imgs {
    width: 100%;
    margin-top: 30px;
    display: flex;
    align-items: center;
    justify-content: space-around;
    .imgboxs {
      position: relative;
      .TimeImgs {
        width: 150px;
        height: 100px;
      }
      .deletebtn {
        position: absolute;
        right: 0px;
        border: none;
        opacity: 0.8;
        font-size: var(--font-16);
        cursor: pointer;
      }
    }
  }
  .InputBox {
    width: 100%;
    padding: 20px 0px;
    margin-top: 20px;
    background-color: #f6f6f6;
    border-radius: 10px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    .boxfooter,
    .boxhead {
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding: 10px 30px;
      .Inselect {
        .title {
          font-size: var(--font-16);
          margin-right: 20px;
        }
        .title2 {
          font-size: var(--font-16);
          margin-right: 6px;
        }
        > select,
        input {
          width: 150px;
          border: none;
          box-shadow: var(--box-shadow);
          border-radius: 20px;
          outline: none;
          padding: 8px;
        }
      }
      > button {
        width: 120px;
        padding: 10px;
        font-size: var(--font-16);
        border: none;
        background-color: var(--logored);
        border-radius: 20px;
        box-shadow: var(--box-shadow);
        color: #fff;
        cursor: pointer;
        :hover {
          background-color: #fa8a8a;
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

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
      .healthaddbutton,
      .canclebutton {
        width: 110px;
        height: 50px;
        border: none;
        box-shadow: var(--box-shadow);
        font-size: var(--font-14);
        border-radius: 10px;
        color: white;
        cursor: pointer;
        font-weight: 700;
      }
      .healthaddbutton {
        background-color: var(--logored);
        margin-right: 10px;
        :hover {
          background-color: #fa8a8a;
        }
      }
      .canclebutton {
        background-color: var(--buttongray);
        :hover {
          background-color: #cfcfcf;
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
      .box2 {
        .name2 {
          margin-top: 30px;
        }
      }
      .box1 {
        .name1 {
          margin-top: 33px;
        }
      }
      .box2,
      .box1 {
        .name2,
        .name1 {
          display: flex;
          flex-direction: column;
          text-align: center;
          margin-bottom: 10px;
          font-weight: 700;
          > img {
            margin: 0 auto;
            width: 30px;
          }
        }
        .userdata1 {
          cursor: pointer;
          background-color: var(--logored);
          .dayover {
            background-color: var(--logored);
          }
          .oneday {
            background-color: var(--logored);
          }
        }
        .userdata2 {
          cursor: pointer;
          background-color: var(--boxblue);
          .dayover {
            background-color: var(--boxblue);
          }
          .oneday {
            background-color: var(--boxblue);
          }
        }
        .userdata2,
        .userdata1 {
          width: 400px;
          display: flex;
          padding: 30px;
          flex-direction: column;
          align-items: top;
          border: none;
          color: white;
          border-radius: 5px;
          box-shadow: var(--box-shadow);
          text-decoration: none;
          cursor: pointer;
          .dayover {
            width: 100%;
            font-size: var(--font-16);
            display: flex;
            justify-content: space-around;
            font-weight: 600;
            margin: 0 auto;
            border: none;
          }
          .oneday {
            border: none;
            padding: 3px;
            width: 100%;
            display: grid;
            grid-template-columns: auto auto;
            text-align: center;
            > span {
              font-size: var(--font-10);
              font-weight: 600;
              margin: 5px 0px;
            }
          }
        }
      }
    }
    .userInfoBox2 {
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      .nobox {
        display: none;
      }
      .box1,
      .box2 {
        .name1 {
          display: flex;
          flex-direction: column;
          text-align: center;
          margin-bottom: 10px;
          font-weight: 700;
          > img {
            margin: 0 auto;
            width: 30px;
          }
        }
        .noname {
          display: none;
        }
        .name2 {
          text-align: center;
          margin-top: 25px;
          margin-bottom: 15px;
          font-weight: 700;
        }
        .userdata1 {
          width: 400px;
          display: flex;
          padding: 30px;
          flex-direction: column;
          align-items: top;
          border: none;
          background-color: var(--logored);
          color: white;
          border-radius: 5px;
          box-shadow: var(--box-shadow);
          text-decoration: none;
          cursor: pointer;
          .dayover {
            width: 100%;
            display: flex;
            justify-content: center;
            font-size: var(--font-20);
            font-weight: 600;
            color: #fff;
            border: none;
            background-color: var(--logored);
            font-size: var(--font-16);
            display: flex;
            justify-content: space-around;
          }
          .oneday {
            border: none;
            padding: 3px;
            background-color: var(--logored);
            width: 100%;
            display: grid;
            grid-template-columns: auto auto;
            text-align: center;
            > span {
              font-size: var(--font-10);
              font-weight: 600;
              margin: 5px 0px;
            }
          }
        }
        .userdata2 {
          width: 400px;
          height: 150px;
          display: flex;
          padding: 30px;
          justify-content: center;
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
        height: 30px;
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
      > button {
        cursor: pointer;
        border: none;
        background-color: var(--logored);
        color: white;
        font-size: var(--font-16);
        font-weight: 600;
        :hover {
          background-color: #fa8a8a;
        }
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
      :hover {
        background-color: #fa8a8a;
      }
    }
    .submit {
      margin-right: 10px;
    }
  }
`;

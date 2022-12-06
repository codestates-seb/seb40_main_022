import styled from 'styled-components';

export const Inside = styled.section`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  .searchInput {
    display: flex;
    justify-content: center;
    position: fixed;
    top: 60px;
    background-color: var(--white);
    width: 850px;
    z-index: 8;

    @media screen and (max-width: 700px) {
      width: 500px;
    }

    .taginput {
      display: flex;
      position: relative;
      align-items: center;
      justify-content: space-between;
      width: 85%;
      height: 50px;
      border: none;
      box-shadow: inset var(--box-shadow);
      border-radius: 50px;
      outline: 1px solid var(--black-100);
      margin-top: 50px;
      padding-left: 30px;
      padding-right: 20px;
      font-size: var(--font-20);
      margin-bottom: 20px;
      &::placeholder {
        color: var(--black-300);
      }

      .tagInfo {
        display: flex;
      }

      > button {
        border: none;
        background-color: var(--white);
        cursor: pointer;
        .searchIcon {
          width: 25px;
        }
      }
    }

    .tags {
      display: flex;
      align-items: center;

      > button {
        top: 0px;
        margin-left: 5px;
        border: none;
        margin: 4px;
        margin-right: 10px;
        padding: 4px;
        color: rgb(57, 115, 157);
        background-color: rgb(225, 236, 244);
        cursor: pointer;
      }
    }
  }
`;

export const MainForm = styled.article`
  display: flex;
  justify-content: center;
  position: relative;
  width: 850px;
  min-height: 800px;
  background-color: var(--white);
  border-radius: 5px;
  box-shadow: var(--box-shadow);
  padding: 0 2vw;
`;

export const MainSearch = styled.input`
  border: none;
  outline: none;
  height: 30px;
`;

export const ContentForm = styled.div`
  margin-top: 180px;
  .cont-picture {
    border-radius: 50%;
  }
  .username {
    text-align: center;
  }
`;

export const Top = styled.div`
  position: relative;
  display: flex;
  height: 200px;
  outline: none;
`;

export const Content = styled.div`
  margin: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  font-weight: bold;

  .imgprofile {
    width: 130px;
    height: 130px;
    border: 5px solid var(--logored);
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    background: var(--white);
    > button {
      background: var(--white);
      border: none;
      border-radius: 50%;
      display: flex;

      > img {
        cursor: pointer;
        width: 110px;
        height: 110px;
        border-radius: 50%;
        object-fit: cover;
      }
    }
  }

  .imgprofile.dailynew {
    > img {
      &:hover {
        filter: opacity(0.4) drop-shadow(0 0 0 var(--logored));
      }
    }
  }
`;

export const DailyForm = styled.div`
  .list {
    position: relative;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 10px;
  }
`;

export const DailyItem = styled.div`
  background-color: var(--white);
  box-shadow: 0px 8px 8px 0px rgba(0, 0, 0, 0.15);
  padding: 2%;
  margin: 5% 10px;

  .commentdiv {
    display: flex;
    flex-direction: column;
    margin-top: 30px;
    background-color: var(--white);
    border-radius: 5px;
    padding: 3%;
    width: 100%;
    animation: modal-bg-show 0.5s;

    .cmtListAdd {
      display: flex;
      justify-content: center;
      > button {
        cursor: pointer;
        color: var(--white);
        border: none;
        background-color: var(--white);
        border-radius: 50%;
        width: 50px;
        height: 50px;
        box-shadow: var(--box-shadow);

        .add {
          width: 50px;
          height: 50px;
          &:hover {
            filter: opacity(0.4) drop-shadow(0 0 0 var(--logored));
          }
        }
      }
    }
    .comment {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 10px;
      padding-right: 20px;
      border-radius: 10px;
      margin-bottom: 10px;

      .cmtContent {
        display: flex;
        align-items: center;
        .content {
          width: 500px;
        }

        .cont-picture {
          background-color: var(--white);
          width: 45px;
          height: 45px;
          margin-right: 10px;
        }
      }

      img {
        cursor: pointer;
        width: 45px;
        height: 45px;
        border-radius: 50%;
        object-fit: cover;
      }
      .cmtUserName {
        font-weight: bold;
      }
      .buttons {
        > button {
          background: var(--white);
          width: 20px;
          height: 20px;
          border-radius: 50%;
          box-shadow: 0px 2px 2px 0px rgba(0, 0, 0, 0.25);
          margin: 5px;
          > img {
            width: 20px;
            height: 20px;
          }
        }
      }
    }
  }

  button {
    border: none;
    cursor: pointer;
  }

  .Img {
    display: flex;
    justify-content: center;
    width: 700px;
    height: 550px;
    position: relative;

    > article {
      height: 550px;
    }
  }

  .dailyImg {
    width: 100%;
    height: 550px;
  }

  .DailyInfo {
    margin-top: 20px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;

    .act {
      > span {
        > button {
          background-color: var(--white);

          > Img {
            width: 18px;
            height: 18px;
            padding: 3px 3px 0 0;
          }
        }
      }
    }

    span {
      margin-right: 20px;
      cursor: pointer;
    }

    div {
      margin: 10px;
    }

    .DailyTags {
      font-weight: bold;
      color: var(--black-500);
    }

    .memo {
      font-weight: bold;
      cursor: text;
      width: 500px;
      overflow: hidden;
      word-wrap: break-word;
      margin: 30px 0;
    }

    .date {
      cursor: text;
    }

    .userInfo {
      display: flex;
      flex-direction: column;
      align-items: center;
      padding: 10px;
      border-radius: 5px;
      height: 110px;
      background-color: var(--white);
      font-weight: bold;

      .user {
        cursor: pointer;
        width: 60px;
        height: 60px;
        border-radius: 50%;
        object-fit: cover;
      }

      span {
        padding-top: 5px;
        margin-right: 0px;
      }
    }
  }
`;

export const AddComment = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px;
  margin-bottom: 20px;
  .user {
    width: 45px;
    height: 45px;
    border-radius: 50%;
    object-fit: cover;
    margin-right: 10px;
  }

  > button {
    cursor: pointer;
    font-weight: bold;
    margin-left: 20px;
    color: var(--white);
    border: none;
    background-color: var(--white);
    border-radius: 50%;
    width: 40px;
    height: 40px;
    box-shadow: var(--box-shadow);

    .add {
      width: 40px;
      height: 40px;
      &:hover {
        filter: opacity(0.4) drop-shadow(0 0 0 var(--logored));
      }
    }
  }
`;

export const CommentInput = styled.input`
  width: 80%;
  height: 40px;
  border: none;
  box-shadow: inset var(--box-shadow);
  border-radius: 50px;
  outline: 1px solid var(--black-100);
  padding: 0 20px;

  &::placeholder {
    color: var(--black-300);
  }
`;

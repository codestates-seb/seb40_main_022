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
    top: 20px;
    background-color: var(--white);
    width: 850px;
    z-index: 8;

    form {
      width: 78%;
      margin: 20px 0;
      position: relative;

      .searchIcon {
        position: absolute;
        cursor: pointer;
        margin-top: 63px;
        margin-left: -45px;
        width: 25px;
        height: 25px;
      }
    }
  }
`;

export const MainForm = styled.article`
  display: flex;
  justify-content: center;
  position: relative;
  /* top: 80px; */
  width: 850px;
  background-color: var(--white);
  border-radius: 5px;
  box-shadow: var(--box-shadow);
  /* background-color: var(--black-025); */
  padding: 0 2vw;
`;

export const MainSearch = styled.input`
  width: 100%;
  height: 50px;
  border: none;
  box-shadow: inset var(--box-shadow);
  border-radius: 50px;
  outline: 1px solid var(--black-100);
  margin-top: 50px;
  padding-left: 30px;
  font-size: var(--font-20);

  &::placeholder {
    color: var(--black-300);
  }
`;

export const ContentForm = styled.div`
  /* position: absolute; */
  margin-top: 180px;
  /* left: 50px;
  right: 50px; */
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
    display: flex;
    align-items: center;
    justify-content: center;
    border-radius: 50%;
    > img {
      cursor: pointer;
      width: 110px;
      height: 110px;
      border-radius: 50%;
      object-fit: cover;
      position: absolute;
      top: 35px;
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
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 10px;
`;

export const DailyItem = styled.div`
  background-color: var(--black-050);
  border-radius: 5px;
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

    .comment {
      display: flex;
      align-items: center;
      padding: 10px;
      img {
        cursor: pointer;
        width: 45px;
        height: 45px;
        border-radius: 50%;
        object-fit: cover;
        margin-right: 10px;
      }
      .cmtUserName {
        font-weight: bold;
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

    .iUXjrE {
      height: 550px;
    }
  }

  .dailyImg {
    width: 100%;
    height: 550px;
  }

  .favorite {
    cursor: pointer;
    .heart {
      width: 18px;
      height: 18px;
      padding: 3px 3px 0 0;
    }
  }

  .DailyInfo {
    margin-top: 20px;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    /* position: relative; */

    span {
      margin-right: 20px;
      cursor: pointer;
    }

    div {
      margin: 10px;
    }

    .DailyTags {
      font-weight: bold;
    }

    .memo {
      font-weight: bold;
      cursor: text;
      width: 500px;
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

    /* @media screen and (max-width: 720px) {
      font-size: var(--font-11);
    } */
  }
`;

export const AddComment = styled.div`
  display: flex;
  align-items: center;
  padding: 10px;
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
  padding-left: 20px;

  &::placeholder {
    color: var(--black-300);
  }
`;

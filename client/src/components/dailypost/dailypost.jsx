import styled from 'styled-components';
import { useRef } from 'react';
import Footer from '../footer/Footer';
import Header from '../header/Header';
import plus from '../../images/plus.png';

const DetailBody = styled.div``;

const DetailMain = styled.main`
  background-color: var(--backcolor);
  padding-top: 60px;
  width: 100%;
  height: 800px;
  display: flex;
  align-items: center;
  justify-content: center;
  .DetailBox {
    border-radius: 10px;
    margin-top: 50px;
    width: 600px;
    height: 650px;
    padding: 50px 50px 0px;
    background-color: #fff;
    display: flex;
    flex-direction: column;
    .contentTitle {
      margin-top: 30px;
      margin-bottom: 5px;
    }
    .contentinput {
      border: none;
      padding: 10px;
      height: 200px;
      box-shadow: var(--box-shadow);
      outline: none;
    }
    .tagTitle {
      margin-top: 20px;
      margin-bottom: 5px;
    }
    .taginput {
      padding: 5px;
      border: none;
      box-shadow: var(--box-shadow);
      outline: none;
    }
    > .buttons {
      display: flex;
      align-items: flex-end;
      justify-content: center;
      height: 100px;
      > button {
        cursor: pointer;
        width: 120px;
        height: 50px;
        font-size: var(--font-18);
        font-weight: 700;
        margin: 0 20px;
        color: #fff;
        border: none;
        border-radius: 20px;
        box-shadow: var(--box-shadow);
      }
    }
    .submitbutton {
      background-color: var(--logored);
    }
    .canclebutton {
      background-color: var(--buttongray);
    }
  }
  .Imgaddbox {
    border: 1px solid #959595;
    width: 100px;
    height: 150px;
    display: flex;
    align-items: center;
    justify-content: center;
    .ImgInput {
      display: none;
    }
    .ImgButton {
      cursor: pointer;
      padding: 50px 25px;
      border: none;
      background-color: transparent;
    }
  }
`;

const dailypost = () => {
  const photoUp = useRef();

  const handelClick = () => {
    photoUp.current.click();
  };
  return (
    <DetailBody>
      <Header />
      <DetailMain>
        <div className="DetailBox">
          <div className="Imgaddbox">
            <input type="file" className="ImgInput" ref={photoUp} />
            <button className="ImgButton" onClick={() => handelClick()}>
              <img src={plus} alt="버튼로고" />
            </button>
          </div>
          <span className="contentTitle">내용</span>
          <textarea
            maxLength="200"
            placeholder="내용"
            className="contentinput"
          />
          <span className="tagTitle">태그</span>
          <input placeholder="태그" className="taginput" />
          <div className="buttons">
            <button className="submitbutton">등록</button>
            <button className="canclebutton">취소</button>
          </div>
        </div>
      </DetailMain>
      <Footer />
    </DetailBody>
  );
};

export default dailypost;

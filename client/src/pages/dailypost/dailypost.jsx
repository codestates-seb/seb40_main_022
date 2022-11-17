import styled from 'styled-components';
import { useRef, useState } from 'react';
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
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
      box-shadow: inset var(--box-shadow);
      outline: none;
      border-radius: 5px;
      &::placeholder {
        color: var(--black-200);
        font-weight: bold;
      }
    }
    .tagTitle {
      margin-top: 20px;
      margin-bottom: 5px;
    }
    .taginput {
      padding: 5px;
      height: 30px;
      border: none;
      box-shadow: inset var(--box-shadow);
      border-radius: 5px;
      outline: none;
      &::placeholder {
        color: var(--black-200);
        font-weight: bold;
      }
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
  .Imgbox {
    display: flex;
    position: relative;
    justify-content: center;
    .Imgaddbox {
      border: 3px solid var(--black-200);
      border-radius: 3px;
      width: 110px;
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
    .boxs {
      position: relative;
      .Imgs {
        width: 110px;
        height: 150px;
        margin: 0 10px;
        border-radius: 3px;
      }
      .Imgdel {
        position: absolute;
        opacity: 0.8;
        border: none;
        font-size: var(--font-18);
        right: 10px;
        cursor: pointer;
      }
    }
  }
`;

const dailypost = () => {
  const photoUp = useRef();
  const [files, setFiles] = useState([]);
  const reader = new FileReader();

  const handleFile = e => {
    reader.readAsDataURL(e.target.files[0]);
    reader.onloadend = () => {
      const resultImg = reader.result;
      setFiles([...files, resultImg.toString()]);
    };
  };
  const handelClick = () => {
    photoUp.current.click();
  };
  return (
    <DetailBody>
      <Header />
      <DetailMain>
        <div className="DetailBox">
          <div className="Imgbox">
            {files &&
              files.map(data => {
                return (
                  <div className="boxs">
                    <img src={data} alt="오완운사진" className="Imgs" />
                    <button className="Imgdel">x</button>
                  </div>
                );
              })}
            {files.length <= 3 ? (
              <div className="Imgaddbox">
                <input
                  type="file"
                  className="ImgInput"
                  ref={photoUp}
                  onChange={handleFile}
                />
                <button className="ImgButton" onClick={() => handelClick()}>
                  <img src={plus} alt="버튼로고" />
                </button>
              </div>
            ) : null}
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

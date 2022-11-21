import { useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { DetailBox, DetailMain } from './Style';
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import rcddetailbtn from '../../images/rcddetailbtn.png';
import rcddecordminus from '../../images/rcddecordminus.png';
import DetailCamera from '../../images/DetailCamera.png';

function Detail() {
  const list = [
    { title: '랫풀다운', time: 20, set: '20회/5세트' },
    { title: '랫풀다운', time: 20, set: '20회/5세트' },
    { title: '랫풀다운', time: 20, set: '20회/5세트' },
    { title: '랫풀다운', time: 20, set: '20회/5세트' },
    { title: '랫풀다운', time: 20, set: '20회/5세트' },
    { title: '랫풀다운', time: 20, set: '20회/5세트' },
    { title: '랫풀다운', time: 20, set: '20회/5세트' },
  ];
  const btns = ['등', '가슴', '어깨', '하체', '팔', '전신', '유산소', '기타'];
  // const taghealth = [
  //   {
  //     등: ['폴업', '바벨 로우', '덤벨 로우', '펜들레이 로우'],
  //   },
  // ];
  const navigate = useNavigate();
  const [clicked, setClicked] = useState(false);
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
  const deleteFile = index => {
    const imgArr = files.filter((el, idx) => idx !== index);
    setFiles([...imgArr]);
  };
  return (
    <DetailBox>
      <Header />
      <DetailMain>
        <section className="Times">
          <div className="startbox">
            <label htmlFor="starttime" className="timelabel">
              Start-Time
            </label>
            <input id="starttime" />
            <input
              className="imgadd"
              type="file"
              ref={photoUp}
              onChange={handleFile}
            />
            {files[0] ? null : (
              <button className="Timebutton" onClick={() => handelClick()}>
                <img src={DetailCamera} alt="시작시간 이미지 추가" />
              </button>
            )}
          </div>
          <div className="startbox">
            <label htmlFor="starttime" className="endtimelabel">
              End-Time
            </label>
            <input id="starttime" />
            <input
              className="imgadd"
              type="file"
              ref={photoUp}
              onChange={handleFile}
            />
            {files[1] ? null : (
              <button className="Timebutton" onClick={() => handelClick()}>
                <img src={DetailCamera} alt="시작시간 이미지 추가" />
              </button>
            )}
          </div>
        </section>
        <section className="Imgs">
          {files &&
            files.map((data, idx) => {
              return (
                <div className="imgboxs">
                  <img src={data} alt="증명사진" className="TimeImgs" />
                  <button onClick={() => deleteFile(idx)} className="deletebtn">
                    x
                  </button>
                </div>
              );
            })}
        </section>
        {clicked ? (
          <section className="InputBox">
            <div className="boxhead">
              <div className="Inselect">
                <span className="title">부위</span>
                <select>
                  {btns &&
                    btns.map(data => {
                      return <option>{data}</option>;
                    })}
                </select>
              </div>
              <div>
                <div className="Inselect">
                  <span className="title">운동</span>
                  <select>
                    {btns &&
                      btns.map(data => {
                        return <option>{data}</option>;
                      })}
                  </select>
                </div>
              </div>
            </div>
            <div className="boxfooter">
              <div className="Inselect">
                <label htmlFor="set" className="title2">
                  세트수
                </label>
                <input id="set" placeholder="ex) 20회/5세트" />
              </div>
              <button>등록</button>
            </div>
          </section>
        ) : null}
        <section className="setHead">
          <h2>운동 상세</h2>
          <button
            onClick={() => {
              setClicked(!clicked);
            }}
          >
            <img
              src={clicked ? rcddecordminus : rcddetailbtn}
              alt="추가 버튼"
            />
          </button>
        </section>
        <section className="setInfo">
          {list &&
            list.map(data => {
              return (
                <div>
                  <span>{data.title}</span>
                  <span>{data.time}</span>
                  <span>{data.set}</span>
                </div>
              );
            })}
        </section>
        <section className="subcan">
          <button className="submit">등록</button>
          <button
            className="cancle"
            onClick={() => {
              navigate('/record');
            }}
          >
            취소
          </button>
        </section>
      </DetailMain>
      <Footer />
    </DetailBox>
  );
}

export default Detail;

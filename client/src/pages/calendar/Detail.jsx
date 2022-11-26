import { useRef, useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { DetailBox, DetailMain } from './Style';
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import rcddetailbtn from '../../images/rcddetailbtn.png';
import rcddecordminus from '../../images/rcddecordminus.png';
import DetailCamera from '../../images/DetailCamera.png';
import { RecordTagAsync, RecordUpAsync } from '../../redux/action/RecordAsync';

function Detail() {
  const list = [
    { title: '랫풀다운', tag: '등', set: '5', num: '20', weight: '80' },
    { title: '랫풀다운', tag: '등', set: '5', num: '20', weight: '80' },
    { title: '랫풀다운', tag: '등', set: '5', num: '20', weight: '80' },
    { title: '랫풀다운', tag: '등', set: '5', num: '20', weight: '80' },
    { title: '랫풀다운', tag: '등', set: '5', num: '20', weight: '80' },
    { title: '랫풀다운', tag: '등', set: '5', num: '20', weight: '80' },
  ];
  const btns = ['등', '가슴', '어깨', '하체', '팔', '전신', '유산소', '기타'];
  const taghealth1 = useSelector(state => state.record.data.data);
  console.log(taghealth1);

  const navigate = useNavigate();
  const photoUp = useRef();
  const [health, setHealth] = useState('풀업');
  const [tags, setTags] = useState('등');
  const [set, setSet] = useState('');
  const [num, setNum] = useState('');
  const [weight, setWeight] = useState('');
  const [startTime, setStartTime] = useState('');
  const [endTime, setEndTime] = useState('');
  const today = new Date().toISOString().slice(0, 10);
  const [clicked, setClicked] = useState(false);
  const [files, setFiles] = useState([]);
  const [realImg, setRealImg] = useState([]);
  const reader = new FileReader();
  const dispatch = useDispatch();

  const handleFile = e => {
    reader.readAsDataURL(e.target.files[0]);
    reader.onloadend = () => {
      const resultImg = reader.result;
      setRealImg([...realImg, resultImg.toString()]);
    };
    setFiles([...files, e.target.files[0]]);
  };
  const handelClick = () => {
    photoUp.current.click();
  };
  const deleteFile = () => {
    setRealImg([]);
    setFiles([]);
  };
  const tagClick = () => {
    dispatch(RecordTagAsync(tags));
  };
  const handleAddClick = () => {
    const Healthdata = [
      startTime,
      endTime,
      today,
      health,
      set,
      num,
      weight,
      tags,
      files,
    ];
    console.log(Healthdata);
    dispatch(RecordUpAsync(Healthdata));
  };
  useEffect(() => {
    dispatch(RecordTagAsync(tags));
  }, []);
  return (
    <DetailBox>
      <Header />
      <DetailMain>
        <section className="Times">
          <div className="startbox">
            <label htmlFor="starttime" className="timelabel">
              Start-Time
            </label>
            <input
              id="starttime"
              placeholder="ex) 21:19:00"
              onChange={e => setStartTime(e.target.value)}
            />
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
            <input
              id="starttime"
              placeholder="ex) 22:19:00"
              onChange={e => setEndTime(e.target.value)}
            />
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
          {realImg &&
            realImg.map((data, idx) => {
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
                <select
                  onChange={e => {
                    setTags(e.target.value);
                    tagClick(e.target.value);
                  }}
                >
                  {btns &&
                    btns.map(data => {
                      return <option>{data}</option>;
                    })}
                </select>
              </div>
              <div>
                <div className="Inselect">
                  <span className="title">운동</span>
                  <select
                    onChange={e => {
                      setHealth(e.target.value);
                    }}
                  >
                    {taghealth1 &&
                      taghealth1.map(data => {
                        return <option>{data.name}</option>;
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
                <input
                  id="set"
                  placeholder="숫자만 적어주세요."
                  onChange={e => setSet(e.target.value)}
                />
              </div>
              <div className="Inselect">
                <label htmlFor="set" className="title">
                  횟수
                </label>
                <input
                  id="set"
                  placeholder="숫자만 적어주세요."
                  onChange={e => setNum(e.target.value)}
                />
              </div>
            </div>
            <div className="boxfooter">
              <div className="Inselect">
                <label htmlFor="set" className="title">
                  중량
                </label>
                <input
                  id="set"
                  placeholder="숫자만 적어주세요."
                  onChange={e => setWeight(e.target.value)}
                />
              </div>
              <button
                onClick={() => {
                  handleAddClick();
                }}
              >
                등록
              </button>
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
                  <span>{data.tag}</span>
                  <span>{data.set}회</span>
                  <span>{data.num}번</span>
                  <span>{data.weight}kg</span>
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
            나가기
          </button>
        </section>
      </DetailMain>
      <Footer />
    </DetailBox>
  );
}

export default Detail;

import { useRef, useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { DetailBox, DetailMain } from './Style';
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import rcddetailbtn from '../../images/rcddetailbtn.png';
import rcddecordminus from '../../images/rcddecordminus.png';
import DetailCamera from '../../images/DetailCamera.png';
import {
  RecordTagAsync,
  RecordUpAsync,
  RecordListAsync,
} from '../../redux/action/RecordAsync';

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
  const List = useSelector(state => state);
  console.log(List);
  const navigate = useNavigate();
  const photoUp = useRef();
  const [health, setHealth] = useState(null);
  const [tags, setTags] = useState('등');
  const [set, setSet] = useState(null);
  const [num, setNum] = useState(null);
  const [weight, setWeight] = useState(null);
  const [split, setSplit] = useState([]);
  const [startTime, setStartTime] = useState('');
  const [endTime, setEndTime] = useState('');
  const today = new Date().toISOString().slice(0, 10);
  const [clicked, setClicked] = useState(false);
  const [files, setFiles] = useState([]);
  const [realImg, setRealImg] = useState([]);
  const reader = new FileReader();
  const dispatch = useDispatch();
  console.log(health, today);
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
    if (set.length !== 0 && num.length !== 0 && weight.length !== 0) {
      setSplit([...split, { id: 22, set, count: num, weight }]);
    } else {
      alert('데이터를 제대로 입력해주세요!');
    }
  };
  const handleUp = () => {
    if (
      split.length !== 0 &&
      startTime.length !== 0 &&
      endTime.length !== 0 &&
      files.length !== 0
    ) {
      const formdata = new FormData();
      const sports = [
        {
          id: 10,
          set: 10,
          count: 10,
          weight: 10,
        },
        {
          id: 10,
          set: 10,
          count: 10,
          weight: 10,
        },
        {
          id: 10,
          set: 10,
          count: 10,
          weight: 10,
        },
      ];
      // const redata = [today, startTime, endTime];
      formdata.append('start', today.toString());
      formdata.append('startTime', startTime);
      formdata.append('endTime', endTime);
      formdata.append('startImage', files[0]);
      formdata.append('endImage', files[1]);
      formdata.append(
        'recordCreateVO',
        new Blob([JSON.stringify({ sports })], { type: 'application/json' }),
      );
      // for (let i = 0; i < split.length; i += 1) {
      //   formdata.append(`sports[${i}].id`, split[i].id);
      //   formdata.append(`sports[${i}].set`, split[i].set);
      //   formdata.append(`sports[${i}].count`, split[i].count);
      //   formdata.append(`sports[${i}].weight`, split[i].weight);
      //   formdata.append(`sports[${i}]`, JSON.stringify(split[i]));
      // }
      dispatch(RecordUpAsync(formdata));
    } else {
      alert('데이터를 제대로 입력해주세요!');
    }
  };
  useEffect(() => {
    dispatch(RecordTagAsync(tags));
    dispatch(RecordListAsync());
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
                      const Id = taghealth1.filter(
                        data => data.name === e.target.value,
                      );
                      setHealth(Id[0].sportsId);
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
                  onChange={e => setSet(+e.target.value)}
                />
              </div>
              <div className="Inselect">
                <label htmlFor="set" className="title">
                  횟수
                </label>
                <input
                  id="set"
                  placeholder="숫자만 적어주세요."
                  onChange={e => setNum(+e.target.value)}
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
                  onChange={e => setWeight(+e.target.value)}
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
          <button className="submit" onClick={() => handleUp()}>
            등록
          </button>
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

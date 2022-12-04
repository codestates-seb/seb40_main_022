import { useRef, useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import uuidv4 from 'react-uuid';
import { DetailBox, DetailMain } from './Style';
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import rcddetailbtn from '../../images/rcddetailbtn.png';
import rcddecordminus from '../../images/rcddecordminus.png';
import DetailCamera from '../../images/DetailCamera.png';
import {
  RecordTagAsync,
  RecordUpAsync,
  RecordImgUp,
} from '../../redux/action/RecordAsync';

function Detail() {
  const btns = ['등', '가슴', '어깨', '하체', '팔', '유산소', '기타'];
  const taghealth1 = useSelector(state => state.record.data.data);
  const navigate = useNavigate();
  const startphotoUp = useRef();
  const endphotoUp = useRef();
  const [tags, setTags] = useState('등');
  const [health, setHealth] = useState(
    taghealth1 ? taghealth1[0].sportsId : 21,
  );
  const [set, setSet] = useState(null);
  const [num, setNum] = useState(null);
  const [weight, setWeight] = useState(null);
  const [split, setSplit] = useState([]);
  const [time, setTime] = useState('');
  const [end, setEnd] = useState('');
  const today = new Date();
  const year = today.getFullYear();
  const month = `0${today.getMonth() + 1}`.slice(-2);
  const day = `0${today.getDate()}`.slice(-2);
  const dateString = `${year}-${month}-${day}`;
  const [clicked, setClicked] = useState(false);
  const [addUpdate, setAddUpdate] = useState([]);
  const [startrealImg, setStartRealImg] = useState('');
  const [endrealImg, setEndRealImg] = useState('');
  const reader = new FileReader();
  const dispatch = useDispatch();
  const formdata = new FormData();
  const startImagePath = useSelector(state => state.record.Start);
  const endImagePath = useSelector(state => state.record.End);
  const handleStartFile = e => {
    formdata.append('point', 'start');
    formdata.append('file', e.target.files[0]);
    dispatch(RecordImgUp(formdata));
    reader.readAsDataURL(e.target.files[0]);
    reader.onloadend = () => {
      const resultImg = reader.result;
      setStartRealImg(resultImg.toString());
    };
  };
  const handleEndFile = e => {
    formdata.append('point', 'end');
    formdata.append('file', e.target.files[0]);
    dispatch(RecordImgUp(formdata));
    reader.readAsDataURL(e.target.files[0]);
    reader.onloadend = () => {
      const resultImg = reader.result;
      setEndRealImg(resultImg.toString());
    };
  };
  const handelClick = name => {
    if (name === 'start') {
      startphotoUp.current.click();
    } else {
      endphotoUp.current.click();
    }
  };
  const deleteFile = name => {
    if (name === 'start') {
      setStartRealImg('');
    } else {
      setEndRealImg('');
    }
  };
  const tagClick = tag => {
    dispatch(RecordTagAsync(tag));
  };
  const handleAddClick = () => {
    if (set.length !== 0 && num.length !== 0 && weight.length !== 0) {
      setSplit([...split, { id: health, set, count: num, weight }]);
    } else {
      alert('데이터를 제대로 입력해주세요!');
    }
  };
  const handleUp = () => {
    const data = [dateString, time, end, startImagePath, endImagePath, split];
    dispatch(RecordUpAsync(data))
      .unwrap()
      .then(() => {
        // window.location.href = '/record';
      });
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
              value={time}
              onChange={e => setTime(e.target.value)}
              placeholder="ex) 22:22:22"
            />
            <input
              className="imgadd"
              type="file"
              ref={startphotoUp}
              onChange={handleStartFile}
            />
            {startrealImg ? null : (
              <button
                className="Timebutton"
                onClick={() => {
                  handelClick('start');
                }}
              >
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
              placeholder="ex) 22:22:22"
              value={end}
              onChange={e => setEnd(e.target.value)}
            />
            <input
              className="imgadd"
              type="file"
              ref={endphotoUp}
              onChange={handleEndFile}
            />
            {endrealImg ? null : (
              <button className="Timebutton" onClick={() => handelClick('end')}>
                <img src={DetailCamera} alt="시작시간 이미지 추가" />
              </button>
            )}
          </div>
        </section>
        <section className="Imgs">
          {startrealImg && (
            <div className="imgboxs">
              <img src={startrealImg} alt="증명사진" className="TimeImgs" />
              <button onClick={() => deleteFile('start')} className="deletebtn">
                x
              </button>
            </div>
          )}
          {endrealImg && (
            <div className="imgboxs">
              <img src={endrealImg} alt="증명사진" className="TimeImgs" />
              <button onClick={() => deleteFile('end')} className="deletebtn">
                x
              </button>
            </div>
          )}
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
                      return <option key={uuidv4}>{data}</option>;
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
                        return <option key={uuidv4}>{data.name}</option>;
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
          {split &&
            split.map((data, index) => {
              if (split.length !== addUpdate.length) {
                addUpdate.push(false);
              }
              return (
                <div key={uuidv4}>
                  <span>{data.set}세트</span>
                  <span>{data.count}회</span>
                  <span>{data.weight}kg</span>
                  <button
                    onClick={() => {
                      addUpdate[index] = !addUpdate[index];
                      setAddUpdate(addUpdate);
                    }}
                  >
                    수정
                  </button>
                  <button
                    onClick={() => {
                      // dispatch(Recorddelete(data.id));
                      const filterdata = [];
                      for (let i = 0; i < split.length; i += 1) {
                        if (i !== index) {
                          filterdata.push(split[i]);
                        }
                      }
                      setSplit(filterdata);
                    }}
                  >
                    삭제
                  </button>
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

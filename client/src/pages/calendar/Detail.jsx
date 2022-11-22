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
    { title: '랫풀다운', tag: '등', set: '5', num: '20', weight: '80' },
    { title: '랫풀다운', tag: '등', set: '5', num: '20', weight: '80' },
    { title: '랫풀다운', tag: '등', set: '5', num: '20', weight: '80' },
    { title: '랫풀다운', tag: '등', set: '5', num: '20', weight: '80' },
    { title: '랫풀다운', tag: '등', set: '5', num: '20', weight: '80' },
    { title: '랫풀다운', tag: '등', set: '5', num: '20', weight: '80' },
  ];
  const btns = ['등', '가슴', '어깨', '하체', '팔', '전신', '유산소', '기타'];
  const taghealth = [
    {
      등: [
        '폴업',
        '바벨 로우',
        '덤벨 로우',
        '펜들레이 로우',
        '머신 로우',
        '랫풀다운',
        '친업',
        '백 익스텐션',
        '굿모닝 엑서사이즈',
        '시티드 케이블 로우',
      ],
      가슴: [
        '벤치 프레스',
        '덤벨 벤치 프레스',
        '인클라인 벤치 프레스',
        '디클라인 벤치 프레스',
        '디클라인 덤벨 벤치 프레스',
        '딥스',
        '덤벨 풀오버',
        '버터 플라이 머신',
        '케이블 크로스 오버',
      ],
    },
  ];
  const navigate = useNavigate();
  const photoUp = useRef();
  const [health, setHealth] = useState('');
  const [tags, setTags] = useState('');
  const [set, setSet] = useState('');
  const [num, setNum] = useState('');
  const [weight, setWeight] = useState('');
  const [clicked, setClicked] = useState(false);
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
                <select onChange={e => setTags(e.target.value)}>
                  {btns &&
                    btns.map(data => {
                      return <option>{data}</option>;
                    })}
                </select>
              </div>
              <div>
                <div className="Inselect">
                  <span className="title">운동</span>
                  <select onChange={e => setHealth(e.target.value)}>
                    {taghealth[0][tags] &&
                      taghealth[0][tags].map(data => {
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
                  list.push({
                    title: health,
                    tag: tags,
                    set,
                    num,
                    weight,
                  });
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
            취소
          </button>
        </section>
      </DetailMain>
      <Footer />
    </DetailBox>
  );
}

export default Detail;

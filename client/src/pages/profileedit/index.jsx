import React, { useRef, useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCaretDown, faGear } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { MypagePost } from '../../redux/action/MypageEditAsync';
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import {
  Wrapper,
  ImageBox,
  ProfileBox,
  ProfileGrid,
  ProfileInputTop,
  ProfileInputDown,
  ProfileInputBox,
  // ErrorP,
  BtnBox,
} from './style';

function ProfileEdit() {
  const navigate = useNavigate();
  const userdata = useSelector(state => state.mypage);
  console.log(userdata);
  // const list = userdata.filter(postdata => postdata.post.postId === +Id.id);
  const [username, setUsername] = useState(userdata.member.userName);
  const [password, setPassword] = useState('');
  const [job, setJob] = useState('');
  const [address, setAddress] = useState('');
  const [gender, setGender] = useState('male');
  const [age, setAge] = useState('');
  const [select, setSelect] = useState('');
  const [height, setHeight] = useState(userdata.member.height);
  const [weight, setWeight] = useState(userdata.member.weight);
  const [kilogram, setKilogram] = useState(userdata.activity.kilogram);
  const [period, setPeriod] = useState('');
  const [prevImage, setPrevImage] = useState('');
  const [profileImage, setProfileImage] = useState(
    userdata.member.profileImage,
  );
  // const [nameError, setNameError] = useState({ display: 'none' });
  // const [passwordError, setPasswordError] = useState({ display: 'none' });
  const photoUp = useRef();
  // const PWDTest = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}$/;

  const dispatch = useDispatch();

  const handleprofileImage = e => {
    const reader = new FileReader();
    reader.readAsDataURL(e.target.files[0]);
    reader.onloadend = () => {
      const resultImg = reader.result;
      setPrevImage(resultImg.toString());
    };
    setProfileImage(e.target.files[0]);
  };
  const handelClick = () => {
    photoUp.current.click();
  };

  // useEffect(() => {
  //   if (username.length === 0) {
  //     setNameError({ display: 'block' });
  //   }
  //   if (username.length > 0) {
  //     setNameError({ display: 'none' });
  //   }
  //   if (!PWDTest.test(password)) {
  //     setPasswordError({ display: 'block' });
  //   }
  //   if (PWDTest.test(password)) {
  //     setPasswordError({ display: 'none' });
  //   }
  // }, [username, password]);

  const handleSubmit = () => {
    const formData = new FormData();
    formData.append('password', password);
    formData.append('username', username);
    formData.append('job', job);
    formData.append('address', address);
    formData.append('gender', gender);
    formData.append('split', +select.slice(0, 1));
    formData.append('age', age);
    formData.append('height', height);
    formData.append('weight', weight);
    formData.append('kilogram', kilogram);
    formData.append('period', period);
    formData.append('profileImage', profileImage);
    for (const pair of formData.entries()) {
      console.log(`${pair[0]}, ${pair[1]}`);
    }
    dispatch(MypagePost(formData));
  };
  return (
    <Wrapper>
      <Header />
      <div>
        <ImageBox>
          <img src={prevImage} alt="userProfile" />
          <FontAwesomeIcon
            icon={faGear}
            className="setting"
            onClick={() => handelClick()}
          />
          <div className="Imgaddbox">
            <input
              type="file"
              className="ImgInput"
              ref={photoUp}
              onChange={handleprofileImage}
            />
          </div>
        </ImageBox>
        <ProfileBox>
          <ProfileGrid>
            <div className="boxname">유저이름*</div>
            <ProfileInputTop
              value={username}
              placeholder="유저이름을 적어주세요."
              onChange={e => {
                setUsername(e.target.value);
              }}
            />
            {/* <ErrorP style={nameError} className="errormsg">
              이름은 1글자 이상이어야 합니다.
            </ErrorP> */}
            <div className="boxname">비밀번호*</div>
            <ProfileInputTop
              value={password}
              type="password"
              placeholder="비밀번호를 적어주세요."
              onChange={e => {
                setPassword(e.target.value);
              }}
            />
            {/* <ErrorP style={passwordError} className="errormsg">
              비밀번호는 숫자, 영문 포함 8자 이상이어야 합니다.
            </ErrorP> */}
            <div className="boxname">직업</div>
            <ProfileInputTop
              value={job}
              placeholder="ex) 트레이너"
              onChange={e => {
                setJob(e.target.value);
              }}
            />
            <div className="boxname">주소</div>
            <ProfileInputTop
              value={address}
              placeholder="ex) 서울시 강북구"
              onChange={e => {
                setAddress(e.target.value);
              }}
            />
            <div className="checkbox">
              <div className="checkLeft">
                <span>성별*</span>
                <div>
                  <label>
                    <input
                      type="radio"
                      name="sex"
                      value="male"
                      defaultChecked
                      onChange={e => {
                        // e.preventDefault();
                        setGender(e.target.value);
                      }}
                    />
                    <span>남성</span>
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="sex"
                      value="female"
                      onChange={e => {
                        // e.preventDefault();
                        setGender(e.target.value);
                      }}
                    />
                    <span>여성</span>
                  </label>
                </div>
              </div>
              <div className="checkright">
                <div>분할*</div>
                <div className="container">
                  <input id="dropdown" type="checkbox" />
                  <label className="dropdownLabel" htmlFor="dropdown">
                    <div>{select}</div>
                    <FontAwesomeIcon icon={faCaretDown} className="caretIcon" />
                  </label>
                  <div className="content">
                    <div className="contents">
                      <button
                        onClick={e => {
                          e.preventDefault();
                          setSelect(e.target.innerHTML);
                        }}
                      >
                        2분할
                      </button>
                      <button
                        onClick={e => {
                          e.preventDefault();
                          setSelect(e.target.innerHTML);
                        }}
                      >
                        3분할
                      </button>
                      <button
                        onClick={e => {
                          e.preventDefault();
                          setSelect(e.target.innerHTML);
                        }}
                      >
                        4분할
                      </button>
                      <button
                        onClick={e => {
                          e.preventDefault();
                          setSelect(e.target.innerHTML);
                        }}
                      >
                        5분할
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div className="boxname">나이*</div>
            <ProfileInputBox>
              <ProfileInputDown
                value={age}
                placeholder="ex) 28"
                onChange={e => {
                  setAge(e.target.value);
                }}
              />
              <div>세</div>
            </ProfileInputBox>

            <div className="boxname">키*</div>
            <ProfileInputBox>
              <ProfileInputDown
                value={height}
                placeholder="ex) 180"
                onChange={e => {
                  setHeight(e.target.value);
                }}
              />
              <div>cm</div>
            </ProfileInputBox>

            <div className="boxname">몸무게*</div>
            <ProfileInputBox>
              <ProfileInputDown
                value={weight}
                placeholder="ex) 80"
                onChange={e => {
                  setWeight(e.target.value);
                }}
              />
              <div>kg</div>
            </ProfileInputBox>

            <div className="boxname">3대 중량*</div>
            <ProfileInputBox>
              <ProfileInputDown
                value={kilogram}
                placeholder="ex) 300"
                onChange={e => {
                  setKilogram(e.target.value);
                }}
              />
              <div>kg</div>
            </ProfileInputBox>

            <div className="boxname">운동 경력*</div>
            <ProfileInputBox>
              <ProfileInputDown
                value={period}
                placeholder="ex) 14"
                onChange={e => {
                  setPeriod(e.target.value);
                }}
              />
              <div>개월</div>
            </ProfileInputBox>
          </ProfileGrid>
          <BtnBox>
            <button
              className="set-btn"
              onClick={e => {
                e.preventDefault();
                handleSubmit();
                navigate('/mypage');
              }}
            >
              완료
            </button>
            <button onClick={() => navigate('/mypage')}>취소</button>
          </BtnBox>
        </ProfileBox>
      </div>
      <Footer />
    </Wrapper>
  );
}

export default ProfileEdit;

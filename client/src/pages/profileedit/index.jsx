import React, { useEffect, useRef, useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCaretDown, faGear } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import { MypagePatch } from '../../redux/action/MypageEditAsync';
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
  ErrorP,
  BtnBox,
} from './style';

function ProfileEdit() {
  const [select, setSelect] = useState('');
  const navigate = useNavigate();
  const ac = useSelector(state => state.authToken.accessToken);
  const re = useSelector(state => state.authToken.token);
  const data = [ac, re];
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [job, setJob] = useState('');
  const [address, setAddress] = useState('');
  const [sex, setSex] = useState('man');
  const [age, setAge] = useState('');
  const [height, setHeight] = useState('');
  const [weight, setWeight] = useState('');
  const [kilogram, setKilogram] = useState('');
  const [period, setPeriod] = useState('');
  const [profileImage, setProfileImage] = useState(
    'https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png',
  );
  const [nameError, setNameError] = useState({ display: 'none' });
  const [passwordError, setPasswordError] = useState({ display: 'none' });

  const PWDTest = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}$/;

  const dispatch = useDispatch();

  // useEffect(() => {
  //   dispatch(MypageEditGet(data));
  // }, [data]);

  const reader = new FileReader();
  const photoUp = useRef();
  const handelClick = () => {
    photoUp.current.click();
  };

  const handleprofileImage = e => {
    reader.readAsDataURL(e.target.files[0]);
    reader.onloadend = () => {
      const resultImg = reader.result;
      setProfileImage(resultImg.toString());
    };
  };

  useEffect(() => {
    if (username.length === 0) {
      setNameError({ display: 'block' });
    }
    if (username.length > 0) {
      setNameError({ display: 'none' });
    }
    if (!PWDTest.test(password)) {
      setPasswordError({ display: 'block' });
    }
    if (PWDTest.test(password)) {
      setPasswordError({ display: 'none' });
    }
  }, [username, password]);

  const handleSubmit = () => {
    dispatch(
      MypagePatch({
        username,
        password,
        job,
        address,
        sex,
        select,
        age,
        height,
        weight,
        kilogram,
        period,
        profileImage,
        data,
      }),
    );
    // navigate('/mypage');
  };

  return (
    <Wrapper>
      <Header />
      <div>
        <ImageBox>
          <img src={profileImage} alt="userProfile" />
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
            <ErrorP style={nameError} className="errormsg">
              이름은 1글자 이상이어야 합니다.
            </ErrorP>
            <div className="boxname">비밀번호*</div>
            <ProfileInputTop
              value={password}
              type="password"
              placeholder="비밀번호를 적어주세요."
              onChange={e => {
                setPassword(e.target.value);
              }}
            />
            <ErrorP style={passwordError} className="errormsg">
              비밀번호는 숫자, 영문 포함 8자 이상이어야 합니다.
            </ErrorP>
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
                      value="man"
                      checked="checked"
                      onChange={e => {
                        setSex(e.target.value);
                      }}
                    />
                    <span>남성</span>
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="sex"
                      value="woman"
                      onChange={e => {
                        setSex(e.target.value);
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
                          setSelect(e.target.innerHTML);
                        }}
                      >
                        2분할
                      </button>
                      <button
                        onClick={e => {
                          setSelect(e.target.innerHTML);
                        }}
                      >
                        3분할
                      </button>
                      <button
                        onClick={e => {
                          setSelect(e.target.innerHTML);
                        }}
                      >
                        4분할
                      </button>
                      <button
                        onClick={e => {
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
              onClick={() => {
                handleSubmit();
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

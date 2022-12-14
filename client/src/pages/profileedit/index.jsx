import React, { useRef, useEffect, useState } from 'react';
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
  BtnBox,
  ErrorP,
} from './style';

function ProfileEdit() {
  const navigate = useNavigate();
  const userdata = useSelector(state => state.mypage);

  const [username, setUsername] = useState(userdata.member.userName);
  const [password, setPassword] = useState('');
  const [job, setJob] = useState('');
  const [address, setAddress] = useState('');
  const [gender, setGender] = useState('male');
  const [age, setAge] = useState('');
  const [select, setSelect] = useState('');
  const [height, setHeight] = useState('');
  const [weight, setWeight] = useState('');
  const [kilogram, setKilogram] = useState('');
  const [period, setPeriod] = useState('');
  const [prevImage, setPrevImage] = useState(userdata.member.profileImage);
  const [profileImage, setProfileImage] = useState(
    userdata.member.profileImage,
  );
  const [nameError, setNameError] = useState({ display: 'none' });
  const photoUp = useRef();
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

  useEffect(() => {
    if (username.length === 0) {
      setNameError({ display: 'block' });
    }
    if (username.length > 0) {
      setNameError({ display: 'none' });
    }
  }, [username, password]);

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
            <div className="boxname">????????????*</div>
            <ProfileInputTop
              value={username}
              placeholder="??????????????? ???????????????."
              onChange={e => {
                setUsername(e.target.value);
              }}
            />
            <ErrorP style={nameError} className="errormsg">
              ????????? 1?????? ??????????????? ?????????.
            </ErrorP>
            <div className="boxname">????????????</div>
            <ProfileInputTop
              value={password}
              type="password"
              placeholder="??????????????? ???????????????."
              onChange={e => {
                setPassword(e.target.value);
              }}
            />
            <div className="boxname">??????</div>
            <ProfileInputTop
              value={job}
              placeholder="ex) ????????????"
              onChange={e => {
                setJob(e.target.value);
              }}
            />
            <div className="boxname">??????</div>
            <ProfileInputTop
              value={address}
              placeholder="ex) ????????? ?????????"
              onChange={e => {
                setAddress(e.target.value);
              }}
            />
            <div className="checkbox">
              <div className="checkLeft">
                <span>??????*</span>
                <div>
                  <label>
                    <input
                      type="radio"
                      name="sex"
                      value="male"
                      defaultChecked
                      onChange={e => {
                        setGender(e.target.value);
                      }}
                    />
                    <span>??????</span>
                  </label>
                  <label>
                    <input
                      type="radio"
                      name="sex"
                      value="female"
                      onChange={e => {
                        setGender(e.target.value);
                      }}
                    />
                    <span>??????</span>
                  </label>
                </div>
              </div>
              <div className="checkright">
                <div>??????*</div>
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
                        2??????
                      </button>
                      <button
                        onClick={e => {
                          e.preventDefault();
                          setSelect(e.target.innerHTML);
                        }}
                      >
                        3??????
                      </button>
                      <button
                        onClick={e => {
                          e.preventDefault();
                          setSelect(e.target.innerHTML);
                        }}
                      >
                        4??????
                      </button>
                      <button
                        onClick={e => {
                          e.preventDefault();
                          setSelect(e.target.innerHTML);
                        }}
                      >
                        5??????
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div className="boxname">??????*</div>
            <ProfileInputBox>
              <ProfileInputDown
                value={age}
                placeholder="ex) 28"
                onChange={e => {
                  setAge(e.target.value);
                }}
              />
              <div>???</div>
            </ProfileInputBox>

            <div className="boxname">???*</div>
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

            <div className="boxname">?????????*</div>
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

            <div className="boxname">3??? ??????*</div>
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

            <div className="boxname">?????? ??????*</div>
            <ProfileInputBox>
              <ProfileInputDown
                value={period}
                placeholder="ex) 14"
                onChange={e => {
                  setPeriod(e.target.value);
                }}
              />
              <div>??????</div>
            </ProfileInputBox>
          </ProfileGrid>
          <BtnBox>
            <button
              className="set-btn"
              onClick={() => {
                if (
                  profileImage.length !== 0 &&
                  username.length > 0 &&
                  username.length <= 15 &&
                  age.length > 0 &&
                  height.length > 0 &&
                  weight.length > 0 &&
                  kilogram.length > 0 &&
                  period.length > 0
                ) {
                  handleSubmit();
                } else if (profileImage.length === 0)
                  alert('???????????? ?????????????????????');
                else if (username.length < 1 || username.length >= 15)
                  alert('????????? 1??? ?????? 15??? ????????? ??????????????????');
                else if (age.length < 1) alert('????????? ??????????????????');
                else if (height.length < 1) alert('?????? ??????????????????');
                else if (weight.length < 1) alert('???????????? ??????????????????');
                else if (kilogram.length < 1) alert('3??? ????????? ??????????????????');
                else if (period.length < 1) alert('?????? ????????? ??????????????????');
              }}
            >
              ??????
            </button>
            <button onClick={() => navigate('/members/mypage')}>??????</button>
          </BtnBox>
        </ProfileBox>
      </div>
      <Footer />
    </Wrapper>
  );
}

export default ProfileEdit;

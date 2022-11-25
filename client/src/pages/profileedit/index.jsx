import React, { useRef, useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCaretDown, faGear } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { MypagePatch } from '../../redux/action/MypageEditAsync';
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import {
  Wrapper,
  ImageBox,
  ProfileBox,
  ProfileGrid,
  ProfileInput,
  BtnBox,
} from './style';

function ProfileEdit() {
  const navigate = useNavigate();
  const photoUp = useRef();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [job, setJob] = useState('');
  const [address, setAddress] = useState('');
  const [sex, setSex] = useState('man');
  const [age, setAge] = useState('');
  const [select, setSelect] = useState('');
  const [height, setHeight] = useState('');
  const [weight, setWeight] = useState('');
  const [kilogram, setKilogram] = useState('');
  const [period, setPeriod] = useState('');
  const [profileImage, setProfileImage] = useState('');
  const formData = new FormData();
  const reader = new FileReader();
  const dispatch = useDispatch();
  formData.append('password', JSON.stringify(password));
  formData.append('username', JSON.stringify(username));
  formData.append('job', JSON.stringify(job));
  formData.append('address', JSON.stringify(address));
  formData.append('sex', JSON.stringify(sex));
  formData.append('split', JSON.stringify(select));
  formData.append('age', JSON.stringify(age));
  formData.append('height', JSON.stringify(height));
  formData.append('weight', JSON.stringify(weight));
  formData.append('kilogram', JSON.stringify(kilogram));
  formData.append('period', JSON.stringify(period));
  formData.append('profileImage', JSON.stringify(profileImage));

  const handleprofileImage = e => {
    reader.readAsDataURL(e.target.files[0]);
    reader.onloadend = () => {
      const resultImg = reader.result;
      setProfileImage(resultImg.toString());
    };
    setProfileImage(e.target.files[0]);
  };
  const handelClick = () => {
    photoUp.current.click();
  };
  const deleteFile = () => {
    setProfileImage('');
  };
  const handleSubmit = () => {
    dispatch(MypagePatch(formData));
    // navigate('/mypage');
  };
  return (
    <Wrapper>
      <Header />
      <form
        id="form"
        onSubmit={e => {
          e.preventDefault();
          handleSubmit();
        }}
      >
        <ImageBox>
          <img src={profileImage} alt="userProfile" />
          <button onClick={() => deleteFile(profileImage)} className="Imgdel">
            x
          </button>
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
            <div className="boxname">이름</div>
            <ProfileInput
              type="text"
              value={username}
              placeholder="이름을 입력하세요."
              onChange={e => {
                setUsername(e.target.value);
              }}
            />
            <div className="boxname">비밀번호</div>
            <ProfileInput
              type="password"
              value={password}
              placeholder="비밀번호를 입력하세요."
              onChange={e => {
                setPassword(e.target.value);
              }}
            />
            <div className="boxname">직업</div>
            <ProfileInput
              type="text"
              value={job}
              placeholder="직업을 입력하세요."
              onChange={e => {
                setJob(e.target.value);
              }}
            />
            <div className="boxname">주소</div>
            <ProfileInput
              type="text"
              value={address}
              placeholder="주소를 입력하세요."
              onChange={e => {
                setAddress(e.target.value);
              }}
            />
            <div className="checkbox">
              <div className="checkLeft">
                <span>성별</span>
                <div>
                  <label>
                    <input
                      type="radio"
                      name="sex"
                      value="man"
                      checked="checked"
                      onChange={e => {
                        e.preventDefault();
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
                        e.preventDefault();
                        setSex(e.target.value);
                      }}
                    />
                    <span>여성</span>
                  </label>
                </div>
              </div>
              <div className="checkright">
                <div>분할</div>
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
            <div className="boxname">나이</div>
            <ProfileInput
              type="text"
              value={age}
              placeholder="나이를 입력하세요."
              onChange={e => {
                setAge(e.target.value);
              }}
            />
            <div className="boxname">키</div>
            <ProfileInput
              value={height}
              placeholder="키를 입력하세요."
              onChange={e => {
                setHeight(e.target.value);
              }}
            />
            <div className="boxname">몸무게</div>
            <ProfileInput
              type="text"
              value={weight}
              placeholder="몸무게를 입력하세요."
              onChange={e => {
                setWeight(e.target.value);
              }}
            />
            <div className="boxname">중량</div>
            <ProfileInput
              type="text"
              value={kilogram}
              placeholder="중량을 입력하세요."
              onChange={e => {
                setKilogram(e.target.value);
              }}
            />
            <div className="boxname">경력</div>
            <ProfileInput
              type="text"
              value={period}
              placeholder="경력을 입력하세요."
              onChange={e => {
                setPeriod(e.target.value);
              }}
            />
          </ProfileGrid>
          <BtnBox>
            <button className="set-btn" type="submit">
              완료
            </button>
            <button onClick={() => navigate('/mypage')}>취소</button>
          </BtnBox>
        </ProfileBox>
      </form>
      <Footer />
    </Wrapper>
  );
}

export default ProfileEdit;

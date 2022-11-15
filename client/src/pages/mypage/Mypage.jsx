import React from 'react';
import styled from 'styled-components';
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import userProfile from '../../images/userprofile.png';

const Wrapper = styled.div`
  width: 100%;
  > div {
    padding-top: 60px;
    height: 1000px;
    max-width: 1200px;
    margin: 0 auto;
    display: flex;
    align-items: center;
    flex-direction: column;
  }
`;

const ProfileBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
  width: 680px;
  height: 200px;
  margin-top: 100px;
`;

const RecordBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 200px;
  > div {
    display: flex;
    justify-content: space-between;
  }
`;
const RecordBtn = styled.button`
  height: 45px;
  color: white;
  background-color: #fc6666;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 0.8em;
  border-radius: 3px;
  border: none;
  cursor: pointer;
`;

const PictureBox = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;

  width: 100%;
  height: 400px;
`;

function Mypage() {
  return (
    <Wrapper>
      <Header />
      <div>
        <ProfileBox>
          <img src={userProfile} alt="userProfile" />
          <div>유저 이름</div>
          <div>
            <span>게시물 22</span>
            <span>팔로워 11</span>
            <span>팔로우 133</span>
          </div>
        </ProfileBox>
        <RecordBox>
          <div>450kg</div>
          <div>132</div>
          <div>680</div>
          <RecordBtn>운동 기록</RecordBtn>
        </RecordBox>
        <hr width="100%" />
        <div />
        <PictureBox>
          <div />
        </PictureBox>
      </div>
      <Footer />
    </Wrapper>
  );
}

export default Mypage;

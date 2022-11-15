import React from 'react';
import styled from 'styled-components';
import QnAImg from '../../images/qnaImg.jpg';
import Header from '../../components/header/Header';
import Footer from '../../components/footer/Footer';

const Detail = styled.div`
  width: 100%;
  height: 100vh;
`;
const Headerwrap = styled.main`
  width: 100%;
  padding-bottom: 40px;
`;
const DetailBack = styled.article`
  max-width: 1200px;
  margin: 100px auto;
  height: auto;
`;
const DetailTitle = styled.section`
  margin: 0 auto;
  border-bottom: 1px solid #b8b8b8;
  margin: 40px 0;
  display: block;
  > h2 {
    height: auto;
    font-size: var(--font-24);

    font-weight: 800;
  }
  > h3 {
    font-size: var(--font-18);
    margin: 60px 0;
    line-height: 1.7rem;
    opacity: 0.7;
    font-weight: 500;
  }
  > div {
    background-image: url(${QnAImg});
    background-size: cover;
    width: 215px;
    height: 150px;
    background-repeat: no-repeat;
    background-position: 50% 55%;
    margin: 40px 0;
  }
`;
const DetailNDB = styled.span`
  display: flex;
  margin-bottom: 50px;
  > h4 {
    margin-right: 50px;
    font-size: var(--font-15);
    line-height: 2rem;
    opacity: 0.6;
  }
  > button {
    background-color: var(--tagyellow);
    border: none;
    width: 50px;
    height: 30px;
    font-weight: 600;
    cursor: pointer;
  }
`;

const DetailAnswer = styled.div`
  padding-top: 40px;
  > h2 {
    font-size: var(--font-20);
  }
  > h3 {
    margin: 40px 0;
    font-size: var(--font-18);
    line-height: 1.7rem;
    opacity: 0.7;
    font-weight: 500;
  }
`;

const AnswerNDB = styled.span`
  display: flex;
  margin-bottom: 50px;
  > h4 {
    margin-right: 50px;
    font-size: var(--font-15);
    line-height: 2rem;
    opacity: 0.6;
  }
  > button {
    background-color: var(--logored);
    border: none;
    border-radius: 30px;
    color: white;
    font-weight: bolder;
    width: 30px;
    height: 30px;
    font-weight: 600;
    cursor: pointer;
  }
`;

const DetailComment = styled.section`
  > h2 {
    font-size: var(--font-20);
    font-weight: 600;
    margin-bottom: 40px;
  }
  > input {
    width: 1200px;
    height: 150px;
    border-radius: 22px;
    border: 1px solid #bababa;
    font-size: var(--font-18);
    padding-left: 50px;
  }
`;

const DetailSubmit = styled.button`
  width: 120px;
  height: 50px;
  border-radius: 20px;
  background-color: var(--logored);
  color: white;
  font-weight: bold;
  border: none;
  margin-top: 25px;
  float: right;
  cursor: pointer;
`;
function QnaDetail() {
  return (
    <Detail>
      <Headerwrap>
        <Header />
      </Headerwrap>
      <DetailBack>
        <DetailTitle>
          <h2>형님들 헬린이 랫풀다운 할 때 팔아픔 문제있나요?</h2>
          <h3>
            헬린이 유튜브 영상 많이 보먼셔 랫풀다운 자세 잡아가고있습니다.
            집에서 연습하면서 영상 찍을때는 견갑골 열리고 닫히는게 괜찮게 보여서
            헬스장가서 오늘 조져봤는데 가벼운 무게에서는 잘 되는거
            같더라구요.그런데 무게를 조금 올려서 힘을 좀 쓸정도가 되면 팔이
            아픕니다. 팔꿈치위쪽(이두인지 삼두인지 근육이)가 아픈데 팔이 아프면
            이상한건가요? 아니면 아직 팔근육이 없어서 당길 때 힘든 건지요...
          </h3>
          <div />
          <DetailNDB>
            <h4>헬린이</h4>
            <h4>2022.11.11</h4>
            <button>운동</button>
          </DetailNDB>
        </DetailTitle>
        <DetailAnswer>
          <h2>답변 1</h2>
          <h3>
            일단 두가지 가능성이 있죠. 등에 집중을 못하는겁니다. 등운동인데 등에
            집중안되고 다른곳에 집중대 그부분이 운동 되서 그럴 확률이 있습니다.
            보통은 이런경우 자세문제가 많죠. 두번째는 팔이 상대적으로
            약한겁니다. 등에 비해서요. 등이 지치기 전에 팔이 먼저 지처 버릴 수
            있죠. 그래서 팔이 먼저 아프고 계속하면 팔이 아파 잘 못하는거죠.
            경력이 많지 않으면 보통은 전자의 경우가 많습니다.
          </h3>
          <AnswerNDB>
            <h4>헬린이</h4>
            <h4>2022.11.11</h4>
            <button>V</button>
          </AnswerNDB>
        </DetailAnswer>
        <DetailComment>
          <h2>답변 작성</h2>
          <input type="text" placeholder="답변을 입력해주세요!" />
        </DetailComment>
        <DetailSubmit>등록</DetailSubmit>
      </DetailBack>
      <Footer />
    </Detail>
  );
}

export default QnaDetail;

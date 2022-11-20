import React from 'react';
import { Link } from 'react-router-dom';
import Header from '../../components/header/Header';
import Footer from '../../components/footer/Footer';
import {
  Detail,
  Headerwrap,
  DetailAnswer,
  DetailBack,
  DetailComment,
  DetailNDB,
  DetailSubmit,
  DetailTitle,
  AnswerNDB,
  DetailUpdate,
} from './QnaDetailStyle';

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
          <section>
            <DetailNDB>
              <div>
                <h4>헬린이</h4>
                <h4>2022.11.11</h4>
              </div>
              <button>운동</button>
            </DetailNDB>
            <DetailUpdate>
              <Link to="/qnaupdate" className="qnaupdate">
                <h3>수정</h3>
              </Link>
            </DetailUpdate>
          </section>
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
            <div>
              <h4>헬린이</h4>
              <h4>2022.11.11</h4>
            </div>
            <button>V</button>
          </AnswerNDB>
        </DetailAnswer>
        <DetailComment>
          <h2>답변 작성</h2>
          <textarea type="text" placeholder="답변을 입력해주세요!" />
        </DetailComment>
        <DetailSubmit>등록</DetailSubmit>
      </DetailBack>
      <Footer />
    </Detail>
  );
}

export default QnaDetail;

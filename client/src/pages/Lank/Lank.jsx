import styled from 'styled-components';
import Header from '../../components/header/Header';
import Footer from '../../components/footer/Footer';
import LankContent from './LankContent';

const LankBox = styled.div`
  width: 100%;
`;
const LankMain = styled.main`
  max-width: 1200px;
  margin: 0 auto;
  width: 100%;
  height: auto;
  padding-top: 60px;
  margin-bottom: 150px;
  .content-wrapper {
    section {
      margin-bottom: -80px;
    }
  }
  .lankhead {
    display: flex;
    justify-content: space-between;
    margin: 60px 0 10px 0;
    > h2 {
      font-weight: bold;
    }
    .lankTab {
      display: flex;
      > li {
        list-style: none;
        > button {
          width: 70px;
          height: 30px;
          background-color: #2b2b2b;
          border: 1px solid #fefefe;
          color: white;
          cursor: pointer;
          margin-right: 4px;
        }
      }
    }
  }
`;

function Lank() {
  const list = [
    { id: 1, name: '분할' },
    { id: 2, name: '키' },
    { id: 3, name: '몸무게' },
    { id: 4, name: '경력' },
    { id: 5, name: '포인트' },
  ];
  // const Devision = [2, 3, 4, 5];
  return (
    <LankBox>
      <Header />
      <LankMain>
        <div className="lankhead">
          <h2>종합 랭킹</h2>
          <ul className="lankTab">
            {list &&
              list.map(data => {
                return (
                  <li key={data.id}>
                    <button
                      onClick={() => {
                        // console.log(data.id);
                      }}
                    >
                      {data.name}
                    </button>
                  </li>
                );
              })}
          </ul>
        </div>
        <div className="content-wrapper">
          <LankContent />
          <LankContent />
          <LankContent />
          <LankContent />
          <LankContent />
        </div>
      </LankMain>
      <Footer />
    </LankBox>
  );
}

export default Lank;

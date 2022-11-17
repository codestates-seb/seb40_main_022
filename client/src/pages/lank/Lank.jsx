import Header from '../../components/header/Header';
import Footer from '../../components/footer/Footer';
import LankContent from './LankContent';
import LankDropdown from './LankDropdown';
import searchIcon from '../../images/searchIcon.png';
import { LankBox, LankMain } from './Lankstyle';

function Lank() {
  // const list = [
  //   { id: 1, name: '분할' },
  //   { id: 2, name: '키' },
  //   { id: 3, name: '몸무게' },
  //   { id: 4, name: '경력' },
  //   { id: 5, name: '포인트' },
  // ];
  // const Devision = [2, 3, 4, 5];
  return (
    <LankBox>
      <Header />
      <LankMain>
        <div className="lankhead">
          <h2>종합 랭킹</h2>
          <LankDropdown />
          <label htmlFor="SearchIn">
            <img src={searchIcon} alt="검색아이콘" className="search" />
          </label>
        </div>
        <LankContent />
        <LankContent />
        <LankContent />
        <LankContent />
      </LankMain>
      <Footer />
    </LankBox>
  );
}

export default Lank;

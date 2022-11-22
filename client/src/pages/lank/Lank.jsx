import Header from '../../components/header/Header';
import Footer from '../../components/footer/Footer';
import LankContent from './LankContent';
import LankDropdown from './LankDropdown';
import searchIcon from '../../images/searchIcon.png';
import { LankBox, LankMain } from './Lankstyle';

function Lank() {
  return (
    <LankBox>
      <Header />
      <LankMain>
        <div className="lankhead">
          <h2>종합 랭킹</h2>
          <div>
            <LankDropdown />
            <button>
              <img src={searchIcon} alt="검색아이콘" className="search" />
            </button>
          </div>
        </div>
        {[...Array(10)].map(() => {
          return <LankContent />;
        })}
      </LankMain>
      <Footer />
    </LankBox>
  );
}

export default Lank;

import Header from '../../components/header/Header';
import Footer from '../../components/footer/Footer';
import LankContent from './LankContent';
import LankDropdown from './LankDropdown';
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
          </div>
        </div>
        <LankContent />
      </LankMain>
      <Footer />
    </LankBox>
  );
}

export default Lank;

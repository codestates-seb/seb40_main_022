import { Link } from 'react-router-dom';
import SideDiv from './SideStyle';

function Sidebar() {
  return (
    <SideDiv>
      <Link to="/dailypost" className="sidemenu">
        운동 기록
      </Link>
      <Link to="/lank" className="sidemenu">
        랭킹
      </Link>
      <span className="sidemenu">Q&A</span>
      <span className="sidemenu">인플루언서</span>
    </SideDiv>
  );
}

export default Sidebar;

import { Link } from 'react-router-dom';
import SideDiv from './SideStyle';

function Sidebar() {
  return (
    <SideDiv>
      <Link to="/record" className="sidemenu">
        운동 기록
      </Link>
      <Link to="/lank" className="sidemenu">
        랭킹
      </Link>
      <Link to="/qna" className="sidemenu">
        Q&A
      </Link>
      <span className="sidemenu">인플루언서</span>
    </SideDiv>
  );
}

export default Sidebar;

import { Link } from 'react-router-dom';
import SideDiv from './SideStyle';

function Sidebar() {
  return (
    <SideDiv>
      <Link to="/login" className="sidemenu">
        Login
      </Link>
      <Link to="/signup" className="sidemenu">
        SignUp
      </Link>
    </SideDiv>
  );
}

export default Sidebar;

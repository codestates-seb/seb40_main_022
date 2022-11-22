import { useDispatch, useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
import SideDiv from './SideStyle';
import { LogoutAsync } from '../../redux/action/LoginAsync';

function Sidebar() {
  const IsLogin = useSelector(state => state.authToken.isLogin);
  const ac = useSelector(state => state.authToken.accessToken);
  const re = useSelector(state => state.authToken.token);
  const data = [ac, re];
  const dispatch = useDispatch();

  return (
    <SideDiv>
      {IsLogin ? (
        <>
          <Link to="/mypage" className="sidemenu">
            Mypage
          </Link>
          <Link
            to="/"
            className="sidemenu"
            onClick={() => {
              dispatch(LogoutAsync(data));
            }}
          >
            Logout
          </Link>
        </>
      ) : (
        <>
          <Link to="/login" className="sidemenu">
            Login
          </Link>
          <Link to="/signup" className="sidemenu">
            SignUp
          </Link>
        </>
      )}
    </SideDiv>
  );
}

export default Sidebar;

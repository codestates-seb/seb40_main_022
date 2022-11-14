import { useState } from 'react';
import { Link } from 'react-router-dom';
import logo from '../../images/logo.png';
import sidebar from '../../images/sidebar.png';
import Sidebar from '../sidebar/Sidebar';
import Head from './Head';

function Header() {
  const [side, setSide] = useState(false);

  return (
    <Head>
      <div className="mainHead">
        <Link to="/" className="logo">
          <img src={logo} alt="logo" />
          <span className="logoname">Fit Challenge</span>
        </Link>
        <div className="Rightheader">
          <Link to="/login" className="loginbut">
            Login
          </Link>
          <Link to="/signup" className="logoutbut">
            SignUp
          </Link>
          <button onClick={() => setSide(!side)}>
            <img src={sidebar} alt="sidebar" className="sideb" />
            {side ? <Sidebar className="sidebar" /> : null}
          </button>
        </div>
      </div>
    </Head>
  );
}

export default Header;

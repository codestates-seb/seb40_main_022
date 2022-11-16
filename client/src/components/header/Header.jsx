import { useState } from 'react';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBell } from '@fortawesome/free-solid-svg-icons';
import logo from '../../images/logo.png';
import sidebar from '../../images/sidebar.png';
import Sidebar from '../sidebar/Sidebar';
import Head from './Head';
import Modal from '../modal';

function Header() {
  const [side, setSide] = useState(false);
  const [modalOpen, setModalOpen] = useState(false);

  const openModal = () => {
    setModalOpen(true);
  };
  const closeModal = () => {
    setModalOpen(false);
  };

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
          <FontAwesomeIcon
            icon={faBell}
            onClick={() => openModal()}
            className="modal"
          />
          <Modal open={modalOpen} close={closeModal} header="알림" />
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

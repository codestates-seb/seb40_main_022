import { useState, useRef, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBell } from '@fortawesome/free-solid-svg-icons';
import { useSelector } from 'react-redux';
import logo from '../../images/logo.png';
import sidebar from '../../images/sidebar.png';
import Sidebar from '../sidebar/Sidebar';
import Head from './Head';
import Modal from '../modal/noticeModal';

function Header() {
  const [side, setSide] = useState(false);
  const [modalOpen, setModalOpen] = useState(false);
  const popRef = useRef(null);
  const noticeRef = useRef(null);

  const closeHandler = ({ target }) => {
    if (side && !popRef.current.contains(target)) setSide(false);
  };

  const noticeCloseHandler = ({ target }) => {
    if (modalOpen && !noticeRef.current.contains(target)) setModalOpen(false);
  };

  useEffect(() => {
    window.addEventListener('click', closeHandler);
    return () => {
      window.removeEventListener('click', closeHandler);
    };
  });

  useEffect(() => {
    window.addEventListener('click', noticeCloseHandler);
    return () => {
      window.removeEventListener('click', closeHandler);
    };
  });
  const IsLogin = useSelector(state => state.authToken.isLogin);
  return (
    <Head>
      <div className="mainHead">
        <Link to="/" className="logo">
          <img src={logo} alt="logo" />
          <span className="logoname">Fit Challenge</span>
        </Link>
        {IsLogin ? (
          <div className="Rightheader">
            <Link to="/record" className="logoutbut">
              운동 기록
            </Link>
            <Link to="/lank" className="logoutbut">
              랭킹
            </Link>
            <Link to="/qna" className="logoutbut">
              Q&A
            </Link>
            <button ref={noticeRef} onClick={() => setModalOpen(true)}>
              <FontAwesomeIcon icon={faBell} className="noticeModal" />
              {modalOpen && <Modal className="notice" />}
            </button>
            <button ref={popRef} onClick={() => setSide(!side)}>
              <img src={sidebar} alt="sidebar" className="sideb" />
              {side ? <Sidebar className="sidebar" /> : null}
            </button>
          </div>
        ) : (
          <div className="Rightheader">
            <Link to="/lank" className="logoutbut">
              랭킹
            </Link>
            <Link to="/qna" className="logoutbut">
              Q&A
            </Link>
            <button ref={popRef} onClick={() => setSide(!side)}>
              <img src={sidebar} alt="sidebar" className="sideb" />
              {side ? <Sidebar className="sidebar" /> : null}
            </button>
          </div>
        )}
      </div>
    </Head>
  );
}

export default Header;

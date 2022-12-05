import { useState, useRef, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faBell } from '@fortawesome/free-solid-svg-icons';
import { useDispatch, useSelector } from 'react-redux';
import Logo from '../../images/Logo.svg';
import LogoText from '../../images/LogoText.svg';
import sidebar from '../../images/sidebar.png';
import ballliston from '../../images/ballliston.png';
import Sidebar from '../sidebar/Sidebar';
import Head from './Head';
import Modal from '../modal/noticeModal';
import { Notifications } from '../../redux/action/LankAsync';

function Header() {
  const [side, setSide] = useState(false);
  const [modalOpen, setModalOpen] = useState(false);
  const popRef = useRef(null);
  const noticeRef = useRef(null);
  const dispatch = useDispatch();
  const allam = useSelector(
    state => state.challenge.data.notificationResponses,
  );
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
  useEffect(() => {
    dispatch(Notifications());
  }, []);
  const IsLogin = useSelector(state => state.authToken.isLogin);
  return (
    <Head>
      <div className="mainHead">
        <Link to="/dailyposts" className="logo">
          <img src={Logo} alt="logo" />
          <img className="logoname" src={LogoText} alt="logo" />
        </Link>
        {IsLogin ? (
          <div className="Rightheader">
            <Link to="/records" className="logoutbut">
              운동 기록
            </Link>
            <Link to="/challenge" className="logoutbut">
              랭킹
            </Link>
            <Link to="/questions" className="logoutbut">
              Q&A
            </Link>
            {allam && allam.length !== 0 ? (
              <button ref={noticeRef} onClick={() => setModalOpen(true)}>
                <img src={ballliston} className="noticeModal" alt="핑크색 종" />
                {modalOpen && <Modal className="notice" />}
              </button>
            ) : (
              <button ref={noticeRef} onClick={() => setModalOpen(true)}>
                <FontAwesomeIcon icon={faBell} className="noticeModal" />
                {modalOpen && <Modal className="notice" />}
              </button>
            )}
            <button ref={popRef} onClick={() => setSide(!side)}>
              <img src={sidebar} alt="sidebar" className="sideb" />
              {side ? <Sidebar className="sidebar" /> : null}
            </button>
          </div>
        ) : (
          <div className="Rightheader">
            <Link to="/challenge" className="logoutbut">
              랭킹
            </Link>
            <Link to="/questions" className="logoutbut">
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

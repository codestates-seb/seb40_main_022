import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import styled from 'styled-components';
import Footer from '../../components/footer/Footer';
import Head from '../../components/header/Header';
import MainInside from './MainInside';
import { ReLodingLogin } from '../../redux/action/LoginAsync';

const Maincontainer = styled.main`
  width: 100%;
  height: 100%;
  background-color: var(--backcolor);
`;

function Main() {
  const data1 = new URL(window.location.href).searchParams.get('access_token');
  const data2 = new URL(window.location.href).searchParams.get('refresh_token');
  const loginboolean = useSelector(state => state.authToken.isLogin);
  const dispatch = useDispatch();

  useEffect(() => {
    if (data1 !== null && data2 !== null) {
      window.localStorage.setItem('Authorization', `Bearer ${data1}`);
      window.localStorage.setItem('RefreshToken', `${data2}`);
      window.location.reload();
      if (loginboolean === false) {
        dispatch(ReLodingLogin());
        window.location.reload();
      }
    }
  }, []);
  return (
    <Maincontainer>
      <Head />
      <MainInside />
      <Footer />
    </Maincontainer>
  );
}

export default Main;

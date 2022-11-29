import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import Footer from '../../components/footer/Footer';
import Head from '../../components/header/Header';
import MainInside from '../../components/main/MainInside';

const Maincontainer = styled.main`
  width: 100%;
  height: 100%;
  background-color: var(--backcolor);
`;

function Main() {
  const data1 = new URL(window.location.href).searchParams.get('access_token');
  const data2 = new URL(window.location.href).searchParams.get('refresh_token');
  const navigate = useNavigate();

  useEffect(() => {
    if (data1 !== null && data2 !== null) {
      window.localStorage.setItem('Authorization', `Bearer ${data1}`);
      window.localStorage.setItem('RefreshToken', `${data2}`);
      navigate('/');
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

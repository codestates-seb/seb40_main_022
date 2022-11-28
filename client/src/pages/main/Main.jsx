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
  return (
    <Maincontainer>
      <Head />
      <MainInside />
      <Footer />
    </Maincontainer>
  );
}

export default Main;

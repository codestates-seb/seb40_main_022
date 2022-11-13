import styled from 'styled-components';
import Footer from '../../components/footer/Footer';
import Head from '../../components/header/Header';

const Maincontainer = styled.main`
  width: 100%;
  height: 100vh;
  > div {
    height: 800px;
  }
`;

function Main() {
  return (
    <Maincontainer>
      <Head />
      <div />
      <Footer />
    </Maincontainer>
  );
}

export default Main;

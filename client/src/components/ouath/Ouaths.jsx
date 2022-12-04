import logingoogle from '../../images/logingoogle.png';
import kakao from '../../images/kakao.png';
import Ouathbutton from './Ouathbutton';

function Ouaths() {
  const handleClick = name => {
    if (name === 'google') {
      window.location.href = `${process.env.REACT_APP_API_URL}/oauth2/authorization/google`;
    } else if (name === 'kakao') {
      window.location.href = `${process.env.REACT_APP_API_URL}/oauth2/authorization/kakao`;
    }
  };
  return (
    <Ouathbutton>
      <button className="goolobutton" onClick={() => handleClick('google')}>
        <img src={logingoogle} alt="구글로고" />
        Log in with Google
      </button>
      <button className="kakaobutton" onClick={() => handleClick('kakao')}>
        <img src={kakao} alt="깃허브로고" />
        Log in with KaKao
      </button>
    </Ouathbutton>
  );
}

export default Ouaths;

import logingoogle from '../../images/logingoogle.png';
import kakao from '../../images/kakao.png';
import Ouathbutton from './Ouathbutton';

function Ouaths() {
  const handleClick = name => {
    // e.preventdefault();
    if (name === 'google') {
      window.location.href =
        'http://ec2-43-201-64-96.ap-northeast-2.compute.amazonaws.com:8080/oauth2/authorization/google';
    } else if (name === 'kakao') {
      window.location.href =
        'http://ec2-43-201-64-96.ap-northeast-2.compute.amazonaws.com:8080/oauth2/authorization/kakao';
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

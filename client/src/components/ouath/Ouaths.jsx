import logingoogle from '../../images/logingoogle.png';
import kakao from '../../images/kakao.png';
import Ouathbutton from './Ouathbutton';

function Ouaths() {
  return (
    <Ouathbutton>
      <button disabled className="goolobutton">
        <img src={logingoogle} alt="구글로고" />
        Log in with Google
      </button>
      <button disabled className="kakaobutton">
        <img src={kakao} alt="깃허브로고" />
        Log in with Github
      </button>
    </Ouathbutton>
  );
}

export default Ouaths;

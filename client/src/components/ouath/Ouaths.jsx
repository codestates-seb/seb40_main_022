import axios from 'axios';
import logingoogle from '../../images/logingoogle.png';
import kakao from '../../images/kakao.png';
import Ouathbutton from './Ouathbutton';

function Ouaths() {
  const handleClick = () => {
    axios
      .post(
        'oauth2/authorization/google?redirect_uri=http://ec2-13-125-247-218.ap-northeast-2.compute.amazonaws.com:8080/login/oauth2/code/google',
      )
      .then(res => console.log(res));
  };
  return (
    <Ouathbutton>
      <button disabled className="goolobutton" onClick={() => handleClick()}>
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

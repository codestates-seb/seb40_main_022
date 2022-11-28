import { useParams } from 'react-router-dom';
import logingoogle from '../../images/logingoogle.png';
import kakao from '../../images/kakao.png';
import Ouathbutton from './Ouathbutton';

function Ouaths() {
  const params = new URLSearchParams(window.location.search);
  const token = params.get('access_token');
  const tokenname = useParams();
  console.log(token, tokenname);
  console.log(window.location);
  const handleClick = () => {
    window.location.href =
      'http://ec2-3-34-98-9.ap-northeast-2.compute.amazonaws.com:8080/oauth2/authorization/google';
  };
  return (
    <Ouathbutton>
      <button className="goolobutton" onClick={() => handleClick()}>
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

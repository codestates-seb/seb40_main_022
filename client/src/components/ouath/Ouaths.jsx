import logingoogle from '../../images/logingoogle.png';
import loginfacebook from '../../images/loginfacebook.png';
import logingithub from '../../images/logingithub.png';
import Ouathbutton from './Ouathbutton';

function Ouaths() {
  return (
    <Ouathbutton>
      <button disabled className="goolobutton">
        <img src={logingoogle} alt="구글로고" />
        Log in with Google
      </button>
      <button disabled className="githubbutton">
        <img src={logingithub} alt="깃허브로고" />
        Log in with Github
      </button>
      <button disabled className="fabookbutton">
        <img src={loginfacebook} alt="페북로고" />
        Log in with Facebook
      </button>
    </Ouathbutton>
  );
}

export default Ouaths;

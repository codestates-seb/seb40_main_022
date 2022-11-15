import facebook from '../../images/facebook.png';
import instagram from '../../images/instagram.png';
import twitter from '../../images/twitter.png';
import Foo from './Foo';

function Footer() {
  return (
    <Foo>
      <div>
        <div className="footer-left">
          <div className="footer-images">
            <img src={facebook} alt="facebook-logo" />
            <img src={instagram} alt="instagram-logo" />
            <img src={twitter} alt="twitter.png" />
          </div>
          <span className="footer-callenge">
            Copyright @ 2022 Fit Challenge
          </span>
        </div>
        <div className="footer-right">
          <span className="right-one">팀장 | 최윤우</span>
          <span>팀원 | 한승진, 고하나, 고정훈, 김신재, 방인석, 조현식 </span>
        </div>
      </div>
    </Foo>
  );
}

export default Footer;

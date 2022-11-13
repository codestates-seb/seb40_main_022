import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import Input from '../../components/Input/Input';
import Ouaths from '../../components/ouath/Ouaths';
import LoginStyle from './LoginStyle';

function Login() {
  return (
    <LoginStyle>
      <Header />
      <div className="loginbox">
        <section className="logosection">
          <span>LOGIN</span>
          <Input Intext="Email" />
          <Input Intext="Password" />
          <Ouaths />
          <button className="LoginButton">Login</button>
        </section>
      </div>
      <Footer />
    </LoginStyle>
  );
}

export default Login;

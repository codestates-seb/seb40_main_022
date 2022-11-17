import { useState } from 'react';
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import Ouaths from '../../components/ouath/Ouaths';
import LoginStyle from './LoginStyle';

function Login() {
  const [Evalue, setEvalue] = useState('');
  const [pwd, setPwd] = useState('');
  return (
    <LoginStyle>
      <Header />
      <div className="loginbox">
        <section className="logosection">
          <span>LOGIN</span>
          <input
            placeholder="Email"
            value={Evalue}
            onChange={e => setEvalue(e.target.value)}
          />
          <input
            type="password"
            placeholder="Password"
            value={pwd}
            onChange={e => setPwd(e.target.value)}
          />
          <Ouaths />
          <button className="LoginButton">Login</button>
        </section>
      </div>
      <Footer />
    </LoginStyle>
  );
}

export default Login;

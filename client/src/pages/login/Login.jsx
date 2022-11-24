import { useState } from 'react';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import Ouaths from '../../components/ouath/Ouaths';
import LoginStyle from './LoginStyle';
import { LoginAsync, ReTokenLogin } from '../../redux/action/LoginAsync';

function Login() {
  const [Evalue, setEvalue] = useState('');
  const [pwd, setPwd] = useState('');
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleClick = () => {
    const data = [Evalue, pwd];
    if (Evalue === '') {
      alert('이메일이 비어있습니다.');
    } else if (pwd === '') {
      alert('비밀번호가 비어있습니다.');
    } else {
      dispatch(LoginAsync(data))
        .unwrap()
        .then(() => setInterval(dispatch(ReTokenLogin()), 10000));

      navigate('/');
    }
  };

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
          <button className="LoginButton" onClick={() => handleClick()}>
            Login
          </button>
        </section>
      </div>
      <Footer />
    </LoginStyle>
  );
}

export default Login;

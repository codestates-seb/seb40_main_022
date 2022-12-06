import { useState } from 'react';
import styled from 'styled-components';
import { useDispatch } from 'react-redux';
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import Ouaths from '../../components/ouath/Ouaths';
import SignupAsync from '../../redux/action/SignupAsync';
import SignMain from './SignMain';

const SignContainer = styled.div`
  width: 100%;
`;

function SignUp() {
  const [email, setEmail] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [repassword, setRepassword] = useState('');
  const PWDTest = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}$/;
  const EMTest = /[a-z0-9]+@[a-z]+\.[a-z]{2,3}/;
  const dispatch = useDispatch();

  const handleClick = () => {
    if (!EMTest.test(email)) {
      alert('이메일 형식이 잘 못 되었습니다.');
    } else if (username.length === 0 || username.length > 15) {
      alert('유저이름은 한 글자 이상 15자 이하로 적어주세요!');
    } else if (!PWDTest.test(password) && !PWDTest.test(repassword)) {
      alert('비밀번호는 숫자, 영문을 포함한 8자이상입니다.');
    } else if (password !== repassword) {
      alert('비밀번호 서로 다릅니다.');
    } else {
      dispatch(SignupAsync({ username, email, password }));
    }
  };

  return (
    <SignContainer>
      <Header />
      <SignMain>
        <span className="SignTitle">SIGN UP</span>
        <input
          placeholder="EMAIL"
          value={email}
          onChange={e => setEmail(e.target.value)}
          type="text"
        />
        <input
          placeholder="USER NAME"
          value={username}
          onChange={e => setUsername(e.target.value)}
          type="text"
        />
        <input
          placeholder="PASSWORD"
          value={password}
          onChange={e => setPassword(e.target.value)}
          type="password"
        />
        <input
          placeholder="PASSWORD CHECK"
          value={repassword}
          onChange={e => setRepassword(e.target.value)}
          type="password"
        />
        <div className="buttons">
          <div className="Ouathbutton">
            <Ouaths />
          </div>
          <button className="SignButton" onClick={() => handleClick()}>
            Sign Up
          </button>
        </div>
      </SignMain>
      <Footer />
    </SignContainer>
  );
}

export default SignUp;

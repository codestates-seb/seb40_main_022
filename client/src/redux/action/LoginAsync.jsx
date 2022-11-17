import axios from 'axios';
import { useDispatch } from 'react-redux';
import { SET_TOKEN } from '../reducer/tokenSlice';
import { setRefreshToken } from '../storage/cookie';

const LoginAsync = ({ email, pwd }) => {
  const dispatch = useDispatch();

  axios
    .post('', JSON.stringify({ email, pwd }), {
      headers: {
        'Content-Type': 'application/json;',
      },
    })
    .then(res => {
      return (
        setRefreshToken(res.json.refresh_token),
        dispatch(SET_TOKEN(res.json.access_token))
      );
    });
};

export default LoginAsync;

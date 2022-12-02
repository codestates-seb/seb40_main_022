import axios from 'axios';
import { createAsyncThunk } from '@reduxjs/toolkit';

export function Retoken() {
  if (
    localStorage.getItem('Authorization') &&
    localStorage.getItem('RefreshToken')
  ) {
    return axios
      .get(`${process.env.REACT_APP_API_URL}/member/reissue`, {
        headers: {
          Authorization: localStorage.getItem('Authorization'),
          RefreshToken: localStorage.getItem('RefreshToken'),
        },
      })
      .then(res => {
        axios.defaults.headers.common.token = res.headers.authorization;
        localStorage.setItem('Authorization', res.headers.authorization);
        localStorage.setItem('RefreshToken', res.headers.refreshtoken);
        const auth = [res.headers.authorization, res.headers.refreshtoken];
        setTimeout(Retoken, 600000);
        return auth;
      })
      .catch(err => {
        if (err.response.data.status === 401) {
          localStorage.removeItem('Authorization');
          localStorage.removeItem('RefreshToken');
          localStorage.clear();
          window.location.reload();
        }
      });
  }
  return null;
}
export const LoginAsync = createAsyncThunk('login', data => {
  return axios
    .post(
      `${process.env.REACT_APP_API_URL}/members/login`,
      JSON.stringify({ username: data[0], password: data[1] }),
      {
        headers: {
          'Access-Control-Allow-Origin': '*',
          'content-type': 'application/application.json',
        },
        withCredentials: false,
      },
    )
    .then(res => {
      axios.defaults.headers.common.token = res.headers.authorization;
      localStorage.setItem('Authorization', res.headers.authorization);
      localStorage.setItem('RefreshToken', res.headers.refreshtoken);
      const auth = [res.headers.authorization, res.headers.refreshtoken];
      axios.get(`${process.env.REACT_APP_API_URL}/connect`, {
        headers: {
          Authorization: res.headers.authorization,
          RefreshToken: res.headers.refreshtoken,
        },
      });
      setTimeout(Retoken, 870000);
      return auth;
    });
});

export const LogoutAsync = createAsyncThunk('logout', data => {
  return axios.delete(`${process.env.REACT_APP_API_URL}/member/logout`, {
    headers: {
      Authorization: data[0],
      RefreshToken: data[1],
    },
  });
});

export const ReLodingLogin = createAsyncThunk('relogin', () => {
  const ac = localStorage.getItem('Authorization');
  const re = localStorage.getItem('RefreshToken');
  const auth = [ac, re];
  setTimeout(Retoken, 1000);
  return auth;
});

import axios from 'axios';
import { createAsyncThunk } from '@reduxjs/toolkit';

export function Retoken() {
  if (
    localStorage.getItem('Authorization') &&
    localStorage.getItem('RefreshToken')
  ) {
    return axios
      .get('/member/reissue', {
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
        console.log(auth);
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
      '/members/login',
      JSON.stringify({ username: data[0], password: data[1] }),
      {
        headers: {
          'Content-Type': 'application/json;',
        },
      },
    )
    .then(res => {
      axios.defaults.headers.common.token = res.headers.authorization;
      localStorage.setItem('Authorization', res.headers.authorization);
      localStorage.setItem('RefreshToken', res.headers.refreshtoken);
      const auth = [res.headers.authorization, res.headers.refreshtoken];
      axios
        .get('/connect', {
          headers: {
            Authorization: res.headers.authorization,
            RefreshToken: res.headers.refreshtoken,
          },
        })
        .then(response => console.log(response));
      setTimeout(Retoken, 870000);
      return auth;
    });
});

export const LogoutAsync = createAsyncThunk('logout', data => {
  return axios.delete('/member/logout', {
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

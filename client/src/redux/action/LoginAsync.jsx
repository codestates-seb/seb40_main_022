import axios from 'axios';
import { createAsyncThunk } from '@reduxjs/toolkit';

export const LoginAsync = createAsyncThunk('login', data => {
  // console.log(data);
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
      // console.log(res);
      axios.defaults.headers.common.token = res.headers.authorization;
      localStorage.setItem('Authorization', res.headers.authorization);
      localStorage.setItem('RefreshToken', res.headers.refreshtoken);
      const auth = [res.headers.authorization, res.headers.refreshtoken];
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
  console.log(auth);
  return auth;
});

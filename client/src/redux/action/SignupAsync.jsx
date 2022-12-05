import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

const SignupAsync = createAsyncThunk(
  '/signup',
  async ({ username, email, password }) => {
    await axios
      .post(
        `${process.env.REACT_APP_API_URL}/members/signup`,
        JSON.stringify({ username, email, password }),
        {
          headers: {
            'Content-Type': 'application/json',
          },
        },
      )
      .then(res => {
        if (res.status === 201) {
          alert('회원가입을 축하드립니다.');
          window.location.href = '/members/login';
        }
      })
      .catch(err => {
        if (err.response.data.status === 409) {
          alert('이미 존재하는 이메일입니다.');
        }
      });
  },
);

export default SignupAsync;

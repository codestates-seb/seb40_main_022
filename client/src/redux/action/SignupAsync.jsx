import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const SignupAsync = createAsyncThunk(
  '/signup',
  async ({ username, email, password }) => {
    await axios.post(
      'http://localhost:3000/signup',
      JSON.stringify({ username, email, password }),
      {
        headers: {
          'Content-Type': 'application/json',
        },
      },
    );
  },
);

export const SignConAsync = createAsyncThunk('/signcon', async () => {
  const data = await axios
    .get('http://localhost:3000/signup', {
      headers: {
        'Content-Type': 'application/json',
      },
    })
    .then(res => {
      return res.data;
    });

  return data;
});

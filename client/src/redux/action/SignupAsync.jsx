import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

const SignupAsync = createAsyncThunk(
  '/signup',
  async ({ username, email, password }) => {
    await axios.post(
      '/members/signup',
      JSON.stringify({ username, email, password }),
      {
        headers: {
          'Content-Type': 'application/json',
        },
      },
    );
  },
);

export default SignupAsync;

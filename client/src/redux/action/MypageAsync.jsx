import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const MypageGet = createAsyncThunk('member/mypage', () => {
  const data = axios
    .get('http://localhost:3000/member/myPage', {
      headers: {
        'Content-Type': 'application/json',
        // Authorization: data[0],
        // RefreshToken: data[1],
      },
    })
    .then(res => {
      return res.data;
    });

  return data;
});

export const MyPostDelete = createAsyncThunk('/member/mypage', ({ id }) => {
  axios.delete(`http://localhost:3000/member/mypage/${id}`);
});

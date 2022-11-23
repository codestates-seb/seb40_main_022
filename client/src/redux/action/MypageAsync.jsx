import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const MypageGet = createAsyncThunk('member/mypage', data => {
  // console.log(data);
  const list = axios
    .get('/members/myPage', {
      headers: {
        Authorization: data[0],
        RefreshToken: data[1],
      },
    })
    .then(res => {
      // console.log(res);
      return res.data;
    });

  return list;
});

export const MyPostDelete = createAsyncThunk('/member/mypage', ({ id }) => {
  axios.delete(`http://localhost:3000/member/mypage/${id}`);
});

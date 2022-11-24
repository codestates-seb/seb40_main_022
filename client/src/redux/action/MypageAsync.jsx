import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const MypageGet = createAsyncThunk('/mypage', data => {
  const list = axios
    .get('/members/myPage', {
      headers: {
        Authorization: data[0],
        RefreshToken: data[1],
      },
    })
    .then(res => {
      return res.data;
    });

  return list;
});

export const MyPostDelete = createAsyncThunk('/member/mypage', data => {
  axios.delete(`/members/myPage/delete`, {
    headers: {
      Authorization: data[0],
      RefreshToken: data[1],
    },
  });
});

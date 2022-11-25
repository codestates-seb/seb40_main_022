import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const MypageEditGet = createAsyncThunk('/mypage/edit', data => {
  const Mylist = axios
    .get('/members/myPage', {
      headers: {
        Authorization: data[0],
        RefreshToken: data[1],
      },
    })
    .then(res => {
      return res.data;
    });

  return Mylist;
});

export const MypagePatch = createAsyncThunk('/member/myPage/edit', data => {
  axios
    .patch('/members/myPage', data, {
      headers: {
        'Content-Type': 'multipart/form-data',
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    })
    .then(res => console.log(res));
  // fetch('/members/myPage', {
  //   method: 'PATCH',
  //   body: data,
  //   headers: {
  //     Authorization: localStorage.getItem('Authorization'),
  //     RefreshToken: localStorage.getItem('RefreshToken'),
  //   },
  // }).then(res => console.log(res));
});

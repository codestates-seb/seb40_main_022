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

export const MypagePatch = createAsyncThunk('/myPage/edit', data => {
  console.log(data);
  for (const pair of data.entries()) {
    console.log(`${pair[0]}, ${pair[1]}`);
  }
  axios
    .post('/members/myPage', data, {
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
  //     'Content-Type': 'multipart/form-data',
  //     Authorization: localStorage.getItem('Authorization'),
  //     RefreshToken: localStorage.getItem('RefreshToken'),
  //   },
  // }).then(res => console.log(res));
});

import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const MypageEditGet = createAsyncThunk('/mypage/edit', () => {
  const Mylist = axios
    .get(`${process.env.REACT_APP_API_URL}/members/myPage`, {
      headers: {
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    })
    .then(res => {
      return res.data;
    });

  return Mylist;
});

export const MypagePost = createAsyncThunk('/myPage/edit', data => {
  axios
    .post(`${process.env.REACT_APP_API_URL}/members/myPage`, data, {
      headers: {
        'Content-Type': 'multipart/form-data',
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    })
    .then(() => {
      window.location.href = '/members/mypage';
    })
    .catch(err => {
      if (err.response.status === 400) {
        alert('이미지를 넣어주세요!');
      }
    });
});

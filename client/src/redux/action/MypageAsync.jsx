import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const MypageGet = createAsyncThunk('/mypage', () => {
  const data = axios
    .get(`${process.env.REACT_APP_API_URL}/members/myPage`, {
      headers: {
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    })
    .then(res => {
      return res.data;
    });

  return data;
});

export const MypageScroll = createAsyncThunk('/mypage', lastPostId => {
  const data = axios
    .get(
      `${process.env.REACT_APP_API_URL}/members/myPage?lastPostId=${lastPostId}`,
      {
        headers: {
          Authorization: localStorage.getItem('Authorization'),
          RefreshToken: localStorage.getItem('RefreshToken'),
        },
      },
    )
    .then(res => {
      return res.data;
    });

  return data;
});

export const MyIdDelete = createAsyncThunk('/member/mypage', () => {
  axios
    .delete(`${process.env.REACT_APP_API_URL}/members/myPage/delete`, {
      headers: {
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    })
    .then(() => {
      localStorage.removeItem('Authorization');
      localStorage.removeItem('RefreshToken');
      localStorage.clear();
    });
});

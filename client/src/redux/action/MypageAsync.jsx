import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const MypageGet = createAsyncThunk('/mypage', () => {
  const list = axios
    .get('/members/myPage', {
      headers: {
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    })
    .then(res => {
      console.log(res);
      return res.data;
    });

  return list;
});

export const MypageScroll = createAsyncThunk('mypage/scroll', lastPostId => {
  console.log(lastPostId);
  const list = axios
    .get(`/members/myPage?lastPostId=${lastPostId}`, {
      headers: {
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    })
    .then(res => {
      console.log(res);
      return res.data;
    });

  return list;
});

export const MyIdDelete = createAsyncThunk('/member/mypage', () => {
  axios
    .delete(`/members/myPage/delete`, {
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

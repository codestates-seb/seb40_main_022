import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const UserProfileGet = createAsyncThunk('/member', id => {
  const data = axios
    .get(`${process.env.REACT_APP_API_URL}/members/${id}`)
    .then(res => {
      return res.data;
    });

  return data;
});

export const UserProfileScroll = createAsyncThunk('/member', lastId => {
  const data = axios
    .get(
      `${process.env.REACT_APP_API_URL}/members/${lastId[0]}?lastPostId=${lastId[1]}`,
    )
    .then(res => {
      return res.data;
    });

  return data;
});

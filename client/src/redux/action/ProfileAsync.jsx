import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const UserProfileGet = createAsyncThunk('/member', id => {
  console.log(id);
  const data = axios.get(`/members/${id}`).then(res => {
    console.log(res);
    return res.data;
  });

  return data;
});

export const UserProfileScroll = createAsyncThunk('/member', lastId => {
  const data = axios
    .get(`/members/${lastId[0]}?lastPostId=${lastId[1]}`)
    .then(res => {
      console.log(res);
      return res.data;
    });

  return data;
});

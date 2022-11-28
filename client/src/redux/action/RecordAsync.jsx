import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const RecordTagAsync = createAsyncThunk('/recordtag', tag => {
  console.log(tag);
  return axios.get(`/sports/detail?bodyPart=${tag}`).then(res => {
    console.log(res);
    return res.data;
  });
});

export const RecordUpAsync = createAsyncThunk('/recordup', data => {
  for (const pair of data.entries()) {
    console.log(`${pair[0]}, ${pair[1]}`);
  }
  axios
    .post('/records', data, {
      headers: {
        'Content-Type': 'multipart/form-data',
        // 'Content-Type': 'application/json',
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    })
    .then(res => console.log(res));
});

export const RecordListAsync = createAsyncThunk('/recordList', () => {
  return axios.get('/records').then(res => console.log(res));
});

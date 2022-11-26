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
  console.log(data);
  console.log();
  return axios.post();
});

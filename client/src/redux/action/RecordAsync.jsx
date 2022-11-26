import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

const RecordTagAsync = createAsyncThunk('/recordtag', tag => {
  return axios.get(`/sports/detail?bodyPar${tag}`).then(res => {
    return res.data;
  });
});

export default RecordTagAsync;

import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

const LankProfileGet = createAsyncThunk('challenge', async () => {
  const lank = await axios.get('/challenge?page=1').then(res => {
    // console.log(res);
    return res.data;
  });

  return lank;
});

export default LankProfileGet;

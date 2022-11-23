import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const QnaAsynclistPost = createAsyncThunk('qnaask', data => {
  if (data[2].length !== 0 && data[3].length !== 0 && data[4].length !== 0) {
    axios.post(
      '/questions',
      JSON.stringify({
        title: data[2],
        content: data[3],
        tag: data[4],
      }),
      {
        headers: {
          'Content-Type': 'application/json;',
          Authorization: data[0],
          RefreshToken: data[1],
        },
      },
    );
  }
});

export const QnaAsynclist = createAsyncThunk('list', async () => {
  const result = await axios.get('/questions?page=1').then(res => {
    return res.data.data;
  });
  return result;
});

export const QnaDetaillistdelete = createAsyncThunk('delete', async data => {
  console.log(data[0]);
  axios.delete(`/questions/${data[0]}`, {
    headers: {
      Authorization: data[1],
      RefreshToken: data[2],
    },
  });
});

export const QnaAsynclistPatch = createAsyncThunk('qnaask', data => {
  if (data[2].length !== 0 && data[3].length !== 0 && data[4].length !== 0) {
    axios.patch(
      `/questions/${data[5]}`,
      JSON.stringify({
        title: data[2],
        content: data[3],
        tag: data[4],
      }),
      {
        headers: {
          'Content-Type': 'application/json;',
          Authorization: data[0],
          RefreshToken: data[1],
        },
      },
    );
  }
});

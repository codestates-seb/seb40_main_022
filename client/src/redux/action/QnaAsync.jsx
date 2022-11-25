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
export const QnaDetailAsync = createAsyncThunk('qnaDetail', ({ data }) => {
  return axios.get(`/questions/${data}`).then(res => {
    return res.data;
  });
});
export const QnaDetailCommentAsync = createAsyncThunk('qnaanswer', data => {
  console.log(data);
  if (data.length !== 0) {
    axios
      .post(
        `/questions/${data[0]}/answers`,
        JSON.stringify({
          content: data[1],
        }),
        {
          headers: {
            'Content-Type': 'application/json;',
            Authorization: localStorage.getItem('Authorization'),
            RefreshToken: localStorage.getItem('RefreshToken'),
          },
        },
      )
      .then(res => console.log(res));
  }
});

export const QnaanswerDetaildelete = createAsyncThunk('delete', async id => {
  console.log(id);
  axios.delete(`/questions/${id[0]}/answers/${id[1]}`, {
    headers: {
      Authorization: localStorage.getItem('Authorization'),
      RefreshToken: localStorage.getItem('RefreshToken'),
    },
  });
});

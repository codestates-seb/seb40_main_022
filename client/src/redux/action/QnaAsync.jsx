import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const QnaAsynclistPost = createAsyncThunk('qnaask', ({ formdata }) => {
  axios
    .post('/questions', formdata, {
      headers: {
        'Content-Type': 'multipart/form-data',
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    })
    .then(res => console.log(res));
});

export const QnaAsynclist = createAsyncThunk('list', async () => {
  const result = await axios.get('/questions?page=1').then(res => {
    console.log(res);
    return res.data.data;
  });
  return result;
});

export const QnaDetaillistdelete = createAsyncThunk('delete', async data => {
  axios.delete(`/questions/${data}`, {
    headers: {
      Authorization: localStorage.getItem('Authorization'),
      RefreshToken: localStorage.getItem('RefreshToken'),
    },
  });
});

export const QnaAsynclistPatch = createAsyncThunk('qnaask', data => {
  axios.post(`/questions/${data[1]}`, data[0], {
    headers: {
      'Content-Type': 'multipart/form-data',
      Authorization: localStorage.getItem('Authorization'),
      RefreshToken: localStorage.getItem('RefreshToken'),
    },
  });
});

export const QnaDetailAsync = createAsyncThunk('qnaDetail', ({ data }) => {
  return axios.get(`/questions/${data}`).then(res => {
    console.log(res);
    return res.data;
  });
});

export const QnaDetailCommentAsync = createAsyncThunk('qnaanswer', data => {
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
  axios
    .delete(`/questions/${id[0]}/answers/${id[1]}`, {
      headers: {
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    })
    .then(res => console.log(res));
});

export const QnaanswerAccept = createAsyncThunk('Accepted', id => {
  axios
    .post(`/questions/${id[0]}/answers/${id[1]}/accept`, id, {
      headers: {
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    })
    .then(res => console.log(res))
    .catch(err => {
      console.log(err);
      if (err.response.status === 400) {
        alert('질문의 작성자가 아닙니다.');
      }
    });
});

export const QnaanswerContentUp = createAsyncThunk(
  'ContentUp',
  (id, content) => {
    console.log(content);
    axios
      .patch(`/questions/${id[0]}/answers/${id[1]}`, content, {
        headers: {
          Authorization: localStorage.getItem('Authorization'),
          RefreshToken: localStorage.getItem('RefreshToken'),
        },
      })
      .then(res => console.log(res));
  },
);

export const QnaSearchreload = createAsyncThunk('search', data => {
  return axios
    .get(`/questions/search?q=${data[0]}&sort=${data[1]}&page=1`)
    .then(res => {
      console.log(res);
      return res.data.data;
    });
});

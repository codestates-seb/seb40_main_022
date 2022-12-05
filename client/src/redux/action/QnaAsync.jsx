import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const QnaAsynclistPost = createAsyncThunk('qnaask', ({ formdata }) => {
  axios.post(`${process.env.REACT_APP_API_URL}/questions`, formdata, {
    headers: {
      'Content-Type': 'multipart/form-data',
      Authorization: localStorage.getItem('Authorization'),
      RefreshToken: localStorage.getItem('RefreshToken'),
    },
  });
});

export const QnaAsynclist = createAsyncThunk('list', async p => {
  const result = await axios
    .get(`${process.env.REACT_APP_API_URL}/questions?page=${p}`)
    .then(res => {
      return res.data;
    });
  return result;
});

export const QnaDetaillistdelete = createAsyncThunk('delete', async data => {
  axios.delete(`${process.env.REACT_APP_API_URL}/questions/${data}`, {
    headers: {
      Authorization: localStorage.getItem('Authorization'),
      RefreshToken: localStorage.getItem('RefreshToken'),
    },
  });
});

export const QnaAsynclistPatch = createAsyncThunk('qnaask', data => {
  axios.post(`${process.env.REACT_APP_API_URL}/questions/${data[1]}`, data[0], {
    headers: {
      'Content-Type': 'multipart/form-data',
      Authorization: localStorage.getItem('Authorization'),
      RefreshToken: localStorage.getItem('RefreshToken'),
    },
  });
});

export const QnaDetailAsync = createAsyncThunk('qnaDetail', ({ data }) => {
  axios.get(`${process.env.REACT_APP_API_URL}/questions/${data}`).then(res => {
    return res.data;
  });
});

export const QnaDetailCommentAsync = createAsyncThunk('qnaanswer', data => {
  if (data.length !== 0) {
    axios.post(
      `${process.env.REACT_APP_API_URL}/questions/${data[0]}/answers`,
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
    );
  }
});

export const QnaanswerDetaildelete = createAsyncThunk('delete', async id => {
  axios.delete(
    `${process.env.REACT_APP_API_URL}/questions/${id[0]}/answers/${id[1]}`,
    {
      headers: {
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    },
  );
});

export const QnaanswerAccept = createAsyncThunk('Accepted', id => {
  axios
    .post(
      `${process.env.REACT_APP_API_URL}/questions/${id[0]}/answers/${id[1]}/accept`,
      id,
      {
        headers: {
          Authorization: localStorage.getItem('Authorization'),
          RefreshToken: localStorage.getItem('RefreshToken'),
        },
      },
    )
    .catch(err => {
      if (err.response.status === 400) {
        alert('질문의 작성자가 아닙니다.');
      }
    });
});

export const QnaanswerContentUp = createAsyncThunk('ContentUp', id => {
  axios.patch(
    `${process.env.REACT_APP_API_URL}/questions/${id[0]}/answers/${id[1]}`,
    JSON.stringify({ content: id[2] }),
    {
      headers: {
        'Content-Type': 'application/json;',
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    },
  );
});

export const QnaSearchreload = createAsyncThunk('search', data => {
  return axios
    .get(
      `${process.env.REACT_APP_API_URL}/questions/search?q=${data[0]}&sort=${data[1]}&page=${data[2]}`,
    )
    .then(res => {
      return res.data.data;
    });
});

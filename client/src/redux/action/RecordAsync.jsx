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
  console.log(
    JSON.stringify({
      start: data[0],
      startTime: data[1],
      endTime: data[2],
      startImagePath: data[3],
      endImagePath: data[4],
      sports: data[5],
    }),
  );
  axios
    .post(
      '/records',
      JSON.stringify({
        start: data[0],
        startTime: data[1],
        endTime: data[2],
        startImagePath: data[3],
        endImagePath: data[4],
        sports: data[5],
      }),
      {
        headers: {
          'Content-Type': 'application/json;',
          Authorization: localStorage.getItem('Authorization'),
          RefreshToken: localStorage.getItem('RefreshToken'),
        },
      },
    )
    .then(res => {
      console.log(res);
      return res;
    });
});

export const RecordListAsync = createAsyncThunk('/recordList', month => {
  return axios
    .get(`/records?month=${month}`, {
      headers: {
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    })
    .then(res => {
      console.log(res);
      return res.data;
    });
});

export const RecordImgUp = createAsyncThunk('Imgup', formdata => {
  axios.post('/records/pictures', formdata).then(res => console.log(res));
});

export const Recorddelete = createAsyncThunk('/record/delete', id => {
  axios.delete(`/records/${id}`).then(res => console.log(res));
});

export const RecordListGet = createAsyncThunk('/record/idlist', id => {
  console.log(id);
  return axios
    .get(`/records/${id}`, {
      headers: {
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    })
    .then(res => {
      console.log(res);
      return res.data;
    });
});

export const RecordListDelete = createAsyncThunk('/record/delete', id => {
  axios
    .delete(`/records/${id}`, {
      headers: {
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    })
    .then(res => console.log(res));
});

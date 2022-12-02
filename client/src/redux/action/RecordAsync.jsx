import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const RecordTagAsync = createAsyncThunk('/recordtag', tag => {
  return axios
    .get(`${process.env.REACT_APP_API_URL}/sports/detail?bodyPart=${tag}`)
    .then(res => {
      return res.data;
    });
});

export const RecordUpAsync = createAsyncThunk('/recordup', data => {
  axios
    .post(
      `${process.env.REACT_APP_API_URL}/records`,
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
      return res;
    });
});

export const RecordListAsync = createAsyncThunk('/recordList', month => {
  return axios
    .get(`${process.env.REACT_APP_API_URL}/records?month=${month}`, {
      headers: {
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    })
    .then(res => {
      return res.data;
    });
});

export const RecordImgUp = createAsyncThunk('Imgup', formdata => {
  return axios
    .post(`${process.env.REACT_APP_API_URL}/records/pictures`, formdata)
    .then(res => {
      return res.data;
    });
});

export const Recorddelete = createAsyncThunk('/record/delete', id => {
  axios.delete(`${process.env.REACT_APP_API_URL}/records/${id}`);
});

export const RecordListGet = createAsyncThunk('/record/idlist', id => {
  return axios
    .get(`${process.env.REACT_APP_API_URL}/records/${id}`, {
      headers: {
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    })
    .then(res => {
      return res.data;
    });
});

export const RecordListDelete = createAsyncThunk('/record/delete', id => {
  axios.delete(`${process.env.REACT_APP_API_URL}/records/${id}`, {
    headers: {
      Authorization: localStorage.getItem('Authorization'),
      RefreshToken: localStorage.getItem('RefreshToken'),
    },
  });
});

export const ChallengeDelete = createAsyncThunk('/challenge/delete', id => {
  axios.delete(`${process.env.REACT_APP_API_URL}/challenge/${id}/suspend`, {
    headers: {
      Authorization: localStorage.getItem('Authorization'),
      RefreshToken: localStorage.getItem('RefreshToken'),
    },
  });
});

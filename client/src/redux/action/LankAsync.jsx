import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const LankProfileGet = createAsyncThunk('challenge', async () => {
  const lank = await axios.get('/challenge?page=1').then(res => {
    // console.log(res);
    return res.data;
  });

  return lank;
});

export const LankChallenge = createAsyncThunk('Lankchallenge', id => {
  axios.post(`/challenge?counterpartId=${id}`, id, {
    headers: {
      Authorization: localStorage.getItem('Authorization'),
      RefreshToken: localStorage.getItem('RefreshToken'),
    },
  });
});

export const Notifications = createAsyncThunk('notifications', () => {
  return axios
    .get('notifications', {
      headers: {
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    })
    .then(res => {
      return res.data;
    });
});

export const ChallengeAccept = createAsyncThunk('accpet', id => {
  // console.log(id);
  return axios.post(`/challenge/${id}/accept`).then(res => console.log(res));
});

export const EnemyUserInfo = createAsyncThunk('userInfo', () => {
  // console.log(id);
  axios
    .get(`/challenge/7`, {
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

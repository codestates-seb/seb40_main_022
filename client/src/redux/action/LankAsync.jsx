import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const LankProfileGet = createAsyncThunk('challenge', async () => {
  const lank = await axios.get('/challenge?page=1').then(res => {
    console.log(res);
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
  console.log(id);
  axios
    .post(`${id}/accept`, id, {
      headers: {
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    })
    .then(res => console.log(res));
});

export const ChallengeDelete = createAsyncThunk('delete', id => {
  console.log(id);
  axios
    .delete(id, {
      headers: {
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    })
    .then(res => console.log(res));
});

export const EnemyUserInfo = createAsyncThunk('userInfo', () => {
  return axios
    .get(`/challenge/1`, {
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

export const Notificationsallam = createAsyncThunk('allam', id => {
  return axios
    .patch(`/notifications/${id}`, id, {
      headers: {
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    })
    .then(res => console.log(res));
});

export const ChallengeSearch = createAsyncThunk('/challenge/search', url => {
  return axios
    .get(`/challenge?${url}&page=1`, {
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

import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const LankProfileGet = createAsyncThunk('challenge', async page => {
  const lank = await axios
    .get(`${process.env.REACT_APP_API_URL}/challenge?page=${page}`)
    .then(res => {
      return res.data;
    });

  return lank;
});

export const LankChallenge = createAsyncThunk('Lankchallenge', id => {
  axios.post(
    `${process.env.REACT_APP_API_URL}/challenge?counterpartId=${id}`,
    id,
    {
      headers: {
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    },
  );
});

export const Notifications = createAsyncThunk('notifications', () => {
  return axios
    .get(`${process.env.REACT_APP_API_URL}/notifications`, {
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
  axios.post(`${process.env.REACT_APP_API_URL}${id}/accept`, id, {
    headers: {
      Authorization: localStorage.getItem('Authorization'),
      RefreshToken: localStorage.getItem('RefreshToken'),
    },
  });
});

export const ChallengeDelete = createAsyncThunk('delete', id => {
  axios.delete(process.env.REACT_APP_API_URL + id, {
    headers: {
      Authorization: localStorage.getItem('Authorization'),
      RefreshToken: localStorage.getItem('RefreshToken'),
    },
  });
});

export const EnemyUserInfo = createAsyncThunk('userInfo', () => {
  return axios
    .get(`${process.env.REACT_APP_API_URL}/challenge/1`, {
      headers: {
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    })
    .then(res => {
      return res.data;
    });
});

export const Notificationsallam = createAsyncThunk('allam', id => {
  axios.patch(`${process.env.REACT_APP_API_URL}/notifications/${id}`, id, {
    headers: {
      Authorization: localStorage.getItem('Authorization'),
      RefreshToken: localStorage.getItem('RefreshToken'),
    },
  });
});

export const ChallengeSearch = createAsyncThunk('/challenge/search', data => {
  return axios
    .get(
      `${process.env.REACT_APP_API_URL}/challenge?${data[0]}&page=${data[1]}`,
      {
        headers: {
          Authorization: localStorage.getItem('Authorization'),
          RefreshToken: localStorage.getItem('RefreshToken'),
        },
      },
    )
    .then(res => {
      return res.data;
    });
});

import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const MypageEditGet = createAsyncThunk('/mypage/edit', data => {
  const Mylist = axios
    .get('/members/myPage', {
      headers: {
        'Content-Type': 'application/json',
        Authorization: data[0],
        RefreshToken: data[1],
      },
    })
    .then(res => {
      return res.data;
    });

  return Mylist;
});

export const MypagePatch = createAsyncThunk(
  '/member/myPage/edit',
  ({
    username,
    password,
    job,
    address,
    sex,
    select,
    age,
    height,
    weight,
    kilogram,
    period,
    profileImage,
    data,
  }) => {
    if (username.length !== 0 && sex.length !== 0 && select.length !== 0) {
      axios.patch(
        '/members/myPage',
        JSON.stringify({
          username,
          password,
          job,
          address,
          sex,
          split: select,
          age,
          height,
          weight,
          kilogram,
          period,
          profileImage,
        }),
        {
          headers: {
            'Content-Type': 'application/json',
            Authorization: data[0],
            RefreshToken: data[1],
          },
        },
      );
    }
  },
);

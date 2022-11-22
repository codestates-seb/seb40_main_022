import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const MypageEditGet = createAsyncThunk('/mypage/edit', () => {
  const data = axios
    .get('http://localhost:3000/member/myPage', {
      headers: {
        'Content-Type': 'application/json',
        // Authorization: data[0],
        // RefreshToken: data[1],
      },
    })
    .then(res => {
      return res.data;
    });

  return data;
});

export const MypagePatch = createAsyncThunk(
  '/member/myPage/edit',
  ({
    username,
    password,
    job,
    address,
    sex,
    split,
    age,
    height,
    weight,
    kilogram,
    period,
    profileImage,
  }) => {
    if (username.length !== 0 && sex.length !== 0 && split.length !== 0) {
      axios.patch(
        'http://localhost:3000/member/myPage',
        JSON.stringify({
          username,
          password,
          job,
          address,
          sex,
          split,
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
            // Authorization: data[0],
            // RefreshToken: data[1],
          },
        },
      );
    }
  },
);

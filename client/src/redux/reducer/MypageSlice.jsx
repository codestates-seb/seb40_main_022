import { createSlice } from '@reduxjs/toolkit';
import { MypageGet } from '../action/MypageAsync';

const MypageSlice = createSlice({
  name: 'mypage',
  initialState: {
    member: [],
    dailyPosts: [],
    activity: [],
    postCounts: 0,
  },
  reducers: {},
  extraReducers: {
    [MypageGet.fulfilled]: (state, action) => {
      state.dailyPosts = action.payload.dailyPosts;
      state.activity = action.payload.activity;
      state.member = action.payload.member;
      state.postCounts = action.payload.postCounts;
    },
  },
});

export const mypageActions = MypageSlice.actions;

export default MypageSlice.reducer;

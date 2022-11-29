import { createSlice } from '@reduxjs/toolkit';
import { MypageGet, UserProfileGet } from '../action/MypageAsync';

const MypageSlice = createSlice({
  name: 'mypage',
  initialState: {
    member: [],
    dailyPosts: [],
    activity: [],
    postCounts: 0,
    // data: [],
  },
  reducers: {},
  extraReducers: {
    [MypageGet.fulfilled]: (state, action) => {
      console.log(state, action);
      state.dailyPosts = action.payload.dailyPosts;
      state.activity = action.payload.activity;
      state.member = action.payload.member;
      state.postCounts = action.payload.postCounts;
    },
    [UserProfileGet.fulfilled]: (state, action) => {
      console.log(state, action);
      state.dailyPosts = action.payload.dailyPosts;
      state.activity = action.payload.activity;
      state.member = action.payload.member;
      state.postCounts = action.payload.postCounts;
    },
    // [MypageGet.fulfilled]: (state, action) => {
    //   console.log(state, action);
    //   state.data = action.payload;
    // },
  },
});

export const mypageActions = MypageSlice.actions;

export default MypageSlice.reducer;

import { createSlice } from '@reduxjs/toolkit';
import { MypageGet, MyIdDelete } from '../action/MypageAsync';

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
      console.log(state, action);
      state.dailyPosts = action.payload.dailyPosts;
      state.activity = action.payload.activity;
      state.member = action.payload.member;
      state.postCounts = action.payload.postCounts;
    },
    [MyIdDelete.fulfilled]: state => {
      state.dailyPosts = [];
      state.activity = [];
      state.userName = '';
    },
  },
});

export const mypageActions = MypageSlice.actions;

export default MypageSlice.reducer;

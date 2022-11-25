import { createSlice } from '@reduxjs/toolkit';
import { MypageGet, MyPostDelete } from '../action/MypageAsync';

const MypageSlice = createSlice({
  name: 'mypage',
  initialState: {
    member: [],
    dailyPosts: [],
    activity: [],
  },
  reducers: {},
  extraReducers: {
    [MypageGet.fulfilled]: (state, action) => {
      console.log(state, action);
      state.dailyPosts = action.payload.dailyPosts;
      state.activity = action.payload.activity;
      state.member = action.payload.member;
    },
    [MyPostDelete.fulfilled]: state => {
      state.dailyPosts = [];
      state.activity = [];
      state.userName = '';
    },
  },
});

export const mypageActions = MypageSlice.actions;

export default MypageSlice.reducer;

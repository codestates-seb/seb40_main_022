import { createSlice } from '@reduxjs/toolkit';
import { MypageGet } from '../action/MypageAsync';

const MypageSlice = createSlice({
  name: 'mypage',
  initialState: {
    userName: '',
    dailyPosts: [],
    activity: [],
  },
  reducers: {},
  extraReducers: {
    [MypageGet.fulfilled]: (state, action) => {
      console.log(state, action);
      state.dailyPosts = action.payload.dailyPosts;
      state.activity = action.payload.activity;
      state.userName = action.payload.userName;
    },
  },
});

export const mypageActions = MypageSlice.actions;

export default MypageSlice.reducer;

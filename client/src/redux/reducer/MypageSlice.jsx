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
<<<<<<< HEAD
      console.log(state, action);
      state.dailyPosts = action.payload.dailyPosts;
      state.activity = action.payload.activity;
      state.userName = action.payload.userName;
=======
      state.data = action.payload;
    },
    [MyPostDelete.fulfilled]: (state, action) => {
      state.comment = action.payload;
>>>>>>> 8768c064e4f6db6de6e2e5fb3bc3a6c181196128
    },
  },
});

export const mypageActions = MypageSlice.actions;

export default MypageSlice.reducer;

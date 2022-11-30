import { createSlice } from '@reduxjs/toolkit';
import { UserProfileGet } from '../action/ProfileAsync';

const ProfileSlice = createSlice({
  name: 'profile',
  initialState: {
    member: [],
    dailyPosts: [],
    activity: [],
    postCounts: 0,
    // data: [],
  },
  reducers: {},
  extraReducers: {
    [UserProfileGet.fulfilled]: (state, action) => {
      console.log(state, action);
      state.dailyPosts = action.payload.dailyPosts;
      state.activity = action.payload.activity;
      state.member = action.payload.member;
      state.postCounts = action.payload.postCounts;
    },
  },
});

export const profileActions = ProfileSlice.actions;

export default ProfileSlice.reducer;

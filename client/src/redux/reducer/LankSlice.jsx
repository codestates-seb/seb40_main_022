import { createSlice } from '@reduxjs/toolkit';
import {
  LankProfileGet,
  Notifications,
  EnemyUserInfo,
  ChallengeSearch,
} from '../action/LankAsync';

const LankSlice = createSlice({
  name: 'lank',
  initialState: {
    member: [],
    data: [],
    userInfo: [],
    items: [],
    url: '',
  },
  reducers: {
    url: (state, action) => {
      state.url = action.payload;
    },
  },
  extraReducers: {
    [LankProfileGet.fulfilled]: (state, action) => {
      state.member = action.payload;
    },
    [Notifications.fulfilled]: (state, action) => {
      state.data = action.payload;
    },
    [EnemyUserInfo.fulfilled]: (state, action) => {
      state.userInfo = action.payload;
    },
    [ChallengeSearch.fulfilled]: (state, action) => {
      state.items = action.payload;
    },
  },
});

export const lankActions = LankSlice.actions;
export const { url, urlinitial } = LankSlice.actions;

export default LankSlice.reducer;

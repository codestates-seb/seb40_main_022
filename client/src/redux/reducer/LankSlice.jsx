import { createSlice } from '@reduxjs/toolkit';
import {
  LankProfileGet,
  Notifications,
  EnemyUserInfo,
} from '../action/LankAsync';

const LankSlice = createSlice({
  name: 'lank',
  initialState: {
    member: [],
    data: [],
    userInfo: [],
  },
  reducers: {
    SET_LANK: (state, action) => {
      state.data = action.payload;
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
  },
});

export const lankActions = LankSlice.actions;

export default LankSlice.reducer;

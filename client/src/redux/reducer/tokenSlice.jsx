import { createSlice } from '@reduxjs/toolkit';
import { LoginAsync, LogoutAsync } from '../action/LoginAsync';

export const TOKEN_TIME_OUT = 600 * 1000;

export const tokenSlice = createSlice({
  name: 'authToken',
  initialState: {
    isLogin: false,
    authenticated: false,
    accessToken: null,
    expireTime: null,
    token: null,
  },
  reducers: {},
  extraReducers: {
    [LoginAsync.fulfilled]: (state, action) => {
      const accessToken = action.payload[0];
      const refresh = action.payload[1];
      state.authenticated = true;
      state.isLogin = true;
      state.accessToken = accessToken;
      state.token = refresh;
    },
    [LoginAsync.rejected]: state => {
      state.isLogin = false;
      state.accessToken = null;
    },
    [LogoutAsync.fulfilled]: state => {
      console.log(state);
      state.authenticated = false;
      state.isLogin = false;
      state.accessToken = null;
      state.token = null;
    },
  },
});

export const { SET_TOKEN, DELETE_TOKEN } = tokenSlice.actions;

export default tokenSlice.reducer;

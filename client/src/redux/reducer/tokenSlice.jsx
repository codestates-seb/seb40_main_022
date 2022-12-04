import { createSlice } from '@reduxjs/toolkit';
import {
  LoginAsync,
  LogoutAsync,
  ReLodingLogin,
  Retoken,
} from '../action/LoginAsync';
import { MyIdDelete } from '../action/MypageAsync';

export const tokenSlice = createSlice({
  name: 'authToken',
  initialState: {
    isLogin: false,
    authenticated: false,
    accessToken: null,
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
      state.authenticated = false;
      state.isLogin = false;
      state.accessToken = null;
      state.token = null;
      localStorage.removeItem('Authorization');
      localStorage.removeItem('RefreshToken');
      window.localStorage.clear();
    },
    [ReLodingLogin.fulfilled]: (state, action) => {
      const accessToken = action.payload[0];
      const refresh = action.payload[1];
      if (
        accessToken !== undefined &&
        refresh !== undefined &&
        accessToken !== null &&
        refresh !== null
      ) {
        state.authenticated = true;
        state.isLogin = true;
        state.accessToken = accessToken;
        state.token = refresh;
      }
    },
    [Retoken.fulfilled]: (state, action) => {
      const accessToken = action.payload[0];
      const refresh = action.payload[1];
      state.authenticated = true;
      state.isLogin = true;
      state.accessToken = accessToken;
      state.token = refresh;
    },
    [MyIdDelete.fulfilled]: state => {
      state.isLogin = false;
      state.authenticated = false;
      state.accessToken = null;
      state.token = null;
    },
  },
});

export const { SET_TOKEN, DELETE_TOKEN } = tokenSlice.actions;

export default tokenSlice.reducer;

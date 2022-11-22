import { createSlice } from '@reduxjs/toolkit';

const SignupSlice = createSlice({
  name: 'signup',
  initialState: {
    data: [],
  },
  reducers: {},
  extraReducers: {},
});

export const signupActions = SignupSlice.actions;

export default SignupSlice.reducer;

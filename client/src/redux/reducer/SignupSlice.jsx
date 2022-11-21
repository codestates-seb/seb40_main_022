import { createSlice } from '@reduxjs/toolkit';
import { SignConAsync } from '../action/SignupAsync';

const SignupSlice = createSlice({
  name: 'signup',
  initialState: {
    data: [],
  },
  reducers: {},
  extraReducers: {
    [SignConAsync.fulfilled]: (state, action) => {
      state.data = action.payload;
    },
  },
});

export const signupActions = SignupSlice.actions;

export default SignupSlice.reducer;

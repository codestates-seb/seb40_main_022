import { createSlice } from '@reduxjs/toolkit';

const RecordSlice = createSlice({
  name: 'record',
  initialState: {
    data: [],
  },
  reducers: {},
  extraReducers: {},
});

export const signupActions = RecordSlice.actions;

export default RecordSlice.reducer;

import { createSlice } from '@reduxjs/toolkit';
import { RecordTagAsync } from '../action/RecordAsync';

const RecordSlice = createSlice({
  name: 'record',
  initialState: {
    data: [],
  },
  reducers: {},
  extraReducers: {
    [RecordTagAsync.fulfilled]: (state, action) => {
      state.data = action.payload;
    },
  },
});

export const signupActions = RecordSlice.actions;

export default RecordSlice.reducer;

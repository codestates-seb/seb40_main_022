import { createSlice } from '@reduxjs/toolkit';
import { RecordTagAsync, RecordListAsync } from '../action/RecordAsync';

const RecordSlice = createSlice({
  name: 'record',
  initialState: {
    data: [],
    List: [],
  },
  reducers: {},
  extraReducers: {
    [RecordTagAsync.fulfilled]: (state, action) => {
      state.data = action.payload;
    },
    [RecordListAsync.fulfilled]: (state, action) => {
      console.log(action);
      state.List = action.payload;
    },
  },
});

export const signupActions = RecordSlice.actions;

export default RecordSlice.reducer;

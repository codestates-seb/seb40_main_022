import { createSlice } from '@reduxjs/toolkit';
import {
  RecordTagAsync,
  RecordListAsync,
  RecordListGet,
} from '../action/RecordAsync';

const RecordSlice = createSlice({
  name: 'record',
  initialState: {
    data: [],
    List: [],
    GetList: [],
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
    [RecordListGet.fulfilled]: (state, action) => {
      console.log(action);
      state.GetList = action.payload;
    },
  },
});

export const signupActions = RecordSlice.actions;

export default RecordSlice.reducer;

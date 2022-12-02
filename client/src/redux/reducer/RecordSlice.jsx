import { createSlice } from '@reduxjs/toolkit';
import {
  RecordTagAsync,
  RecordListAsync,
  RecordListGet,
  RecordImgUp,
} from '../action/RecordAsync';

const RecordSlice = createSlice({
  name: 'record',
  initialState: {
    data: [],
    List: [],
    GetList: [],
    ChallengeId: null,
    Start: '',
    End: '',
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
    [RecordImgUp.fulfilled]: (state, action) => {
      console.log(action);
      if (action.payload.point === 'start') {
        state.Start = action.payload.imagePath;
      } else {
        state.End = action.payload.imagePath;
      }
    },
  },
});

export const signupActions = RecordSlice.actions;

export default RecordSlice.reducer;

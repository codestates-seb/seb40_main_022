import { createSlice } from '@reduxjs/toolkit';
import { QnaAsynclist, QnaDetailAsync } from '../action/QnaAsync';

const QnaSlice = createSlice({
  name: 'qna',
  initialState: {
    list: [],
    answers: [],
  },
  reducers: {},
  extraReducers: {
    [QnaAsynclist.fulfilled]: (state, action) => {
      state.list = action.payload;
    },
    [QnaDetailAsync.fulfilled]: (state, action) => {
      console.log(action);
      state.answers = action.payload;
    },
  },
});

export const QnaActions = QnaSlice.actions;

export default QnaSlice.reducer;

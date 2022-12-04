import { createSlice } from '@reduxjs/toolkit';
import {
  QnaAsynclist,
  QnaDetailAsync,
  QnaSearchreload,
} from '../action/QnaAsync';

const QnaSlice = createSlice({
  name: 'qna',
  initialState: {
    list: [],
    detail: [],
    answers: [],
    search: [],
    pageInfo: [],
  },
  reducers: {},
  extraReducers: {
    [QnaAsynclist.fulfilled]: (state, action) => {
      state.list = action.payload.data;
      state.pageInfo = action.payload.pageInfo;
    },
    [QnaDetailAsync.fulfilled]: (state, action) => {
      state.answers = action.payload;
    },
    [QnaSearchreload.fulfilled]: (state, action) => {
      state.search = action.payload;
    },
    [QnaDetailAsync.fulfilled]: (state, action) => {
      state.detail = action.payload;
    },
  },
});

export const QnaActions = QnaSlice.actions;

export default QnaSlice.reducer;

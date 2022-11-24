import { createSlice } from '@reduxjs/toolkit';
import { QnaAsynclist } from '../action/QnaAsync';

const QnaSlice = createSlice({
  name: 'qna',
  initialState: {
    list: [],
  },
  reducers: {},
  extraReducers: {
    [QnaAsynclist.fulfilled]: (state, action) => {
      state.list = action.payload;
    },
  },
});

export const QnaActions = QnaSlice.actions;

export default QnaSlice.reducer;

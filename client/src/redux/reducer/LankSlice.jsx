import { createSlice } from '@reduxjs/toolkit';
import LankProfileGet from '../action/LankAsync';

const LankSlice = createSlice({
  name: 'lank',
  initialState: {
    member: [],
  },
  reducers: {
    SET_LANK: (state, action) => {
      state.data = action.payload;
    },
  },
  extraReducers: {
    [LankProfileGet.fulfilled]: (state, action) => {
      // console.log(state, action);
      state.member = action.payload;
    },
  },
});

export const lankActions = LankSlice.actions;

export default LankSlice.reducer;

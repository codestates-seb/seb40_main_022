import { createSlice } from '@reduxjs/toolkit';
import { MypageEditGet, MypagePost } from '../action/MypageEditAsync';

const MypageEditSlice = createSlice({
  name: 'mypageedit',
  initialState: {
    data: [],
  },
  reducers: {},
  extraReducers: {
    [MypageEditGet.fulfilled]: (state, action) => {
      state.data = action.payload;
    },
    [MypagePost.fulfilled]: (state, action) => {
      state.comment = action.payload;
    },
  },
});

export const mypageeditActions = MypageEditSlice.actions;

export default MypageEditSlice.reducer;

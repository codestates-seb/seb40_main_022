import { createSlice } from '@reduxjs/toolkit';
import { MypageGet, MyPostDelete } from '../action/MypageAsync';

const MypageSlice = createSlice({
  name: 'mypage',
  initialState: {
    data: [],
  },
  reducers: {},
  extraReducers: {
    [MypageGet.fulfilled]: (state, action) => {
      console.log(state, action);
      state.data = action.payload;
    },
    [MyPostDelete.fulfilled]: (state, action) => {
      state.comment = action.payload;
    },
  },
});

export const mypageActions = MypageSlice.actions;

export default MypageSlice.reducer;

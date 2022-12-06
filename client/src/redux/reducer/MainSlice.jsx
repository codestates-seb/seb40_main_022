import { createSlice } from '@reduxjs/toolkit';
import { asyncPost, asyncPostCmt, MainSearchAsync } from '../action/MainAsync';

const MainSlice = createSlice({
  name: 'content',
  initialState: {
    data: [],
    comment: [],
    search: '',
    searchList: [],
    searchload: false,
  },
  reducers: {
    searchchange: (state, action) => {
      state.search = action.payload;
      state.searchload = true;
    },
    searchclose: state => {
      state.searchload = false;
    },
  },
  extraReducers: {
    [asyncPost.fulfilled]: (state, action) => {
      state.data = action.payload;
    },
    [asyncPostCmt.fulfilled]: (state, action) => {
      state.comment = action.payload;
    },
    [MainSearchAsync.fulfilled]: (state, action) => {
      state.searchList = action.payload;
    },
  },
});

// export const DetailActions = MainSlice.actions;
export const { searchchange, searchclose } = MainSlice.actions;
export default MainSlice.reducer;

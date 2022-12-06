import { createSlice } from '@reduxjs/toolkit';
import { asyncPost, asyncPostCmt, MainSearchAsync } from '../action/MainAsync';

const MainSlice = createSlice({
  name: 'content',
  initialState: {
    data: [],
    comment: [],
    search: '',
    searchList: [],
  },
  reducers: {
    searchchange: (state, action) => {
      state.search = action.payload;
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
      console.log(action);
      state.searchList = action.payload;
    },
  },
});

// export const DetailActions = MainSlice.actions;
export const { searchchange } = MainSlice.actions;
export default MainSlice.reducer;

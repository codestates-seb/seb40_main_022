import { createSlice } from '@reduxjs/toolkit';
import { asyncPost, asyncPostCmt } from '../action/MainAsync';

const MainSlice = createSlice({
  name: 'content',
  initialState: {
    data: [],
    comment: [],
  },
  reducers: {},
  extraReducers: {
    [asyncPost.fulfilled]: (state, action) => {
      state.data = action.payload;
    },
    [asyncPostCmt.fulfilled]: (state, action) => {
      state.comment = action.payload;
    },
  },
});

// export const DetailActions = MainSlice.actions;

export default MainSlice.reducer;

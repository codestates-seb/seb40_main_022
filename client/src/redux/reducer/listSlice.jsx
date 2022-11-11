import { createSlice } from '@reduxjs/toolkit';
import asncloginfetch from '../action/asncloginfetch';

const listSlice = createSlice({
  name: 'content',
  initialState: {},
  reducers: {},
  extraReducers: {
    [asncloginfetch.fulfilled]: (state, action) => {
      console.log(action, state);
    },
  },
});

export const DetailActions = listSlice.actions;

export default listSlice.reducer;

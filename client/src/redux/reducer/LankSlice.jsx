import { createSlice } from '@reduxjs/toolkit';

const LankSlice = createSlice({
  name: 'lank',
  initialState: {
    list: ['분할', '키', '몸무게', '경력', '점수'],
  },
  reducers: {
    SET_LANK: (state, action) => {
      console.log(state, action);
      state.list = action.payload;
    },
  },
});

export const lankActions = LankSlice.actions;

export default LankSlice.reducer;

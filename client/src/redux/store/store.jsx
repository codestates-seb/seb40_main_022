import { configureStore } from '@reduxjs/toolkit';
import LankSlice from '../reducer/LankSlice';
import SignupSlice from '../reducer/SignupSlice';
import tokenReducer from '../reducer/tokenSlice';
import MainSlice from '../reducer/MainSlice';
// Slice Append Here
const reducer = {
  signup: SignupSlice,
  authToken: tokenReducer,
  dailypost: MainSlice,
  lank: LankSlice,
};

const store = configureStore({
  reducer,
});

export default store;

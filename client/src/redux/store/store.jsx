import { configureStore } from '@reduxjs/toolkit';
import LankSlice from '../reducer/LankSlice';
import SignupSlice from '../reducer/SignupSlice';
import tokenReducer from '../reducer/tokenSlice';
// Slice Append Here
const reducer = {
  signup: SignupSlice,
  authToken: tokenReducer,
  lank: LankSlice,
};

const store = configureStore({
  reducer,
});

export default store;

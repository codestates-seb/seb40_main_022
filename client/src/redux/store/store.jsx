import { configureStore } from '@reduxjs/toolkit';
import SignupSlice from '../reducer/SignupSlice';
import tokenReducer from '../reducer/tokenSlice';
// Slice Append Here
const reducer = {
  signup: SignupSlice,
  authToken: tokenReducer,
};

const store = configureStore({
  reducer,
});

export default store;

import { configureStore } from '@reduxjs/toolkit';
import SignupSlice from '../reducer/SignupSlice';

// Slice Append Here
const reducer = {
  signup: SignupSlice,
};

const store = configureStore({
  reducer,
});

export default store;

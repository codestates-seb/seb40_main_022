import { configureStore } from '@reduxjs/toolkit';
import LankSlice from '../reducer/LankSlice';
import SignupSlice from '../reducer/SignupSlice';
import tokenReducer from '../reducer/tokenSlice';
import MainSlice from '../reducer/MainSlice';
import QnaSlice from '../reducer/QnaSlice';
import MypageSlice from '../reducer/MypageSlice';
import MypageEditSlice from '../reducer/MypageEditSlice';

// Slice Append Here
const reducer = {
  signup: SignupSlice,
  authToken: tokenReducer,
  dailypost: MainSlice,
  lank: LankSlice,
  qnalist: QnaSlice,
  mypage: MypageSlice,
  mypageedit: MypageEditSlice,
};

const store = configureStore({
  reducer,
});

export default store;

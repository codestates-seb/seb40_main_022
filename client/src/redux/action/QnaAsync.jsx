import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const QnaAsynclistPost = createAsyncThunk(
  'qnaask',
  ({ title, tag, content }) => {
    if (tag.length !== 0 && title.length !== 0 && content.length !== 0) {
      axios.post(
        'http://localhost:3000/qnaask',
        JSON.stringify({ title, content, tag }),
        {
          headers: {
            'Content-Type': 'application/json',
          },
        },
      );
    }
  },
);

export const QnaAsynclist = createAsyncThunk('list', async () => {
  const data = await axios
    .get('http://localhost:3000/data', {
      headers: {
        'Content-Type': 'application/json',
      },
    })
    .then(res => {
      return res.data;
    });

  return data;
});

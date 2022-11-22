import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const asyncPostUp = createAsyncThunk(
  'post/up',
  ({ files, content, tags }) => {
    if (files.length !== 0 && content.length !== 0 && tags.length !== 0) {
      axios.post(
        'http://localhost:3000/dailypost',
        JSON.stringify({ picture: files, content, tags }),
        {
          headers: {
            'Content-Type': 'application/json',
          },
        },
      );
    }
  },
);

export const asyncPost = createAsyncThunk('post', async () => {
  const data = await axios
    .get('http://localhost:3000/dailypost', {
      headers: {
        'Content-Type': 'application/json',
      },
    })
    .then(res => {
      return res.data;
    });

  return data;
});

export const asyncPostDel = createAsyncThunk('post/del', ({ id }) => {
  axios.delete(`http://localhost:3000/dailypost/${id}`);
});

export const asyncPostUpdate = createAsyncThunk(
  'list/update',
  ({ files, content, tags, id }) => {
    if (files.length !== 0 && content.length !== 0 && tags.length !== 0) {
      axios.put(
        `http://localhost:3000/dailypost/${Number(id.id)}`,
        JSON.stringify({ picture: files, content, tags }),
        {
          headers: {
            'Content-Type': 'application/json',
          },
        },
      );
    }
  },
);

// 몰루
export const asyncPostCmt = createAsyncThunk('comment', () => {
  const data = axios
    .get('http://localhost:3000/comment', {
      headers: {
        'Content-Type': 'application/json',
      },
    })
    .then(res => {
      return res.data;
    });

  return data;
});

// 몰루
export const asyncPostCmtUp = createAsyncThunk('post/up', data => {
  axios.post(
    'http://localhost:3000/comment',
    JSON.stringify({
      contentId: data[0],
      contentImg: data[1],
      contentName: data[2],
      content: data[3],
      isBol: false,
    }),
    {
      headers: {
        'Content-Type': 'application/json',
      },
    },
  );
});

// 몰루
export const asyncPostCmtDel = createAsyncThunk('post/del', id => {
  axios.delete(`http://localhost:3000/comment/${id}`);
});

import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const asyncPostUp = createAsyncThunk(
  'post/up',
  ({ files, content, tagList }) => {
    console.log(files, content, tagList);
    if (files.length !== 0 && content.length !== 0 && tagList.length !== 0) {
      axios.post(
        '/dailyPosts',
        JSON.stringify({
          pictures: files,
          post: { content },
          tags: tagList,
        }),
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
    .get('/dailyPosts', {
      headers: {
        'Content-Type': 'application/json',
      },
    })
    .then(res => {
      return res.data;
    });

  return data;
});

export const asyncLike = createAsyncThunk('post/up', ({ likeStates, id }) => {
  axios.post(
    `http://localhost:3001/dailyPost/${id}/like`,
    JSON.stringify({ likeStates }),
    {
      headers: {
        'Content-Type': 'application/json',
      },
    },
  );
});

export const asyncLikeundo = createAsyncThunk(
  'post/up',
  ({ likeStates, id }) => {
    axios.post(
      `http://localhost:3001/dailyPost/${id}/like/undo`,
      JSON.stringify({ likeStates }),
      {
        headers: {
          'Content-Type': 'application/json',
        },
      },
    );
  },
);

export const asyncPostDel = createAsyncThunk('post/del', ({ id }) => {
  axios.delete(`/dailyPosts/${id}`);
});

export const asyncPostUpdate = createAsyncThunk(
  'list/update',
  ({ files, content, tags, id }) => {
    if (files.length !== 0 && content.length !== 0 && tags.length !== 0) {
      axios.put(
        `/dailyPosts/${id}`,
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

export const asyncPostCmt = createAsyncThunk('comment', id => {
  const data = axios
    .get(`/dailyPosts/${id}/comments`, {
      headers: {
        'Content-Type': 'application/json',
      },
    })
    .then(res => {
      return res.data;
    });

  return data;
});

export const asyncPostCmtUp = createAsyncThunk('post/up', (id, data) => {
  axios.post(
    `/dailyPosts/${id}/comments`,
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

export const asyncPostCmtDel = createAsyncThunk('post/del', (id, commentId) => {
  axios.delete(`/dailyPosts/${id}/comments${commentId}`);
});

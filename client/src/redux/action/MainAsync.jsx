import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const asyncPostUp = createAsyncThunk('post/up', ({ formData }) => {
  axios.post(`${process.env.REACT_APP_API_URL}/dailyPosts`, formData, {
    headers: {
      'Content-Type': 'multipart/form-data',
      Authorization: localStorage.getItem('Authorization'),
      RefreshToken: localStorage.getItem('RefreshToken'),
    },
  });
  // .then(() => {
  //   window.location.reload();
  // });
});

export const asyncPostUpdate = createAsyncThunk(
  'list/update',
  ({ formData, editId }) => {
    axios.post(
      `${process.env.REACT_APP_API_URL}/dailyPosts/${editId}`,
      formData,
      {
        headers: {
          'Content-Type': 'multipart/form-data',
          Authorization: localStorage.getItem('Authorization'),
          RefreshToken: localStorage.getItem('RefreshToken'),
        },
      },
    );
  },
);

export const asyncPost = createAsyncThunk('post', () => {
  const data = axios
    .get(`${process.env.REACT_APP_API_URL}/dailyPosts`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    })
    .then(res => {
      return res.data;
    });
  return data;
});

export const asyncPostScroll = createAsyncThunk('post', lastPostId => {
  const data = axios
    .get(
      `${process.env.REACT_APP_API_URL}/dailyPosts?lastPostId=${lastPostId}`,
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: localStorage.getItem('Authorization'),
          RefreshToken: localStorage.getItem('RefreshToken'),
        },
      },
    )
    .then(res => {
      return res.data;
    });
  return data;
});

export const asyncPostDel = createAsyncThunk('post/del', async postId => {
  axios.delete(`${process.env.REACT_APP_API_URL}/dailyPosts/${postId}`, {
    headers: {
      Authorization: localStorage.getItem('Authorization'),
      RefreshToken: localStorage.getItem('RefreshToken'),
    },
  });
});

export const asyncLike = createAsyncThunk('post/up', postId => {
  axios.post(
    `${process.env.REACT_APP_API_URL}/dailyPosts/${postId}/like`,
    { postId },
    {
      headers: {
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    },
  );
});

export const asyncLikeundo = createAsyncThunk('post/up', postId => {
  axios.post(
    `${process.env.REACT_APP_API_URL}/dailyPosts/${postId}/like/undo`,
    { postId },
    {
      headers: {
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    },
  );
});

export const asyncPostCmt = createAsyncThunk('comment', index => {
  const data = axios
    .get(`${process.env.REACT_APP_API_URL}/dailyPosts/${index}/comments`, {
      headers: {
        'Content-Type': 'application/json',
      },
    })
    .then(res => {
      return res.data;
    });
  return data;
});

export const asynCmtScroll = createAsyncThunk('comment', listUp => {
  const data = axios
    .get(
      `${process.env.REACT_APP_API_URL}/dailyPosts/${listUp[0]}/comments?lastCommentId=${listUp[1]}`,
      {
        headers: {
          'Content-Type': 'application/json',
        },
      },
    )
    .then(res => {
      return res.data;
    });
  return data;
});

export const asyncPostCmtUp = createAsyncThunk(
  'post/up',
  ({ answervalue, index }) => {
    axios.post(
      `${process.env.REACT_APP_API_URL}/dailyPosts/${index}/comments`,
      JSON.stringify({
        content: answervalue,
      }),
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: localStorage.getItem('Authorization'),
          RefreshToken: localStorage.getItem('RefreshToken'),
        },
      },
    );
  },
);

export const asyncPostCmtDel = createAsyncThunk(
  'post/del',
  ({ index, commentId }) => {
    axios.delete(
      `${process.env.REACT_APP_API_URL}/dailyPosts/${index}/comments/${commentId}`,
      {
        headers: {
          Authorization: localStorage.getItem('Authorization'),
          RefreshToken: localStorage.getItem('RefreshToken'),
        },
      },
    );
  },
);

export const asyncPostCmtEdit = createAsyncThunk(
  'post/up',
  (index, commentId, editAnswer) => {
    axios.patch(
      `${process.env.REACT_APP_API_URL}/dailyPosts/${index}/comments/${commentId}`,
      JSON.stringify({ content: editAnswer }),
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: localStorage.getItem('Authorization'),
          RefreshToken: localStorage.getItem('RefreshToken'),
        },
      },
    );
  },
);

export const MainSearchAsync = createAsyncThunk('search', data => {
  console.log(data);
  axios
    .get(
      `${process.env.REACT_APP_API_URL}/dailyPosts/search?tag=${data[0]}&lastPostId=${data[1]}`,
    )
    .then(res => console.log(res));
});

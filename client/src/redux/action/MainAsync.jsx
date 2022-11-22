import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const asyncPostUp = createAsyncThunk(
  'post/up',
  ({ files, content, tagList, ac, re }) => {
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
            'Content-Type': 'multipart/form-data',
            Authorization: ac,
            RefreshToken: re,
          },
        },
      );
    }
  },
);

export const asyncPostUpdate = createAsyncThunk(
  'list/update',
  ({ files, content, tagList, id, ac, re }) => {
    if (files.length !== 0 && content.length !== 0 && tagList.length !== 0) {
      axios.patch(
        `/dailyPosts/${id}`,
        JSON.stringify({ pictures: files, post: { content }, tags: tagList }),
        {
          headers: {
            'Content-Type': 'multipart/form-data',
            Authorization: ac,
            RefreshToken: re,
          },
        },
      );
    }
  },
);

export const asyncPost = createAsyncThunk('post', async (ac, re) => {
  const data = await axios
    .get('/dailyPosts', {
      headers: {
        'Content-Type': 'application/json',
        Authorization: ac,
        RefreshToken: re,
      },
    })
    .then(res => {
      return res.data;
    });

  return data;
});

export const asyncLike = createAsyncThunk(
  'post/up',
  ({ likeStates, id, ac, re }) => {
    axios.post(
      `http://localhost:3001/dailyPost/${id}/like`,
      JSON.stringify({ likeStates }),
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: ac,
          RefreshToken: re,
        },
      },
    );
  },
);

export const asyncLikeundo = createAsyncThunk(
  'post/up',
  ({ likeStates, id, ac, re }) => {
    axios.post(
      `http://localhost:3001/dailyPost/${id}/like/undo`,
      JSON.stringify({ likeStates }),
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: ac,
          RefreshToken: re,
        },
      },
    );
  },
);

export const asyncPostDel = createAsyncThunk('post/del', ({ id }) => {
  axios.delete(`/dailyPosts/${id}`);
});

export const asyncPostCmt = createAsyncThunk('comment', (id, ac, re) => {
  const data = axios
    .get(`/dailyPosts/${id}/comments`, {
      headers: {
        'Content-Type': 'application/json',
        Authorization: ac,
        RefreshToken: re,
      },
    })
    .then(res => {
      return res.data;
    });

  return data;
});

export const asyncPostCmtUp = createAsyncThunk(
  'post/up',
  (id, data, ac, re) => {
    axios.post(
      `/dailyPosts/${id}/comments`,
      JSON.stringify({
        contentId: data[0],
        content: data[1],
        memberId: data[2],
        userName: data[3],
        profileImage: data[4],
        isBol: false,
      }),
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: ac,
          RefreshToken: re,
        },
      },
    );
  },
);

export const asyncPostCmtDel = createAsyncThunk('post/del', (id, commentId) => {
  axios.delete(`/dailyPosts/${id}/comments${commentId}`);
});

export const asyncPostCmtEdit = createAsyncThunk(
  'post/up',
  (id, data, ac, re) => {
    axios.patch(
      `/dailyPosts/${id}/comments`,
      JSON.stringify({
        contentId: data[0],
        content: data[1],
        memberId: data[2],
        userName: data[3],
        profileImage: data[4],
        isBol: false,
      }),
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: ac,
          RefreshToken: re,
        },
      },
    );
  },
);

// export const asyncPostSearch = createAsyncThunk(
//   '/dailyPosts/search',
//   (id, data, ac, re) => {
//     axios.get(
//       `/dailyPosts/${id}/comments`,
//       JSON.stringify({
//         contentId: data[0],
//         contentImg: data[1],
//         contentName: data[2],
//         content: data[3],
//         isBol: false,
//       }),
//       {
//         headers: {
//           'Content-Type': 'application/json',
//           Authorization: ac,
//           RefreshToken: re,
//         },
//       },
//     );
//   },
// );

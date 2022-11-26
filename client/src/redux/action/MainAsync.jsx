import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const asyncPostUp = createAsyncThunk('post/up', ({ formData }) => {
  for (const pair of formData.entries()) {
    console.log(`${pair[0]}, ${pair[1]}`);
  }
  axios
    .post('/dailyPosts', formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
        // 'Content-Type': 'application/json',
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
      // transformRequest: formData => formData,
    })
    .then(res => {
      console.log(res);
      window.location.reload();
    });
});

export const asyncPostUpdate = createAsyncThunk(
  'list/update',
  ({ formData }, editId) => {
    // for (const pair of formData.entries()) {
    //   console.log(`${pair[0]}, ${pair[1]}`);
    // }
    // console.log(editId);
    axios.post(`/dailyPosts/${editId}`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    });
  },
);

export const asyncPost = createAsyncThunk('post', (ac, re) => {
  const data = axios
    .get(
      '/dailyPosts',
      // 'http://localhost:3001/dailypost',
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: ac,
          RefreshToken: re,
        },
      },
    )
    .then(res => {
      return res.data;
    });
  return data;
});

export const asyncLike = createAsyncThunk('post/up', postId => {
  axios.post(
    `/dailyPosts/${postId}/like`,
    { postId },
    {
      headers: {
        // 'Content-Type': 'application/json',
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    },
  );
});

export const asyncLikeundo = createAsyncThunk('post/up', postId => {
  axios.post(
    `dailyPosts/${postId}/like/undo`,
    { postId },
    {
      headers: {
        // 'Content-Type': 'application/json',
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    },
  );
});

export const asyncPostDel = createAsyncThunk('post/del', async postId => {
  axios.delete(`/dailyPosts/${postId}`, {
    headers: {
      Authorization: localStorage.getItem('Authorization'),
      RefreshToken: localStorage.getItem('RefreshToken'),
    },
  });
});

export const asyncPostCmt = createAsyncThunk('comment', (id, ac, re) => {
  const data = axios
    .get(
      // `/dailyposts/${id}/comments`,
      `http://localhost:3001/comment`,
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: ac,
          RefreshToken: re,
        },
      },
    )
    .then(res => {
      return res.data;
    });

  return data;
});

export const asyncPostCmtUp = createAsyncThunk('post/up', (data, ac, re) => {
  axios.post(
    `http:/localhost:3001/comment`,
    JSON.stringify({
      contentId: data[0],
      content: data[1],
      // memberId: data[2],
      // userName: data[3],
      // profileImage: data[4],
      // isBol: false,
    }),
    {
      headers: {
        'Content-Type': 'application/json',
        Authorization: ac,
        RefreshToken: re,
      },
    },
  );
});

export const asyncPostCmtDel = createAsyncThunk('post/del', id => {
  axios.delete(`http://localhost:3001/comment/${id}`);
});

export const asyncPostCmtEdit = createAsyncThunk(
  'post/up',
  (id, data, ac, re) => {
    axios.patch(
      `http://localhost:3001/comment/${id.id}`,
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

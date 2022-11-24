import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

export const asyncPostUp = createAsyncThunk(
  'post/up',
  ({ formData, ac, re }) => {
    axios.post(
      '/dailyPosts',
      formData,
      // 'http://localhost:3001/dailypost',
      // JSON.stringify({
      //   pictures: files,
      //   content,
      //   tagDtos: tagList,
      // }),
      {
        headers: {
          'Content-Type': 'multipart/form-data',
          // 'Content-Type': 'application/json',
          Authorization: ac,
          RefreshToken: re,
        },
        // transformRequest: formData => formData,
      },
    );
  },
);

export const asyncPostUpdate = createAsyncThunk(
  'list/update',
  ({ files, content, tagList, id, ac, re }) => {
    if (files.length !== 0 && content.length !== 0 && tagList.length !== 0) {
      axios.patch(
        `/dailyPosts/${id}`,
        // `http://localhost:3001/dailypost/${id.id}`,
        JSON.stringify({ pictures: files, post: { content }, tags: tagList }),
        {
          headers: {
            // 'Content-Type': 'multipart/form-data',
            'Content-Type': 'application/json',
            Authorization: ac,
            RefreshToken: re,
          },
        },
      );
    }
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

export const asyncLike = createAsyncThunk(
  'post/up',
  ({ likeStates, id, ac, re }) => {
    axios.post(`/dailyPosts/${id}/like`, JSON.stringify({ likeStates }), {
      headers: {
        'Content-Type': 'application/json',
        Authorization: ac,
        RefreshToken: re,
      },
    });
  },
);

export const asyncLikeundo = createAsyncThunk(
  'post/up',
  ({ likeStates, id, ac, re }) => {
    axios.post(`dailyPosts/${id}/like/undo`, JSON.stringify({ likeStates }), {
      headers: {
        'Content-Type': 'application/json',
        Authorization: ac,
        RefreshToken: re,
      },
    });
  },
);

export const asyncPostDel = createAsyncThunk('post/del', id => {
  axios.delete(
    `/dailyPosts/${id}`,
    // `http://localhost:3001/dailypost/${id}`,
    {
      id,
    },
  );
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

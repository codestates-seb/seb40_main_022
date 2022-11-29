import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';

// 게시글 create
export const asyncPostUp = createAsyncThunk('post/up', ({ formData }) => {
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

// 게시글 update
export const asyncPostUpdate = createAsyncThunk(
  'list/update',
  ({ formData, editId }) => {
    for (const pair of formData.entries()) {
      console.log(`${pair[0]}, ${pair[1]}`);
    }
    console.log(editId);
    axios.post(`/dailyPosts/${editId}`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
        Authorization: localStorage.getItem('Authorization'),
        RefreshToken: localStorage.getItem('RefreshToken'),
      },
    });
  },
);

// 게시글 read
export const asyncPost = createAsyncThunk('post', () => {
  const data = axios
    .get(
      // `/dailyPosts?lastPostId=${lastPostId}`,
      `/dailyPosts`,
      {
        headers: {
          'Content-Type': 'application/json',
          Authorization: localStorage.getItem('Authorization'),
          RefreshToken: localStorage.getItem('RefreshToken'),
        },
      },
    )
    .then(res => {
      console.log(res);
      return res.data;
    });
  return data;
});

export const asyncPostScroll = createAsyncThunk('post', lastPostId => {
  console.log(lastPostId);
  const data = axios
    .get(`/dailyPosts?lastPostId=${lastPostId}`, {
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

// 게시글 delete
export const asyncPostDel = createAsyncThunk('post/del', async postId => {
  axios.delete(`/dailyPosts/${postId}`, {
    headers: {
      Authorization: localStorage.getItem('Authorization'),
      RefreshToken: localStorage.getItem('RefreshToken'),
    },
  });
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

// 댓글 read
export const asyncPostCmt = createAsyncThunk('comment', index => {
  const data = axios
    .get(`/dailyPosts/${index}/comments`, {
      headers: {
        'Content-Type': 'application/json',
      },
    })
    .then(res => {
      // console.log(res);
      return res.data;
    });
  return data;
});

// 댓글 create
export const asyncPostCmtUp = createAsyncThunk(
  'post/up',
  ({ answervalue, index }) => {
    // console.log(answervalue, index);
    axios
      .post(
        `/dailyPosts/${index}/comments`,
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
      )
      .then(res => {
        window.location.reload();
        console.log(res);
      });
  },
);

// 댓글 delete
export const asyncPostCmtDel = createAsyncThunk(
  'post/del',
  ({ index, commentId }) => {
    axios
      .delete(`/dailyPosts/${index}/comments/${commentId}`, {
        headers: {
          Authorization: localStorage.getItem('Authorization'),
          RefreshToken: localStorage.getItem('RefreshToken'),
        },
      })
      .then(res => {
        window.location.reload();
        console.log(res);
      });
  },
);

// 댓글 update
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

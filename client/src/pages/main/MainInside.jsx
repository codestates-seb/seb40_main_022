import { useState, useEffect } from 'react';
import axios from 'axios';
import { useDispatch } from 'react-redux';
import DailyPost from './DailyPost';
import search from '../../images/search.svg';
import { MainSearchAsync } from '../../redux/action/MainAsync';
import { Inside, MainForm, MainSearch, ContentForm } from './MainStyle';

export default function MainInside() {
  const [tagsearch, setTagearch] = useState('');
  const [postList, setPostList] = useState([]);
  console.log(tagsearch, postList);
  const dispatch = useDispatch();
  const handleSearch = () => {
    const data = [tagsearch, 16];
    dispatch(MainSearchAsync(data));
  };
  useEffect(() => {
    const getPost = async () => {
      const res = await axios.get(
        `${process.env.REACT_APP_API_URL}/dailyPosts`,
      );
      const post = await res.data.items;
      setPostList([post]);
    };
    getPost();
  }, []);
  return (
    <Inside>
      <div className="searchInput">
        <div>
          <MainSearch
            placeholder="태그를 입력하세요"
            value={tagsearch}
            onChange={e => setTagearch(e.target.value)}
          />
          <button
            onClick={() => {
              handleSearch();
            }}
          >
            <img className="searchIcon" src={search} alt="search" />
          </button>
        </div>
      </div>
      <MainForm>
        <ContentForm>
          <DailyPost />
        </ContentForm>
      </MainForm>
    </Inside>
  );
}

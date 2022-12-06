import { useState } from 'react';
import { useDispatch } from 'react-redux';
import DailyPost from './DailyPost';
import search from '../../images/search.svg';
import { searchchange } from '../../redux/reducer/MainSlice';
import { Inside, MainForm, MainSearch, ContentForm } from './MainStyle';

export default function MainInside() {
  const [tagsearch, setTagearch] = useState('');
  const dispatch = useDispatch();
  const handleSearch = () => {
    const tagchange = tagsearch.split('#').join('%23');
    dispatch(searchchange(tagchange));
    if (tagsearch === '') {
      window.location.reload();
    }
  };

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

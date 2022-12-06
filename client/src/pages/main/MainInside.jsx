import { useState } from 'react';
import { useDispatch } from 'react-redux';
import uuidv4 from 'react-uuid';
import DailyPost from './DailyPost';
import search from '../../images/search.svg';
import { searchchange } from '../../redux/reducer/MainSlice';
import { Inside, MainForm, MainSearch, ContentForm } from './MainStyle';

export default function MainInside() {
  const [tagsearch, setTagSearch] = useState('');
  const [searchTagList, setSearchTagList] = useState([]);
  const dispatch = useDispatch();
  const handleSearch = () => {
    const tagchange = searchTagList.join('').split('#').join('%23');
    console.log(tagchange);
    dispatch(searchchange(tagchange));
    if (searchTagList === '') {
      window.location.reload();
    }
  };

  const handleTag = e => {
    if (e.key === 'Enter' && e.target.value !== '') {
      const updateTagList = [...searchTagList];
      updateTagList.push(`#${tagsearch}`);
      const filteredTagList = updateTagList.filter(
        (el, idx) => updateTagList.indexOf(el) === idx,
      );
      setSearchTagList(filteredTagList);
      setTagSearch('');
    }
  };

  const handleTagDelete = tagfil => {
    const filtertag = searchTagList.filter(data => data !== tagfil);
    setSearchTagList(filtertag);
  };

  return (
    <Inside>
      <div className="searchInput">
        <div className="taginput">
          <div className="tagInfo">
            {searchTagList.map(data => {
              return (
                <div className="tags" key={uuidv4()}>
                  <button onClick={() => handleTagDelete(data)}>
                    <p>{data}</p>
                  </button>
                </div>
              );
            })}
            {searchTagList.length <= 3 ? (
              <MainSearch
                placeholder="태그를 입력하세요(Enter)"
                maxLength={6}
                value={tagsearch}
                onChange={e => setTagSearch(e.target.value)}
                onKeyUp={e => {
                  handleTag(e);
                }}
              />
            ) : null}
          </div>
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

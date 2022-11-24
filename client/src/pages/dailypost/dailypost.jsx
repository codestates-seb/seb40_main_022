import { useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import plus from '../../images/plus.png';
import { DetailBody, DetailMain } from './dailyStyle';
import { asyncPostUp } from '../../redux/action/MainAsync';

const dailypost = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const photoUp = useRef();

  const [files, setFiles] = useState([]);
  const [content, setContent] = useState('');
  const [tags, setTags] = useState('');
  const [tagList, setTagList] = useState([]);
  const ac = useSelector(state => state.authToken.accessToken);
  const re = useSelector(state => state.authToken.token);
  const reader = new FileReader();

  const handleTag = e => {
    if (e.key === 'Enter' && e.target.value !== '') {
      const updateTagList = [...tagList];
      updateTagList.push(tags);
      setTagList(updateTagList);
      setTags('');
    }
  };

  const handleTagDelete = tagfil => {
    const filtertag = tagList.filter(data => data !== tagfil);
    setTagList(filtertag);
  };

  const handleFile = e => {
    reader.readAsDataURL(e.target.files[0]);
    reader.onloadend = () => {
      const resultImg = reader.result;
      setFiles([...files, resultImg.toString()]);
    };
  };

  const handelClick = () => {
    photoUp.current.click();
  };

  const deleteFile = index => {
    const imgArr = files.filter((el, idx) => idx !== index);
    setFiles([...imgArr]);
  };

  const handleSubmit = () => {
    dispatch(asyncPostUp({ files, content, tagList, ac, re }));
    navigate('/');
  };

  return (
    <DetailBody>
      <Header />
      <DetailMain>
        <div className="DetailBox">
          <div className="Imgbox">
            {files &&
              files.map((data, index) => {
                return (
                  <div className="boxs">
                    <img src={data} alt="오완운사진" className="Imgs" />
                    <button
                      onClick={() => deleteFile(index)}
                      className="Imgdel"
                    >
                      x
                    </button>
                  </div>
                );
              })}
            {files.length <= 3 ? (
              <div className="Imgaddbox">
                <input
                  type="file"
                  className="ImgInput"
                  ref={photoUp}
                  onChange={handleFile}
                />
                <button className="ImgButton" onClick={() => handelClick()}>
                  <img src={plus} alt="버튼로고" />
                </button>
              </div>
            ) : null}
          </div>
          {/* {files ? null : <p>이미지를 업로드해주세요</p>} */}
          <span className="contentTitle">내용</span>
          <textarea
            maxLength="200"
            className="contentinput"
            value={content}
            onChange={e => {
              setContent(e.target.value);
            }}
          />
          <div className="limit">{content.length}/200</div>
          <span className="tagTitle">태그</span>
          <div className="taginput">
            {tagList.map(data => {
              return (
                <div className="tags">
                  <button onClick={() => handleTagDelete(data)}>
                    <p>{`#${data}`}</p>
                  </button>
                </div>
              );
            })}
            {tagList.length <= 3 ? (
              <input
                className="tagbox"
                value={tags}
                onChange={e => {
                  setTags(e.target.value);
                }}
                onKeyUp={e => handleTag(e)}
              />
            ) : null}
          </div>
          <div className="buttons">
            <button
              className="submitbutton"
              onClick={() => {
                handleSubmit();
              }}
            >
              등록
            </button>
            <button
              className="canclebutton"
              onClick={() => {
                navigate('/');
              }}
            >
              취소
            </button>
          </div>
        </div>
      </DetailMain>
      <Footer />
    </DetailBody>
  );
};

export default dailypost;

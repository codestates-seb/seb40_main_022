import { useRef, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import plus from '../../images/plus.png';
import { DetailBody, DetailMain } from './dailyStyle';
import { asyncPostUpdate } from '../../redux/action/MainAsync';

function DailyEdit() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const photoUp = useRef();
  const id = useParams();
  const data = useSelector(state => state.dailypost.data);
  const list = data.filter(postdata => postdata.id === +id.id);

  const [files, setFiles] = useState(list[0].files);
  const [content, setContent] = useState(list[0].content);
  const [tag, setTag] = useState(list[0].tag);
  const [tagList, setTagList] = useState(list[0].tagList);
  const ac = useSelector(state => state.authToken.accessToken);
  const re = useSelector(state => state.authToken.token);

  const reader = new FileReader();

  const handleTag = e => {
    if (e.key === 'Enter' && e.target.value !== '') {
      const updateTagList = [...tagList];
      updateTagList.push(tag);
      setTagList(updateTagList);
      setTag('');
    }
  };

  const handleTagDelete = tagfil => {
    const filtertag = tagList.filter(el => el !== tagfil);
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
    if (files.length !== 0 && content.length !== 0 && tagList.length !== 0) {
      dispatch(asyncPostUpdate({ files, content, tagList, id, ac, re }));
      navigate('/');
    }
  };

  return (
    <DetailBody>
      <Header />
      <DetailMain>
        <div className="DetailBox">
          <div className="Imgbox">
            {files &&
              files.map((el, index) => {
                return (
                  <div className="boxs">
                    <img src={el} alt="오완운사진" className="Imgs" />
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
            {tagList.map(el => {
              return (
                <div className="tags">
                  <button onClick={() => handleTagDelete(el)}>
                    <p>{`#${el}`}</p>
                  </button>
                </div>
              );
            })}
            {tagList.length <= 3 ? (
              <input
                className="tagbox"
                value={tag}
                onChange={e => {
                  setTag(e.target.value);
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
}

export default DailyEdit;

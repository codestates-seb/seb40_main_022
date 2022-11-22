import { useRef, useState, useParams, useSelector } from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import plus from '../../images/plus.png';
import { DetailBody, DetailMain } from './dailyStyle';
import { asyncPostUp } from '../../redux/action/MainAsync';

function DailyEdit() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const id = useParams();
  const idData = useSelector(state => state.content.data);
  const list = idData.filter(data => data.id === +id.id);
  const photoUp = useRef();
  const [files, setFiles] = useState(list[0].files);
  const [content, setContent] = useState(list[0].content);
  const [tag, setTag] = useState(list[0].tag);
  const [tagList, setTagList] = useState([]);

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
    dispatch(asyncPostUp({ files, content, tag, id }));
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

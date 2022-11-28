import { useRef, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import plus from '../../images/plus.png';
import { DetailBody, DetailMain } from './dailyStyle';
import { asyncPostUpdate } from '../../redux/action/MainAsync';

function DailyEdit() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const Id = useParams();
  const photoUp = useRef();
  const selectdata = useSelector(state => state.dailypost.data.items);
  const list = selectdata.filter(postdata => postdata.post.postId === +Id.id);
  const [files, setFiles] = useState(list[0].pictures);
  const [imgBase64, setImgBase64] = useState(list[0].pictures);
  const [content, setContent] = useState(list[0].post.content);
  const [tags, setTags] = useState('');
  const [tagList, setTagList] = useState(list[0].tags);

  const handleFile = e => {
    setFiles(e.target.files);
    setImgBase64([]);

    for (let i = 0; i < e.target.files.length; i += 1) {
      if (e.target.files[i]) {
        const reader = new FileReader();
        reader.readAsDataURL(e.target.files[i]);
        reader.onloadend = () => {
          const base64 = reader.result;
          if (base64) {
            const base64Sub = base64.toString();
            setImgBase64([...imgBase64, base64Sub]);
            setFiles([...files, e.target.files[i]]);
          }
        };
      }
    }
  };

  const handleTag = e => {
    if (e.key === 'Enter' && e.target.value !== '') {
      const updateTagList = [...tagList];
      updateTagList.push(tags.split('#').join(''));
      const filteredTagList = updateTagList.filter(
        (el, idx) => updateTagList.indexOf(el) === idx,
      );
      setTagList(filteredTagList);
      setTags('');
    }
  };

  const handleTagDelete = tagfil => {
    const filtertag = tagList.filter(el => el !== tagfil);
    setTagList(filtertag);
  };

  const handelClick = () => {
    photoUp.current.click();
  };

  const deleteFile = index => {
    const imgArr = imgBase64.filter((el, idx) => idx !== index);
    setImgBase64([...imgArr]);

    const uploadImgArr = files.filter((el, idx) => idx !== index);
    setFiles([...uploadImgArr]);
  };

  const handleSubmit = async () => {
    const formData = new FormData();
    for (let i = 0; i < files.length; i += 1) {
      formData.append('files', files[i]);
    }

    formData.append('content', content);

    Array.from(tagList).forEach(el => {
      formData.append('tagDtos', el);
    });

    const editId = +Id.id;
    if (
      imgBase64.length !== 0 &&
      content.length >= 10 &&
      tagList.length !== 0
    ) {
      dispatch(asyncPostUpdate({ formData, editId }));
      // navigate('/');
    } else if (files.length === 0) alert('이미지를 업로드해주세요');
    else if (content.length < 10) alert('내용은 10자 이상 입력해주세요');
    else if (tagList.length === 0) alert('태그를 입력해주세요');
  };

  return (
    <DetailBody>
      <Header />
      <DetailMain>
        <div className="DetailBox">
          <div className="Imgbox">
            {imgBase64 &&
              imgBase64.map((el, index) => {
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
                  accept="image/jpg, image/jpeg, image/png"
                  multiple="multiple"
                />
                <button className="ImgButton" onClick={() => handelClick()}>
                  <img src={plus} alt="버튼로고" />
                </button>
              </div>
            ) : null}
          </div>
          <div className="errorMsg">
            {imgBase64.length < 1 ? <p>이미지를 업로드해주세요</p> : null}
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
          <div className="errorMsg">
            {content.length < 10 ? <p>10자 이상 입력해주세요</p> : null}
          </div>
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
                value={tags}
                onChange={e => {
                  setTags(e.target.value);
                }}
                onKeyUp={e => handleTag(e)}
              />
            ) : null}
          </div>
          <div className="errorMsg">
            {tagList.length < 1 ? <p>태그를 입력해주세요</p> : null}
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

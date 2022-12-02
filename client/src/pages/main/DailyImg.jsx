// import { Link } from 'react-router-dom';
// import { useDispatch } from 'react-redux';
import ImageSlider, { Slide } from 'react-auto-image-slider';
// import { asyncPostDel } from '../../redux/action/MainAsync';

export default function DailyImg({ el }) {
  // const dispatch = useDispatch();

  // const handleDelPost = id => {
  //   dispatch(asyncPostDel(id));
  //   window.location.reload();
  // };

  return (
    <div>
      <article className="Img">
        <ImageSlider effectDelay={500} autoPlayDelay={3000}>
          {el.pictures &&
            el.pictures.map(ele => {
              return (
                <Slide>
                  <img className="dailyImg" src={ele} alt="daily" />
                </Slide>
              );
            })}
        </ImageSlider>
      </article>
      {/* <div>
        <Link to={`/dailypost/edit/${el.post.postId}`} className="buttons">
          수정
        </Link>
        <button
          onClick={() => {
            handleDelPost(el.post.postId);
          }}
        >
          삭제
        </button>
      </div> */}
    </div>
  );
}

import ImageSlider, { Slide } from 'react-auto-image-slider';
import uuidv4 from 'react-uuid';

export default function DailyImg({ el }) {
  return (
    <div>
      <article className="Img">
        <ImageSlider effectDelay={500} autoPlayDelay={3000}>
          {el.pictures &&
            el.pictures.map(ele => {
              return (
                <Slide key={uuidv4()}>
                  <img className="dailyImg" src={ele} alt="daily" />
                </Slide>
              );
            })}
        </ImageSlider>
      </article>
    </div>
  );
}

import { Link } from 'react-router-dom';
import daily from '../../images/daily.jpg';
import dailyAdd from '../../images/daily_add.svg';
import { Top, Content } from './MainStyle';

export default function TopList() {
  return (
    <Top>
      {[...Array(4)].map(() => {
        return (
          <Content>
            <div className="imgprofile">
              <img src={daily} alt="daily" />
            </div>
            <span>운동인</span>
          </Content>
        );
      })}
      <Content>
        <Link to="/dailypost">
          <div className="imgprofile dailynew">
            <img src={dailyAdd} alt="dailyAdd" />
          </div>
        </Link>
        <span>새 게시물</span>
      </Content>
    </Top>
  );
}

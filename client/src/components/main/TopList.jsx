import { Link } from 'react-router-dom';
import { useSelector } from 'react-redux';
import daily from '../../images/daily.jpg';
import dailyAdd from '../../images/daily_add.svg';
import { Top, Content } from './MainStyle';

export default function TopList() {
  // const member = useSelector(state => state.mypage.member);
  const data = useSelector(state => state.dailypost.data.items);

  return (
    <Top>
      {data &&
        data.map(list => {
          console.log(list.member.profileImage);
          return (
            <Content>
              <div className="imgprofile">
                <img
                  src={
                    list.member.profileImage ? list.member.profileImage : daily
                  }
                  alt="daily"
                />
              </div>
              <span className="userName">
                {list.member.username ? list.member.username : null}
              </span>
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

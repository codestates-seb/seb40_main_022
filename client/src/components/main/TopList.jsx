import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';
import daily from '../../images/daily.jpg';
import dailyAdd from '../../images/daily_add.svg';
import { Top, Content } from './MainStyle';

export default function TopList() {
  // const member = useSelector(state => state.mypage.member);
  const data = useSelector(state => state.dailypost.data.items);
  // const ac = localStorage.getItem('Authorization');
  const navigate = useNavigate();

  const newPost = () => {
    // if (!ac) {
    //   alert('로그인 후 이용할 수 있습니다.');
    // } else {
    navigate('/dailypost');
    // }
  };

  return (
    <Top>
      {data &&
        data.map(list => {
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
        <button className="imgprofile dailynew" onClick={() => newPost()}>
          <img src={dailyAdd} alt="dailyAdd" />
        </button>
        <span>새 게시물</span>
      </Content>
    </Top>
  );
}

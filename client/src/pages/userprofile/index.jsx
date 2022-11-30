import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useParams } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {
  faDumbbell,
  faTrophy,
  faPersonRunning,
} from '@fortawesome/free-solid-svg-icons';
import { UserProfileGet } from '../../redux/action/ProfileAsync';
import Footer from '../../components/footer/Footer';
import Header from '../../components/header/Header';
import userPicture from './userPicture';
import { Wrapper, ProfileBox, NameBox, FollowBox, RecordBox } from './style';

function UserProfile() {
  const dispatch = useDispatch();
  const Id = +useParams().id;
  useEffect(() => {
    dispatch(UserProfileGet(Id));
  }, []);

  const data = useSelector(state => state.profile);
  console.log(data);
  const member = useSelector(state => state.profile.member);
  const activity = useSelector(state => state.profile.activity);

  return (
    <Wrapper>
      <Header />
      <div className="box">
        <ProfileBox>
          <div className="circle">
            <img src={member.profileImage} alt="userProfile" />
          </div>
        </ProfileBox>
        <NameBox>
          <div className="username">{member.userName}</div>
        </NameBox>
        <FollowBox>
          <div>게시물 {data.postCounts || 0}</div>
          <div> {member.height ? member.height : 0}cm</div>
          <div> {member.weight ? member.weight : 0}kg</div>
        </FollowBox>
        <RecordBox>
          <div className="boxs">
            <div className="box">
              <FontAwesomeIcon icon={faDumbbell} />
              {activity.kilogram}kg
            </div>
            <div className="box">
              <FontAwesomeIcon icon={faTrophy} />
              {activity.point ? activity.point : 0}점 73위
            </div>
            <div className="box">
              <FontAwesomeIcon icon={faPersonRunning} />
              {activity.dayCount ? activity.dayCount : 0}일
            </div>
          </div>
        </RecordBox>
        <hr className="line" />
        <userPicture />
      </div>
      <Footer />
    </Wrapper>
  );
}

export default UserProfile;

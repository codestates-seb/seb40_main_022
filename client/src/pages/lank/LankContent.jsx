import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import ChallengeReq from '../../components/modal/ChallengeReq';
import { LankBody, Lankcontents } from './LankContentstyle';
import { LankProfileGet } from '../../redux/action/LankAsync';

function LankContent() {
  const [id, setId] = useState(null);
  const [challengeReq, setChallengeReq] = useState(false);
  // const [userName, setUserName] = useState('');
  // const [height, setHeight] = useState('');
  // const [weight, setWeight] = useState('');
  // const [period, setPeriod] = useState('');
  // const [point, setPoint] = useState('');
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const lanklist = useSelector(state => state.challenge.member.responses);
  // const listdata = [userName, height, weight, period, point];
  useEffect(() => {
    dispatch(LankProfileGet());
  }, []);

  return (
    <LankBody>
      <ChallengeReq
        open={challengeReq}
        close={() => setChallengeReq(false)}
        id={id}
      />
      {lanklist &&
        lanklist.map((data, idx) => {
          return (
            <Lankcontents>
              <h1>{idx + 1}</h1>
              <button
                onClick={() => {
                  navigate(`/members/${data.memberId}`);
                }}
                className="cont-picturebtn"
              >
                <img
                  src={data.profileImage}
                  className="cont-picture"
                  alt="프로필이미지"
                />
              </button>
              <h4 className="cont-name">{data.userName}</h4>
              <h4 className="cont-height">신장 : {data.height}</h4>
              <h4 className="cont-weight">몸무게 : {data.weight}</h4>
              <h4 className="cont-exp">경력 : {data.period}</h4>
              <h4 className="cont-point">포인트 : {data.point}</h4>
              <button
                onClick={() => {
                  setId(data.memberId);
                  setChallengeReq(true);
                }}
                className="challenge"
              >
                대결신청
              </button>
            </Lankcontents>
          );
        })}
    </LankBody>
  );
}

export default LankContent;

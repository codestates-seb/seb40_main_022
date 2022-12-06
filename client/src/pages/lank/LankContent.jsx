import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import uuidv4 from 'react-uuid';
import ChallengeReq from '../../components/modal/ChallengeReq';
import { LankBody, Lankcontents } from './LankContentstyle';
import { ChallengeSearch } from '../../redux/action/LankAsync';
import Pagination from './Pagination';

function LankContent() {
  const [id, setId] = useState(null);
  const [challengeReq, setChallengeReq] = useState(false);
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const lanklist = useSelector(state => state.challenge.member.responses);
  const url = useSelector(state => state.challenge.url);
  const searchList = useSelector(state => state.challenge.items.responses);
  const list = useSelector(state => state.challenge.pageInfo.pageInfo);
  const [currentPage, setCurrentPage] = useState(1);
  const SearchPaingdata = [url, currentPage];
  useEffect(() => {
    dispatch(ChallengeSearch(SearchPaingdata));
  }, [url]);

  const currentPageHandler = page => {
    setCurrentPage(page);
    const searchdata = [url, page];
    dispatch(ChallengeSearch(searchdata));
  };

  return (
    <LankBody>
      <ChallengeReq
        open={challengeReq}
        close={() => setChallengeReq(false)}
        id={id}
      />
      {searchList
        ? searchList &&
          searchList.map((data, idx) => {
            return (
              <Lankcontents key={uuidv4}>
                <h1>{(currentPage - 1) * 10 + idx + 1}</h1>
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
                <h4 className="cont">{data.userName}</h4>
                <h4 className="cont">신장 : {data.height}</h4>
                <h4 className="cont">몸무게 : {data.weight}</h4>
                <h4 className="cont">경력 : {data.period}개월</h4>
                <h4 className="cont">포인트 : {Math.round(data.point)}</h4>
                {data.challengeStatus ? (
                  <button
                    disabled={data.challengeStatus}
                    className="challenging"
                  >
                    대결중
                  </button>
                ) : (
                  <button
                    onClick={() => {
                      setId(data.memberId);
                      setChallengeReq(true);
                    }}
                    className="challenge"
                  >
                    대결신청
                  </button>
                )}
              </Lankcontents>
            );
          })
        : lanklist &&
          lanklist.map((data, idx) => {
            return (
              <Lankcontents key={uuidv4}>
                <h1>{(currentPage - 1) * 10 + idx + 1}</h1>
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
                <h4 className="cont-point">
                  포인트 : {Math.round(data.point)}
                </h4>
                {data.challengeStatus ? (
                  <button
                    disabled={data.challengeStatus}
                    className="challenging"
                  >
                    대결중
                  </button>
                ) : (
                  <button
                    onClick={() => {
                      setId(data.memberId);
                      setChallengeReq(true);
                    }}
                    className="challenge"
                  >
                    대결신청
                  </button>
                )}
              </Lankcontents>
            );
          })}
      <Pagination
        currentPage={currentPage}
        currentPageHandler={currentPageHandler}
        list={list}
      />
    </LankBody>
  );
}

export default LankContent;

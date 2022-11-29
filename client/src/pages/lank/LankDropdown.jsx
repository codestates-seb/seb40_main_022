import React, { useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCaretDown } from '@fortawesome/free-solid-svg-icons';
// import { useDispatch } from 'react-redux';
import {
  Dropdown,
  Dropdivi,
  DropHeight,
  DropCareer,
  DropPoint,
  DropWeight,
} from './LankDropdownStyle';
// import { lankActions } from '../../redux/reducer/LankSlice';
// import { useSelector } from 'react-redux';

function LankDropdown() {
  // const list = [
  //   {
  //     분할: ['2분할', '3분할', '4분할', '5분할'],
  //   },
  //   { 키: ['181cm이상', '175cm ~ 179cm', '171cm ~ 174cm', '170cm 이하'] },
  //   {
  //     몸무게: [
  //       '120kg이상',
  //       '101kg ~ 119kg',
  //       '81kg ~ 100kg',
  //       '60kg ~ 80kg',
  //       '40kg ~ 59kg',
  //       '39kg이하',
  //     ],
  //   },
  //   {
  //     경력: [
  //       '20년 이상',
  //       '15년 ~ 19년',
  //       '10년 ~ 14년',
  //       '5년 ~ 9년',
  //       '4년 이하',
  //     ],
  //   },
  //   {
  //     점수: ['10000p 이상', '8000p ~ 9999p', '6000p ~ 7999p', '4000p ~ 5999p'],
  //   },
  // ];
  // const listadata1 = useSelector(state => state);
  // const name = [];
  // const [names, setNames] = useState(['분할', '키', '몸무게', '경력', '점수']);
  // const dispatch = useDispatch();
  // dispatch(SET_LANK);

  const [divi, setDivi] = useState('');
  const [height, setHeight] = useState('');
  const [weight, setWeight] = useState('');
  const [career, setCareer] = useState('');
  const [point, setPoint] = useState('');
  // const [lankValue, setLankValue] = useState('');

  // const dispatch = useDispatch();

  // const LankInputHandler = e => {
  //   setLankValue(e.target.value);
  // };

  // const filterHandler = e => {
  //   e.preventDefault();
  //   dispatch(lankActions.SET_LANK(lankValue));
  //   setLankValue('');
  //   console.log(lankValue);
  // };
  return (
    <Dropdown>
      {/* 소프트코딩 */}
      {/* {listadata1 &&
        listadata1.map((data, idx) => {
          name.push(Object.keys(list[idx])[0]);
          return (
            <Dropdivi>
              <div className="container">
                <input
                  id={`dropdown${idx}`}
                  type="checkbox"
                  className="dropdown"
                />
                <label className="dropdownLabel" htmlFor={`dropdown${idx}`}>
                  <div className="boxname">{data}</div>
                  <FontAwesomeIcon icon={faCaretDown} className="caretIcon" />
                </label>
                <div className="content">
                  {list[idx][name[idx]] &&
                    list[idx][name[idx]].map(listdata => {
                      return (
                        <button
                          onClick={e => {
                            names[idx] = e.target.innerText;
                            setNames(names);
                            dispatch(SET_LANK(names));
                          }}
                          className={
                            names[idx] === listdata ? 'backblack' : 'btns'
                          }
                        >
                          {listdata}
                        </button>
                      );
                    })}
                </div>
              </div>
            </Dropdivi>
          );
        })} */}
      {/* 하드코딩 */}
      <Dropdivi>
        <div className="container">
          <div>분할</div>
          <input id="dropdown" type="checkbox" />
          <label className="dropdownLabel" htmlFor="dropdown">
            <div>{divi}</div>
            <FontAwesomeIcon icon={faCaretDown} className="caretIcon" />
          </label>
          <div className="content">
            <button
              onClick={e => {
                e.preventDefault();
                setDivi(e.target.innerHTML);
              }}
            >
              2분할
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setDivi(e.target.innerHTML);
              }}
            >
              3분할
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setDivi(e.target.innerHTML);
              }}
            >
              4분할
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setDivi(e.target.innerHTML);
              }}
            >
              5분할이상
            </button>
          </div>
        </div>
      </Dropdivi>
      <DropHeight>
        <div className="container2">
          <div>신장</div>
          <input id="dropdown2" type="checkbox" />
          <label className="dropdownLabel2" htmlFor="dropdown2">
            <div>{height}</div>
            <FontAwesomeIcon icon={faCaretDown} className="caretIcon2" />
          </label>
          <div className="content2">
            <button
              onClick={e => {
                e.preventDefault();
                setHeight(e.target.innerHTML);
              }}
            >
              190cm 이상
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setHeight(e.target.innerHTML);
              }}
            >
              185cm ~ 189cm
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setHeight(e.target.innerHTML);
              }}
            >
              180cm ~ 184cm
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setHeight(e.target.innerHTML);
              }}
            >
              175cm ~ 179cm
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setHeight(e.target.innerHTML);
              }}
            >
              170cm ~ 174cm
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setHeight(e.target.innerHTML);
              }}
            >
              165cm ~ 169cm
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setHeight(e.target.innerHTML);
              }}
            >
              160cm ~ 164cm
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setHeight(e.target.innerHTML);
              }}
            >
              155cm ~ 159cm
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setHeight(e.target.innerHTML);
              }}
            >
              150cm ~ 154cm
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setHeight(e.target.innerHTML);
              }}
            >
              150cm 미만
            </button>
          </div>
        </div>
      </DropHeight>
      <DropWeight>
        <div className="container3">
          <div>몸무게</div>
          <input id="dropdown3" type="checkbox" />
          <label className="dropdownLabel3" htmlFor="dropdown3">
            <div>{weight}</div>
            <FontAwesomeIcon icon={faCaretDown} className="caretIcon3" />
          </label>
          <div className="content3">
            <button
              onClick={e => {
                e.preventDefault();
                setWeight(e.target.innerHTML);
              }}
            >
              100kg이상
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setWeight(e.target.innerHTML);
              }}
            >
              90kg ~ 99kg
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setWeight(e.target.innerHTML);
              }}
            >
              85kg ~ 89kg
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setWeight(e.target.innerHTML);
              }}
            >
              80kg ~ 84kg
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setWeight(e.target.innerHTML);
              }}
            >
              75kg ~ 79kg
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setWeight(e.target.innerHTML);
              }}
            >
              70kg ~ 74kg
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setWeight(e.target.innerHTML);
              }}
            >
              65kg ~ 69kg
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setWeight(e.target.innerHTML);
              }}
            >
              60kg ~ 64kg
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setWeight(e.target.innerHTML);
              }}
            >
              55kg ~ 59kg
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setWeight(e.target.innerHTML);
              }}
            >
              50kg ~ 54kg
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setWeight(e.target.innerHTML);
              }}
            >
              45kg ~ 49kg
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setWeight(e.target.innerHTML);
              }}
            >
              40kg ~ 44kg
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setWeight(e.target.innerHTML);
              }}
            >
              40kg 미만
            </button>
          </div>
        </div>
      </DropWeight>
      <DropCareer>
        <div className="container4">
          <div>경력</div>
          <input id="dropdown4" type="checkbox" />
          <label className="dropdownLabel4" htmlFor="dropdown4">
            <div>{career}</div>
            <FontAwesomeIcon icon={faCaretDown} className="caretIcon4" />
          </label>
          <div className="content4">
            <button
              onClick={e => {
                e.preventDefault();
                setCareer(e.target.innerHTML);
              }}
            >
              10년 이상
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setCareer(e.target.innerHTML);
              }}
            >
              8년 ~ 10년
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setCareer(e.target.innerHTML);
              }}
            >
              5년 ~ 7년
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setCareer(e.target.innerHTML);
              }}
            >
              3년 ~ 5년
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setCareer(e.target.innerHTML);
              }}
            >
              3년 미만
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setCareer(e.target.innerHTML);
              }}
            >
              2년 미만
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setCareer(e.target.innerHTML);
              }}
            >
              1년 미만
            </button>
            <button
              onClick={e => {
                e.preventDefault();
                setCareer(e.target.innerHTML);
              }}
            >
              6개월 미만
            </button>
          </div>
        </div>
      </DropCareer>
      <DropPoint>
        <div className="container5">
          <div>포인트</div>
          <input id="dropdown5" type="checkbox" />
          <label className="dropdownLabel5" htmlFor="dropdown5">
            <div>{point}</div>
            <FontAwesomeIcon icon={faCaretDown} className="caretIcon5" />
          </label>
          <div className="content5">
            <ul>
              <button
                onClick={e => {
                  e.preventDefault();
                  setPoint(e.target.innerHTML);
                }}
              >
                10000p 이상
              </button>
              <button
                onClick={e => {
                  e.preventDefault();
                  setPoint(e.target.innerHTML);
                }}
              >
                8000p ~ 9999p
              </button>
              <button
                onClick={e => {
                  e.preventDefault();
                  setPoint(e.target.innerHTML);
                }}
              >
                6000p ~ 7999p
              </button>
              <button
                onClick={e => {
                  e.preventDefault();
                  setPoint(e.target.innerHTML);
                }}
              >
                4000p ~ 5999p
              </button>
              <button
                onClick={e => {
                  e.preventDefault();
                  setPoint(e.target.innerHTML);
                }}
              >
                3000p ~ 3999p
              </button>
              <button
                onClick={e => {
                  e.preventDefault();
                  setPoint(e.target.innerHTML);
                }}
              >
                2000p ~ 2999p
              </button>
              <button
                onClick={e => {
                  e.preventDefault();
                  setPoint(e.target.innerHTML);
                }}
              >
                2000p 미만
              </button>
            </ul>
          </div>
        </div>
      </DropPoint>
    </Dropdown>
  );
}

export default LankDropdown;

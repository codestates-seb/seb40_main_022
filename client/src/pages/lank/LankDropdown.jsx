import React, { useEffect, useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCaretDown } from '@fortawesome/free-solid-svg-icons';
import { Dropdown, Dropdivi } from './LankDropdownStyle';

function LankDropdown() {
  const list = [
    {
      DIVISION: ['2분할', '3분할', '4분할', '5분할'],
    },
    { HEIGHT: ['181cm이상', '175cm ~ 179cm', '171cm ~ 174cm', '170cm 이하'] },
    {
      WEIGHT: [
        '120kg이상',
        '101kg ~ 119kg',
        '81kg ~ 100kg',
        '60kg ~ 80kg',
        '40kg ~ 59kg',
        '39kg이하',
      ],
    },
    {
      CAREER: [
        '20년 이상',
        '15년 ~ 19년',
        '10년 ~ 14년',
        '5년 ~ 9년',
        '4년 이하',
      ],
    },
    {
      POINT: ['10000p 이상', '8000p ~ 9999p', '6000p ~ 7999p', '4000p ~ 5999p'],
    },
  ];
  const name = [];
  const [names, setNames] = useState([
    'DIVISION',
    'HEIGHT',
    'WEIGHT',
    'CAREER',
    'POINT',
  ]);
  useEffect(() => {}, [names]);
  return (
    <Dropdown>
      {list &&
        list.map((data, idx) => {
          name.push(Object.keys(data)[0]);
          return (
            <Dropdivi>
              <div className="container">
                <input
                  id={`dropdown${idx}`}
                  type="checkbox"
                  className="dropdown"
                />
                <label className="dropdownLabel" htmlFor={`dropdown${idx}`}>
                  <div>{names[idx]}</div>
                  <FontAwesomeIcon icon={faCaretDown} className="caretIcon" />
                </label>
                <div className="content">
                  {data[name[idx]] &&
                    data[name[idx]].map(listdata => {
                      return (
                        <button
                          onClick={e => {
                            names[idx] = e.target.innerHTML;
                            setNames(names);
                          }}
                        >
                          {listdata}
                        </button>
                      );
                    })}
                </div>
              </div>
            </Dropdivi>
          );
        })}
      {/* <Dropdivi>
        <div className="container">
          <input id="dropdown" type="checkbox" />
          <label className="dropdownLabel" htmlFor="dropdown">
            <div>DIVISION</div>
            <FontAwesomeIcon icon={faCaretDown} className="caretIcon" />
          </label>
          <div className="content">
            <ul>
              <li>2분할</li>
              <li>3분할</li>
              <li>4분할</li>
              <li>5분할이상</li>
            </ul>
          </div>
        </div>
      </Dropdivi>
      <DropHeight>
        <div className="container2">
          <input id="dropdown2" type="checkbox" />
          <label className="dropdownLabel2" htmlFor="dropdown2">
            <div>HEIGHT</div>
            <FontAwesomeIcon icon={faCaretDown} className="caretIcon2" />
          </label>
          <div className="content2">
            <ul>
              <li>181cm이상</li>
              <li>175cm ~ 179cm</li>
              <li>171cm ~ 174cm</li>
              <li>170cm 이하</li>
            </ul>
          </div>
        </div>
      </DropHeight>
      <DropWeight>
        <div className="container3">
          <input id="dropdown3" type="checkbox" />
          <label className="dropdownLabel3" htmlFor="dropdown3">
            <div>WEIGHT</div>
            <FontAwesomeIcon icon={faCaretDown} className="caretIcon3" />
          </label>
          <div className="content3">
            <ul>
              <li>120kg이상</li>
              <li>101kg ~ 119kg</li>
              <li>81kg ~ 100kg</li>
              <li>60kg ~ 80kg</li>
              <li>40kg ~ 59kg</li>
              <li>39kg이하</li>
            </ul>
          </div>
        </div>
      </DropWeight>
      <DropCareer>
        <div className="container4">
          <input id="dropdown4" type="checkbox" />
          <label className="dropdownLabel4" htmlFor="dropdown4">
            <div>CAREER</div>
            <FontAwesomeIcon icon={faCaretDown} className="caretIcon4" />
          </label>
          <div className="content4">
            <ul>
              <li>20년 이상</li>
              <li>15년 ~ 19년</li>
              <li>10년 ~ 14년</li>
              <li>5년 ~ 9년</li>
              <li>4년 이하</li>
            </ul>
          </div>
        </div>
      </DropCareer>
      <DropPoint>
        <div className="container5">
          <input id="dropdown5" type="checkbox" />
          <label className="dropdownLabel5" htmlFor="dropdown5">
            <div>POINT</div>
            <FontAwesomeIcon icon={faCaretDown} className="caretIcon5" />
          </label>
          <div className="content5">
            <ul>
              <li>10000p 이상</li>
              <li>8000p ~ 9999p</li>
              <li>6000p ~ 7999p</li>
              <li>4000p ~ 5999p</li>
            </ul>
          </div>
        </div>
      </DropPoint> */}
    </Dropdown>
  );
}

export default LankDropdown;

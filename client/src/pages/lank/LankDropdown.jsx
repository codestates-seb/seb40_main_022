import React, { useState } from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCaretDown } from '@fortawesome/free-solid-svg-icons';
import { useDispatch } from 'react-redux';
import searchIcon from '../../images/searchIcon.png';
import resetIcon from '../../images/Vector.png';
import {
  Dropdown,
  Dropdivi,
  DropHeight,
  DropCareer,
  DropWeight,
} from './LankDropdownStyle';
import { url } from '../../redux/reducer/LankSlice';

function LankDropdown() {
  const dispatch = useDispatch();
  const [divi, setDivi] = useState('');
  const [diviurl, setDiviurl] = useState('');
  const [height, setHeight] = useState('');
  const [heighturl, setHeighturl] = useState('');
  const [weight, setWeight] = useState('');
  const [weighturl, setWeighturl] = useState('');
  const [career, setCareer] = useState('');
  const [careerurl, setCareerurl] = useState('');
  const handledivi = e => {
    setDivi(e.target.innerHTML);
    if (e.target.innerHTML !== '') {
      setDiviurl(`splitEq=${e.target.innerHTML.slice(0, 1)}`);
    }
  };
  const handleheight = e => {
    setHeight(e.target.innerHTML);
    if (e.target.innerHTML !== '') {
      if (e.target.innerHTML === '190cm 이상') {
        setHeighturl(`heightGoe=190`);
      } else if (e.target.innerHTML === '150cm 미만') {
        setHeighturl(`heightLt=150`);
      } else {
        setHeighturl(
          `heightGoe=${e.target.innerHTML.slice(
            0,
            3,
          )}&heightLt=${e.target.innerHTML.slice(8, 11)}`,
        );
      }
    }
  };
  const handleweight = e => {
    setWeighturl('');
    setWeight(e.target.innerHTML);
    if (e.target.innerHTML !== '') {
      if (e.target.innerHTML === '100kg이상') {
        setWeighturl(`weightGoe=100`);
      } else if (e.target.innerHTML === '40kg 미만') {
        setWeighturl(`weightLt=40`);
      } else if (e.target.innerHTML.length === 12) {
        setWeighturl(
          `weightGoe=${e.target.innerHTML.slice(
            0,
            2,
          )}&weightLt=${e.target.innerHTML.slice(7, 10)}`,
        );
      } else {
        setWeighturl(
          `weightGoe=${weight.slice(0, 2)}&weightLt=${weight.slice(7, 9)}`,
        );
      }
    }
  };
  const handlecareer = e => {
    setCareerurl('');
    setCareer(e.target.innerHTML);
    if (e.target.innerHTML !== '') {
      if (e.target.innerHTML === '10년 이상') {
        setCareerurl(`careerGoe=10`);
      } else if (e.target.innerHTML === '1년 미만') {
        setCareerurl(`careerLt=1`);
      } else if (e.target.innerHTML.length === 8) {
        setCareerurl(
          `careerGoe=${e.target.innerHTML.slice(
            0,
            1,
          )}&careerLt=${e.target.innerHTML.slice(5, 7)}`,
        );
      } else if (e.target.innerHTML.length === 7) {
        setCareerurl(
          `careerGoe=${e.target.innerHTML.slice(
            0,
            1,
          )}&careerLt=${e.target.innerHTML.slice(5, 6)}`,
        );
      } else {
        setCareerurl(`careerGoe=${e.target.innerHTML.slice(0, 1)}`);
      }
    }
  };
  const handleClick = () => {
    const dataurl =
      (diviurl !== '' ? `&${diviurl}` : '') +
      (heighturl !== '' ? `&${heighturl}` : '') +
      (weighturl.length !== 0 ? `&${weighturl}` : '') +
      (careerurl.length !== 0 ? `&${careerurl}` : ``);
    const searchurl = dataurl.slice(1, dataurl.length);
    dispatch(url(searchurl));
  };
  const handleResetClick = () => {
    setCareer('');
    setCareerurl('');
    setDivi('');
    setDiviurl('');
    setHeight('');
    setHeighturl('');
    setWeight('');
    setWeighturl('');
    dispatch(url(''));
  };
  return (
    <Dropdown>
      <Dropdivi>
        <div className="container">
          <div>분할</div>
          <input id="dropdown" type="checkbox" />
          <label className="dropdownLabel" htmlFor="dropdown">
            <div>{divi}</div>
            <FontAwesomeIcon icon={faCaretDown} className="caretIcon" />
          </label>
          {divi ? null : (
            <div className="content">
              <button
                onClick={e => {
                  handledivi(e);
                }}
              >
                2분할
              </button>
              <button
                onClick={e => {
                  handledivi(e);
                }}
              >
                3분할
              </button>
              <button
                onClick={e => {
                  handledivi(e);
                }}
              >
                4분할
              </button>
              <button
                onClick={e => {
                  handledivi(e);
                }}
              >
                5분할
              </button>
            </div>
          )}
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
          {height ? null : (
            <div className="content2">
              <button
                onClick={e => {
                  handleheight(e);
                }}
              >
                190cm 이상
              </button>
              <button
                onClick={e => {
                  handleheight(e);
                }}
              >
                185cm ~ 190cm
              </button>
              <button
                onClick={e => {
                  handleheight(e);
                }}
              >
                180cm ~ 185cm
              </button>
              <button
                onClick={e => {
                  handleheight(e);
                }}
              >
                175cm ~ 180cm
              </button>
              <button
                onClick={e => {
                  handleheight(e);
                }}
              >
                170cm ~ 175cm
              </button>
              <button
                onClick={e => {
                  handleheight(e);
                }}
              >
                165cm ~ 170cm
              </button>
              <button
                onClick={e => {
                  handleheight(e);
                }}
              >
                160cm ~ 165cm
              </button>
              <button
                onClick={e => {
                  handleheight(e);
                }}
              >
                155cm ~ 160cm
              </button>
              <button
                onClick={e => {
                  handleheight(e);
                }}
              >
                150cm ~ 155cm
              </button>
              <button
                onClick={e => {
                  handleheight(e);
                }}
              >
                150cm 미만
              </button>
            </div>
          )}
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
          {weight ? null : (
            <div className="content3">
              <button
                onClick={e => {
                  handleweight(e);
                }}
              >
                100kg이상
              </button>
              <button
                onClick={e => {
                  handleweight(e);
                }}
              >
                90kg ~ 100kg
              </button>
              <button
                onClick={e => {
                  handleweight(e);
                }}
              >
                85kg ~ 90kg
              </button>
              <button
                onClick={e => {
                  handleweight(e);
                }}
              >
                80kg ~ 85kg
              </button>
              <button
                onClick={e => {
                  handleweight(e);
                }}
              >
                75kg ~ 80kg
              </button>
              <button
                onClick={e => {
                  handleweight(e);
                }}
              >
                70kg ~ 75kg
              </button>
              <button
                onClick={e => {
                  handleweight(e);
                }}
              >
                65kg ~ 70kg
              </button>
              <button
                onClick={e => {
                  handleweight(e);
                }}
              >
                60kg ~ 65kg
              </button>
              <button
                onClick={e => {
                  handleweight(e);
                }}
              >
                55kg ~ 60kg
              </button>
              <button
                onClick={e => {
                  handleweight(e);
                }}
              >
                50kg ~ 55kg
              </button>
              <button
                onClick={e => {
                  handleweight(e);
                }}
              >
                45kg ~ 50kg
              </button>
              <button
                onClick={e => {
                  handleweight(e);
                }}
              >
                40kg ~ 45kg
              </button>
              <button
                onClick={e => {
                  handleweight(e);
                }}
              >
                40kg 미만
              </button>
            </div>
          )}
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
          {career ? null : (
            <div className="content4">
              <button
                onClick={e => {
                  handlecareer(e);
                }}
              >
                10년 이상
              </button>
              <button
                onClick={e => {
                  handlecareer(e);
                }}
              >
                8년 ~ 10년
              </button>
              <button
                onClick={e => {
                  handlecareer(e);
                }}
              >
                6년 ~ 8년
              </button>
              <button
                onClick={e => {
                  handlecareer(e);
                }}
              >
                4년 ~ 6년
              </button>
              <button
                onClick={e => {
                  handlecareer(e);
                }}
              >
                3년 이상
              </button>
              <button
                onClick={e => {
                  handlecareer(e);
                }}
              >
                2년 이상
              </button>
              <button
                onClick={e => {
                  handlecareer(e);
                }}
              >
                1년 이상
              </button>
              <button
                onClick={e => {
                  handlecareer(e);
                }}
              >
                1년 미만
              </button>
            </div>
          )}
        </div>
      </DropCareer>
      <button onClick={() => handleClick()} className="searchbutton">
        <img src={searchIcon} alt="검색아이콘" className="search" />
      </button>
      <button onClick={() => handleResetClick()} className="reset">
        <img src={resetIcon} alt="리셋아이콘" className="resetIcon" />
      </button>
    </Dropdown>
  );
}

export default LankDropdown;

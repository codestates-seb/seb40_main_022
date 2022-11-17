import React from 'react';
import styled from 'styled-components';

const DropDivi = styled.div`
  .container {
    min-width: 100px;
    box-shadow: 0 4px 5px 0 #00000026;
    position: relative;
    margin-right: 10px;
    #dropdown {
      left: 0;
      visibility: hidden;
      position: absolute;
    }
    .dropdownLabel {
      display: flex;
      padding: 15px;
      border: 1px solid red;
      cursor: pointer;
      > h3 {
        font-weight: 500;
        font-size: var(--font-12);
      }
    }
    .content {
      display: none;
      position: absolute;
      width: 100%;
      left: 0;
      background: white;
      box-shadow: 0 4px 5px 0 #00000026;
    }
    #dropdown:checked + label + div {
      display: block;
      border-top: 1px solid #00000026;
    }
    .caretIcon {
      transition: transform 250ms ease-out;
    }
    #dropdown:checked + label > .caretIcon {
      transform: rotate(-180deg);
    }
    .content ul {
      list-style-type: none;
      padding: 12px;
      margin: 0;
    }
    .content ul li {
      margin: 0.8rem 0;
    }
  }
`;
const DropHeight = styled.div`
  .container2 {
    min-width: 100px;
    box-shadow: 0 4px 5px 0 #00000026;
    position: relative;
    margin-right: 10px;
    #dropdown2 {
      left: 210px;
      visibility: hidden;
      position: absolute;
    }
    .dropdownLabel2 {
      display: flex;
      padding: 15px;
      border: 1px solid red;
      cursor: pointer;
      > h3 {
        font-weight: 500;
        font-size: var(--font-12);
      }
    }
    .content2 {
      position: absolute;
      width: 100%;
      left: 0;
      background: white;
      box-shadow: 0 4px 5px 0 #00000026;
    }
    #dropdown2:checked + label + div {
      display: block;
      border-top: 1px solid #00000026;
    }
    .caretIcon2 {
      transition: transform 250ms ease-out;
    }
    #dropdown2:checked + label > .caretIcon {
      transform: rotate(-180deg);
    }
    .content2 ul {
      list-style-type: none;
      padding: 12px;
      margin: 0;
    }
    .content2 ul li {
      margin: 0.8rem 0;
    }
  }
`;
const DropWeight = styled.div`
  .container3 {
    min-width: 100px;
    box-shadow: 0 4px 5px 0 #00000026;
    position: relative;
    margin-right: 10px;
    #dropdown3 {
      left: 0;
      visibility: hidden;
      position: absolute;
    }
    .dropdownLabel3 {
      display: flex;
      padding: 15px;
      border: 1px solid red;
      cursor: pointer;
      > h3 {
        font-weight: 500;
        font-size: var(--font-12);
      }
    }
    .content3 {
      position: absolute;
      width: 100%;
      left: 0;
      background: white;
      box-shadow: 0 4px 5px 0 #00000026;
    }
    #dropdown3:checked + label + div {
      display: block;
      border-top: 1px solid #00000026;
    }
    .caretIcon3 {
      transition: transform 250ms ease-out;
    }
    #dropdown3:checked + label > .caretIcon {
      transform: rotate(-180deg);
    }
    .content3 ul {
      list-style-type: none;
      padding: 12px;
      margin: 0;
    }
    .content3 ul li {
      margin: 0.8rem 0;
    }
  }
`;
const DropCareer = styled.div`
  .container4 {
    min-width: 100px;
    box-shadow: 0 4px 5px 0 #00000026;
    position: relative;
    margin-right: 10px;
    #dropdown4 {
      left: 0;
      visibility: hidden;
      position: absolute;
    }
    .dropdownLabel4 {
      display: flex;
      padding: 15px;
      border: 1px solid red;
      cursor: pointer;
      > h3 {
        font-weight: 500;
        font-size: var(--font-12);
      }
    }
    .content4 {
      position: absolute;
      width: 100%;
      left: 0;
      background: white;
      box-shadow: 0 4px 5px 0 #00000026;
    }
    #dropdown4:checked + label + div {
      display: block;
      border-top: 1px solid #00000026;
    }
    .caretIcon4 {
      transition: transform 250ms ease-out;
    }
    #dropdown4:checked + label > .caretIcon {
      transform: rotate(-180deg);
    }
    .content4 ul {
      list-style-type: none;
      padding: 12px;
      margin: 0;
    }
    .content4 ul li {
      margin: 0.8rem 0;
    }
  }
`;
const DropPoint = styled.div`
  .container5 {
    min-width: 100px;
    box-shadow: 0 4px 5px 0 #00000026;
    position: relative;
    margin-right: 10px;
    #dropdown5 {
      left: 0;
      visibility: hidden;
      position: absolute;
    }
    .dropdownLabel5 {
      display: flex;
      padding: 15px;
      border: 1px solid red;
      cursor: pointer;
      > h3 {
        font-weight: 500;
        font-size: var(--font-12);
      }
    }
    .content5 {
      position: absolute;
      width: 100%;
      left: 0;
      background: white;
      box-shadow: 0 4px 5px 0 #00000026;
    }
    #dropdown5:checked + label + div {
      display: block;
      border-top: 1px solid #00000026;
    }
    .caretIcon5 {
      transition: transform 250ms ease-out;
    }
    #dropdown5:checked + label > .caretIcon {
      transform: rotate(-180deg);
    }
    .content5 ul {
      list-style-type: none;
      padding: 12px;
      margin: 0;
    }
    .content5 ul li {
      margin: 0.8rem 0;
    }
  }
`;
const Dropdown = styled.main`
  display: flex;
`;
function LankDropdown() {
  return (
    <Dropdown>
      <DropDivi>
        <div className="container">
          <input id="dropdown" type="checkbox" />
          <label className="dropdownLabel" htmlFor="dropdown">
            <div>DIVISION</div>
            <div className="caretIcon" />
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
      </DropDivi>
      <DropHeight>
        <div className="container2">
          <input id="dropdown2" type="checkbox" />
          <label className="dropdownLabel2" htmlFor="dropdown">
            <div>HEIGHT</div>
            <div className="caretIcon2" />
          </label>
          <div className="content2">
            <ul>
              <li>181cm이상</li>
              <li>175cm ~ 179cm이상</li>
              <li>171cm ~ 174cm이상</li>
              <li>170cm 이하</li>
            </ul>
          </div>
        </div>
      </DropHeight>
      <DropWeight>
        <div className="container3">
          <input id="dropdown3" type="checkbox" />
          <label className="dropdownLabel3" htmlFor="dropdown">
            <div>WEIGHT</div>
            <div className="caretIcon3" />
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
          <label className="dropdownLabel4" htmlFor="dropdown">
            <div>CAREER</div>
            <div className="caretIcon4" />
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
          <label className="dropdownLabel5" htmlFor="dropdown">
            <div>POINT</div>
            <div className="caretIcon5" />
          </label>
          <div className="content5">
            <ul>
              <li>10000p 이상</li>
              <li>8000p</li>
              <li>Option 3</li>
              <li>Option 4</li>
            </ul>
          </div>
        </div>
      </DropPoint>
    </Dropdown>
  );
}

export default LankDropdown;

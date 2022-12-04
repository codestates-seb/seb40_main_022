import styled from 'styled-components';

export const Dropdown = styled.main`
  display: flex;
  position: absolute;
  top: 120px;
  right: 0;
  .searchbutton {
    border: none;
    background-color: white;
    > img {
      width: 30px;
      height: 30px;
      cursor: pointer;
      margin-left: 30px;
    }
  }
  .reset {
    border: none;
    background-color: white;
    > img {
      width: 20px;
      height: 20px;
      margin-left: 30px;
      cursor: pointer;
    }
  }
`;
export const Dropdivi = styled.div`
  .container {
    box-shadow: 0 4px 5px 0 #00000026;
    position: relative;
    margin-right: 10px;
    :nth-child(1) {
      text-align: center;
      font-weight: bold;
    }
    #dropdown {
      left: 0;
      visibility: hidden;
      position: absolute;
    }
    .dropdownLabel {
      display: flex;
      align-items: center;
      justify-content: center;
      cursor: pointer;
      > div {
        text-align: center;
        width: 120px;
        font-weight: 500;
        padding: 15px;
        font-size: var(--font-15);
        margin: 0 auto;
        position: relative;
        padding-right: 13px;
      }
      .caretIcon {
        position: absolute;
        top: 0;
        right: 10px;
      }
    }
    .content {
      display: none;
      position: absolute;
      width: 100%;
      left: 0;
      background: white;
      box-shadow: 0 4px 5px 0 #00000026;
      .btns {
        width: 100%;
        padding: 10px 0px;
        border: none;
        background-color: #fff;
        cursor: pointer;
        :hover {
          background-color: black;
          color: #fff;
        }
      }
      .backblack {
        background-color: black;
        color: #fff;
      }
    }
    #dropdown:checked + label + div {
      display: flex;
      flex-direction: column;
      border-top: 1px solid #00000026;
    }
    .caretIcon {
      transition: transform 250ms ease-out;
    }
    #dropdown:checked + label > .caretIcon {
      transform: rotate(-180deg);
    }
    .content button {
      list-style-type: none;
      padding: 12px;
      margin: 0;
      border: none;
      background-color: white;
      width: 100%;
      height: 30px;
      margin: 0.2rem 0;
    }
    .content button {
      cursor: pointer;
      border-bottom: 1px solid #b2b2b2;
      padding-bottom: 25px;
    }
  }
`;
export const DropHeight = styled.div`
  .container2 {
    min-width: 139px;
    box-shadow: 0 4px 5px 0 #00000026;
    position: relative;
    margin-right: 10px;
    :nth-child(1) {
      text-align: center;
      font-weight: bold;
    }
    #dropdown2 {
      left: 0;
      visibility: hidden;
      position: absolute;
    }
    .dropdownLabel2 {
      display: flex;
      padding: 15px;
      cursor: pointer;
      text-align: center;
      > div {
        font-weight: 500;
        font-size: var(--font-15);
        text-align: center;
        padding-left: 15px;
      }
      .caretIcon2 {
        position: absolute;
        top: 0;
        right: 10px;
      }
    }
    .content2 {
      display: none;
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
    #dropdown2:checked + label > .caretIcon2 {
      transform: rotate(-180deg);
    }
    .content2 button {
      list-style-type: none;
      padding: 12px;
      margin: 0;
      border: none;
      background-color: white;
      width: 100%;
      height: 30px;
      margin: 0.2rem 0;
    }
    .content2 button {
      cursor: pointer;
      border-bottom: 1px solid #b2b2b2;
      padding-bottom: 25px;
    }
  }
`;
export const DropWeight = styled.div`
  .container3 {
    min-width: 126px;
    box-shadow: 0 4px 5px 0 #00000026;
    position: relative;
    margin-right: 10px;
    :nth-child(1) {
      text-align: center;
      font-weight: bold;
    }
    #dropdown3 {
      left: 0;
      visibility: hidden;
      position: absolute;
    }
    .dropdownLabel3 {
      display: flex;
      padding: 15px;
      cursor: pointer;
      > div {
        font-weight: 500;
        font-size: var(--font-15);
        padding-left: 13px;
        padding-right: 5px;
      }
      .caretIcon3 {
        position: absolute;
        top: 0;
        right: 10px;
      }
    }
    .content3 {
      display: none;
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
    #dropdown3:checked + label > .caretIcon3 {
      transform: rotate(-180deg);
    }
    .content3 button {
      list-style-type: none;
      padding: 12px;
      margin: 0;
      border: none;
      background-color: white;
      width: 100%;
      height: 30px;
      margin: 0.2rem 0;
    }
    .content3 button {
      cursor: pointer;
      border-bottom: 1px solid #b2b2b2;
      padding-bottom: 25px;
    }
  }
`;
export const DropCareer = styled.div`
  .container4 {
    min-width: 120px;
    box-shadow: 0 4px 5px 0 #00000026;
    position: relative;
    margin-right: 10px;
    :nth-child(1) {
      text-align: center;
      font-weight: bold;
    }
    #dropdown4 {
      left: 0;
      visibility: hidden;
      position: absolute;
    }
    .dropdownLabel4 {
      display: flex;
      padding: 15px;
      cursor: pointer;
      > div {
        font-weight: 500;
        font-size: var(--font-15);
        padding-left: 20px;
        padding-right: 15px;
      }
      .caretIcon4 {
        position: absolute;
        top: 0;
        right: 10px;
      }
    }
    .content4 {
      display: none;
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
    #dropdown4:checked + label > .caretIcon4 {
      transform: rotate(-180deg);
    }
    .content4 button {
      list-style-type: none;
      padding: 12px;
      margin: 0;
      border: none;
      background-color: white;
      width: 100%;
      height: 30px;
      margin: 0.2rem 0;
    }
    .content4 button {
      cursor: pointer;
      border-bottom: 1px solid #b2b2b2;
      padding-bottom: 25px;
    }
  }
`;
export const DropPoint = styled.div`
  .container5 {
    min-width: 130px;
    box-shadow: 0 4px 5px 0 #00000026;
    position: relative;
    margin-right: 10px;
    :nth-child(1) {
      text-align: center;
      font-weight: bold;
    }
    #dropdown5 {
      left: 0;
      visibility: hidden;
      position: absolute;
    }
    .dropdownLabel5 {
      display: flex;
      padding: 15px;
      cursor: pointer;
      > div {
        font-weight: 500;
        font-size: var(--font-15);
        padding-left: 13px;
        padding-right: 5px;
      }
      .caretIcon5 {
        position: absolute;
        top: 0;
        right: 10px;
      }
    }
    .content5 {
      display: none;
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
    #dropdown5:checked + label > .caretIcon5 {
      transform: rotate(-180deg);
    }
    .content5 button {
      list-style-type: none;
      padding: 12px;
      margin: 0;
      border: none;
      background-color: white;
      width: 100%;
      height: 30px;
      margin: 0.2rem 0;
    }
    .content5 button {
      cursor: pointer;
      border-bottom: 1px solid #b2b2b2;
      padding-bottom: 25px;
    }
  }
`;
